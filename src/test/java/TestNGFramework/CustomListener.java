package TestNGFramework;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("La prueba " + result.getName() + " ha comenzado.");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("La prueba " + result.getName() + " ha pasado exitosamente.");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("La prueba " + result.getName() + " ha fallado.");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("La prueba " + result.getName() + " ha sido omitida.");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Este método se llama cuando una prueba falla pero está dentro del porcentaje de éxito configurado.
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Las pruebas han comenzado.");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Las pruebas han finalizado.");
    }
}
