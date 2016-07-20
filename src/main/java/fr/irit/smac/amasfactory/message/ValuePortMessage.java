/*
 * #%L
 * amas-factory
 * %%
 * Copyright (C) 2015 - 2016 IRIT - SMAC Team and Brennus Analytics
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */
package fr.irit.smac.amasfactory.message;

/**
 * A message allowing to the sender to send a value to the port of the receiver
 */
public class ValuePortMessage extends Message {

    private final Object value;
    private final String port;

    public ValuePortMessage(String port, Object value, String sender) {

        super(EMessageType.SEND_TO_TARGET_MESSAGE, sender);

        this.port = port;
        this.value = value;
    }

    public Object getValue() {

        return value;
    }

    public String getPort() {

        return port;
    }

    @Override
    public String toString() {
        return "SendToTargetMessage [v=" + value + ", target=" + port + "]";
    }

}
