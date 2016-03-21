package fr.irit.smac.amasfactory.util.parser;

import java.io.InputStream;

import com.google.gson.JsonElement;

import fr.irit.smac.amasfactory.IAgentSideInfrastructure;
import fr.irit.smac.amasfactory.IInfrastructure;
import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;
import fr.irit.smac.amasfactory.service.IInfraService;

public interface IAmasFactoryParser {

	public void init(InputStream configuration);

	public <A extends IInfrastructureAgent<M>, M> IInfrastructure<A, M>  instantiateAndInitInfrastructure();

	public <A extends IInfrastructureAgent<M>, M> void instantiateAndInitAgents(IInfrastructure<A, M> infrastructure);

	public <A extends IInfrastructureAgent<M>, M> IInfraService<A, M> instantiateAndInitService(IAgentSideInfrastructure<M> infrastructure, JsonElement jsonService);

	public JsonElement getMessagingService();

	public JsonElement getExecutionService();

	public JsonElement getHandlerService();

	public JsonElement getLoggingService();

}
