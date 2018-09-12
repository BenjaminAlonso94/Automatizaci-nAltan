package seleniumaltan;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import herramientas.Word;

public class AltanValidateAPN {
	private By btnSuscriber = By
			.xpath("//div[@style='background-image: url(\"theme/default/images/usm/siteMap24.png\");']");
	private By btnOffering = By.xpath("//a[@subcategoryid='c_902001001']");
	private By frameOffering = By.xpath("//div[@id='tabpage_body']/div[2]/div/iframe");
	private By frameOffering2 = By.xpath("//div[@id='tabpage_body']/div[3]/div/iframe");
	private By txtOferring = By.xpath(
			"//td[@class='bc_block_td upc_offering_search_condition_b2c_first_col_td']/div/table/tbody/tr[2]/td/div/div/div/div/input[@id='search.offerId_input_value']");
	private By btnSearchOff = By.xpath("//div[@class='btn_group btn_group_alignleft bc']/span[2]");
	private By selectOffering = By.xpath("//a[@class='show-blank bc bc_ui_ele bc_link']");
	private By OfferingType = By
			.xpath("//td[@id='upc_offering_offering_list_0_4']/span/label[@class='bc_label bc_ui_ele bc']");
	private By iframeProperty = By.xpath("//div[@id='tabpage_body']/div[4]/div/iframe");
	private By btnProperty = By.xpath("//li[@id='properties_head']");
	private By iframePropertyADD = By.xpath("//div[@id='properties']/iframe");
	private By txtAPN = By
			.xpath("//div[@id='upc_offering_property_staticParamInsts']/div[2]/div/div[2]/table/tbody/tr[2]/td[8]/div");
	String OfferType = "";
	String Resultado = "";
	private By txtUsername = By.id("username");
	private By txtPassword = By.id("password");
	private By btnSubmit = By.id("submitBtn");
	private By btnOpc = By.id("usm_continue");
	WebDriver driver = null;

	public void mainValidityDate(Word word, String usuario, String password,
			String urlBss, String oferta, String TipoOferta, String MatrizAPN) {
		try {
			word.CrearTitulo("Inicia validación de APN");
			if (TipoOferta.equals("Primary")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\driver\\chromedriver.exe");
				ChromeOptions opciones = new ChromeOptions();
				opciones.addArguments("--start-maximized");
				driver = new ChromeDriver(opciones);
				abrirUrl(driver, urlBss);
				credenciales(driver, usuario, password);
				BuscarOffering(driver, word);
				InsertarOferring(driver, oferta, word);
				extraerAPN(driver, word, MatrizAPN);
				driver.close();
			} else {
				word.crearSaltoL();
				word.CrearParrafo("Esta oferta se valida con la oferta Primaria");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void BuscarOffering(WebDriver driver, Word word) {
		try {
			driver.findElement(btnSuscriber).click();
			Thread.sleep(2000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(frameOffering));
			driver.findElement(btnOffering).click();
			driver.switchTo().parentFrame();
			Thread.sleep(2000);
		} catch (Exception e) {
			word.CrearTitulo("Hubo un error en la prueba");
			word.CrearTitulo(e.toString());
			driver.close();
		}
	}

	public void InsertarOferring(WebDriver driver, String oferID, Word word) {
		try {
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(frameOffering2));
			driver.findElement(txtOferring).sendKeys(oferID);
			Thread.sleep(2000);
			driver.findElement(btnSearchOff).click();
			Thread.sleep(2000);
			OfferType = driver.findElement(OfferingType).getAttribute("title");
			driver.findElement(selectOffering).click();
			driver.switchTo().parentFrame();
			Thread.sleep(2000);
		} catch (Exception e) {
			word.CrearTitulo("Hubo un error en la prueba");
			word.CrearTitulo(e.toString());
			driver.close();
		}
	}

	public void extraerAPN(WebDriver driver, Word word, String MatrizAPN) {
		try {
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(iframeProperty));
			driver.findElement(btnProperty).click();
			driver.switchTo().parentFrame();
			Thread.sleep(2000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(iframeProperty));
			driver.switchTo().frame(driver.findElement(iframePropertyADD));
			driver.findElement(txtAPN).click();
			WebElement element_apn = driver.findElement(txtAPN);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].style.border='5px solid red'", element_apn);
			String APN = driver.findElement(txtAPN).getText();
			word.CrearImagen(driver);
			word.crearSaltoL();
			word.crearTablaR();
			if (APN.equals(MatrizAPN)) {
				Resultado = "CORRECTO";
			} else {
				Resultado = "INCORRECTO";
			}
			word.crearTablaPolicyCounter();
			word.crearTablaRC("APN", MatrizAPN, APN, Resultado);
			System.out.println(APN);
		} catch (Exception e) {
			// TODO: handle exception
		}
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
