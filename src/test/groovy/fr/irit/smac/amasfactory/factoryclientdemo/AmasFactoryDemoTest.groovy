package fr.irit.smac.amasfactory.factoryclientdemo


import spock.lang.Specification

import com.fasterxml.jackson.databind.JsonMappingException

import fr.irit.smac.amasfactory.IInfrastructure
import fr.irit.smac.amasfactory.agent.IAgent
import fr.irit.smac.amasfactory.agent.features.IFeature
import fr.irit.smac.amasfactory.agent.features.IFeatures
import fr.irit.smac.amasfactory.agent.features.basic.IKnowledgeBasic
import fr.irit.smac.amasfactory.agent.features.basic.ISkillBasic
import fr.irit.smac.amasfactory.agent.features.impl.Feature
import fr.irit.smac.amasfactory.agent.features.impl.Features
import fr.irit.smac.amasfactory.factoryclientdemo.example1.IKnowledgeCustom
import fr.irit.smac.amasfactory.factoryclientdemo.example1.ISkillCustom
import fr.irit.smac.amasfactory.factoryclientdemo.example1.impl.DemoAgent
import fr.irit.smac.amasfactory.factoryclientdemo.example1.impl.KnowledgeCustom
import fr.irit.smac.amasfactory.factoryclientdemo.example1.impl.SkillCustom
import fr.irit.smac.amasfactory.impl.BasicAmasFactory
import fr.irit.smac.amasfactory.service.IServices
import fr.irit.smac.amasfactory.service.agenthandler.impl.BasicAgentHandler
import fr.irit.smac.amasfactory.service.execution.impl.TwoStepAgExecutionService
import fr.irit.smac.amasfactory.service.logging.impl.AgentLogLoggingService
import fr.irit.smac.amasfactory.service.messaging.impl.MessagingService

class AmasFactoryDemoTest extends Specification{

    def 'check if the system is correctly instantiated'() {

        given:
        BasicAmasFactory basicAmasFactory = new BasicAmasFactory()


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
        BasicAmasFactory basicAmasFactory = new BasicAmasFactory()


        when:
        IInfrastructure<IServices,IAgent> infra =
                        basicAmasFactory.createInfrastructure(ClassLoader.getSystemResourceAsStream("./config/demo_config3.json"))

        then:
        infra.getServices().getExecutionService() instanceof TwoStepAgExecutionService<A, M>
        infra.getServices().getAgentHandlerService() instanceof BasicAgentHandler<IInfrastructureAgent<M>, M>
        infra.getServices().getMessagingService() instanceof MessagingService<M>
        infra.shutdown()
    }

    def 'check if the system is working correctly'() {

        given:
        BasicAmasFactory basicAmasFactory = new BasicAmasFactory()

        IInfrastructure<IServices,IAgent> infra =
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
            assert agent.primaryFeature.getKnowledge().getCount() == 20
        }
        infra.shutdown()
    }

    def 'check if an exception is thrown when the configuration file has an error of syntax'() {

        when:
        BasicAmasFactory basicAmasFactory = new BasicAmasFactory()

        IInfrastructure infra =
                        basicAmasFactory.createInfrastructure(ClassLoader.getSystemResourceAsStream("./config/exceptions/error_syntax.json"))

        then:
        thrown JsonMappingException
    }
}
