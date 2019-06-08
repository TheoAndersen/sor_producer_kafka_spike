package com.show_me_kafka.sor.sor_producer.model;

import java.util.ArrayList;
import java.util.List;

public class OrganizationalUnit extends BaseEntity {
    public String ParentSorIdentifier;

    public OrganizationalUnit() {
        this.organizationalUnits = new ArrayList<OrganizationalUnit>();
    }

    public List<OrganizationalUnit> organizationalUnits;
}

