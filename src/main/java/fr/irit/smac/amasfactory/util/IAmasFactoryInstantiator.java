package fr.irit.smac.amasfactory.util;

import com.google.gson.JsonElement;

public interface IAmasFactoryInstantiator {

    /**
     * Instantiate an object from json configuration that follow the example below
     * 
     * {
     *    className:"com.brennus.amas.influencenetwork.datasource.impl.CsvBasicDataSource",
     *    configuration:{ some:"json",parameters:""}
     * }
     * 
     * @param configuration
     * @return
     */
    public <T> T instantiateAndInitObjectFromConfig(JsonElement configuration) ;
    
}
