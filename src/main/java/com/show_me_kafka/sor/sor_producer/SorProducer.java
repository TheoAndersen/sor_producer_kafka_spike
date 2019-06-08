package com.show_me_kafka.sor.sor_producer;

import com.show_me_kafka.sor.sor_producer.model.HealthInstitution;
import com.show_me_kafka.sor.sor_producer.model.InstitutionOwner;
import com.show_me_kafka.sor.sor_producer.model.OrganizationalUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.schema.avro.AvroSchemaMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.util.MimeType;

interface ProducingSorProcessor {
  @Output("mitsortopic")
  MessageChannel SorKafkaTopic();
}

@EnableBinding(ProducingSorProcessor.class)
public class SorProducer {



  @Autowired private ProducingSorProcessor processor;

  public void publish(InstitutionOwner institutionOwner) {

    com.show_me_kafka.sor.sor_producer.model.kafka.InstitutionOwner kafkaInstitutionOwner = com.show_me_kafka.sor.sor_producer.model.kafka.InstitutionOwner.newBuilder()
            .setSorIdentifier(institutionOwner.SorIdentifier)
            .setEntityName(institutionOwner.EntityName)
            .setEntityTypeIdentifier(institutionOwner.EntityTypeIdentifier)
            .build();

    MessageBuilder<com.show_me_kafka.sor.sor_producer.model.kafka.InstitutionOwner> messageBuilder = MessageBuilder.withPayload(kafkaInstitutionOwner);
    messageBuilder.setHeader(KafkaHeaders.MESSAGE_KEY, institutionOwner.SorIdentifier);
    messageBuilder.setHeader("type", "InstitutionOwner");
    Message<com.show_me_kafka.sor.sor_producer.model.kafka.InstitutionOwner> message = messageBuilder.build();
    processor.SorKafkaTopic().send(message);

    for(HealthInstitution healthInstitution : institutionOwner.healthInstitutionEntities) {
      this.publishHealthInstitution(healthInstitution);

      for(OrganizationalUnit organizationalUnit : healthInstitution.organizationalUnits) {
        this.publishOrganizationalUnit(organizationalUnit);
      }
    }
  }

  private void publishHealthInstitution(HealthInstitution healthInstitution) {
      com.show_me_kafka.sor.sor_producer.model.kafka.HealthInstitution kafkaHealthInstitution = com.show_me_kafka.sor.sor_producer.model.kafka.HealthInstitution.newBuilder()
              .setSorIdentifier(healthInstitution.SorIdentifier)
              .setEntityName(healthInstitution.EntityName)
              .setEntityTypeIdentifier(healthInstitution.EntityTypeIdentifier)
              .setParentSorIdentifier(healthInstitution.ParentSorIdentifier)
              .build();
    MessageBuilder<com.show_me_kafka.sor.sor_producer.model.kafka.HealthInstitution> messageBuilder = MessageBuilder.withPayload(kafkaHealthInstitution);
    messageBuilder.setHeader(KafkaHeaders.MESSAGE_KEY, healthInstitution.ParentSorIdentifier);
    messageBuilder.setHeader("type", "HealthInstitution");
    Message<com.show_me_kafka.sor.sor_producer.model.kafka.HealthInstitution> message = messageBuilder.build();
    processor.SorKafkaTopic().send(message);
  }

  private void publishOrganizationalUnit(OrganizationalUnit organizationalUnit) {
      com.show_me_kafka.sor.sor_producer.model.kafka.OrganizationalUnit kafkaOrganizationalUnit = com.show_me_kafka.sor.sor_producer.model.kafka.OrganizationalUnit.newBuilder()
              .setSorIdentifier(organizationalUnit.SorIdentifier)
              .setEntityName(organizationalUnit.EntityName)
              .setEntityTypeIdentifier(organizationalUnit.EntityTypeIdentifier)
              .setParentSorIdentifier(organizationalUnit.ParentSorIdentifier)
              .build();
    MessageBuilder<com.show_me_kafka.sor.sor_producer.model.kafka.OrganizationalUnit> messageBuilder = MessageBuilder.withPayload(kafkaOrganizationalUnit);
    messageBuilder.setHeader(KafkaHeaders.MESSAGE_KEY, organizationalUnit.ParentSorIdentifier);
    messageBuilder.setHeader("type", "OrganizationalUnit");
    Message<com.show_me_kafka.sor.sor_producer.model.kafka.OrganizationalUnit> message = messageBuilder.build();
    processor.SorKafkaTopic().send(message);
  }
}

