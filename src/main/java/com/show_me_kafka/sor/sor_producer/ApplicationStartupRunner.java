package com.show_me_kafka.sor.sor_producer;

import com.show_me_kafka.sor.sor_producer.SorProducer;
import com.show_me_kafka.sor.sor_producer.model.InstitutionOwner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ApplicationStartupRunner implements ApplicationRunner {
    private static final Logger LOG =
            LoggerFactory.getLogger(ApplicationStartupRunner.class);

    private SorProducerCommand sorProducerCommand;
    private boolean run_automatically_on_startup;

    public ApplicationStartupRunner(SorProducerCommand sorProducerCommand,
                                    @Value("${sorproducer.run_automatically_on_startup}") boolean run_automatically_on_startup) {
        this.sorProducerCommand = sorProducerCommand;
        this.run_automatically_on_startup = run_automatically_on_startup;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(!run_automatically_on_startup) {
            ApplicationStartupRunner.LOG.info("Skipped running automatically");
            return;
        }

        sorProducerCommand.Execute();
    }
}

