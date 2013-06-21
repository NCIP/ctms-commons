/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.suite.authorization.domain;

import gov.nih.nci.cabig.ctms.suite.authorization.SiteMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rhett Sutphin
 */
public class TestSiteMapping implements SiteMapping<TestSite> {
    public String getSharedIdentity(TestSite site) {
        return site.getIdent();
    }

    public List<TestSite> getApplicationInstances(List<String> identities) {
        List<TestSite> sites = new ArrayList<TestSite>(identities.size());
        for (String identity : identities) {
            sites.add(new TestSite(identity));
        }
        return sites;
    }

    public boolean isInstance(Object o) {
        return o instanceof TestSite;
    }
}
