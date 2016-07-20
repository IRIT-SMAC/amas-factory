package fr.irit.smac.amasfactory.factoryclientdemo

import static org.junit.Assert.*
import spock.lang.Specification
import fr.irit.smac.amasfactory.agent.IAgent
import fr.irit.smac.amasfactory.agent.features.social.IPort
import fr.irit.smac.amasfactory.agent.impl.Agent
import fr.irit.smac.amasfactory.factoryclientdemo.example2.impl.DemoAgent2
import fr.irit.smac.amasfactory.impl.AmasFactory
import fr.irit.smac.amasfactory.infrastructure.IInfrastructure;

class KnowledgeConnectionsTest extends Specification {

    def 'check if the system is working correctly with knowledge connections'() {

        given:
        AmasFactory basicAmasFactory = new AmasFactory()

        IInfrastructure infra =
                        basicAmasFactory.createInfrastructure(ClassLoader.getSystemResourceAsStream("./config/knowledge_connections.json"))
        Map<String,IAgent> agents = infra.getServices().getAgentHandlerService().getAgentMap()

        agents.get("ag1").getKnowledge().setValue("hello")
        
        when:
        for (int i = 0 ; i < 4; i++) {
            System.out.println("\n=== step "+i+" ===")
            infra.getServices().getExecutionService().step().get()
        }

        then:
        int j = 0
        for (e in agents) {
            DemoAgent2 agent = e.value
            assert agent.getKnowledge().getValue() == "hello"
            j++
        }
        infra.shutdown()
    }

    def 'check if the system is working correctly with knowledge connections2'() {

        given:
        AmasFactory basicAmasFactory = new AmasFactory()

        IInfrastructure infra =
                        basicAmasFactory.createInfrastructure(ClassLoader.getSystemResourceAsStream("./config/knowledge_connections2.json"))
        Map<String,IAgent> agents = infra.getServices().getAgentHandlerService().getAgentMap()

        agents.get("ag1").getKnowledge().setValue("hello")
        agents.get("ag2").getKnowledge().setValue("hallo")
        

        when:
        for (int i = 0 ; i < 4; i++) {
            System.out.println("\n=== step "+i+" ===")
            infra.getServices().getExecutionService().step().get()
        }

        then:
        int j = 0
        List outputs = ["hello", "hallo"]

        Agent agent = agents.get("ag3")
        agent.getFeatures().getFeatureSocial().getKnowledge().getPortMap().each { k,v ->
            Set<Object> val = v.getValue()
            assert val.getAt(0) == outputs[j]
            j++
        }

        infra.shutdown()
    }
}
