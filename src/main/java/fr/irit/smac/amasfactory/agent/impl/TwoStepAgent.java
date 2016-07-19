package fr.irit.smac.amasfactory.agent.impl;

import fr.irit.smac.amasfactory.agent.IKnowledge;
import fr.irit.smac.amasfactory.agent.ISkill;
import fr.irit.smac.amasfactory.agent.features.ICommonFeatures;
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent;

/**
 * This class extends the agent class and implements a strategy in which the
 * life cycle of an agent is in two steps
 * 
 * @param <F>
 *            the common features
 * @param <K>
 *            the knowledge
 * @param <S>
 *            the skill
 */
public abstract class TwoStepAgent<F extends ICommonFeatures, K extends IKnowledge, S extends ISkill<K>>
    extends Agent<F, K, S>implements ITwoStepsAgent {

}
