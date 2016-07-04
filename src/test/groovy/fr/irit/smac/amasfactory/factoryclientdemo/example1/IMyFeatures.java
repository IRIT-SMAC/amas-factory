package fr.irit.smac.amasfactory.factoryclientdemo.example1;

import fr.irit.smac.amasfactory.agent.IFeature;
import fr.irit.smac.amasfactory.factoryclientdemo.example1.impl.KnowledgeCustom;
import fr.irit.smac.amasfactory.factoryclientdemo.example1.impl.SkillCustom;

public interface IMyFeatures {

    public IFeature<KnowledgeCustom, SkillCustom> getFeatureCustom();

}
