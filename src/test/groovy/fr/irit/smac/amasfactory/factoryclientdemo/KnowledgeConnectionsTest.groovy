package fr.irit.smac.amasfactory.factoryclientdemo

import static org.junit.Assert.*
import spock.lang.Specification
import fr.irit.smac.amasfactory.IInfrastructure
import fr.irit.smac.amasfactory.agent.IAgent
import fr.irit.smac.amasfactory.agent.features.social.IPort
import fr.irit.smac.amasfactory.agent.features.social.impl.KnowledgeSocial
import fr.irit.smac.amasfactory.agent.impl.Agent
import fr.irit.smac.amasfactory.factoryclientdemo.example2.impl.DemoAgent2
import fr.irit.smac.amasfactory.impl.BasicAmasFactory

class KnowledgeConnectionsTest extends Specification {

    def 'check if the system is working correctly with knowledge connections'() {

        given:
        BasicAmasFactory basicAmasFactory = new BasicAmasFactory()

        IInfrastructure infra =
                        basicAmasFactory.createInfrastructure(ClassLoader.getSystemResourceAsStream("./config/knowledge_connections.json"))
        Map<String,IAgent> agents = infra.getServices().getAgentHandlerService().getAgentMap()

        agents.get("ag1").getFeatures().getFeatureSocial().getKnowledge().setOutputValue("hello")
        agents.get("ag5").getFeatures().getFeatureSocial().getKnowledge().setOutputValue("hallo")

        when:
        for (int i = 0 ; i < 4; i++) {
            System.out.println("\n=== step "+i+" ===")
            infra.getServices().getExecutionService().step().get()
        }

        then:
        List outputs = ["hello", "hellohallohello", "hellohallohello", "hallo", "hallo"]
        int j = 0
        for (e in agents) {
            DemoAgent2 agent = e.value
            assert agent.getFeatures().getFeatureSocial().getKnowledge().getOutputValue() == outputs[j]
            j++
        }
        infra.shutdown()
    }

    def 'check if the system is working correctly with knowledge connections2'() {

        given:
        BasicAmasFactory basicAmasFactory = new BasicAmasFactory()

        IInfrastructure infra =
                        basicAmasFactory.createInfrastructure(ClassLoader.getSystemResourceAsStream("./config/knowledge_connections2.json"))
        Map<String,IAgent> agents = infra.getServices().getAgentHandlerService().getAgentMap()

        agents.get("ag1").getFeatures().getFeatureSocial().getKnowledge().setOutputValue("hello")
        agents.get("ag2").getFeatures().getFeatureSocial().getKnowledge().setOutputValue("hallo")

        when:
        for (int i = 0 ; i < 4; i++) {
            System.out.println("\n=== step "+i+" ===")
            infra.getServices().getExecutionService().step().get()
        }

        then:
        int j = 0
        List outputs = ["hello", "hallo"]

        Agent agent = agents.get("ag3")
        HashMap<IPort> portMap = agent.getFeatures().getFeatureSocial().getKnowledge().getPortMap()
        for (p in portMap) {
            IPort port = p.value
            assert port.getValue() == outputs[j]
            j++
        }

        infra.shutdown()
    }
}
