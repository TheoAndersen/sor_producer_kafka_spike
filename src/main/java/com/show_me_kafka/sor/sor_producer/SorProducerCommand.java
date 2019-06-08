package com.show_me_kafka.sor.sor_producer;

import com.show_me_kafka.sor.sor_producer.model.InstitutionOwner;
import com.show_me_kafka.sor.sor_producer.model.SorTree;
import com.show_me_kafka.sor.sor_producer.parser.SorXmlSaxParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component()
public class SorProducerCommand {
	Logger logger = LoggerFactory.getLogger(SorProducerCommand.class);
	private SorProducer sorProducer;
	private SorXmlSaxParser sorXmlParser;
	private String sorxmlurl;

	public SorProducerCommand(SorProducer sorProducer,
							  SorXmlSaxParser sorXmlParser,
							  @Value("${sorproducer.sorxmlurl}") String sorxmlurl) {
		this.sorProducer = sorProducer;
		this.sorXmlParser = sorXmlParser;
		this.sorxmlurl = sorxmlurl;
	}

	public void Execute() throws Exception {
		logger.info("Fetching and processing the file: " + sorxmlurl);
		SorTree sorTree = sorXmlParser.parse(sorxmlurl);
		for(InstitutionOwner institutionOwner : sorTree.institutionOwners) {
			sorProducer.publish(institutionOwner);
		}
	}
}
