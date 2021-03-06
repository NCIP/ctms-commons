/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.suite.authorization;

import gov.nih.nci.cabig.ctms.suite.authorization.domain.TestSiteMapping;
import gov.nih.nci.cabig.ctms.suite.authorization.domain.TestStudy;
import gov.nih.nci.cabig.ctms.suite.authorization.domain.TestStudyMapping;
import gov.nih.nci.security.authorization.domainobjects.Group;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElement;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroup;
import gov.nih.nci.security.authorization.domainobjects.Role;
import gov.nih.nci.security.exceptions.CSObjectNotFoundException;

import java.util.Set;

import static gov.nih.nci.cabig.ctms.suite.authorization.CsmIntegratedTestHelper.*;

/**
 * @author Rhett Sutphin
 */
public class CsmHelperIntegratedTest extends IntegratedTestCase {
    private CsmHelper csmHelper;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        csmHelper = new CsmHelper();
        csmHelper.setAuthorizationManager(getAuthorizationManager());
        csmHelper.setSiteMapping(new TestSiteMapping());
        csmHelper.setStudyMapping(new TestStudyMapping());
    }

    public void testGetExistingProtectionElementForScopeDescription() throws Exception {
        ProtectionElement actual = csmHelper.getOrCreateScopeProtectionElement(
            ScopeDescription.createForOne(ScopeType.STUDY, "CRM114"));
        assertNotNull("This method should never return null", actual);
        assertEquals("Returned the wrong PE", "Study.CRM114", actual.getProtectionElementName());
        assertEquals("Did not return the existing instance", -1L, (long) actual.getProtectionElementId());
    }

    public void testGetExistingProtectionElementForScopeAndObject() throws Exception {
        ProtectionElement actual = csmHelper.getOrCreateScopeProtectionElement(
            ScopeType.STUDY, new TestStudy("CRM114"));
        assertNotNull("This method should never return null", actual);
        assertEquals("Returned the wrong PE", "Study.CRM114", actual.getProtectionElementName());
        assertEquals("Did not return the existing instance", -1L, (long) actual.getProtectionElementId());
    }

    public void testGetNewProtectionElement() throws Exception {
        ProtectionElement actual = csmHelper.getOrCreateScopeProtectionElement(
            ScopeDescription.createForOne(ScopeType.SITE, "60"));
        assertNotNull("This method should never return null", actual);
        assertEquals("Returned the wrong PE", "HealthcareSite.60", actual.getProtectionElementName());
        assertNotNull("Did not return an instance with an ID", actual.getProtectionElementId());
    }

    public void testGetNewProtectionElementCreatesTheProtectionGroup() throws Exception {
        ProtectionElement actual = csmHelper.getOrCreateScopeProtectionElement(
            ScopeDescription.createForOne(ScopeType.SITE, "IL034"));
        assertNotNull("This method should never return null", actual);

        try {
            ProtectionGroup parallel = getAuthorizationDao().getProtectionGroup("HealthcareSite.IL034");
            assertNotNull("Protection group not created", parallel);
        } catch (CSObjectNotFoundException e) {
            fail("Protection group not created");
        }
    }

    public void testGetNewProtectionElementCreatesTheLinkBetweenTheNewPEAndTheNewPG() throws Exception {
        ProtectionElement actual = csmHelper.getOrCreateScopeProtectionElement(
            ScopeDescription.createForOne(ScopeType.SITE, "IL034"));
        assertNotNull("This method should never return null", actual);

        Set actualPgs = getAuthorizationDao().getProtectionGroups(actual.getProtectionElementId().toString());
        assertEquals("Wrong number of associated PGs: " + actualPgs, 1, actualPgs.size());
        assertEquals("Wrong associated PG", "HealthcareSite.IL034",
            ((ProtectionGroup) actualPgs.iterator().next()).getProtectionGroupName());
    }

    public void testGetExistingProtectionElementAddsAMissingProtectionGroup() throws Exception {
        try {
            ProtectionGroup pg = getAuthorizationDao().getProtectionGroup("HealthcareSite.THX11");
            fail("Test setup failure.  PG already exists: " + pg);
        } catch (CSObjectNotFoundException e) {
            // expected
        }

        csmHelper.getOrCreateScopeProtectionElement(
            ScopeDescription.createForOne(ScopeType.SITE, "THX11"));

        try {
            ProtectionGroup pg = getAuthorizationDao().getProtectionGroup("HealthcareSite.THX11");
            assertNotNull("PG not created", pg);
        } catch (CSObjectNotFoundException e) {
            fail("PG not created: " + e.getMessage());
        }
    }

    public void testGetExistingProtectionGroupForScopeDescription() throws Exception {
        ProtectionGroup actual = csmHelper.getOrCreateScopeProtectionGroup(
            ScopeDescription.createForOne(ScopeType.STUDY, "CRM114"));
        assertNotNull("This method should never return null", actual);
        assertEquals("Returned the wrong PG", "Study.CRM114", actual.getProtectionGroupName());
        assertEquals("Did not return the existing instance", -1L, (long) actual.getProtectionGroupId());
    }

    public void testGetExistingProtectionGroupForScopeAndObject() throws Exception {
        ProtectionGroup actual = csmHelper.getOrCreateScopeProtectionGroup(
            ScopeType.STUDY, new TestStudy("CRM114"));
        assertNotNull("This method should never return null", actual);
        assertEquals("Returned the wrong PG", "Study.CRM114", actual.getProtectionGroupName());
        assertEquals("Did not return the existing instance", -1L, (long) actual.getProtectionGroupId());
    }

    public void testGetNewProtectionGroup() throws Exception {
        ProtectionElement actual = csmHelper.getOrCreateScopeProtectionElement(
            ScopeDescription.createForOne(ScopeType.STUDY, "E45"));
        assertNotNull("This method should never return null", actual);
        assertEquals("Returned the wrong PG", "Study.E45", actual.getProtectionElementName());
        assertNotNull("Did not return an instance with an ID", actual.getProtectionElementId());
    }

    public void testGetNewProtectionGroupCreatesTheProtectionElement() throws Exception {
        ProtectionGroup actual = csmHelper.getOrCreateScopeProtectionGroup(
            ScopeDescription.createForOne(ScopeType.SITE, "IL034"));
        assertNotNull("This method should never return null", actual);

        try {
            ProtectionElement parallel = getAuthorizationManager().getProtectionElement("HealthcareSite.IL034");
            assertNotNull("Protection element not created", parallel);
        } catch (CSObjectNotFoundException e) {
            fail("Protection element not created");
        }
    }

    public void testGetNewProtectionGroupCreatesTheLinkBetweenTheNewPEAndTheNewPG() throws Exception {
        ProtectionGroup actual = csmHelper.getOrCreateScopeProtectionGroup(
            ScopeDescription.createForOne(ScopeType.SITE, "IL034"));
        assertNotNull("This method should never return null", actual);

        Set actualPes = getAuthorizationDao().getProtectionElements(actual.getProtectionGroupId().toString());
        assertEquals("Wrong number of associated PEs: " + actualPes, 1, actualPes.size());
        assertEquals("Wrong associated PE", "HealthcareSite.IL034",
            ((ProtectionElement) actualPes.iterator().next()).getProtectionElementName());
    }

    public void testGetExistingProtectionGroupAddsAMissingProtectionElement() throws Exception {
        try {
            ProtectionElement pe = getAuthorizationDao().getProtectionElement("HealthcareSite.PR39");
            fail("Test setup failure.  PE already exists: " + pe);
        } catch (CSObjectNotFoundException e) {
            // expected
        }

        csmHelper.getOrCreateScopeProtectionElement(
            ScopeDescription.createForOne(ScopeType.SITE, "PR39"));

        try {
            ProtectionElement pe = getAuthorizationDao().getProtectionElement("HealthcareSite.PR39");
            assertNotNull("PE not created", pe);
        } catch (CSObjectNotFoundException e) {
            fail("PE not created: " + e.getMessage());
        }
    }

    public void testGetSuiteRoleCsmGroup() throws Exception {
        Group actual = csmHelper.getRoleCsmGroup(SuiteRole.STUDY_SUBJECT_CALENDAR_MANAGER);
        assertNotNull("This method should never return null", actual);
        assertEquals("Wrong group returned", "study_subject_calendar_manager", actual.getGroupName());
    }

    public void testAllSuiteRolesAreAccessibleAsCsmGroups() throws Exception {
        for (SuiteRole role : SuiteRole.values()) {
            csmHelper.getRoleCsmGroup(role);
        }
        // It's sufficient that there aren't any exceptions
    }

    public void testGetSuiteRoleCsmRole() throws Exception {
        Role actual = csmHelper.getRoleCsmRole(SuiteRole.STUDY_SUBJECT_CALENDAR_MANAGER);
        assertNotNull("This method should never return null", actual);
        assertEquals("Wrong object returned", "study_subject_calendar_manager", actual.getName());
    }

    public void testAllSuiteRolesAreAccessibleAsCsmRoles() throws Exception {
        for (SuiteRole role : SuiteRole.values()) {
            csmHelper.getRoleCsmRole(role);
        }
        // It's sufficient that there aren't any exceptions
    }

    public void testAttemptingToUseAnUnavailableMappingGivesAHelpfulErrorMessage() throws Exception {
        csmHelper.setStudyMapping(null);
        try {
            csmHelper.getOrCreateScopeProtectionGroup(ScopeType.STUDY, new TestStudy("Y"));
            fail("Exception not thrown");
        } catch (SuiteAuthorizationProvisioningFailure e) {
            assertEquals("Wrong message",
                "No study mapping was provided.  Either provide one or stick to the identifier-based methods.",
                e.getMessage());
        }
    }

    public void testRenameScopePairRenamesExistingProtectionElement() throws Exception {
        csmHelper.renameScopePair(ScopeType.STUDY, "CRM114", "CRM006");

        try {
            ProtectionElement pe = getAuthorizationDao().getProtectionElement("Study.CRM006");
            assertNotNull("PE with new object ID not found", pe);
            assertEquals("PE with new object ID isn't the original",
                new Long(-1L), pe.getProtectionElementId());
            assertEquals("PE with new object ID does not have updated name",
                "Study.CRM006", pe.getProtectionElementName());
        } catch (CSObjectNotFoundException e) {
            fail("PE not found under new name: " + e);
        }

        assertProtectionElementNotPresent("PE with old name still present", "Study.CRM114");
    }

    public void testRenameScopePairRenamesExistingProtectionGroup() throws Exception {
        csmHelper.renameScopePair(ScopeType.SITE, "MI001", "MI002");

        try {
            ProtectionGroup pg = getAuthorizationDao().getProtectionGroup("HealthcareSite.MI002");
            assertNotNull("PG with new name not found", pg);
            assertEquals("PG with new name isn't the original", 
                new Long(-2L), pg.getProtectionGroupId());
        } catch (CSObjectNotFoundException e) {
            fail("PG not found under new name: " + e);
        }

        assertProtectionGroupNotPresent("PG with old name still present", "HealthcareSite.MI001");
    }

    public void testRenameScopePairDoesNothingForNonExistentPair() throws Exception {
        csmHelper.renameScopePair(ScopeType.SITE, "IL036", "IL037");

        assertProtectionElementNotPresent("PE with old name created", "HealthcareSite.IL036");
        assertProtectionGroupNotPresent("PG with old name created", "HealthcareSite.IL036");

        assertProtectionElementNotPresent("PE with new name created", "HealthcareSite.IL037");
        assertProtectionGroupNotPresent("PG with new name created", "HealthcareSite.IL037");
    }

    private void assertProtectionElementNotPresent(String message, String name) {
        try {
            getAuthorizationDao().getProtectionElement(name);
            fail(message);
        } catch (CSObjectNotFoundException e) {
            // expected
        }
    }

    private void assertProtectionGroupNotPresent(String message, String name) {
        try {
            getAuthorizationDao().getProtectionGroup(name);
            fail(message);
        } catch (CSObjectNotFoundException e) {
            // expected
        }
    }
}
