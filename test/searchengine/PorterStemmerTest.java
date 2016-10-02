/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchengine;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author manikhanuja
 */
public class PorterStemmerTest {
    
    public PorterStemmerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of processToken method, of class PorterStemmer.
     */
    @Test
    public void testProcessToken_step1a_1() {
        System.out.println("processToken: SSES->SS example caresses->caress:");
        String token = "caresses";
        String expResult = "caress";
        String result = PorterStemmer.processToken(token);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    @Test
    public void testProcessToken_step1a_2() {
        System.out.println("processToken: 1a.b ends with ies --> i: ponies --> poni");
        String token = "ponies";
        String expResult = "poni";
        String result = PorterStemmer.processToken(token);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    @Test
    public void testProcessToken_step1c_1() {
        System.out.println("processToken: ends with y: happy --> happi");
        String token = "happy";
        String expResult = "happi";
        String result = PorterStemmer.processToken(token);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    @Test
    public void testProcessToken_step1a_3() {
        System.out.println("processToken: step 1a.3 - token ends with s and not ss: cats --> cat");
        String token = "cats";
        String expResult = "cat";
        String result = PorterStemmer.processToken(token);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
    @Test
    public void testProcessToken_step1a_c() {
        System.out.println("processToken: SS->SS: caress --> caress");
        String token = "caress";
        String expResult = "caress";
        String result = PorterStemmer.processToken(token);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    @Test
    public void testProcessToken_step1b_1() {
        System.out.println("processToken: ends with eed --> e: agreed --> agree");
        String token = "agreed";
        String expResult = "agree";
        String result = PorterStemmer.processToken(token);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
    @Test
    public void testProcessToken_step1b_2() {
        System.out.println("processToken: ends with ed: plastered --> plaster");
        String token = "plastered";
        String expResult = "plaster";
        String result = PorterStemmer.processToken(token);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
    @Test
    public void testProcessToken_step1b_3() {
        System.out.println("processToken: ends with ing: motoring --> motor");
        String token = "motoring";
        String expResult = "motor";
        String result = PorterStemmer.processToken(token);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
    @Test
    public void testProcessToken_step1b_3b() {
        System.out.println("processToken: ends with ing: sing --> sing");
        String token = "sing";
        String expResult = "sing";
        String result = PorterStemmer.processToken(token);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
    @Test
    public void testProcessToken_step1b_4a() {
        System.out.println("If the second or third of the rules in Step 1b");
        System.out.println("processToken: ends with at-->ate: conflat(ed) --> conflate");
        String token = "conflated";
        String expResult = "conflat";
        String result = PorterStemmer.processToken(token);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
    @Test
    public void testProcessToken_step1b_4b() {
        System.out.println("If the second or third of the rules in Step 1b");
        System.out.println("processToken: ends with bl-->ble: troubl(ed) --> trouble");
        String token = "troubled";
        String expResult = "trouble";
        String result = PorterStemmer.processToken(token);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
    @Test
    public void testProcessToken_step1b_4c() {
        System.out.println("If the second or third of the rules in Step 1b");
        System.out.println("processToken: ends with iz-->ize: siz(ed) --> size");
        String token = "sized";
        String expResult = "size";
        String result = PorterStemmer.processToken(token);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of helperMatrixStep method, of class PorterStemmer.
     */
   /* @Test
    public void testHelperMatrixStep() {
        System.out.println("helperMatrixStep");
        String token = "";
        String[][] steppairs = null;
        PorterStemmer.stepName step = null;
        String expResult = "";
        String result = PorterStemmer.helperMatrixStep(token, steppairs, step);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    
}
