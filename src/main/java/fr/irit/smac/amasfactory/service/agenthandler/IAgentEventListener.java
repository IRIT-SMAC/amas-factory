package fr.irit.smac.amasfactory.service.agenthandler;

public interface IAgentEventListener<A> {
    public void agentAdded(A agent);

    public void agentRemoved(A agent);

}
