package TestNGFramework;

import org.junit.Assert;
import org.testng.annotations.Test;
public class TestNGUsages {
    int i = 1;
    @Test(description = "Test numero 1", invocationCount = 3, successPercentage = 60)
    public void testUno() {
        boolean condition = true;
        i++;
        if (i % 2 != 0) {
            condition = false;
        }
        Assert.assertTrue(condition);
        System.out.println("Test finished OK");
    }
    @Test(description = "Test numero 2", dependsOnMethods = "testTres")
    public void testDos() {
        System.out.println("TEST DOS");
    }
    @Test(description = "Test numero 3", priority = 1)
    public void testTres() {
        System.out.println("TEST TRES");
    }
}
