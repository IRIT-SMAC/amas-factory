package fr.irit.smac.amasfactory.factoryclientdemo


import fr.irit.smac.amasfactory.IInfrastructure
import fr.irit.smac.amasfactory.factoryclientdemo.DemoAgent
import fr.irit.smac.amasfactory.factoryclientdemo.DemoMessage
import fr.irit.smac.amasfactory.impl.BasicAmasFactory
import fr.irit.smac.amasfactory.service.agenthandler.impl.BasicAgentHandler
import fr.irit.smac.amasfactory.service.execution.impl.TwoStepAgExecutionService
import fr.irit.smac.amasfactory.service.logging.impl.AgentLogLoggingService
import fr.irit.smac.amasfactory.service.messaging.impl.MessagingService
import fr.irit.smac.libs.tooling.messaging.IMsgBox
import spock.lang.Specification

class AmasFactoryDemoTest extends Specification{

	def 'check if the system is correctly instantiated'() {

		given:
		BasicAmasFactory basicAmasFactory = new BasicAmasFactory()


		when:
		IInfrastructure<DemoAgent, DemoMessage> infra =
						basicAmasFactory.createInfrastructure(ClassLoader.getSystemResourceAsStream("./config.json"))

		then:
		infra.getAgentHandler().getAgentMap().size() == 2
		infra.getExecutionService() instanceof TwoStepAgExecutionService<A, M>
		infra.getAgentHandler() instanceof BasicAgentHandler<IInfrastructureAgent<M>, M>
		infra.getLoggingService() instanceof AgentLogLoggingService<M>
		infra.getMessagingService() instanceof MessagingService<M>
		infra.shutdown()
	}

	def 'check if the system is working correctly'() {

		given:
		BasicAmasFactory basicAmasFactory = new BasicAmasFactory()

		IInfrastructure<DemoAgent, DemoMessage> infra =
						basicAmasFactory.createInfrastructure(ClassLoader.getSystemResourceAsStream("./config.json"))

		when:
		for (int i = 0 ; i < 10; i++) {
			System.out.println("\n=== step "+i+" ===")
			infra.getExecutionService().step().get()
		}

		then:
		Map<String,DemoAgent> agents = infra.getAgentHandler().getAgentMap()
		for (Map.Entry<String, String> entry : agents.entrySet()) {
			System.out.println(entry.getKey() + "/" + entry.getValue())
			DemoAgent agent = entry.getValue()

			List<DemoMessage> messages = agent.msgBox.msgs

			assert messages.size() == 2
		}
		infra.shutdown()
	}
}
