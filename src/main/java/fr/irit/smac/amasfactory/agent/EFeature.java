package fr.irit.smac.amasfactory.agent;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EFeature {
    SOCIAL("social"), BASIC("basic");

    private String name;

    private EFeature(final String name) {
        this.name = name;
    }
    
    @JsonValue
    public String getName() {
        return name;
    }
}
