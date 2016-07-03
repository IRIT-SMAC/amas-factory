package fr.irit.smac.amasfactory.agent;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EExtraKnowledgeSkill {
    SOCIAL("social");

    private String name;

    private EExtraKnowledgeSkill(final String name) {
        this.name = name;
    }
    
    @JsonValue
    public String getName() {
        return name;
    }
}
