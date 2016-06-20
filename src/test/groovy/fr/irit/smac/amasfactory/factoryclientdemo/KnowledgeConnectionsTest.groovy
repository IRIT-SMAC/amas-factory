package fr.irit.smac.amasfactory.factoryclientdemo

import static org.junit.Assert.*
import fr.irit.smac.amasfactory.IInfrastructure
import fr.irit.smac.amasfactory.impl.BasicAmasFactory
import spock.lang.Specification

class KnowledgeConnectionsTest extends Specification {

    def 'check if the system is working correctly with knowledge connections'() {

        given:
        BasicAmasFactory basicAmasFactory = new BasicAmasFactory()

        IInfrastructure<DemoAgent, DemoMessage> infra =
                        basicAmasFactory.createInfrastructure(ClassLoader.getSystemResourceAsStream("./config/knowledge_connections.json"))
        Map<String,DemoAgent2> agents = infra.getAgentHandler().getAgentMap()

        agents.get("ag1").getInnerKnowledge().setOutputValue("hello")
        agents.get("ag5").getInnerKnowledge().setOutputValue("hallo")

        when:
        for (int i = 0 ; i < 4; i++) {
            System.out.println("\n=== step "+i+" ===")
            infra.getExecutionService().step().get()
        }

        then:
        List outputs = ["hello", "hellohallohello", "hellohallohello", "hallo", "hallo"]
        int j = 0
        for (e in agents) {
            DemoAgent2 agent = e.value
            assert agent.getInnerKnowledge().getOutputValue() == outputs[j]
            j++
        }
        infra.shutdown()
    }
}