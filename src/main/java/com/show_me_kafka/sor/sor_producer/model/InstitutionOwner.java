package com.show_me_kafka.sor.sor_producer.model;

import java.util.ArrayList;
import java.util.List;

public class InstitutionOwner extends BaseEntity {

    public InstitutionOwner() {
        this.healthInstitutionEntities = new ArrayList<HealthInstitution>();
    }

    public List<HealthInstitution> healthInstitutionEntities;

}

