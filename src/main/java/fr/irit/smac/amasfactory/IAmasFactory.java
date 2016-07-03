package fr.irit.smac.amasfactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * The interface IAmasFactory exposes methods to create an infrastructure.
 */
@FunctionalInterface
public interface IAmasFactory {

    /**
     * Creates a new IAmas object.
     *
     * @param <A>
     *            the generic type
     * @param <M>
     *            the generic type
     * @param configuration
     *            the configuration
     * @return the i infrastructure< a, m>
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public IInfrastructure createInfrastructure(InputStream configuration)
        throws IOException;
}
