/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.lang;

import junit.framework.TestCase;

import java.util.Calendar;
import java.util.Date;
import java.sql.Timestamp;

/**
 * @author Rhett Sutphin
 */
public class StaticNowFactoryTest extends TestCase {
    private static final Timestamp NOW = DateTools.createTimestamp(2005, Calendar.MAY, 1);
    private StaticNowFactory nowFactory;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        nowFactory = new StaticNowFactory();
        nowFactory.setNowTimestamp(NOW);
    }

    public void testNowAsDateReflectsSetTimestamp() throws Exception {
        Date actual = nowFactory.getNow();
        assertEquals(NOW.getTime(), actual.getTime());
    }
}
