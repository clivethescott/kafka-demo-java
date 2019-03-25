package com.research.kafkademos;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.time.Duration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * From https://dzone.com/articles/kafka-producer-and-consumer-example
 */
public class Main {

    public static void main(String[] args) {

        runProducer();

        runConsumer();
    }

    private static void runConsumer() {

        try (Consumer<Long, String> consumer = ConsumerFactory.newConsumer()) {

            ConsumerRecords<Long, String> consumerRecords = consumer.poll(Duration.ofMinutes(1));

            if (consumerRecords.count() == 0) {
                System.out.println("No message found after waiting period");
                System.exit(0);
            } else {

                consumerRecords.forEach(record -> {
                    System.out.println("--------------------------------------");
                    System.out.println("Record Key " + record.key());
                    System.out.println("Record value " + record.value());
                    System.out.println("Record partition " + record.partition());
                    System.out.println("Record offset " + record.offset());
                });

                consumer.commitAsync();

            }
        }

    }

    private static void runProducer() {

        final Producer<Long, String> producer = ProducerFactory.newProducer();

        for (int index = 0; index < ConsumerConstants.MESSAGE_COUNT; index++) {

            ProducerRecord<Long, String> record = new ProducerRecord<>(ProducerConstants.TOPIC_NAME, (long)index, "This is record " + index);

            final Future<RecordMetadata> recordMetadataFuture = producer.send(record);

            try {

                RecordMetadata metadata = recordMetadataFuture.get();
                System.out.println("Record sent with key " + index + " to partition " + metadata.partition()
                        + " with offset " + metadata.offset());

            } catch (InterruptedException e) {
                System.err.println("Error in sending record");
                System.err.println(e);
            } catch (ExecutionException e) {
                System.err.println("Error in sending record");
                System.err.println(e);
            }

        }


    }

}
