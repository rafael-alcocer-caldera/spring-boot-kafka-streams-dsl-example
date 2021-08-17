# SPRING BOOT KAFKA STREAMS DSL EXAMPLE

## Synopsis

The project is a Spring Boot Application that is a Kafka Streams DSL.

## Motivation

I wanted to do a Kafka Consumer with Spring Boot Application.

## PRE REQUIREMENTS

- Kafka Server must be running
- SPRING BOOT KAFKA PRODUCER EXAMPLE running (port: 8089, topic-name: rac-topic)
- SPRING BOOT KAFKA CONSUMER EXAMPLE running (port: 8088, topic-name: rac3-topic)
- Postman to send messages

## Example to run kafka on windows

<pre><code>

Base directory:
C:\RAC\kafka_2.13-2.4.0\bin\windows

Start Zookeeper in one cmd console:
zookeeper-server-start.bat ../../config/zookeeper.properties

Start Kafka in another cmd console:
kafka-server-start.bat ../../config/server.properties

Create topic called "rac-topic" in another cmd console:
kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic rac-topic

Create topic called "rac3-topic" in another cmd console:
kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic rac3-topic

</code></pre>

## POSTMAN REQUESTS

<pre><code>

POST
http://localhost:8089/kafka/producer

BODY
{
	"id": "1",
	"topicName": "rac-topic",
	"json": {
		"nombre": "RAFAEL",
		"apellido": "ALCOCER",
		"edad": 1000,
		"fecha": "1900 - 08 - 13"
	}
}

RESPONSE

</code></pre>

## EXECUTION
1) This application consumes messages from "rac-topic" (this is the Source in the Topology) sent by the SPRING BOOT KAFKA PRODUCER EXAMPLE.
<pre><code>
POST
http://localhost:8089/kafka/producer
{
	"id": "13",
	"topicName": "rac-topic",
	"json": {
		"nombre": "RAC13",
		"apellido": "ALCOCER",
		"edad": 66,
		"fecha": "2000 - 11 - 08"
	}
}
</code></pre>

2) Prints the messages in the console (this is the stream).
<pre><code>
##### valueClass=class net.sf.json.JSONObject
##### key=null, ##### value={"nombre":"RAC13","apellido":"ALCOCER","edad":66,"fecha":"2000 - 11 - 08"}
</code></pre>

3) And sends the messages to the "rac3-topic" (this is the Sink in the Topology).

4) SPRING BOOT KAFKA CONSUMER EXAMPLE consumes the messages in "rac3-topic".
<pre><code>
##### JSONObject received: {"nombre":"RAC13","apellido":"ALCOCER","edad":66,"fecha":"2000 - 11 - 08"}
</code></pre>

## License

All work is under Apache 2.0 license