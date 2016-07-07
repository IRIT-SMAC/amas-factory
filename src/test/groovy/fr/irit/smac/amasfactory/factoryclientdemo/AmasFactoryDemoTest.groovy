package fr.irit.smac.amasfactory.factoryclientdemo


import spock.lang.Specification

import com.fasterxml.jackson.databind.JsonMappingException

import fr.irit.smac.amasfactory.IInfrastructure
import fr.irit.smac.amasfactory.agent.features.IFeatures
import fr.irit.smac.amasfactory.factoryclientdemo.example1.impl.DemoAgent
import fr.irit.smac.amasfactory.impl.BasicAmasFactory
import fr.irit.smac.amasfactory.service.agenthandler.impl.BasicAgentHandler
import fr.irit.smac.amasfactory.service.execution.impl.TwoStepAgExecutionService
import fr.irit.smac.amasfactory.service.logging.impl.AgentLogLoggingService
import fr.irit.smac.amasfactory.service.messaging.impl.MessagingService

class AmasFactoryDemoTest extends Specification{

    def 'check if the system is correctly instantiated'() {

        given:
        BasicAmasFactory basicAmasFactory = new BasicAmasFactory()


        when:
        IInfrastructure infra =
                        basicAmasFactory.createInfrastructure(ClassLoader.getSystemResourceAsStream("./config/demo_config.json"))

        then:
        infra.getServices().getAgentHandlerService().getAgentMap().size() == 2
        infra.getServices().getExecutionService() instanceof TwoStepAgExecutionService<A, M>
        infra.getServices().getAgentHandlerService() instanceof BasicAgentHandler<IInfrastructureAgent<M>, M>
        infra.getServices().getLoggingService() instanceof AgentLogLoggingService<M>
        infra.getServices().getMessagingService() instanceof MessagingService<M>
        infra.shutdown()
    }

    def 'check if the system is working correctly'() {

        given:
        BasicAmasFactory basicAmasFactory = new BasicAmasFactory()

        IInfrastructure infra =
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

            assert agent.getPrimaryFeature().getKnowledge().getCount() == 20
        }
        infra.shutdown()
    }

    def 'check if an exception is thrown when a service is missing'() {

        when:
        BasicAmasFactory basicAmasFactory = new BasicAmasFactory()

        IInfrastructure infra =
                        basicAmasFactory.createInfrastructure(ClassLoader.getSystemResourceAsStream("./config/exceptions/missing_service.json"))

        then:
        thrown JsonMappingException
    }

    def 'check if an exception is thrown when the messageClass of the messagingService is missing'() {

        when:
        BasicAmasFactory basicAmasFactory = new BasicAmasFactory()

        IInfrastructure infra =
                        basicAmasFactory.createInfrastructure(ClassLoader.getSystemResourceAsStream("./config/exceptions/missing_attribute_messaging_service.json"))

        then:
        thrown JsonMappingException
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
