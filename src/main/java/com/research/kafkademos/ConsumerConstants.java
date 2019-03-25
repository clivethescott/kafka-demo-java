package com.research.kafkademos;

public interface ConsumerConstants extends KafkaConstants {

    Integer MESSAGE_COUNT = 20;
    String GROUP_ID = "consumerGroup1";
    String OFFSET_RESET_LATEST = "latest";
    String OFFSET_RESET_EARLIEST = "earliest";
    Integer MAX_POLL_RECORDS = 1000;
}
