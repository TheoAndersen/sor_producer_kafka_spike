package com.show_me_kafka.sor.sor_producer;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@ConfigurationProperties(prefix = "sorproducer")
@Validated()
public class ApplicationConfiguration {

    public String getBrokers() { return brokers; }
    public void setBrokers(String brokers) {
        this.brokers = brokers;
    }

    /**
     * De kafka brokere som applikationen skal benytte
     */
    @NotBlank(message = "En liste af endpoints til Kafka brokere er påkrævet")
    private String brokers;

    public String getTopic() {
        return topic;
    }
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /*
     * Det kafka Topic der skrives til
     */
    @NotBlank(message = "Navnet af det Kafka-topic, som der skal skrives til er påkrævet (fx 'sor'?)")
    private String topic;

    public String getSorxmlurl() { return sorxmlurl; }
    public void setSorxmlurl(String sorxmlurl) { this.sorxmlurl = sorxmlurl; }

    /*
     * Url på hvor Sor.xml'en skal hentes fra
     */
    @NotBlank(message = "stien/url til den sor xml-fil som skal læses ind i Kafka er påkrævet")
    private String sorxmlurl;

    @NotBlank(message = "url til schemaregistry er påkrævet")
    private String schemaregistryurl;

    public String getSchemaregistryurl() {
        return schemaregistryurl;
    }

    public void setSchemaregistryurl(String schemaregistryurl) {
        this.schemaregistryurl = schemaregistryurl;
    }
}


