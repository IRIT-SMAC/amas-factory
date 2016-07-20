package fr.irit.smac.amasfactory.factoryclientdemo


import spock.lang.Specification

import com.fasterxml.jackson.databind.JsonMappingException

import fr.irit.smac.amasfactory.agent.IAgent
import fr.irit.smac.amasfactory.agent.features.ICommonFeatures
import fr.irit.smac.amasfactory.agent.features.IFeature
import fr.irit.smac.amasfactory.agent.features.basic.IKnowledgeBasic
import fr.irit.smac.amasfactory.agent.features.basic.ISkillBasic
import fr.irit.smac.amasfactory.agent.features.basic.impl.KnowledgeBasic
import fr.irit.smac.amasfactory.agent.features.basic.impl.SkillBasic
import fr.irit.smac.amasfactory.agent.features.impl.CommonFeatures
import fr.irit.smac.amasfactory.agent.features.impl.Feature
import fr.irit.smac.amasfactory.factoryclientdemo.example1.IKnowledgeCustom
import fr.irit.smac.amasfactory.factoryclientdemo.example1.ISkillCustom
import fr.irit.smac.amasfactory.factoryclientdemo.example1.impl.DemoAgent
import fr.irit.smac.amasfactory.factoryclientdemo.example1.impl.KnowledgeCustom
import fr.irit.smac.amasfactory.factoryclientdemo.example1.impl.SkillCustom
import fr.irit.smac.amasfactory.impl.AmasFactory
import fr.irit.smac.amasfactory.infrastructure.IInfrastructure;
import fr.irit.smac.amasfactory.service.IServices
import fr.irit.smac.amasfactory.service.agenthandler.IAgentHandlerService
import fr.irit.smac.amasfactory.service.agenthandler.impl.BasicAgentHandler
import fr.irit.smac.amasfactory.service.execution.impl.TwoStepAgExecutionService
import fr.irit.smac.amasfactory.service.impl.Services
import fr.irit.smac.amasfactory.service.logging.impl.AgentLogLoggingService
import fr.irit.smac.amasfactory.service.messaging.impl.MessagingService

class AmasFactoryDemoTest extends Specification{

    def 'check if the system is correctly instantiated'() {

        given:
        AmasFactory basicAmasFactory = new AmasFactory()


        when:
        IInfrastructure<IServices,IAgent> infra =
                        basicAmasFactory.createInfrastructure(ClassLoader.getSystemResourceAsStream("./config/demo_config.json"))

        then:
        infra.getServices().getAgentHandlerService().getAgentMap().size() == 2
        infra.getServices().getExecutionService() instanceof TwoStepAgExecutionService<A, M>
        infra.getServices().getAgentHandlerService() instanceof BasicAgentHandler<IInfrastructureAgent<M>, M>
        infra.getServices().getLoggingService() instanceof AgentLogLoggingService<M>
        infra.getServices().getMessagingService() instanceof MessagingService<M>
        infra.shutdown()
    }

    def 'check if the system is correctly instantiated with 3 services'() {

        given:
        AmasFactory basicAmasFactory = new AmasFactory()


        when:
        IInfrastructure<IServices,IAgent> infra =
                        basicAmasFactory.createInfrastructure(ClassLoader.getSystemResourceAsStream("./config/demo_config3.json"))

        then:
        infra.getServices().getExecutionService() instanceof TwoStepAgExecutionService<A, M>
        infra.getServices().getAgentHandlerService() instanceof BasicAgentHandler<IInfrastructureAgent<M>, M>
        infra.getServices().getMessagingService() instanceof MessagingService<M>
        infra.shutdown()
    }

    private DemoAgent createDemoAgent(String id) {

        DemoAgent<ICommonFeatures, IKnowledgeCustom, ISkillCustom<IKnowledgeCustom>> agent = new DemoAgent<>()
        agent.setCommonFeatures(new CommonFeatures())
        agent.setKnowledge(new KnowledgeCustom())
        agent.setSkill(new SkillCustom())
        agent.getFeatures().setFeatureBasic(new Feature<IKnowledgeBasic,ISkillBasic>())
        Feature<IKnowledgeBasic,ISkillBasic> featureBasic = agent.getFeatures().getFeatureBasic()
        featureBasic.setKnowledge(new KnowledgeBasic())
        featureBasic.setSkill(new SkillBasic())
        featureBasic.getKnowledge().setId(id)
        
        return agent
    }

    def 'check if the system works correctly when services and agents are instantiated with code'() {

        given:
        AmasFactory basicAmasFactory = new AmasFactory()
        IInfrastructure<IServices<IAgent>,IAgent> infra =
                        basicAmasFactory.createInfrastructure()

        infra.setServices(new Services())
        infra.getServices().setAgentHandlerService(new BasicAgentHandler<IAgent>())
        infra.getServices().setExecutionService(new TwoStepAgExecutionService())
        infra.getServices().setMessagingService(new MessagingService())
        infra.getServices().getExecutionService().setNbThreads(2)

        IAgentHandlerService<IAgent> agentHandlerService = infra.getServices().getAgentHandlerService()
        Map<String,IAgent> agentMap = new HashMap<>()
        agentMap.put("agent1", createDemoAgent("agent1"))
        agentMap.put("agent2", createDemoAgent("agent2"))
        agentHandlerService.setAgentMap(agentMap)

        infra.start()

        when:
        for (int i = 0 ; i < 10; i++) {
            System.out.println("\n=== step "+i+" ===")
            infra.getServices().getExecutionService().step().get()
        }

        then:
        infra.getServices().getExecutionService() instanceof TwoStepAgExecutionService<A, M>
        infra.getServices().getAgentHandlerService() instanceof BasicAgentHandler<IInfrastructureAgent<M>, M>
        infra.getServices().getMessagingService() instanceof MessagingService<M>

        Map<String,DemoAgent> agents = infra.getServices().getAgentHandlerService().getAgentMap()
        agents.each { k,v -> assert v.getKnowledge().getCount() == 20 }
        infra.shutdown()
    }

    def 'check if the system is working correctly'() {

        given:
        AmasFactory basicAmasFactory = new AmasFactory()

        IInfrastructure<IAgent> infra =
                        basicAmasFactory.createInfrastructure(ClassLoader.getSystemResourceAsStream("./config/demo_config.json"))


        when:
        for (int i = 0 ; i < 10; i++) {
            System.out.println("\n=== step "+i+" ===")
            infra.getServices().getExecutionService().step().get()
        }

        then:
        Map<String,DemoAgent> agents = infra.getServices().getAgentHandlerService().getAgentMap()
        for (Map.Entry<String, String> entry : agents.entrySet()) {
            System.out.println(entry.getKey() + "/" + entry.getValue())
            DemoAgent agent = entry.getValue()
            assert agent.getKnowledge().getCount() == 20
        }
        infra.shutdown()
    }

    def 'check if an exception is thrown when the configuration file has an error of syntax'() {

        when:
        AmasFactory basicAmasFactory = new AmasFactory()

        IInfrastructure infra =
                        basicAmasFactory.createInfrastructure(ClassLoader.getSystemResourceAsStream("./config/exceptions/error_syntax.json"))

        then:
        thrown JsonMappingException
    }
}

