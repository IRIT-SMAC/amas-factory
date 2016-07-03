package fr.irit.smac.amasfactory.agent;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
public interface ISkill extends IExtraSkill{

    public Map<String, IExtraSkill> getExtraSkill();
}
