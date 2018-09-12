package seleniumaltan;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import herramientas.Herramientas;
import herramientas.Word;

public class AltanPolicyCounter {
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
	private By frameRoot1 = By
			.xpath("//div[@class='bc_tabitem_body closeable  mode_url selected ForceIE7Render']/div/iframe");
	private By frameRoot2 = By.xpath("//div[@id='overview']/iframe[@id='overview_iframe']");
	private By frameRoot3 = By.xpath("//div[@class='bc_cell firstBlock']/iframe[@id='frameId']");
	private By linkRoot = By.xpath("//div[@id='infor_area']/div[10]/div[1]/table/tbody/tr/td[2]/div");
	private By frameCounter1 = By.xpath(
			"//div[@id='tabpage_body']/div[@class='bc_tabitem_body closeable  mode_url selected ForceIE7Render']/div[@class='bc_tabitem']/iframe");
	private By txtCounter = By.xpath(
			"//table[@id='pattern_table_id']/tbody/tr/td[2]/div[@id='vgs-notify-pcrf-action_panel_1']/form/div/table/tbody/tr[3]/td[2]/div/div[3]/div/div/div[@name='showTextDiv']");
	private By siteMap = By.xpath("//ul[@id='tabpage_items']/li[2]");
	private By frameSiteMap = By
			.xpath("//div[@id='tabpage_body']/div[2]/div/iframe[@class='bc_tabitem_iframe bc_tabitem_framework']");
	private By btnCounter = By.xpath("//a[@title='Counter'][@class='clickable']");
	private By iframeCounter = By.xpath(
			"//div[@class='bc_tabitem_body closeable  mode_url selected ForceIE7Render']/div/iframe[@class='bc_tabitem_iframe bc_tabitem_framework']");
	private By btnHBB = By.xpath(
			"//div[@id='CounterDatagrid']/div[2]/div[3]/table/tbody/tr[@id='CounterDatagrid_0']/td[@id='CounterDatagrid_0_6']/div/img[@title='Configure Level']");
	boolean bandera;
	public String CounterLevel = "";
	private By frameBasic = By.xpath("//div[@id='tabpage_body']/div[6]/div/iframe");

	private By selectBasic = By.xpath(
			"//div[@id='CounterLevelPanel']/div[2]/div/table/tbody/tr[4]/td/div/div[2]/div/table/tbody/tr[2]/td[@class='bc_block_td levelBasicInfo_first_col_td']/div/div[3]/label");
	private By btnEdit = By.xpath(
			"//div[@id='CounterLevelPanel']/div[2]/div/table/tbody/tr[4]/td/div/div[2]/div/table/tbody/tr[5]/td[@class='bc_block_td levelBasicInfo_first_col_td']/div/span[1]");
	private By txtUsername = By.id("username");
	private By txtPassword = By.id("password");
	private By btnSubmit = By.id("submitBtn");
	private By btnOpc = By.id("usm_continue");
	String Resultado = "";
	WebDriver driver = null;

	public String getCounterLevel() {
		return CounterLevel;
	}

	public void mainPolicyCounter(Word word, String oferta, String usuario,
			String password, String urlBss, String PolicyCounterMatriz) {
		try {
			word.CrearTitulo("Inicia validación de PolicyCounter");
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\driver\\chromedriver.exe");
			ChromeOptions opciones = new ChromeOptions();
			opciones.addArguments("--start-maximized");
			driver = new ChromeDriver(opciones);
			abrirUrl(driver, urlBss);
			credenciales(driver, usuario, password);
			BuscarOffering(driver);
			InsertarOferring(driver, oferta, word, PolicyCounterMatriz);
		} catch (Exception e) {
			e.printStackTrace();
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

	public void InsertarOferring(WebDriver driver, String oferID, Word word, String PolicyCounterMatriz) {
		try {
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(frameOffering2));
			driver.findElement(txtOferring).sendKeys(oferID);
			Thread.sleep(2000);
			driver.findElement(btnSearchOff).click();
			Thread.sleep(2000);
			String OfferType = driver.findElement(OfferingType).getAttribute("title");
			if (OfferType.contains("Primary")) {
				driver.findElement(selectOffering).click();
				driver.switchTo().parentFrame();
				Thread.sleep(2000);
				Overview(driver);
				Obtener1Counter(driver, word);
				SiteMap(driver);
				HBBCounter(driver);
				scroll(driver, word, PolicyCounterMatriz);
			} else {
				driver.switchTo().parentFrame();
				Thread.sleep(2000);
				word.CrearImagen(driver);
				word.CrearParrafo("El tipo de oferta perteneciente al ID es \"Supplementary\"");
				driver.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
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

	public void Obtener1Counter(WebDriver driver, Word word) {
		try {
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(frameCounter1));
			checarObjeto(txtCounter, driver);
			CounterLevel = driver.findElement(txtCounter).getAttribute("title");
			word.CrearImagen(driver);
			driver.switchTo().parentFrame();
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void SiteMap(WebDriver driver) {
		try {
			driver.findElement(siteMap).click();
			Thread.sleep(3000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(frameSiteMap));
			driver.findElement(btnCounter).click();
			driver.switchTo().parentFrame();
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void HBBCounter(WebDriver driver) {
		try {
			System.out.println(CounterLevel);
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(iframeCounter));
			driver.findElement(btnHBB).click();
			Thread.sleep(3000);
			driver.findElement(By.linkText(CounterLevel)).click();
			Thread.sleep(1000);
			driver.findElement(By.linkText(CounterLevel)).click();
			driver.switchTo().parentFrame();
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean checarObjeto(By by, WebDriver driver) {
		boolean bandera = false;
		try {
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.findElement(by);
			bandera = true;
		} catch (Exception e) {
			bandera = false;
		}
		return bandera;
	}

	public void scroll(WebDriver driver, Word word, String PolicyCounterMatriz) {
		try {
			Herramientas herram = new Herramientas();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(frameBasic));
			String attr = driver.findElement(selectBasic).getAttribute("title");		    
		    herram.sombrearObjeto(driver,selectBasic);
			driver.findElement(btnEdit).click();
			driver.switchTo().parentFrame();
			Thread.sleep(3000);
			word.crearSaltoL();
			word.CrearImagen(driver);
			word.crearSaltoL();
			word.crearTablaR();
			word.crearTablaPolicyCounter();
			if (attr.equals(PolicyCounterMatriz)) {
				Resultado = "CORRECTO";
			} else {
				Resultado = "INCORRECTO";
			}
			word.crearTablaRC("Policy Counter", PolicyCounterMatriz, attr, Resultado);
			Thread.sleep(1000);
			driver.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
