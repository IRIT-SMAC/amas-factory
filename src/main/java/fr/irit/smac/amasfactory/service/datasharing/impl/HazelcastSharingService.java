package fr.irit.smac.amasfactory.service.datasharing.impl;

import fr.irit.smac.amasfactory.agent.IAgent;
import fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService;
import fr.irit.smac.amasfactory.service.datasharing.IDataSharingService;
import fr.irit.smac.amasfactory.service.execution.IExecutionService;
import fr.irit.smac.amasfactory.util.IHazelcastKnowledgeAccessor;
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
public class HazelcastSharingService<K, S>
    implements IDataSharingService {

    IHazelcastKnowledgeAccessor hazelcastKnowledgeAccessor;

    // BEFORE_ITERATION AFTER_ITERATION
    public static final String PERSISTENCE_UPDATE_POLICY = "AFTER_ITERATION";

    private Runnable persistencePolicy;

    private IAgentHandlerService agentHandlerService;

    private IExecutionService executionService;

    public HazelcastSharingService() {
        this.hazelcastKnowledgeAccessor = new HazelcastKnowledgeAccessor();
    }

    @Override
    public void agentAdded(IAgent agent) {
//        this.hazelcastKnowledgeAccessor.registerKnowledge((IKnowledgeBasic) agent.getFeatures().get(EFeature.BASIC.getName()));
    }

    @Override
    public void agentRemoved(IAgent agent) {
        this.hazelcastKnowledgeAccessor.removeKnowledge(agent.getId());
    }

    @Override
    public void start() {

        // Register to react to agents creation, deletion.
        this.agentHandlerService.addAgentEventListener(this);

        this.persistencePolicy = generatePersistenceUpdatePolicy(this.agentHandlerService);

        // Register to share the agent knowledge at the end of each step.
        switch (PERSISTENCE_UPDATE_POLICY) {
            case "AFTER_ITERATION":
                this.executionService.addPostStepHook(this.persistencePolicy);
                break;
            case "BEFORE_ITERATION":
                this.executionService.addPreStepHook(this.persistencePolicy);
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
    private Runnable generatePersistenceUpdatePolicy(IAgentHandlerService agentHandler) {
        return new Runnable() {
            @Override
            public void run() {
//                for (IAgent agent : agentHandler.getAgents()) {
//                    hazelcastKnowledgeAccessor.registerKnowledge(agent.getFeatures().getFeatureBasic().getKnowledge());
                    // System.out.println("registering " + agent.getId() );
//                }
            }
        };
    }

    @Override
    public void shutdown() {

        this.agentHandlerService.removeAgentEventListener(this);

        switch (PERSISTENCE_UPDATE_POLICY) {
            case "AFTER_ITERATION":
                this.executionService.removePostStepHook(this.persistencePolicy);
                break;
            case "BEFORE_ITERATION":
                this.executionService.removePreStepHook(this.persistencePolicy);
                break;
            default:
                break;
        }

    }

    @Override
    public void setAgentHandlerService(IAgentHandlerService agentHandlerService) {
        this.agentHandlerService = agentHandlerService;
    }

    @Override
    public void setExecutionService(IExecutionService executionService) {
        this.executionService = executionService;
    }

}
