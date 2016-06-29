package fr.irit.smac.amasfactory.agent.social;

public interface IAgentSocial {

    /**
     * Sends the output value of an agent to its targets
     */
    public void sendOutputValue();

    /**
     * Send the name of its port to the target
     */
    public void sendPort();

    /**
     * Add the targets according if the name of the port of an agent is received
     */
    public void addTargetFromMessage();

    /**
     * Updates the port if a value for this port is received
     */
    public void updatePortFromMessage();

}
