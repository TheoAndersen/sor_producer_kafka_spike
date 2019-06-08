package com.show_me_kafka.sor.sor_producer.parser;

import com.show_me_kafka.sor.sor_producer.model.*;

import java.io.File;
import java.io.IOException;
import java.util.Stack;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

@Component
public class SorXmlSaxParser {
  public SorTree parse(String filename)
      throws ParserConfigurationException, SAXException, IOException {

    SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
    SAXParser saxParser = saxParserFactory.newSAXParser();
    InstitutionOwnerHandler institutionOwnerHandler = new InstitutionOwnerHandler();

    saxParser.parse(new File((String) filename), institutionOwnerHandler);

    return institutionOwnerHandler.getSorTree();
  }
}

class InstitutionOwnerHandler extends DefaultHandler {

  private SorTree sorTree;
  private InstitutionOwner currentInstitutionOwnerElement = null;
  private HealthInstitution currentHealthInstitution = null;
  private Stack<OrganizationalUnit> currentOrganizationalUnitStack;
  private StringBuilder data = null;

  InstitutionOwnerHandler() {
    sorTree = new SorTree();
    this.currentOrganizationalUnitStack = new Stack<>();
  }

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes)
      throws SAXException {
    if (qName.equals("InstitutionOwnerEntity")) {
      this.currentInstitutionOwnerElement = new InstitutionOwner();
    }

    if (qName.equals("HealthInstitutionEntity")) {
      this.currentHealthInstitution = new HealthInstitution();
    }

    if (qName.equals("OrganizationalUnitEntity")) {
        this.currentOrganizationalUnitStack.push(new OrganizationalUnit());
    }

    this.data = new StringBuilder();
    // System.out.println("StartElement: " + qName);
    super.startElement(uri, localName, qName, attributes);
  }

  @Override
  public void characters(char[] ch, int start, int length) throws SAXException {
    data.append(ch, start, length);
  }

  @Override
  public void endElement(String uri, String localName, String qName) throws SAXException {
    if (qName.equals("InstitutionOwnerEntity")) {
      this.sorTree.institutionOwners.add(currentInstitutionOwnerElement);
      currentInstitutionOwnerElement = null;
    }

    if (qName.equals("HealthInstitutionEntity")) {
      currentHealthInstitution.ParentSorIdentifier = currentInstitutionOwnerElement.SorIdentifier;
      currentInstitutionOwnerElement.healthInstitutionEntities.add(currentHealthInstitution);
      currentHealthInstitution = null;
    }

    if (qName.equals("OrganizationalUnitEntity")) {
      OrganizationalUnit currentOrganizationalUnit = this.currentOrganizationalUnitStack.pop();

      if(this.currentOrganizationalUnitStack.size() == 0) {
        currentOrganizationalUnit.ParentSorIdentifier = currentHealthInstitution.SorIdentifier;
        currentHealthInstitution.organizationalUnits.add(currentOrganizationalUnit);
      }
      else {
        currentOrganizationalUnit.ParentSorIdentifier = currentOrganizationalUnitStack.firstElement().SorIdentifier;
        currentOrganizationalUnitStack.firstElement().organizationalUnits.add(currentOrganizationalUnit);
      }
    }

    if (currentInstitutionOwnerElement != null) {
        setBaseField(qName, currentInstitutionOwnerElement);

      if (currentHealthInstitution != null) {
          setBaseField(qName, this.currentHealthInstitution);

        if (this.currentOrganizationalUnitStack.size() > 0) {
            setBaseField(qName, this.currentOrganizationalUnitStack.firstElement());
        }
      }
    }
  }

  private void setBaseField(String qName, BaseEntity baseEntity) {

    if (qName.equals("sor1:SorIdentifier") && baseEntity.SorIdentifier == null) {
      baseEntity.SorIdentifier = data.toString();
    }

    if (qName.equals("sor1:EntityName") && baseEntity.EntityName == null) {
      baseEntity.EntityName = data.toString();
    }

    if (qName.equals("EntityTypeIdentifier") && baseEntity.EntityTypeIdentifier == null) {
      baseEntity.EntityTypeIdentifier = data.toString();
    }
  }

  SorTree getSorTree() {
    return sorTree;
  }
}
