package seleniumaltan;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import herramientas.Word;

public class AltanUrlRedirect {
	private By txtUsername = By.id("username");
	private By txtPassword = By.id("password");
	private By btnSubmit = By.id("submitBtn");
	private By btnOpc = By.id("usm_continue");
	private By btnSiteMap = By
			.xpath("//div[@style='background-image: url(\"theme/default/images/usm/siteMap24.png\");']");
	private By frameOffering = By.xpath("//div[@id='tabpage_body']/div[2]/div/iframe");// 1
	private By btnBilling = By.xpath("//div[@id='sitemap']/div[@id='catalog']/ul/li[@itemid='c_305']");
	private By menuRedirect = By.xpath(
			"//div[@id='sitemap']/div[4]/div[5]/div[2]/span[45]/a[@title='Data Service Redirection Configuration']");
	private By iframeRedirect = By.xpath(
			"//div[@id='tabpage_body']/div[@class='bc_tabitem_body closeable  mode_url selected ForceIE7Render']/div/iframe");
	private By btnSelect = By
			.xpath("//div[@id='queryCondition_primary_offering_id']/div[@class='bc_field_body']/div/div/select");
	private By btnSearch = By.xpath(
			"//div[@id='view']/div[3]/table/tbody/tr[3]/td/div/table/tbody/tr[2]/td[@class='bc_block_td block_not_first_td']/div/span[@id='query']");
	private By listRedirect = By.xpath("//div[@id='List_View']/div[2]/div[3]/table");
	private By txtRasonRedirect1 = By.xpath("//tbody[@id='List_View_databody']/tr[@id='List_View_0']/td[4]/span/label");
	private By txtRasonRedirect2 = By.xpath("//tbody[@id='List_View_databody']/tr[@id='List_View_1']/td[4]/span/label");
	private By txtRasonRedirect3 = By.xpath("//tbody[@id='List_View_databody']/tr[@id='List_View_2']/td[4]/span/label");
	private By txtURL1 = By
			.xpath("//tbody[@id='List_View_databody']/tr[@id='List_View_0']/td[@id='List_View_0_7']/span/label");
	private By txtURL2 = By
			.xpath("//tbody[@id='List_View_databody']/tr[@id='List_View_1']/td[@id='List_View_1_7']/span/label");
	private By txtURL3 = By
			.xpath("//tbody[@id='List_View_databody']/tr[@id='List_View_2']/td[@id='List_View_2_7']/span/label");
	private By btnUPC = By.xpath("//div[@id='sitemap']/div[@id='catalog']/ul/li[@itemid='c_902']");
	private By btnOffering = By.xpath("//a[@subcategoryid='c_902001001']");
	private By frameOffering2 = By.xpath("//div[@id='tabpage_body']/div[3]/div/iframe");
	private By txtOferring = By.xpath(
			"//td[@class='bc_block_td upc_offering_search_condition_b2c_first_col_td']/div/table/tbody/tr[2]/td/div/div/div/div/input[@id='search.offerId_input_value']");
	private By btnSearchOff = By.xpath("//div[@class='btn_group btn_group_alignleft bc']/span[2]");
	private By OfferingType = By
			.xpath("//td[@id='upc_offering_offering_list_0_4']/span/label[@class='bc_label bc_ui_ele bc']");
	private By btnsiteR = By.xpath("//ul[@id='tabpage_items']/li[2]/div[2]/div/div");
	boolean bandera;
	String Resultado = "";
	String Resultado1 = "";
	String Resultado2 = "";
	WebDriver driver = null;

	public void mainRedirect(Word word, String usuario, String password,
			String urlBss, String ofertaID, String URLMatriz) {
		try {
			Thread.sleep(5000);
			word.CrearTitulo("Inicia validación de Redirect");
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\driver\\chromedriver.exe");
			ChromeOptions opciones = new ChromeOptions();
			opciones.addArguments("--start-maximized");
			driver = new ChromeDriver(opciones);
			AltanUrlRedirect rdt = new AltanUrlRedirect();
			rdt.abrirUrl(driver, urlBss);
			rdt.credenciales(driver, usuario, password, word);
			rdt.BuscarOffering(driver, word, ofertaID, URLMatriz);
		} catch (Exception e) {
			word.CrearTitulo("Hubo un error en la prueba");
			word.CrearTitulo(e.toString());
			driver.close();
		}
	}

	public void BuscarOffering(WebDriver driver, Word word, String ofertaID, String URLMatriz) {
		try {
			bandera = true;
			driver.findElement(btnSiteMap).click();
			Thread.sleep(2000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(frameOffering));
			driver.findElement(btnUPC).click();
			Thread.sleep(2000);
			driver.findElement(btnOffering).click();
			driver.switchTo().parentFrame();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(frameOffering2));
			String[] ofertaC = ofertaID.split("\\|");
			String ofertaCC = ofertaC[0];
			Thread.sleep(2000);
			driver.findElement(txtOferring).sendKeys(ofertaCC);
			Thread.sleep(2000);
			driver.findElement(btnSearchOff).click();
			Thread.sleep(2000);
			String OfferType = driver.findElement(OfferingType).getAttribute("title");
			System.out.println(OfferType);
			driver.switchTo().parentFrame();
			Thread.sleep(2000);
			if (OfferType.equals("Primary")) {
				driver.findElement(btnsiteR).click();
				driver.switchTo().frame(driver.findElement(frameOffering));
				driver.findElement(btnBilling).click();
				Thread.sleep(2000);
				driver.findElement(menuRedirect).click();
				driver.switchTo().parentFrame();
				Thread.sleep(2000);
				CapturarRedirect(driver, ofertaID, word);
				ExtraerRedirect(driver, word, ofertaID, URLMatriz);
				driver.close();
			} else {
				word.CrearParrafo("La validación es solo para ofertas primarias.");
				driver.close();
			}

		} catch (Exception e) {
			word.CrearTitulo("Hubo un error en la prueba");
			word.CrearTitulo(e.toString());
			driver.close();
		}
	}

	public void CapturarRedirect(WebDriver driver, String ofertaID, Word word) {
		try {
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(iframeRedirect));
			driver.findElement(btnSelect).click();
			Thread.sleep(2000);

			Select ofertaIDName = new Select(driver.findElement(By.name("queryCondition.primary_offering_id")));
			ofertaIDName.selectByVisibleText(ofertaID);
			Thread.sleep(2000);
			driver.findElement(btnSearch).click();
			Thread.sleep(2000);
			WebElement element_red = driver.findElement(listRedirect);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].style.border='5px solid red'", element_red);
			word.CrearImagen(driver);
			driver.switchTo().parentFrame();
		} catch (Exception e) {
			word.CrearTitulo("Hubo un error en la prueba");
			word.CrearTitulo(e.toString());
			driver.close();
		}
	}

	public void ExtraerRedirect(WebDriver driver, Word word, String ofertaID, String URLMAtriz) {
		try {
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(iframeRedirect));
			String Razon1 = driver.findElement(txtRasonRedirect1).getAttribute("title");
			String Razon2 = driver.findElement(txtRasonRedirect2).getAttribute("title");
			String Razon3 = driver.findElement(txtRasonRedirect3).getAttribute("title");
			String[] URL1 = driver.findElement(txtURL1).getAttribute("title").split("\\?");
			String urlOp1 = URL1[0] + "/";
			String[] URL2 = driver.findElement(txtURL2).getAttribute("title").split("\\?");
			String urlOp2 = URL2[0] + "/";
			String[] URL3 = driver.findElement(txtURL3).getAttribute("title").split("\\?");
			String urlOp3 = URL3[0] + "/";
			if (URLMAtriz.equals(urlOp1)) {
				Resultado = "CORRECTO";
			} else {
				Resultado = "INCORRECTO";
			}
			if (URLMAtriz.equals(urlOp2)) {
				Resultado1 = "CORRECTO";
			} else {
				Resultado1 = "INCORRECTO";
			}
			if (URLMAtriz.equals(urlOp3)) {
				Resultado2 = "CORRECTO";
			} else {
				Resultado2 = "INCORRECTO";
			}
			word.crearSaltoL();
			word.crearTablaR();
			word.crearTablaRedirect();
			word.crearTablaRC2(ofertaID, Razon1, URLMAtriz, urlOp1, Resultado);
			word.crearTablaRC2(ofertaID, Razon2, URLMAtriz, urlOp2, Resultado1);
			word.crearTablaRC2(ofertaID, Razon3, URLMAtriz, urlOp3, Resultado2);
		} catch (Exception e) {
			word.CrearTitulo("No hay ningún Redirect configurado.");
			word.CrearTitulo(e.toString());
			driver.close();
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

	public void credenciales(WebDriver driver, String name, String pass, Word word) {
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
			Thread.sleep(2000);
		} catch (Exception e) {
			word.CrearTitulo("Hubo un error en la prueba");
			word.CrearTitulo(e.toString());
			driver.close();
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
