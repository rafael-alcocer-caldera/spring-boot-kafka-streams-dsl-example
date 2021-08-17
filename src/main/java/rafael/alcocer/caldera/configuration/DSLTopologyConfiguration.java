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
package rafael.alcocer.caldera.configuration;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;
import rafael.alcocer.caldera.deserializer.CustomDeserializer;
import rafael.alcocer.caldera.model.Model;
import rafael.alcocer.caldera.serde.CustomSerde;
import rafael.alcocer.caldera.serializer.CustomSerializer;

/**
 * This class reads information from application.yml.
 * 
 * @author Rafael Alcocer Caldera
 *
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties("spring.kafka.streams.dsl.topology")
public class DSLTopologyConfiguration {

    private String applicationId;
    private String bootstrapServers;
    private String sourceTopicName;
    private String sinkTopicName;
    private String autoOffsetResetConfig;
    private String keySerializer;
    private String serde;

    @Bean
    public Properties geProperties() {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, applicationId);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetResetConfig);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, CustomSerde.class);
        
        return props;
    }

    @Bean
    public Serde<Model> serde() {
        return Serdes.serdeFrom(new CustomSerializer<Model>(), new CustomDeserializer<Model>());
    }
}
