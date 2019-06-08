package com.show_me_kafka.sor.sor_producer;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.common.errors.UnknownTopicOrPartitionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class TestContext implements AutoCloseable {

    public final String TOPIC = "sor_test";
    private final String schemaregistry;
    private String brokers;

    private static final Logger LOG = LoggerFactory.getLogger(TestContext.class);

    public TestContext() throws TimeoutException {
        this(null);
    }

    public TestContext(String brokers) throws TimeoutException {
        if (brokers != null) {
            this.brokers = brokers;
        }
        else {
            this.brokers = System.getenv().getOrDefault("SORPRODUCER_BROKERS", "localhost:9092");
        }
        this.schemaregistry = System.getenv().getOrDefault("SORPRODUCER_SCHEMAREGISTRYURL", "http://localhost:8990");

        // Set the topic to be sent to .
        System.setProperty("sorproducer.topic", this.TOPIC);
        System.setProperty("sorproducer.run_automatically_on_startup", "true");
        this.deleteTopic(this.TOPIC);
        System.out.println("TEST >> TEST INITIALIZED");
    }

    public String[] getProperties() {
        return new String[]
                {"--SORPRODUCER_BROKERS=" + this.brokers,
                "--SORPRODUCER_SCHEMAREGISTRYURL=" + this.schemaregistry};
    }

    private void deleteTopic(String topicName) throws TimeoutException {
        Properties kafkaAdminProperties = new Properties();
        //TODO get config from configuration
        kafkaAdminProperties.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, brokers);
        kafkaAdminProperties.setProperty(AdminClientConfig.RETRIES_CONFIG, "1");
        kafkaAdminProperties.setProperty(AdminClientConfig.RETRY_BACKOFF_MS_CONFIG, "1");
        kafkaAdminProperties.setProperty(AdminClientConfig.RECONNECT_BACKOFF_MAX_MS_CONFIG, "10000");
        kafkaAdminProperties.setProperty(AdminClientConfig.RECONNECT_BACKOFF_MS_CONFIG, "1000");


        AdminClient adminClient = AdminClient.create(kafkaAdminProperties);
        try  {
            adminClient.deleteTopics(Arrays.asList(topicName)).all().get(1000, TimeUnit.MILLISECONDS);
        }
        catch(TimeoutException tex)
        {
            LOG.error("Timeout or could not connect to: " + brokers,tex);
            throw tex;
        }
        catch(UnknownTopicOrPartitionException | InterruptedException | ExecutionException ex)  {
            return;
        }
        finally {
            adminClient.close(10, TimeUnit.MILLISECONDS);
        }
    }


    @Override
    public void close() {
    }
}

