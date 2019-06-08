package com.show_me_kafka.sor.sor_producer.model;

import java.util.ArrayList;
import java.util.List;

public class HealthInstitution extends BaseEntity {

    public String ParentSorIdentifier;

    public HealthInstitution() {
        this.organizationalUnits = new ArrayList<OrganizationalUnit>();
    }

    public List<OrganizationalUnit> organizationalUnits;

}

