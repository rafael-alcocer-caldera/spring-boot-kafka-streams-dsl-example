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

## License

All work is under Apache 2.0 license