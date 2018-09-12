package seleniumaltan;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import herramientas.Word;

public class AltanThreshold {
	private By btnSuscriber = By
			.xpath("//div[@style='background-image: url(\"theme/default/images/usm/siteMap24.png\");']");
	private By btnOffering = By.xpath("//a[@subcategoryid='c_902001001']");
	private By frameOffering = By.xpath("//div[@id='tabpage_body']/div[2]/div/iframe");
	private By frameOffering2 = By.xpath("//div[@id='tabpage_body']/div[3]/div/iframe");
	private By txtOferring = By.xpath(
			"//td[@class='bc_block_td upc_offering_search_condition_b2c_first_col_td']/div/table/tbody/tr[2]/td/div/div/div/div/input[@id='search.offerId_input_value']");
	private By btnSearchOff = By.xpath("//div[@class='btn_group btn_group_alignleft bc']/span[2]");
	private By OfferingType = By
			.xpath("//td[@id='upc_offering_offering_list_0_4']/span/label[@class='bc_label bc_ui_ele bc']");
	private By selectOffering = By.xpath("//a[@class='show-blank bc bc_ui_ele bc_link']");
	private By frameRoot1 = By
			.xpath("//div[@class='bc_tabitem_body closeable  mode_url selected ForceIE7Render']/div/iframe");
	private By frameRoot2 = By.xpath("//div[@id='overview']/iframe[@id='overview_iframe']");
	private By frameRoot3 = By.xpath("//div[@class='bc_cell firstBlock']/iframe[@id='frameId']");
	private By linkRoot = By.xpath("//div[@title='Gprs']");
	private By iframeTH = By
			.xpath("//div[@id='tabpage_body']/div[5]/div/iframe[@class='bc_tabitem_iframe bc_tabitem_framework']");
	private By txtThreshold = By
			.xpath("//div[@id='threshold-pattern_panel_0']/div[3]/div[2]/table/tbody/tr/td[2]/span/label");
	private By txtUsername = By.id("username");
	private By txtPassword = By.id("password");
	private By btnSubmit = By.id("submitBtn");

	private By btnOpc = By.id("usm_continue");
	boolean bandera;
	String porcentajeBSS = "";
	int total = 0;
	String Resultado = "";
	
	private WebDriver driver = null;

	public String getporcentajeBSS() {
		return porcentajeBSS;
	}

	public void mainThreshold(Word word, String oferta, String usuario,
			String password, String urlBss, String porcentajeMatriz) {
		try {
			Thread.sleep(5000);
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\driver\\chromedriver.exe");
			ChromeOptions opciones = new ChromeOptions();
			opciones.addArguments("--start-maximized");
			driver = new ChromeDriver(opciones);
			AltanThreshold tsh = new AltanThreshold();
			abrirUrl(driver, urlBss);
			credenciales(driver, usuario, password);
			tsh.BuscarOffering(driver);
			tsh.InsertarOferring(driver, oferta, word, porcentajeMatriz);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void BuscarOffering(WebDriver driver) {
		try {
			bandera = true;
			driver.findElement(btnSuscriber).click();
			Thread.sleep(2000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(frameOffering));
			driver.findElement(btnOffering).click();
			driver.switchTo().parentFrame();
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void InsertarOferring(WebDriver driver, String oferID, Word word, String porcentajeMatriz) {
		try {
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(frameOffering2));
			driver.findElement(txtOferring).sendKeys(oferID);
			Thread.sleep(2000);
			driver.findElement(btnSearchOff).click();
			Thread.sleep(2000);
			String OfferType = driver.findElement(OfferingType).getAttribute("title");
			if (OfferType.contains("Supplementary")) {
				driver.findElement(selectOffering).click();
				driver.switchTo().parentFrame();
				Thread.sleep(2000);
				Overview(driver);
				ExtraerPorcentaje(driver, word, porcentajeMatriz);
			} else {
				driver.switchTo().parentFrame();
				Thread.sleep(2000);
				word.CrearImagen(driver);
				word.CrearParrafo("El tipo de oferta perteneciente al ID es \"Primary\"");
				driver.close();

			}

		} catch (Exception e) {
			e.printStackTrace();
			word.CrearTitulo("No se encuentra la Oferta");
			word.CrearParrafo(e.getMessage());
		}
	}

	public void Overview(WebDriver driver) {
		try {
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(frameRoot1));
			driver.switchTo().frame(driver.findElement(frameRoot2));
			driver.switchTo().frame(driver.findElement(frameRoot3));
			driver.findElement(linkRoot).click();
			driver.switchTo().parentFrame();
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ExtraerPorcentaje(WebDriver driver, Word word, String porcentajeMatriz) {
		try {
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(iframeTH));
			porcentajeBSS = driver.findElement(txtThreshold).getText();
			word.CrearParrafo("EL porcentaje de Threshold: " + porcentajeBSS);
			WebElement element_thl = driver.findElement(txtThreshold); 
		    JavascriptExecutor jse = (JavascriptExecutor) driver; 
		    jse.executeScript("arguments[0].style.border='4px solid red'", element_thl);	
			word.CrearImagen(driver);
			word.crearSaltoL();
			txtComparacion(word, porcentajeMatriz);
			word.crearTablaR();
			word.crearTablaThreshold();
			int totalporcentaje = 100;
			String porcentajeMatrizCon = porcentajeMatriz.replaceAll("%", "");
			String porcentajeBSSCon = porcentajeBSS.replaceAll("%", "").replaceAll(" ", "");
			int porcentajeMatrizCon1 = Integer.parseInt(porcentajeMatrizCon);
			int resta = (totalporcentaje - porcentajeMatrizCon1);
			String resta1 = String.valueOf(resta);
			if (resta1.equals(porcentajeBSSCon)) {
				Resultado = "CORRECTO";
			} else {
				Resultado = "INCORRECTO";
			}
			word.crearTablaRC("Threshold", porcentajeMatriz, porcentajeBSS, Resultado);
			driver.switchTo().parentFrame();
			Thread.sleep(1000);
			driver.close();
		} catch (Exception e) {
			e.printStackTrace();
			word.CrearTitulo("Error flujo Threshold");
			word.CrearParrafo(e.getMessage());
		}
	}

	public void txtComparacion(Word word, String porcentajeMatriz) {
		String expl = "El campo porcentaje Matriz (MatrizOferta) nos indica que al llegar al " + porcentajeMatriz
				+ " del consumo de la oferta se enviara una notificación al cliente, para la validación visual dentro del BSS tiene que mostrarse el restante de la oferta para cubrir el 100% en este caso el resultado es: ";
		word.CrearParrafo(expl);
	}
	
	public void abrirUrl(WebDriver driver, String url) {
		try {
			driver.get(url);
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void credenciales(WebDriver driver, String name, String pass) {
		try {
			driver.findElement(txtUsername).sendKeys(name);
			Thread.sleep(2000);
			driver.findElement(txtPassword).sendKeys(pass);
			Thread.sleep(2000);
			driver.findElement(btnSubmit).click();
			Thread.sleep(2000);
			boolean temp = checarObjeto(btnOpc, driver);
			if (temp == true) {
				driver.findElement(btnOpc).click();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean checarObjeto(By by, WebDriver driver) {
		boolean bandera = false;

		try {
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			driver.findElement(by);
			bandera = true;
		} catch (Exception e) {
			bandera = false;

		}
		return bandera;

	}

}
