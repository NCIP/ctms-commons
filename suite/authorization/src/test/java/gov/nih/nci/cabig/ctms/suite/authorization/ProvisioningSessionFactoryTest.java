/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.suite.authorization;

import junit.framework.TestCase;
import gov.nih.nci.cabig.ctms.suite.authorization.domain.TestSiteMapping;
import gov.nih.nci.cabig.ctms.suite.authorization.domain.TestStudyMapping;

/**
 * @author Rhett Sutphin
 */
public class ProvisioningSessionFactoryTest extends TestCase {
    private ProvisioningSessionFactory factory;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        factory = new ProvisioningSessionFactory();
        factory.setSiteMapping(new TestSiteMapping());
        factory.setStudyMapping(new TestStudyMapping());
    }

    public void testCreateRoleMembership() throws Exception {
        SuiteRoleMembership aRoleMembership = factory.createSuiteRoleMembership(SuiteRole.DATA_IMPORTER);
        assertNotNull(aRoleMembership);
        assertEquals("Wrong role", SuiteRole.DATA_IMPORTER, aRoleMembership.getRole());
        assertNotNull("Site mapping not passed along", aRoleMembership.getMapping(ScopeType.SITE));
        assertNotNull("Study mapping not passed along", aRoleMembership.getMapping(ScopeType.STUDY));
    }
}
