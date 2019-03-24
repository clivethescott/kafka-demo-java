package com.research.kafkademos;

public interface ConsumerConstants extends KafkaConstants {

    String CLIENT_ID = "client1";
    Integer MESSAGE_COUNT = 1000;
    String TOPIC_NAME = "demo";
    String GROUP_ID = "consumerGroup1";
    Integer MAX_NO_MESSAGE_FOUND_COUNT=100;
    String OFFSET_RESET_LATEST = "latest";
    String OFFSET_RESET_EARLIER = "earliest";
    Integer MAX_POLL_RECORDS = 1;
}
