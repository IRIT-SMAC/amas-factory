package fr.irit.smac.amasfactory.factoryclientdemo;

import org.slf4j.Logger;

import com.google.gson.JsonElement;

import fr.irit.smac.amasfactory.agent.impl.AbsInfrastructureAgent;
import fr.irit.smac.libs.tooling.messaging.IMsgBox;
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent;

public class DemoAgent extends AbsInfrastructureAgent<DemoMessage> implements ITwoStepsAgent
{
    private String message;
    private IMsgBox<DemoMessage> msgBox;
    private Logger logger;

    @Override
    public void perceive()
    {
        for (DemoMessage demoMessage : msgBox.getMsgs())
        {
        	System.out.println(("agent "+this.getId()+" : I received "+demoMessage));
            logger.debug("agent "+this.getId()+" : I received "+demoMessage);
        }
    }

    @Override
    public void decideAndAct()
    {
        logger.debug("agent "+this.getId()+" : I send "+ this.message);
        this.msgBox.broadcast(new DemoMessage(this, this.message));
    }

    @Override
    protected void initParameters(JsonElement configuration)
    {
    	
    	this.msgBox = this.getInfra().getMessagingService().getMsgBox(this.getId());
        this.logger = this.getInfra().getLoggingService().getAgentLogger(this.getId());
    }

}
