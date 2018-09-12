package seleniumaltan;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import herramientas.Word;

public class AltanValidityDate {

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
	String OfferType = "";
	private By linkPrimary = By.xpath("//div[@title='RecurringCharge']");
	private By frameRoot2 = By.xpath("//div[@id='overview']/iframe[@id='overview_iframe']");
	private By frameRoot3 = By.xpath("//div[@class='bc_cell firstBlock']/iframe[@id='frameId']");
	private By frameRoot4 = By
			.xpath("//div[@class='bc_tabitem_body closeable  mode_url selected ForceIE7Render']/div/iframe");
	private By btnBasicInformation = By.xpath("//div[@id='policyInfoTab']/div[1]/div[4]/div/div[1]");
	private By txtDate = By
			.xpath("//div[@id='policyBasInfo']/form/div[1]/table/tbody/tr[1]/td[2]/div/div[3]/div/div/div[2]");
	private By menuOferta = By.xpath("//div[@id='tabpage_head']/div[1]/ul/li[4]");
	private By frameDespegable = By.xpath("//div[@id='tabpage_body']/div[4]/div/iframe");
	private By btnDespegable = By.xpath("//div[@id='tabpanel_head']/div[2]");
	private By frameDespegable2 = By
			.xpath("//div[@id='tabpage_body']/div[4]/div/iframe[@class='bc_tabitem_iframe bc_tabitem_framework']");
	private By btnContractterm = By.xpath("//div[@id='tabpanel_dropmenu']/ul/li[@itemid='duration_manage']");
	private By frameContract = By.xpath("//div[@id='duration_manage']/iframe");
	private By txtContract = By.xpath("//td[@id='cdDG_0_2']");
	String ResultadoBSS = "";
	String Resultado = "";
	String BSSValidityDate = "";
	String Contracttext = "";
	private By txtUsername = By.id("username");
	private By txtPassword = By.id("password");
	private By btnSubmit = By.id("submitBtn");

	private By btnOpc = By.id("usm_continue");
	private WebDriver driver = null;

	public String getResultadoBSS() {
		return ResultadoBSS;
	}

	public String getOfferType() {
		return OfferType;
	}

	public void mainValidityDate(String oferta, String usuario, String password, String urlBss, Word word,
			String MtrzRenewal, String MtrzRedirect, String MtrzValidityDate, String MtrzType) {
		try {
			word.CrearTitulo("Inicia validación de Validity Date");
			if ((MtrzRenewal.equals("N") && MtrzRedirect.equals("N") && MtrzType.equals("Primary"))
					|| (MtrzRenewal.equals("Y") && MtrzRedirect.equals("N") && MtrzType.equals("Primary"))) {
				word.CrearParrafo("Esta oferta se valida con la oferta suplementaria default.");
			} else {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\driver\\chromedriver.exe");
				ChromeOptions opciones = new ChromeOptions();
				opciones.addArguments("--start-maximized");
				driver = new ChromeDriver(opciones);
				AltanValidityDate vd = new AltanValidityDate();
				vd.abrirUrl(driver, urlBss, word);
				vd.credenciales(driver, usuario, password, word);
				vd.BuscarOffering(driver, word);
				vd.InsertarOferring(driver, oferta, word);
				vd.MenuPlan(driver, word);
				// vd.IngresaRoot(driver, word);
				vd.ExtraerValidity(driver, word, MtrzValidityDate, MtrzRenewal);
				vd.ContractTerm(driver, word, MtrzRenewal, MtrzValidityDate);
				driver.close();
			}
		} catch (Exception e) {
			word.CrearTitulo("Hubo un error en la prueba");
			word.CrearTitulo(e.toString());
			driver.close();
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

	public void MenuPlan(WebDriver driver, Word word) {
		try {
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(frameRoot1));
			driver.switchTo().frame(driver.findElement(frameRoot2));
			driver.switchTo().frame(driver.findElement(frameRoot3));
			driver.findElement(linkPrimary).click();
			driver.switchTo().parentFrame();
			Thread.sleep(4000);
		} catch (Exception e) {
			word.CrearTitulo("Hubo un error en la prueba");
			word.CrearTitulo(e.toString());
			driver.close();
		}
	}

	public void ExtraerValidity(WebDriver driver, Word word, String MtrzValidityDate, String MtrzRenewal) {
		try {
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(frameRoot4));
			driver.findElement(btnBasicInformation).click();
			Thread.sleep(2000);
			WebElement element_node = driver.findElement(By
					.xpath("//div[@id='policyBasInfo']/form/div[1]/table/tbody/tr[1]/td[2]/div/div[3]/div/div/div[2]"));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].style.border='5px solid red'", element_node);
			Thread.sleep(2000);
			word.CrearImagen(driver);
			BSSValidityDate = driver.findElement(txtDate).getAttribute("title");
			System.out.println(BSSValidityDate);
			driver.switchTo().parentFrame();
			Message(word, MtrzRenewal, MtrzValidityDate);
			Coomparacion(word, MtrzValidityDate, MtrzRenewal);
		} catch (Exception e) {
			word.CrearTitulo("Hubo un error en la prueba");
			word.CrearTitulo(e.toString());
			driver.close();
		}
	}

	public void ChecarObjeto1(By by, WebDriver driver) {
		boolean bandera = false;
		while (bandera == false) {
			try {
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				driver.findElement(by);
				bandera = true;
			} catch (Exception e) {
				bandera = false;
			}
		}
	}

	public void ContractTerm(WebDriver driver, Word word, String MtrzRenewal, String MtrzValidityDate) {
		try {
			if (MtrzRenewal.equals("N") && OfferType.equals("Supplementary")) {
				driver.findElement(menuOferta).click();
				Thread.sleep(2000);
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(frameDespegable));
				driver.findElement(btnDespegable).click();
				driver.switchTo().parentFrame();
				Thread.sleep(2000);
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(frameDespegable2));
				driver.findElement(btnContractterm).click();
				driver.switchTo().parentFrame();
				Thread.sleep(3000);
				driver.switchTo().defaultContent();
				driver.switchTo().frame(driver.findElement(frameDespegable2));
				driver.switchTo().frame(driver.findElement(frameContract));
				WebElement element_ct = driver.findElement(txtContract);
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].style.border='5px solid red'", element_ct);
				Contracttext = driver.findElement(txtContract).getText();
				driver.switchTo().parentFrame();
				word.crearSaltoL();
				word.CrearParrafoSJ(
						"La siguiente validación se presenta para las Ofertas Suplementarias No Renovables");
				word.crearSaltoL();
				word.CrearImagen(driver);
				CoomparacionContract(word, MtrzValidityDate);
				Thread.sleep(2000);
			}

		} catch (Exception e) {
			word.CrearTitulo("Hubo un error en la prueba");
			word.CrearTitulo(e.toString());
			driver.close();
		}
	}

	public void Coomparacion(Word word, String MtrzValidityDate, String MtrzRenewal) {
		if (BSSValidityDate.equals("C_Cycle_30Days")) {
			ResultadoBSS = "30";
		}
		if (BSSValidityDate.equals("C_Month")) {
			ResultadoBSS = "Calendar Month";
		}
		if (BSSValidityDate.equals("C_Cycle_Month_adjustTo1th")) {
			ResultadoBSS = "According with bill cycle";
		}
		if (BSSValidityDate.equals("C_Cycle_21Days")) {
			ResultadoBSS = "21";
		}
		if (BSSValidityDate.equals("C_Cycle_7Days")) {
			ResultadoBSS = "7";
		}
		if (BSSValidityDate.equals("C_Cycle_2Days")) {
			ResultadoBSS = "2";
		}
		word.crearSaltoL();
		word.crearTablaR();
		word.crearTablaPolicyCounter();
		if (ResultadoBSS.equals(MtrzValidityDate) || (BSSValidityDate.equals("C_OneOff") && MtrzRenewal.equals("N"))
				|| (BSSValidityDate.equalsIgnoreCase("C_Cycle_Month_adjustTo1th")
						&& MtrzValidityDate.equals("Month Fixed Date"))) {
			Resultado = "CORRECTO";
		} else {
			Resultado = "INCORRECTO";
		}
		word.crearTablaRC("Validity Date", MtrzValidityDate, BSSValidityDate, Resultado);
	}

	public void CoomparacionContract(Word word, String MtrzValidityDate) {
		if (Contracttext.equals("30 Calendar day")) {
			ResultadoBSS = "30";
		}
		if (Contracttext.equals("C_Month")) {
			ResultadoBSS = "Calendar Month";
		}
		if (Contracttext.equals("C_Cycle_Month_adjustTo1th")) {
			ResultadoBSS = "According with bill cycle";
		}
		if (Contracttext.equals("21 Calendar day")) {
			ResultadoBSS = "21";
		}
		if (Contracttext.equals("7 Calendar day")) {
			ResultadoBSS = "7";
		}
		if (Contracttext.equals("2 Calendar day")) {
			ResultadoBSS = "2";
		}
		if (Contracttext.equals("30 Calendar day") && MtrzValidityDate.equals("Calendar Month")) {

		}
		word.crearSaltoL();
		word.crearTablaR();
		word.crearTablaPolicyCounter();
		if (ResultadoBSS.equals(MtrzValidityDate)
				|| (Contracttext.equals("30 Calendar day") && MtrzValidityDate.equals("Calendar Month"))) {
			Resultado = "CORRECTO";
		} else {
			Resultado = "INCORRECTO";
		}
		word.crearTablaRC("Validity Date", MtrzValidityDate, Contracttext, Resultado);
	}

	public void Message(Word word, String MtrzRenewal, String MtrzValidityDate) {
		if (MtrzRenewal.equals("N") && OfferType.equals("Supplementary")) {
			word.crearSaltoL();
			word.CrearParrafo(
					"La validación en este caso es Correcta ya que la oferta es NO renovable pero se termina en "
							+ MtrzValidityDate);
		}
	}

	public void abrirUrl(WebDriver driver, String url, Word word) {
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