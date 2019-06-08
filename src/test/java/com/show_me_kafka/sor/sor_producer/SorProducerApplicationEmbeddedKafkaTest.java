package com.show_me_kafka.sor.sor_producer;

import com.show_me_kafka.sor.sor_producer.model.kafka.HealthInstitution;
import com.show_me_kafka.sor.sor_producer.model.kafka.InstitutionOwner;
import com.show_me_kafka.sor.sor_producer.model.kafka.OrganizationalUnit;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.TimeoutException;

import static org.junit.Assert.assertEquals;

@ActiveProfiles("test")
public class SorProducerApplicationEmbeddedKafkaTest {

    @Test
    public void will_produce_to_kafka_and_can_be_consumed_again() throws Exception {

        String filePath = this.getClass().getClassLoader().getResource("Sor_small.xml").getFile();

        try(TestContext context = new TestContext()) {
            System.setProperty("sorproducer.sorxmlurl", filePath);

            new SpringApplication(SorProducerApplication.class).run(context.getProperties()).close();

            TestConsumer.Result results = TestConsumer.run_and_fetch_number_of_messages(context.getProperties(), 1);
            assertEquals(3, results.size());
            assertEquals(1, results.institutionOwners.size());
            assertEquals(1, results.healthInstitutions.size());
            assertEquals(1, results.organizationalUnits.size());
            InstitutionOwner institutionOwner = results.institutionOwners.get(0);
            assertEquals("element 0. institutionowner.soridentity", "698471000016005", institutionOwner.getSorIdentifier().toString());
            assertEquals("institutionOwner.entityname", "Baadsgaard Fodterapi", institutionOwner.getEntityName().toString());
            assertEquals("institutionowner.entitytypeidentifier", "550891000005100", institutionOwner.getEntityTypeIdentifier().toString());
            HealthInstitution healthInstitution = results.healthInstitutions.get(0);
            assertEquals("healthInstitutionENtity.soridentifier", "698481000016007", healthInstitution.getSorIdentifier().toString());
            assertEquals("healthInstitutionEntity.entityname", "Brønderslev fodterapi", healthInstitution.getEntityName().toString());
            assertEquals("healthInstitutioinEntity.entityTypeIdentifier", "554061000005105", healthInstitution.getEntityTypeIdentifier().toString());
            assertEquals("healthinstitutionentity.parentSorIdentifier", "698471000016005", healthInstitution.getParentSorIdentifier().toString());
            OrganizationalUnit organizationalUnit = results.organizationalUnits.get(0);
            assertEquals("organizationalUnit.SorIdentifier", "698491000016009", organizationalUnit.getSorIdentifier().toString());
            assertEquals("organizationalUnit.entityname", "Trine Baadsgaard", organizationalUnit.getEntityName().toString());
            assertEquals("organizationalUnit.entityTypeIdentifier", "255203001", organizationalUnit.getEntityTypeIdentifier().toString());
            assertEquals("organizationalUnit.parentsoridentifier", "698481000016007", organizationalUnit.getParentSorIdentifier().toString());
        }
    }

    @Test(expected = TimeoutException.class)
    public void will_timeout_and_fail_if_no_kafka_server_is_running() throws TimeoutException {
        String host_that_exists = "localhost";
        String port_where_nothing_is_running = "1234";
        try (TestContext context = new TestContext(host_that_exists + ":" + port_where_nothing_is_running)) {
            // TestContext tries to delete the topic to begin with, this should timeout
        }
    }
}
