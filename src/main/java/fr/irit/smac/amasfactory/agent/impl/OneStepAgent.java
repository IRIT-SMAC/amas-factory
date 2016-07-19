package fr.irit.smac.amasfactory.agent.impl;

import fr.irit.smac.amasfactory.agent.IKnowledge;
import fr.irit.smac.amasfactory.agent.ISkill;
import fr.irit.smac.amasfactory.agent.features.ICommonFeatures;
import fr.irit.smac.libs.tooling.scheduling.IAgentStrategy;

/**
 * This class extends the agent class and implements a strategy in which the
 * life cycle of an agent is in one step
 * 
 * @param <F>
 *            the common features
 * @param <K>
 *            the knowledge
 * @param <S>
 *            the skill
 */
public abstract class OneStepAgent<F extends ICommonFeatures, K extends IKnowledge, S extends ISkill<K>>
    extends Agent<F, K, S>implements IAgentStrategy {

}
