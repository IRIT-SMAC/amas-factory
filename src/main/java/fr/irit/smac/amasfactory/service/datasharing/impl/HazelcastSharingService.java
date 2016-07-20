/*
 * #%L
 * amas-factory
 * %%
 * Copyright (C) 2015 - 2016 IRIT - SMAC Team and Brennus Analytics
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */
package fr.irit.smac.amasfactory.service.datasharing.impl;

import fr.irit.smac.amasfactory.agent.IAgent;
import fr.irit.smac.amasfactory.agent.features.basic.IKnowledgeBasic;
import fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService;
import fr.irit.smac.amasfactory.service.datasharing.IDataSharingService;
import fr.irit.smac.amasfactory.service.execution.IExecutionService;
import fr.irit.smac.amasfactory.util.hazelcast.IHazelcastKnowledgeAccessor;
import fr.irit.smac.amasfactory.util.hazelcast.impl.HazelcastKnowledgeAccessor;

/**
 * Create a Map with Hazelcast and fill it with the data from the agents
 * knowledge. Update the shared data at the end of each step. Automatically add
 * to the map the knowledge of newly created agent. Automatically remove from
 * the map the knowledge of deleted agent.
 * 
 * @author SVI
 *
 * @param <A>
 * @param <M>
 */
@SuppressWarnings("rawtypes")
public class HazelcastSharingService<A extends IAgent>
    implements IDataSharingService<A> {

    IHazelcastKnowledgeAccessor hazelcastKnowledgeAccessor;

    // BEFORE_ITERATION AFTER_ITERATION
    public static final String PERSISTENCE_UPDATE_POLICY = "AFTER_ITERATION";

    private Runnable persistencePolicy;

    private IAgentHandlerService<A> agentHandlerService;

    private IExecutionService<A> executionService;

    public HazelcastSharingService() {
        hazelcastKnowledgeAccessor = new HazelcastKnowledgeAccessor();
    }

    @Override
    public void agentAdded(A agent) {
        hazelcastKnowledgeAccessor.registerKnowledge((IKnowledgeBasic) agent.getFeatures().getFeatureBasic());
    }

    @Override
    public void agentRemoved(A agent) {
        hazelcastKnowledgeAccessor.removeKnowledge(agent.getFeatures().getFeatureBasic().getKnowledge().getId());
    }

    @Override
    public void start() {

        // Register to react to agents creation, deletion.
        agentHandlerService.addAgentEventListener(this);

        persistencePolicy = generatePersistenceUpdatePolicy(agentHandlerService);

        // Register to share the agent knowledge at the end of each step.
        switch (PERSISTENCE_UPDATE_POLICY) {
            case "AFTER_ITERATION":
                executionService.addPostStepHook(persistencePolicy);
                break;
            case "BEFORE_ITERATION":
                executionService.addPreStepHook(persistencePolicy);
                break;
            default:
                break;
        }

    }

    /**
     * Persist the data in the map at the end of each iteration.
     * 
     * @param infrastructure
     * @return
     */
    private Runnable generatePersistenceUpdatePolicy(IAgentHandlerService<A> agentHandler) {
        return () -> agentHandler.getAgents().forEach(agent -> hazelcastKnowledgeAccessor
            .registerKnowledge(agent.getFeatures().getFeatureBasic().getKnowledge()));

    }

    @Override
    public void shutdown() {

        agentHandlerService.removeAgentEventListener(this);

        switch (PERSISTENCE_UPDATE_POLICY) {
            case "AFTER_ITERATION":
                executionService.removePostStepHook(persistencePolicy);
                break;
            case "BEFORE_ITERATION":
                executionService.removePreStepHook(persistencePolicy);
                break;
            default:
                break;
        }

    }

    @Override
    public void setAgentHandlerService(IAgentHandlerService<A> agentHandlerService) {
        this.agentHandlerService = agentHandlerService;
    }

    @Override
    public void setExecutionService(IExecutionService<A> executionService) {
        this.executionService = executionService;
    }

}
