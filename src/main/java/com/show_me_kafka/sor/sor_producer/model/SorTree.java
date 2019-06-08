package com.show_me_kafka.sor.sor_producer.model;

import java.util.ArrayList;
import java.util.List;

public class SorTree {

    public SorTree() {
        this.institutionOwners = new ArrayList<InstitutionOwner>();
    }

    public List<InstitutionOwner> institutionOwners;
}
