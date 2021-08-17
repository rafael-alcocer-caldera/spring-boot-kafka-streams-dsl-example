/**
 * Copyright [2021] [RAFAEL ALCOCER CALDERA]
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package rafael.alcocer.caldera.starter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.stereotype.Component;

import net.sf.json.JSONObject;
import rafael.alcocer.caldera.configuration.DSLTopologyConfiguration;

@Component
public class StreamsStarter {

    private final DSLTopologyConfiguration config;
    private KafkaStreams kafkaStreams;

    public StreamsStarter(DSLTopologyConfiguration config) {
        this.config = config;
    }

    @PostConstruct
    public void start() {
        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, JSONObject> inputStream = builder.stream(config.getSourceTopicName());

        inputStream.peek((k, v) -> {
            System.out.println("##### valueClass=" + v.getClass());
            System.out.println("##### key=" + k + ", ##### value=" + v);
        }).to(config.getSinkTopicName());

        Topology topology = builder.build();

        kafkaStreams = new KafkaStreams(topology, config.geProperties());
        Runtime.getRuntime().addShutdownHook(new Thread(kafkaStreams::close));
        kafkaStreams.start();
    }

    @PreDestroy
    public void close() {
        kafkaStreams.close();
    }
}
