package fr.irit.smac.amasfactory.util.parser.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import fr.irit.smac.amasfactory.IInfrastructure;
import fr.irit.smac.amasfactory.agent.IInfrastructureAgent;
import fr.irit.smac.amasfactory.impl.BasicInfrastructure;
import fr.irit.smac.amasfactory.util.parser.IAmasFactoryParser;

public class AmasFactoryParser implements IAmasFactoryParser {

	protected AmasFactoryParser() {

	}

	private static AmasFactoryParser INSTANCE = new AmasFactoryParser();

	public static AmasFactoryParser getInstance() {
		return INSTANCE;
	}

	public <A extends IInfrastructureAgent<M>, M> IInfrastructure<A, M> parseInfrastructure(InputStream configuration) {
		
		ObjectMapper mapper = new ObjectMapper();

		IInfrastructure<A, M> infrastructure = null;
		try {
			
			JsonElement configurationJson = new JsonParser().parse(new InputStreamReader(configuration));
			infrastructure = mapper.readValue(configurationJson.getAsJsonObject().toString(), BasicInfrastructure.class);
			infrastructure.init(null);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return infrastructure;
	}
}
