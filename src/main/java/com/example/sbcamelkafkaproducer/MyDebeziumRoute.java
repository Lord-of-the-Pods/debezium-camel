package com.example.sbcamelkafkaproducer;

import org.apache.camel.builder.RouteBuilder;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.Struct;
import org.springframework.stereotype.Component;

@Component
public class MyDebeziumRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("debezium-mysql:dbz-test?databaseHostname=localhost" +
                "&databaseServerName=sakila" +
                "&databaseServerId=1" +
                "&databaseUser=sakila" +
                "&databasePassword=p_ssW0rd" +
                "&offsetStorageFileName=offset-file-1.dat" +
                "&databaseHistoryFileFilename=history-file-1.dat")
                .process(exchange -> {
                    final Struct bodyValue = exchange.getIn().getBody(Struct.class);
                    final Schema schemaValue = bodyValue.schema();

                    log.info("Body value is :" + bodyValue);
                    log.info("With Schema : " + schemaValue);
                });


    }
}
