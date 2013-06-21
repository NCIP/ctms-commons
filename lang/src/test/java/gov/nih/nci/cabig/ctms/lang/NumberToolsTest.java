/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.lang;

import junit.framework.TestCase;

/**
 * @author Rhett Sutphin
 */
public class NumberToolsTest extends TestCase {
    public void testCountDigits() {
        assertEquals(1, NumberTools.countDigits(0));
        assertEquals(1, NumberTools.countDigits(5));
        assertEquals(1, NumberTools.countDigits(9));
        assertEquals(2, NumberTools.countDigits(10));
        assertEquals(2, NumberTools.countDigits(11));
        assertEquals(2, NumberTools.countDigits(99));
        assertEquals(3, NumberTools.countDigits(100));
        assertEquals(3, NumberTools.countDigits(101));
    }
}
