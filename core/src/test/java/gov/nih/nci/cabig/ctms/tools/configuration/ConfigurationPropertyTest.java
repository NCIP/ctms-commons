/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.tools.configuration;

import gov.nih.nci.cabig.ctms.testing.CommonsCoreTestCase;

/**
 * @author Rhett Sutphin
 */
public class ConfigurationPropertyTest extends CommonsCoreTestCase {
    public void testGetName() throws Exception {
        assertEquals("Outgoing mail (SMTP) server name", ExampleConfiguration.SMTP_HOST.getName());
    }
    
    public void testGetDescription() throws Exception {
        assertEquals("The port on which to communicate with the SMTP server",
            ExampleConfiguration.SMTP_PORT.getDescription());
    }
    
    public void testClonesEqual() throws Exception {
        assertEquals(ExampleConfiguration.ADDRESSES, ExampleConfiguration.ADDRESSES.clone());
    }
}
