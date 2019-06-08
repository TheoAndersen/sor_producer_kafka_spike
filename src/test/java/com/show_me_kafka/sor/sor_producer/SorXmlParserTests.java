package com.show_me_kafka.sor.sor_producer;

import java.io.IOException;
import java.net.URL;
import javax.xml.parsers.ParserConfigurationException;

import com.show_me_kafka.sor.sor_producer.model.*;
import com.show_me_kafka.sor.sor_producer.parser.SorXmlSaxParser;
import org.junit.Test;
import org.xml.sax.SAXException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SorXmlParserTests {

  @Test
  public void should_parse_sor_small()
      throws IOException, SAXException, ParserConfigurationException {
    String filepath = this.getClass().getClassLoader().getResource("Sor_small.xml").getFile();
    SorTree result = new SorXmlSaxParser().parse(filepath);

    assertNotNull(result);
    assertEquals("institutionOwners", 1, result.institutionOwners.size());
    InstitutionOwner institutionOwner = result.institutionOwners.get(0);
    assertEquals("institutionOwner.Soridentifier", "698471000016005", institutionOwner.SorIdentifier);
    assertEquals("institutionOwner.entityname", "Baadsgaard Fodterapi", institutionOwner.EntityName);
    assertEquals("institutionowner.entitytypeidentifier", "550891000005100", institutionOwner.EntityTypeIdentifier);
    assertEquals("healthInstitutionEntities", 1, institutionOwner.healthInstitutionEntities.size());
    HealthInstitution healthInstitutionEntity = institutionOwner.healthInstitutionEntities.get(0);
    assertEquals("healthInstitutionENtity.soridentifier", "698481000016007", healthInstitutionEntity.SorIdentifier);
    assertEquals("healthInstitutionEntity.entityname", "Brønderslev fodterapi", healthInstitutionEntity.EntityName);
    assertEquals("healthInstitutioinEntity.entityTypeIdentifier", "554061000005105", healthInstitutionEntity.EntityTypeIdentifier);
    assertEquals("healthinstitutionentity.parentSorIdentifier", "698471000016005", healthInstitutionEntity.ParentSorIdentifier);
    assertEquals("organizationalUnits", 1, healthInstitutionEntity.organizationalUnits.size());
    OrganizationalUnit organizationalUnit = healthInstitutionEntity.organizationalUnits.get(0);
    assertEquals("organizationalUnit.SorIdentifier", "698491000016009", organizationalUnit.SorIdentifier);
    assertEquals("organizationalUnit.entityname", "Trine Baadsgaard", organizationalUnit.EntityName);
    assertEquals("organizationalUnit.entityTypeIdentifier", "255203001", organizationalUnit.EntityTypeIdentifier);
    assertEquals("organizationalUnit.parentsoridentifier", "698481000016007", organizationalUnit.ParentSorIdentifier);
    assertEquals("organizationalUnits[0].orgnaizationalUnits", 1, organizationalUnit.organizationalUnits.size());
  }

  @Test
  public void should_parse_sor_full()
      throws IOException, SAXException, ParserConfigurationException {
    String filepath = this.getClass().getClassLoader().getResource("Sor_big.xml").getFile();
    SorTree result = new SorXmlSaxParser().parse(filepath);

    assertNotNull(result);
  }
}


