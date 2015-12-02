/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import facades.UrlFacade;
import javax.persistence.Persistence;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public class UrlFacadeUnitTest {

    private static UrlFacade urlFacade;

    @BeforeClass
    public static void setUpClass() throws Exception {

        urlFacade = new UrlFacade(Persistence.createEntityManagerFactory("PU-Local"));

    }

    @Test
    public void testAddAndGetSingleUrl() {

        String expected = "http://testUrl/";
        int id = urlFacade.addUrl(expected);
        String result = urlFacade.getUrl(id);
        assertEquals(expected, result);

    }

    @Test
    public void testAddAndGetAnotherSingleUrl() {
        String expected = "http://anotherUrl";
        int id = urlFacade.addUrl(expected);
        String result = urlFacade.getUrl(id);
        assertEquals(expected, result);
    }

    @Test
    public void testAddFromOneFacadeAndGetFromAnotherFacade() {
        UrlFacade urlFacade1 = new UrlFacade(Persistence.createEntityManagerFactory("PU-Local"));
        UrlFacade urlFacade2 = new UrlFacade(Persistence.createEntityManagerFactory("PU-Local"));
        
        String expected = "http://anotherUrl";
        int id = urlFacade1.addUrl(expected);
        String result = urlFacade2.getUrl(id);
        assertEquals(expected, result);
        
    }
    
    @Test
    public void testAddDeleteGetUrl() {
        String expected = "http://deleteUrl";
        int id = urlFacade.addUrl(expected);
        urlFacade.deleteUrl(id);
        String result = urlFacade.getUrl(id);
        assertNull(result);
    }
}
