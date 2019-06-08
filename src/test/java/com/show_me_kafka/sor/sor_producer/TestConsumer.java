package com.show_me_kafka.sor.sor_producer;

import com.show_me_kafka.sor.sor_producer.model.kafka.HealthInstitution;
import com.show_me_kafka.sor.sor_producer.model.kafka.InstitutionOwner;
import com.show_me_kafka.sor.sor_producer.model.kafka.OrganizationalUnit;
import org.apache.avro.generic.GenericData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.annotation.StreamMessageConverter;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.schema.avro.AvroSchemaMessageConverter;
import org.springframework.cloud.stream.schema.client.EnableSchemaRegistryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.util.MimeType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeoutException;


@EnableBinding(Sink.class)
@EnableAutoConfiguration
@EnableSchemaRegistryClient
public class TestConsumer {

    private List<InstitutionOwner> institutionOwners;
    private List<HealthInstitution> healthInstitutions;
    private List<OrganizationalUnit> organizationalUnits;
    private List<Exception> errors;

    @Bean
    @StreamMessageConverter
    public MessageConverter TestConsumerAvroMessageConverter() {
        return new AvroSchemaMessageConverter(MimeType.valueOf("avro/bytes"));
    }

    public static TestConsumer.Result run_and_fetch_number_of_messages(String[] properties, int number_of_messages_to_wait_for) throws Exception {
        return run_and_fetch_number_of_messages(properties, number_of_messages_to_wait_for, 2000);
    }

    public static TestConsumer.Result run_and_fetch_number_of_messages(String[] properties, int number_of_messages_to_wait_for, int timeout_ms) throws Exception {
        List<String> consumerProperties = new ArrayList<>(Arrays.asList(properties));
        consumerProperties.add("--spring.cloud.stream.bindings.input.destination=sor_test");
        consumerProperties.add("--spring.cloud.stream.kafka.bindings.input.consumer.startOffset=earliest");
        consumerProperties.add("--spring.cloud.stream.kafka.bindings.input.consumer.resetOffsets=true");
        consumerProperties.add("--spring.cloud.stream.bindings.input.consumer.key.serializer=stringSerializer");
        consumerProperties.add("--spring.jmx.default-domain=TestConsumer");
        consumerProperties.add("--spring.cloud.stream.kafka.bindings.input.contentType=application/*+avro");

        String[] props = consumerProperties.toArray(new String[0]);
        try (ConfigurableApplicationContext context = SpringApplication.run(TestConsumer.class, props)) {
            TestConsumer consumer = context.getBean(TestConsumer.class);

            int ms_slept = 0;

            while(consumer.institutionOwners.size() == 0) {
                Thread.sleep(100);
                ms_slept += 100;

                if(consumer.errors.size() > 0) {
                    throw consumer.errors.get(0);
                }

                if(ms_slept > timeout_ms) {
                    throw new TimeoutException("Timed out waiting to consume messages from Kafka");
                }
            }
            return consumer.new Result(consumer.institutionOwners, consumer.healthInstitutions, consumer.organizationalUnits);
        }
    }


    public TestConsumer() {
        this.institutionOwners = new ArrayList<>();
        this.healthInstitutions = new ArrayList<>();
        this.organizationalUnits = new ArrayList<>();
        this.errors = new ArrayList<>();
    }

    @StreamListener(value = "input", condition = "headers['type']=='InstitutionOwner'")
    public void ReceivedInstitutionOwner(Message<InstitutionOwner> message) {

       this.institutionOwners.add(message.getPayload());
    }

    @StreamListener(value = "input", condition = "headers['type']=='HealthInstitution'")
    public void ReceivedHealthInstitution(Message<HealthInstitution> message) {

        this.healthInstitutions.add(message.getPayload());
    }

    @StreamListener(value = "input", condition = "headers['type']=='OrganizationalUnit'")
    public void ReceivedOrganizationalUnit(Message<OrganizationalUnit> message) {

        this.organizationalUnits.add(message.getPayload());
    }

    @StreamListener("errorChannel")
    public void error(Message<?> message) {
        System.out.println("Handling ERROR: " + message);
        this.errors.add(new Exception("TestConsumer failed - " + message));
    }

    class Result {
        public List<InstitutionOwner> institutionOwners;
        public List<HealthInstitution> healthInstitutions;
        public List<OrganizationalUnit> organizationalUnits;

        public Result(List<InstitutionOwner> institutionOwners, List<HealthInstitution> healthInstitutions, List<OrganizationalUnit> organizationalUnits) {
            this.institutionOwners = institutionOwners;
            this.healthInstitutions = healthInstitutions;
            this.organizationalUnits = organizationalUnits;
        }

        public int size() {
            return institutionOwners.size() + healthInstitutions.size() + organizationalUnits.size();
        }
    }
}
