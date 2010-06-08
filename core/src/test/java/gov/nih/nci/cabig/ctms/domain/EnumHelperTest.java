package gov.nih.nci.cabig.ctms.domain;

import gov.nih.nci.cabig.ctms.testing.CommonsCoreTestCase;

/**
 * @author Rhett Sutphin
 */
public class EnumHelperTest extends CommonsCoreTestCase {
    public void testSentenceCasedName() throws Exception {
        assertEquals("Grizzly", EnumHelper.sentenceCasedName(SampleCodedEnum.GRIZZLY));
        assertEquals("Human skull", EnumHelper.sentenceCasedName(SampleCodedEnum.HUMAN_SKULL));
    }

    public void testTitleCasedName() throws Exception {
        assertEquals("Grizzly", EnumHelper.titleCasedName(SampleCodedEnum.GRIZZLY));
        assertEquals("Human Skull", EnumHelper.titleCasedName(SampleCodedEnum.HUMAN_SKULL));
    }
}
