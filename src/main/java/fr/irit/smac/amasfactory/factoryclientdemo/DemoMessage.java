package fr.irit.smac.amasfactory.factoryclientdemo;

public class DemoMessage
{
    public final DemoAgent sender;
    public final String message;
    
    public DemoMessage(DemoAgent sender, String message)
    {
        super();
        this.sender = sender;
        this.message = message;
    }

    @Override
    public String toString()
    {
        return "Msg [sender=" + sender.getId() + ", message=" + message + "]";
    }
    
}
