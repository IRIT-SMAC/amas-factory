package fr.irit.smac.amasfactory.factoryclientdemo.example1;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EMyFeature {
    CUSTOM("custom");

    private String name;

    private EMyFeature(final String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
