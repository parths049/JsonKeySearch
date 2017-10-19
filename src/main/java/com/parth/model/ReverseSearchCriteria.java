package com.parth.model;

import org.hibernate.validator.constraints.NotBlank;

public class ReverseSearchCriteria {

    @NotBlank(message = "Please Enter label to search!")
    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}