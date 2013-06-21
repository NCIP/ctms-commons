/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.testing;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;

/**
 * @author Rhett Sutphin
 */
public class TestObject extends AbstractMutableDomainObject {
    public TestObject() { }

    public TestObject(int id) { setId(id); }

    public TestObject(int id, String gridId) { setId(id); setGridId(gridId); }

    public static class MockableDao implements MutableDomainObjectDao<TestObject> {
        public Class<TestObject> domainClass() {
            return TestObject.class;
        }

        public TestObject getById(int id) {
            throw new UnsupportedOperationException("getById not implemented");
        }

        public TestObject getByGridId(String gridId) {
            throw new UnsupportedOperationException("getByGridId not implemented");
        }

        public void save(TestObject obj) {
            throw new UnsupportedOperationException("save not implemented");
        }
    }
}
