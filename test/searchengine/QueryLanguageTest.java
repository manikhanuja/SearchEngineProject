/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchengine;

import java.util.List;
import java.util.Set;
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
public class QueryLanguageTest {
    
    public QueryLanguageTest() {
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
     * Test of readQueryFromUser method, of class QueryLanguage.
     */
    @Test
    public void testReadQueryFromUser_NaiveInvertedIndex_List() {
        System.out.println("readQueryFromUser");
        NaiveInvertedIndex index = null;
        List docId = null;
        QueryLanguage.readQueryFromUser(index, docId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of queryParser method, of class QueryLanguage.
     */
    @Test
    public void testQueryParser() {
        System.out.println("queryParser");
        NaiveInvertedIndex index = null;
        String query = "";
        List docId = null;
        QueryLanguage.queryParser(index, query, docId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readQueryFromUser method, of class QueryLanguage.
     */
    @Test
    public void testReadQueryFromUser_0args() {
        System.out.println("readQueryFromUser");
        String expResult = "";
        String result = QueryLanguage.readQueryFromUser();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of wordQuery method, of class QueryLanguage.
     */
    @Test
    public void testWordQuery() {
        System.out.println("wordQuery");
        NaiveInvertedIndex index = null;
        String word = "";
        Set expResult = null;
        Set result = QueryLanguage.wordQuery(index, word);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of notWordQuery method, of class QueryLanguage.
     */
    @Test
    public void testNotWordQuery() {
        System.out.println("notWordQuery");
        NaiveInvertedIndex index = null;
        String word = "";
        List docId = null;
        List expResult = null;
        List result = QueryLanguage.notWordQuery(index, word, docId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of phraseWordQuery method, of class QueryLanguage.
     */
    @Test
    public void testPhraseWordQuery() {
        System.out.println("phraseWordQuery");
        NaiveInvertedIndex index = null;
        String query = "";
        Set expResult = null;
        Set result = QueryLanguage.phraseWordQuery(index, query);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of callStemmer method, of class QueryLanguage.
     */
    @Test
    public void testCallStemmer() {
        System.out.println("callStemmer");
        NaiveInvertedIndex index = null;
        List docId = null;
        QueryLanguage.callStemmer(index, docId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVocabulary method, of class QueryLanguage.
     */
    @Test
    public void testGetVocabulary() {
        System.out.println("getVocabulary");
        NaiveInvertedIndex index = null;
        QueryLanguage.getVocabulary(index);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
