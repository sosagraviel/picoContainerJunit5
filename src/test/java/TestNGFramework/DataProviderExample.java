package TestNGFramework;

import org.testng.annotations.DataProvider;

import Utilities.Utilities;

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
        return Utilities.readFromExcelFile("D:\\IdeaProjectsGlobal\\SeleniumJavaWorkshop\\src\\test\\resources\\inicioSesionInvalido.xlsx", "Hoja1");
    }

}
