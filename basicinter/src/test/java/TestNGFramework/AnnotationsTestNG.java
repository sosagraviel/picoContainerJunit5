package TestNGFramework;

import org.testng.annotations.*;
public class AnnotationsTestNG {
    @BeforeSuite
    public void beforeSuite() {
        System.out.println("BEFORE SUITE");
    }
    @BeforeTest
    public void beforeTest() {
        System.out.println("BEFORE TEST");
    }
    @BeforeClass
    public void beforeClass() {
        System.out.println("BEFORE CLASS");
    }
    int beforeMethodI = 0;
    @BeforeMethod
    public void beforeMethod() {
        beforeMethodI++;
        System.out.println("BEFORE METHOD " + beforeMethodI);
    }
    @Test()
    public void testUno() {
        System.out.println("TEST UNO");
    }
    @Test()
    public void testDos() {
        System.out.println("TEST DOS");
    }
    @Test()
    public void testTres() {
        System.out.println("TEST TRES");
    }
    int afterMethodI = 0;
    @AfterMethod
    public void afterMethod() {
        afterMethodI++;
        System.out.println("AFTER METHOD " + afterMethodI);
    }
    @AfterClass
    public void afterClass() {
        System.out.println("AFTER CLASS");
    }
    @AfterTest
    public void afterTest() {
        System.out.println("AFTER TEST");
    }
    @AfterSuite
    public void afterSuite() {
        System.out.println("AFTER SUITE");
    }
}
