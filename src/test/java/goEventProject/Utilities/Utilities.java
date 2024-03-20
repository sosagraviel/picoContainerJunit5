package goEventProject.Utilities;

import goEventProject.Pages.BasePage;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.util.Units;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.*;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Utilities extends BasePage {
	public static final String WINDOW_OPEN = "window.open()";
	public static final String SCROLL_INTO_VIEW = "arguments[0].scrollIntoView();";
	public static final String SCROLL_TO_DIR_START = "window.scrollBy(0,";
	public static final String SCROLL_TO_DIR_END = ")";
	public Utilities(WebDriver remoteDriver){
		driver = remoteDriver;
		initElements(driver, this);

	}
	public Utilities(){

	}
	/*
	 * This method is used tu initialize the properties from config_file
	 * */
	public Properties init_prop() throws IOException {
		FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");

		prop = new Properties();
		prop.load(ip);
		return prop;

	}
	public String getPropertyByValue(Properties properties, String aValue){
		return properties.getProperty(aValue);

	}
	public JavascriptExecutor createJse() {
		return (JavascriptExecutor)driver;

	}
	public void scrollToADirection(String aDirection) {
		JavascriptExecutor jse = createJse();
		jse.executeScript(SCROLL_TO_DIR_START + aDirection + SCROLL_TO_DIR_END);

	}
	public void scrollToADirectionPixel() {
		JavascriptExecutor jse = createJse();
		jse.executeScript("window.scrollBy(0,12500)");

	}
	public void scrollIntoView(WebElement anElement) {
		JavascriptExecutor jse = createJse();
		jse.executeScript(SCROLL_INTO_VIEW, anElement);

	}
	public void getTabs(int aTab){
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(aTab));

	}
	public Alert getSwitchToAlert() {
		return driver.switchTo().alert();

	}
	public void acceptOnOkPrompt() throws InterruptedException {
		getSwitchToAlert().accept();
		Thread.sleep(1000);

	}
	public String AlertGetText() {
		String AlertText = getSwitchToAlert().getText();
		return AlertText;

	}
	public static void createDocxFile(WebDriver driver, String nombreArchivo, String rutaImagen) throws IOException, InvalidFormatException {
		File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File imageFile = new File(rutaImagen);
		FileUtils.copyFile(screen, imageFile);
		
		File fichero = new File(nombreArchivo); 
		 XWPFDocument docx;
		 if (!fichero.exists()) {
			 docx = new XWPFDocument();
			 
		 } else {
			 FileInputStream ficheroStream = new FileInputStream(fichero);
			 docx = new XWPFDocument(ficheroStream);
			 
		 }
		 XWPFParagraph paragraph = docx.createParagraph();
		 XWPFRun run = paragraph.createRun();
		 
		 run.setText("Evidencia de pruebas");
		 run.setFontSize(13);
		 
		 InputStream pic = new FileInputStream(rutaImagen);
		 run.addPicture(pic, Document.PICTURE_TYPE_PNG, rutaImagen,
		 Units.toEMU(500), Units.toEMU(200));
		 pic.close();

		 FileOutputStream out = new FileOutputStream(nombreArchivo);
		 docx.write(out);
		 out.flush();
		 out.close();
		 docx.close();
 
    }
	public static void takeScreenshot(WebDriver driver, String imgPath) throws IOException, InvalidFormatException {
		File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen, new File (imgPath));
		
	}
	public static Object[][] readFromExcelFile(String ruta, String nombreHoja) throws Exception {
		FileInputStream file = new FileInputStream(new File(ruta));
		//instancio archivo excel desde el archivo situado en la ruta indicada
		XSSFWorkbook excelFile = new XSSFWorkbook(file);
		//instancio una hoja en base al archivo excel y asignando el nombre de la hoja
		XSSFSheet sheet = excelFile.getSheet(nombreHoja);
		
		//instanciamos una fila
		XSSFRow row;
		
		//tomamos el numero total de filas
		int rows = sheet.getPhysicalNumberOfRows();
		//instanciamos las columnas
		int column = sheet.getRow(0).getPhysicalNumberOfCells();
		
		//instanciamos el objeto bidimensional que nos va a devolver esta funcion
		Object cellValue[][]=new Object[rows][column];
		
		for (int r = 0; r < rows; r++) {
			row = sheet.getRow(r);

			if (row == null){ 
				break; 
			}else{ 
				for (int c = 0; c < column; c++) {
					DataFormatter dataFormatter = new DataFormatter();
					cellValue[r][c] = dataFormatter.formatCellValue(sheet.getRow(r).getCell(c));
					
				} 
				
			}
			
	   }
	   excelFile.close();
	   return cellValue; 
	
    }
	public static void downloadFile(String wget_command) {
        try {
        @SuppressWarnings("deprecation")
			Process exec = Runtime.getRuntime().exec(wget_command);
        	int exitVal = exec.waitFor();
        	System.out.println("Exit value: " + exitVal);
        
        } catch (Exception ex) {
        	System.out.println(ex.toString());
        
        }
	}
	public static void devToolsCreateSession(WebDriver driver ) {
		DevTools devTools = ((HasDevTools) driver).getDevTools();
		devTools.createSession();

	}
	public static void setMobileMetrics(WebDriver driver, Object width, Object height) {
		Map<String, Object> deviceMetrics = new HashMap<>();
		deviceMetrics.put("width", width);
		deviceMetrics.put("height", height);
		deviceMetrics.put("mobile", true);
		deviceMetrics.put("deviceScaleFactor", 50);

		((ChromiumDriver) driver).executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);
	}
}
