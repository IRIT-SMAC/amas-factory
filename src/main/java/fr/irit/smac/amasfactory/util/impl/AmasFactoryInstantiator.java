package fr.irit.smac.amasfactory.util.impl;

import com.google.gson.JsonElement;

import fr.irit.smac.amasfactory.util.IAmasFactoryInstantiator;

public class AmasFactoryInstantiator implements IAmasFactoryInstantiator {

    private AmasFactoryInstantiator() {

    }
    
    private static AmasFactoryInstantiator INSTANCE = new AmasFactoryInstantiator();
   
    public static AmasFactoryInstantiator getInstance() {
        return INSTANCE;
    }
    
    @Override
    public <T> T instantiateAndInitObjectFromConfig(JsonElement configuration) {
        
//        T object = this.createInstanceFromClassNameField(configuration);
        
//        JsonElement config = configuration.get(CONF_KEY);
//        
//        if (object instanceof IJSonInitialisable && config != null) {
//            ((IJSonInitialisable) object).init(configuration);
//        }
        
//        return object;
            return null;
    }

    

}
