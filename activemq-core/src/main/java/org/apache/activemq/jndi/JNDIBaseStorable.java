/**
 *
 * Copyright 2005-2006 The Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.activemq.jndi;

import javax.naming.NamingException;
import javax.naming.Reference;
import java.util.Properties;

/**
 * Faciliates objects to be stored in JNDI as properties
 */

public abstract class JNDIBaseStorable implements JNDIStorableInterface {
    private Properties properties = null;


    /**
     * Set the properties that will represent the instance in JNDI
     *
     * @param props
     */
    protected abstract void buildFromProperties(Properties props);

    /**
     * Initialize the instance from properties stored in JNDI
     *
     * @param props
     */

    protected abstract void populateProperties(Properties props);

    /**
     * set the properties for this instance as retrieved from JNDI
     *
     * @param props
     */

    public synchronized void setProperties(Properties props) {
        this.properties = props;
        buildFromProperties(props);
    }

    /**
     * Get the properties from this instance for storing in JNDI
     *
     * @return the properties
     */

    public synchronized Properties getProperties() {
        if (this.properties == null) {
            this.properties = new Properties();
        }
        populateProperties(this.properties);
        return this.properties;
    }


    /**
     * Retrive a Reference for this instance to store in JNDI
     *
     * @return the built Reference
     * @throws NamingException if error on building Reference
     */
    public Reference getReference() throws NamingException {
        return JNDIReferenceFactory.createReference(this.getClass().getName(), this);
    }

}

