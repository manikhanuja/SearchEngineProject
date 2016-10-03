/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchengine;

import java.util.ArrayList;
import java.util.Arrays;
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
public class NormalizeTokenTest {
    
    public NormalizeTokenTest() {
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
     * Test of normalizeToken method, of class NormalizeToken.
     */
    @Test
    public void testNormalizeToken_removeNonAlphanumericCharsFromBeginAndEnd() {
        System.out.println("remove non-alphanumeric chars from beginning and end");
        String token = "@!##hopeful###!@%";
        ArrayList<String> expResult = new ArrayList<String>();
        expResult.add("hopeful");
        ArrayList result = NormalizeToken.normalizeToken(token);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void testNormalizeToken_removeHypen() {
        System.out.println("remove hypen from word and return all three posibilities for example: co-ordinator will return co, ordinator and coordinator");
        String token = "co-ordinator";
        ArrayList<String> expResult = new ArrayList<String>();
        expResult.add("co");
        expResult.add("ordinator");
        expResult.add("coordinator");
        ArrayList result = NormalizeToken.normalizeToken(token);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void testNormalizeToken_removeApostropes() {
        System.out.println("remove apostropes from word for example: stemmer's will return stemmers");
        String token = "stemmer's";
        ArrayList<String> expResult = new ArrayList<String>();
        expResult.add("stemmers");
        ArrayList result = NormalizeToken.normalizeToken(token);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
