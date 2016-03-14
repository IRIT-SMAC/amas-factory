package fr.irit.smac.amasfactory.factoryclientdemo;

import java.io.FileNotFoundException;
import java.util.concurrent.ExecutionException;

import fr.irit.smac.amasfactory.IInfrastructure;
import fr.irit.smac.amasfactory.impl.BasicAmasFactory;

public class ClientDemo
{
    public static final void main(String[] args) throws FileNotFoundException, InterruptedException, ExecutionException {
        BasicAmasFactory basicAmasFactory = new BasicAmasFactory();
        
        IInfrastructure<DemoAgent, DemoMessage> infra =
            basicAmasFactory.createInfrastructure(ClassLoader.getSystemResourceAsStream("./config.json"));
        
        for (int i = 0 ; i < 10; i++) {
            System.out.println("\n=== step "+i+" ===");
            infra.getExecutionService().step().get();
        }
        infra.shutdown();
       
        
    }
}
