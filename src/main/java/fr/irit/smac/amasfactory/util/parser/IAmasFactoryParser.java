package fr.irit.smac.amasfactory.util.parser;

import java.io.InputStream;

import fr.irit.smac.amasfactory.IInfrastructure;
import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;

public interface IAmasFactoryParser {

	public <A extends IInfrastructureAgent<M>, M> IInfrastructure<A, M>  parseInfrastructure(InputStream configuration);

}
