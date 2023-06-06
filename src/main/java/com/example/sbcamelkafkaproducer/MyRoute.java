package com.example.sbcamelkafkaproducer;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class MyRoute extends RouteBuilder {

    @Override
    public void configure() {

        restConfiguration()
                .enableCORS(true)
                .component("jetty")
                .host("0.0.0.0")
                .port(8988)
                .bindingMode(RestBindingMode.json);

        rest()
                .get("/hello")
                .to("direct:hello")
                .get("/hello-kafka")
                .to("direct:hello-kafka");

        from("direct:hello")
                .routeId("GreetingRoute")
                .to("direct:greetStranger");

        from("direct:greetStranger")
                .routeId("greetStranger")
                .setBody(simple("Hello Application!"));

        from("direct:hello-kafka")
                .routeId("KafkaGreetingRoute")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        exchange.getIn().setBody(exchange.getIn().getHeader("log" ,String.class));
                    }
                })
                .to("kafka:{{topic}}?brokers={{broker}}");

        // Kafka Consumer
        from("kafka:{{topic}}?brokers={{broker}}")
                .log("Message received from Kafka : ${body}")
                .log("    on the topic ${headers[kafka.TOPIC]}")
                .log("    on the partition ${headers[kafka.PARTITION]}")
                .log("    with the offset ${headers[kafka.OFFSET]}")
                .log("    with the key ${headers[kafka.KEY]}");

    }

}