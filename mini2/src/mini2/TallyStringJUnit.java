package mini2;

import org.junit.Test;
import static org.junit.Assert.*;

public class TallyStringJUnit {

	@Test
	public void testMakeGroup() {
		assertEquals("37 tally group", "*******||", TallyString.makeGroup(37));
		assertEquals("0 tally group", "0", TallyString.makeGroup(0));
		assertEquals("5 tally group", "*", TallyString.makeGroup(5));
		assertEquals("4 tally group", "||||", TallyString.makeGroup(4));
		assertEquals("9 tally group", "*||||", TallyString.makeGroup(9));
		assertEquals("10 tally group", "**", TallyString.makeGroup(10));
		assertEquals("11 tally group", "**|", TallyString.makeGroup(11));
	}
	
	@Test
	public void testMakeNormalizedString() {
		assertEquals("37 tally", "||| *||", TallyString.makeNormalizedString(37));
		assertEquals("1641 tally", "| *| |||| |", TallyString.makeNormalizedString(1641));
		assertEquals("0 tally", "0", TallyString.makeNormalizedString(0));
		assertEquals("101 tally", "| 0 |", TallyString.makeNormalizedString(101));
		assertEquals("505 tally", "* 0 *", TallyString.makeNormalizedString(505));
		assertEquals("510 tally", "* | 0", TallyString.makeNormalizedString(510));
	}
	
	@Test
	public void testIsValidGroup() {
		assertFalse("Empty nonvalid group", TallyString.isValidGroup(""));
		assertTrue("||**|*| vaild group", TallyString.isValidGroup("||**|*|"));
		assertFalse("||* *|*| nonvalid group", TallyString.isValidGroup("||* *|*|"));
		assertFalse("**0 nonvalid group", TallyString.isValidGroup("**0"));
		assertFalse("||234 nonvalid group", TallyString.isValidGroup("||234"));
	}
	
	@Test
	public void testEvaluateGroup() {
		assertEquals("Invalid group", -1, TallyString.evaluateGroup("notvalid"));
		assertEquals("Space in group", -1, TallyString.evaluateGroup("| |"));
		assertEquals("Empty group", -1, TallyString.evaluateGroup(""));
		assertEquals("0 group", 0, TallyString.evaluateGroup("0"));
		assertEquals("1 group", 1, TallyString.evaluateGroup("|"));
		assertEquals("2 group", 2, TallyString.evaluateGroup("||"));
		assertEquals("Improper 5 group", 5, TallyString.evaluateGroup("|||||"));
		assertEquals("Proper 5 group", 5, TallyString.evaluateGroup("*"));
		assertEquals("9 group forward", 9, TallyString.evaluateGroup("*||||"));
		assertEquals("9 group backward", 9, TallyString.evaluateGroup("||||*"));
		assertEquals("9 group mixed", 9, TallyString.evaluateGroup("||*||"));
	}
	
	@Test
	public void testEvaluateString() {
		assertEquals("Invalid group", -1, TallyString.evaluateString("invalid group"));
		assertEquals("Empty string", -1, TallyString.evaluateString(""));
		assertEquals("Space string", -1, TallyString.evaluateString(" "));
		assertEquals("| string", 1, TallyString.evaluateString("|"));
		assertEquals("|| string", 2, TallyString.evaluateString("||"));
		assertEquals("* string", 5, TallyString.evaluateString("*"));
		assertEquals("*| string", 6, TallyString.evaluateString("*|"));
		assertEquals("|* string", 6, TallyString.evaluateString("|*"));
		assertEquals("** string", 10, TallyString.evaluateString("**"));
		assertEquals("| | string", 11, TallyString.evaluateString("| |"));
		assertEquals("|| 0 string", 20, TallyString.evaluateString("|| 0"));
		assertEquals("0 string", 0, TallyString.evaluateString("0"));
		assertEquals("0 0 string", 0, TallyString.evaluateString("0 0"));
		assertEquals("00 string", -1, TallyString.evaluateString("00"));
		assertEquals("*|** || |**** string", 1641, TallyString.evaluateString("*|** || |****"));
		assertEquals(" 0 0 | 0 ************|||| | ", 1641, TallyString.evaluateString(" 0 0 | 0 ************|||| | "));
	}
}
