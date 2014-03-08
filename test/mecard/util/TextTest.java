package mecard.util;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author andrew
 */
public class TextTest
{
    
    public TextTest()
    {
    }

    /**
     * Test of isValidExpiryDate method, of class MeCardPolicy.
     */
    @Test
    public void testProperCase()
    {
        System.out.println("==setProperCase==");
        // TODO Set up tests.
        String custData = "initial string";
        String result   = Text.toDisplayCase(custData);
        String expected = "Initial String";
        assertTrue(expected.compareTo(result) == 0);
        
        custData = "Initial String";
        result   = Text.toDisplayCase(custData);
        expected = "Initial String";
        assertTrue(expected.compareTo(result) == 0);
        
        custData = " initial String";
        result   = Text.toDisplayCase(custData);
        expected = " Initial String";
        assertTrue(expected.compareTo(result) == 0);
        
        custData = "";
        result   = Text.toDisplayCase(custData);
        expected = "";
        assertTrue(expected.compareTo(result) == 0);
        
        custData = "mark-antony";
        result   = Text.toDisplayCase(custData);
        expected = "Mark-Antony";
        assertTrue(expected.compareTo(result) == 0);
        
        custData = "ALL CAPS";
        result   = Text.toDisplayCase(custData);
        expected = "All Caps";
        assertTrue(expected.compareTo(result) == 0);
    }

    /**
     * Test of getNew4DigitPin method, of class Text.
     */
    @Test
    public void testGetNewPin()
    {
        System.out.println("===getNewPin===");
        System.out.println("PIN:'" + Text.getNew4DigitPin() + "'");
        System.out.println("PIN:'" + Text.getNew4DigitPin() + "'");
        System.out.println("PIN:'" + Text.getNew4DigitPin() + "'");
        System.out.println("PIN:'" + Text.getNew4DigitPin() + "'");
        System.out.println("PIN:'" + Text.getNew4DigitPin() + "'");
        System.out.println("PIN:'" + Text.getNew4DigitPin() + "'");
        System.out.println("PIN:'" + Text.getNew4DigitPin() + "'");
        System.out.println("PIN:'" + Text.getNew4DigitPin() + "'");
        System.out.println("PIN:'" + Text.getNew4DigitPin() + "'");
        System.out.println("PIN:'" + Text.getNew4DigitPin() + "'");
    }
    
    /**
     * Test of getNew4DigitPin method, of class Text.
     */
    @Test
    public void testIsMaxDigits()
    {
        System.out.println("===isMaxDigits===");
        String userPin = "1234";
        assertTrue(Text.isMaximumDigits(userPin, 4));
        
        userPin = "a234";
        assertFalse(Text.isMaximumDigits(userPin, 4));
        
        userPin = "";
        assertFalse(Text.isMaximumDigits(userPin, 4));
        
        userPin = "12345";
        assertFalse(Text.isMaximumDigits(userPin, 4));
                
        userPin = "X";
        assertFalse(Text.isMaximumDigits(userPin, 4));
        
        userPin = "12345";
        assertTrue(Text.isMaximumDigits(userPin, 5));
        
        userPin = "a";
        assertFalse(Text.isMaximumDigits(userPin, 0)); // Intersting corner case.
    }

    /**
     * Test of toDisplayCase method, of class Text.
     */
    @Test
    public void testToDisplayCase()
    {
        System.out.println("==toDisplayCase==");
        String s = "BOX 43 COUNTY OF LETHBRIDGE";
        String expResult = "Box 43 County Of Lethbridge";
        String result = Text.toDisplayCase(s);
        assertTrue(expResult.compareTo(result) == 0);
    }

    /**
     * Test of getNew4DigitPin method, of class Text.
     */
    @Test
    public void testGetNew4DigitPin()
    {
        System.out.println("==getNew4DigitPin==");
        String expResult = "";
        String result = Text.getNew4DigitPin();
        System.out.println("NEW_PIN:" + result);
        assertTrue(result.length() == 4);
    }

    /**
     * Test of longestMatch method, of class Text.
     */
    @Test
    public void testChoppingAndMatch()
    {
        System.out.println("==isMaximumDigits==");
        List<String> names = new ArrayList<>();
        names.add("County of Lakehead");
        names.add("of Lethbridge");
        names.add("Lethbridge");
        names.add("County of Lethbridge");
        

        String result = Text.chopLeft("BOX 43 COUNTY OF LETHBRIDGE");
        System.out.println("chop:'" + result + "'");
        assertTrue(result.compareTo("43 COUNTY OF LETHBRIDGE") == 0);
        result = Text.chopLeft(result);
        assertTrue(result.compareTo("COUNTY OF LETHBRIDGE") == 0);
        System.out.println("chop:'" + result + "'");
        result = Text.chopLeft(result);
        System.out.println("chop:'" + result + "'");
        assertTrue(result.compareTo("OF LETHBRIDGE") == 0);
        result = Text.chopLeft(result);
        System.out.println("chop:'" + result + "'");
        assertTrue(result.compareTo("LETHBRIDGE") == 0);
        result = Text.chopLeft(result);
        System.out.println("chop:'" + result + "'");
        assertTrue(result.compareTo("") == 0);
        result = Text.chopLeft(result);
        System.out.println("chop:'" + result + "'");
        assertTrue(result.compareTo("") == 0);
        result = Text.chopLeft(result);
        System.out.println("chop:'" + result + "'");
        assertTrue(result.compareTo("") == 0);
        
        System.out.println("LONGEST_MATCH:'" + Text.longestMatch(names, "BOX 43 COUNTY OF LETHBRIDGE") + "'");
        assertTrue(Text.longestMatch(names, "BOX 43 COUNTY OF LETHBRIDGE").compareTo("County of Lethbridge") == 0);
        System.out.println("LONGEST_MATCH:'" + Text.longestMatch(names, "BOX 43 LETHBRIDGE") + "'");
        assertTrue(Text.longestMatch(names, "BOX 43 LETHBRIDGE").compareTo("Lethbridge") == 0);
        System.out.println("LONGEST_MATCH:'" + Text.longestMatch(names, "BOX 43 COUNTY OF LETHBRIDGE, ALBERTA T1J 3Y3 403-555-1234") + "'");
        assertTrue(Text.longestMatch(names, "BOX 43 COUNTY OF LETHBRIDGE, ALBERTA T1J 3Y3 403-555-1234").compareTo("") == 0);
        System.out.println("LONGEST_MATCH:'" + Text.longestMatch(names, "BOX 43 LETHBRIDGE, ALBERTA T1J 3Y3 403-555-1234") + "'");
        assertTrue(Text.longestMatch(names, "BOX 43 LETHBRIDGE, ALBERTA T1J 3Y3 403-555-1234").compareTo("") == 0);
        
        result = "BOX 43 COUNTY OF LETHBRIDGE";
        String word = Text.lastWord(result);
        System.out.println("chopRight:'" + word + "'");
        assertTrue(Text.lastWord(result).compareTo(word) == 0);
        result = "BOX 43 COUNTY OF";
        word = Text.lastWord(result);
        System.out.println("chopRight:'" + word + "'");
        assertTrue(Text.lastWord(result).compareTo(word) == 0);
        result = "BOX 43 COUNTY ";
        word = Text.lastWord(result);
        System.out.println("chopRight:'" + word + "'");
        assertTrue(Text.lastWord(result).compareTo(word) == 0);
        result = "";
        word = Text.lastWord(result);
        System.out.println("chopRight:'" + word + "'");
        assertTrue(Text.lastWord(result).compareTo(word) == 0);
        
        
        
        result = "BOX 43 COUNTY OF LETHBRIDGE";
        System.out.println("chopOff:'" + Text.chopOff(result, "COUNTY OF LETHBRIDGE") + "'");
        assertTrue(Text.chopOff(result, "COUNTY OF LETHBRIDGE").compareTo("BOX 43") == 0);
        result = "BOX 43 COUNTY OF LETHBRIDGE";
        System.out.println("chopOff:'" + Text.chopOff(result, "LETHBRIDGE") + "'");
        assertTrue(Text.chopOff(result, "LETHBRIDGE").compareTo("BOX 43 COUNTY OF") == 0);
        result = "BOX 43 COUNTY OF LETHBRIDGE";
        System.out.println("chopOff:'" + Text.chopOff(result, "BOX 43 COUNTY OF LETHBRIDGE") + "' should be empty");
        assertTrue(Text.chopOff(result, "BOX 43 COUNTY OF LETHBRIDGE").compareTo("") == 0);
        result = "BOX 43 COUNTY OF LETHBRIDGE";
        System.out.println("chopOff:'" + Text.chopOff(result, "COUNTY OF Mexico") + "' should original");
        assertTrue(Text.chopOff(result, "COUNTY OF Mexico").compareTo("BOX 43 COUNTY OF LETHBRIDGE") == 0);
        result = "BOX 43 COUNTY OF LETHBRIDGE";
        System.out.println("chopOff:'" + Text.chopOff(result, "") + "' should be original sentence");
        assertTrue(Text.chopOff(result, "").compareTo("BOX 43 COUNTY OF LETHBRIDGE") == 0);
        result = "";
        System.out.println("chopOff:'" + Text.chopOff(result, "Something") + "' should be original sentence (empty)");
        assertTrue(Text.chopOff(result, "Something").compareTo("") == 0);
        System.out.println("chopOff:'" + Text.chopOff(result, "") + "' should be original sentence (empty)");
        assertTrue(Text.chopOff(result, "").compareTo("") == 0);
        
        
        
        result = "BOX 43 COUNTY OF LETHBRIDGE";
        System.out.println("chopOff:'" + Text.chopOff(result, "County Of Lethbridge") + "'");
        assertTrue(Text.chopOff(result, "County Of Lethbridge").compareTo("BOX 43") == 0);
        
        String r = Text.cleanTrailing(",", "this is one line");
        String e = "this is one line";
        assertTrue(r.compareTo(e) == 0);
        
        r = Text.cleanTrailing(",", "this is one line,");
        e = "this is one line";
        assertTrue(r.compareTo(e) == 0);
        
        r = Text.cleanTrailing(",", "this is one, line");
        e = "this is one, line";
        assertTrue(r.compareTo(e) == 0);
        
        r = Text.cleanTrailing(",", "this, is one, line,      ");
        e = "this, is one, line";
        assertTrue(r.compareTo(e) == 0);
    }
}