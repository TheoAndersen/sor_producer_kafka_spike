package com.show_me_kafka.sor.sor_producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.StreamMessageConverter;
import org.springframework.cloud.stream.schema.avro.AvroSchemaMessageConverter;
import org.springframework.cloud.stream.schema.client.EnableSchemaRegistryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.util.MimeType;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfiguration.class)
@EnableSchemaRegistryClient
public class SorProducerApplication {

	public static void main(String[] args) {
		new SpringApplication(SorProducerApplication.class).run(args);
	}

	@Bean
	@StreamMessageConverter
	public MessageConverter thisMessageConverter() {
		return new AvroSchemaMessageConverter(MimeType.valueOf("avro/bytes"));
	}
}
