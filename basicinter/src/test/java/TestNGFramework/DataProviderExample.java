package TestNGFramework;

import org.testng.annotations.DataProvider;

import Utilities.Utilities;

import java.net.URL;
import java.io.File;

public class DataProviderExample {
    @DataProvider(name="usuariosInvalidos")
    public Object[][] usuariosInvalidos(){
        //email------------------password
        return new Object[][]{
                {"invalidEmail@email.com", "pasdsda"},
                {"mailmail.com", "asdpasdsda"},
                {"invalidEmail@", "pasdsda"}

        };
    }
    @DataProvider(name="usuariosInvalidosExcel")
    public Object[][] usuariosInvalidosExcel() throws Exception{
        URL resource = getClass().getResource("/data/inicioSesionInvalido.xlsx");
        if (resource == null) {
            throw new IllegalArgumentException("File not found!");
        }
        File file = new File(resource.getFile());

        // Now pass the file path to your method
        Object[][] hoja1s = Utilities.readFromExcelFile(file.getAbsolutePath(), "Hoja1");

        return hoja1s;

    }

}
