package seleniumaltan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import herramientas.Herramientas;
import herramientas.Word;

public class AltanProfileSelenium {
	private static final long MEGABYTE = 1024L * 1024L;
	private WebDriver driver;
	String dn = "";
	String idSubscriber = "";
	String IMSI = "";
	String ICCID = "";
	String subStatus = "";
	List<List<String>> listDetOfert = new ArrayList<List<String>>();

	public String getDn() {
		return dn;
	}

	public String getIdSubscriber() {
		return idSubscriber;
	}

	public String getIMSI() {
		return IMSI;
	}

	public String getICCID() {
		return ICCID;
	}

	public String getSubStatus() {
		return subStatus;
	}

	public List<List<String>> getListDetOfert() {
		return listDetOfert;
	}

	public void setDn(String dn) {
		this.dn = dn;
	}

	public void setIdSubscriber(String idSubscriber) {
		this.idSubscriber = idSubscriber;
	}

	public void setIMSI(String iMSI) {
		IMSI = iMSI;
	}

	public void setICCID(String iCCID) {
		ICCID = iCCID;
	}

	public void setSubStatus(String subStatus) {
		this.subStatus = subStatus;
	}

	private By frame1 = By.xpath(
			"//div[@id='tabpage_body']/div[@class='bc_tabitem_body closeable  mode_url selected']/div[@class='bc_tabitem']/iframe[@class='bc_tabitem_iframe bc_tabitem_framework']");
	private By frame2 = By.xpath("//iframe[@name='zBusinessAccept_Subscriber_iframe']");
	private By frame3 = By.xpath("//iframe[@name='zSubscriberInfo_SubsOffering_iframe']");
	private By frame4 = By.xpath("//iframe[@name='zSubsOffering_OfferingDetail_iframe']");
	private By frame5 = By.xpath("//iframe[@name='zSubscriberInfo_SubscriberDetail_iframe']");
	private By frame6 = By.xpath("//iframe[@name='zSubscriberInfo_SubSimNew_iframe']");
	private By frame7 = By.xpath("//iframe[@id='zBusinessAccept_Billing_iframe']");
	private By frame8 = By.xpath("//iframe[@name='zBillingAccount_BillFreeResource_iframe']");
	private By frame9 = By.xpath("//iframe[@name='tabPage_902001001_iframe']");
	private By frame10 = By
			.xpath("//div[@id='tabpage_body']/div[@class='bc_tabitem_body closeable  mode_url selected']/div/iframe");
	private By txtUsuario = By.id("username");
	private By txtPassword = By.id("password");
	private By btnIniciarSesion = By.id("submitBtn");
	private By btnContinuar = By.id("usm_continue");
	private By btnCliente = By.id("newcustomer");
	private By txtDn = By.xpath("//input[@id='serviceNo_input_value']");
	private By btnBuscar = By.id("searchCustomer");
	private By btnRegistroR = By.xpath(
			"//tbody[@id='contentList_databody']/tr[@id='contentList_0']/td[@id='contentList_0_7']/span/img[@title='Details']");
	private By btnTitleSubs = By.id("zBusinessAccept_Subscriber_title");
	private By btntitleOffer = By.id("zSubscriberInfo_SubsOffering_title");
	private By btnTitleSubsDetail = By.id("zSubscriberInfo_SubscriberDetail_title");
	private By btnTitleSim = By.id("zSubscriberInfo_SubSimNew_title");
	private By btnTitleFacturacion = By.id("zBusinessAccept_Billing_title");
	private By btnTitleFreeResource = By.id("zBillingAccount_BillFreeResource_title");
	private By btnTitleSiteMap = By.id("sitemap");
	private By btnTitleProductU = By.xpath("//div[@title='Unified Product Catalog']");
	private By btnTitleOffering = By.xpath("//div[@class='crm_sitemap_category']/div/span/a[@title='Offering(B2C)']");
	private By txtNameOferta = By.id("mainofferofferName_input");
	private By txtDnTable = By.xpath("//label[@id='mobileNo_input']");
	private By txtIccidTable = By.xpath("//label[@id='iccid_input']");
	private By txtSubEstatusTable = By.xpath("//label[@id='status_input']");
	private By txtImsisTable = By
			.xpath("//td[@id='resources_0_3']/span[@class='bc_sfield bc']/label[@class='bc_label bc']");
	private String freeUnitP1 = "//tbody[@id='freeUnitItemCbs55_databody']/tr/td[@id='freeUnitItemCbs55_";
	private String freeUnitP2 = "_0']/span/label[@class='bc_label bc']";
	private String initialAmtP1 = "//tbody[@id='freeUnitItemCbs55_databody']/tr/td[@id='freeUnitItemCbs55_";
	private String initialAmtP2 = "_2']/span/label[@class='bc_label bc']";
	private String unusedAmtP1 = "//tbody[@id='freeUnitItemCbs55_databody']/tr/td[@id='freeUnitItemCbs55_";
	private String unusedAmtP2 = "_3']/span/label[@class='bc_label bc']";
	private String effectiveDP1 = "//tbody[@id='freeUnitItemCbs55_databody']/tr/td[@id='freeUnitItemCbs55_";
	private String effectiveDP2 = "_5']/span/label[@class='bc_label bc']";
	private String expireDP1 = "//tbody[@id='freeUnitItemCbs55_databody']/tr/td[@id='freeUnitItemCbs55_";
	private String expireDP2 = "_6']/span/label[@class='bc_label bc']";
	private By txtBusquedaOferta = By.xpath(
			"//td[@class='bc_block_td upc_offering_search_condition_b2c_first_col_td']/div/table/tbody/tr/td/div/div/div/div/input[@id='search.offerId_input_value']");
	private By btnBusquedaOferta = By.id("searchButton_b2c");
	private By txtDatosOferta = By.id("upc_offering_offering_list_0_1");

	public void consultaProfile(Herramientas herram, Word word, String url, String user, String pass, String DN) {
		word.CrearTitulo("Inicia prueba de BSS con DN: " + DN);
		cColLis();
		try {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\driver\\chromedriver.exe");
			ChromeOptions opciones = new ChromeOptions();
			opciones.addArguments("--start-maximized");
			driver = new ChromeDriver(opciones);
			driver.get(url);
			Thread.sleep(2000);
			word.CrearParrafo("Se inicia URL");
			word.CrearImagen(driver);
			driver.findElement(txtUsuario).clear();
			Thread.sleep(1000);
			driver.findElement(txtUsuario).sendKeys(user);
			Thread.sleep(1000);
			word.CrearParrafo("Se ingresa usuario: " + user);
			word.CrearImagen(driver);
			driver.findElement(txtPassword).clear();
			Thread.sleep(1000);
			driver.findElement(txtPassword).sendKeys(pass);
			Thread.sleep(1000);
			word.CrearParrafo("Se ingresa password: " + pass);
			word.CrearImagen(driver);
			driver.findElement(btnIniciarSesion).click();
			Thread.sleep(3000);
			word.CrearParrafo("Se da clic en iniciar sesión:");
			word.CrearImagen(driver);
			boolean res = ChecarObjeto(btnContinuar, driver);
			if (res == true) {
				driver.findElement(btnContinuar).click();
				Thread.sleep(3000);
				word.CrearParrafo("Se da clic en continuar:");
				word.CrearImagen(driver);
			}
			driver.findElement(btnCliente).click();
			Thread.sleep(3000);
			word.CrearParrafo("Se da clic en Buscar Cliente:");
			word.CrearImagen(driver);
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(frame1));
			driver.findElement(txtDn).clear();
			Thread.sleep(1000);
			driver.findElement(txtDn).sendKeys(DN);
			Thread.sleep(1000);
			word.CrearParrafo("Se ingresa el DN a buscar: " + DN);
			word.CrearImagen(driver);
			driver.findElement(btnBuscar).click();
			Thread.sleep(1000);
			word.CrearParrafo("Se da clic en Buscar:");
			word.CrearImagen(driver);

			res = ChecarObjeto(btnRegistroR, driver);
			if (res == true) {
				driver.findElement(btnRegistroR).click();
				Thread.sleep(3000);
				word.CrearParrafo("Se selecciona el primer registro:");
				word.CrearImagen(driver);
			}
			driver.findElement(btnTitleSubs).click();
			Thread.sleep(1000);
			word.CrearParrafo("Se da clic en Subscriptor:");
			word.CrearImagen(driver);
			driver.switchTo().frame(driver.findElement(frame2));
			driver.findElement(btntitleOffer).click();
			Thread.sleep(1000);
			word.CrearParrafo("Se da clic en Offering");
			word.CrearImagen(driver);
			driver.switchTo().frame(driver.findElement(frame3));
			driver.switchTo().frame(driver.findElement(frame4));
			word.CrearParrafo("Se captura Offering Name del BSS: " + driver.findElement(txtNameOferta).getText());
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(frame1));
			Thread.sleep(1000);
			driver.switchTo().frame(driver.findElement(frame2));
			driver.findElement(btnTitleSubsDetail).click();
			Thread.sleep(1000);
			word.CrearParrafo("Se da clic en Información de Subscriptor:");
			word.CrearImagen(driver);
			driver.switchTo().frame(driver.findElement(frame5));
			dn = driver.findElement(txtDnTable).getText();
			word.CrearParrafo("Se captura el DN que muestra el BSS: " + dn);
			ICCID = driver.findElement(txtIccidTable).getText();
			word.CrearParrafo("Se captura el ICCID que muestra el BSS: " + ICCID);
			subStatus = driver.findElement(txtSubEstatusTable).getText();
			word.CrearParrafo("Se captura el subStatus que muestra el BSS: " + subStatus);
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(frame1));
			driver.switchTo().frame(driver.findElement(frame2));
			driver.findElement(btnTitleSim).click();
			Thread.sleep(1000);
			word.CrearParrafo("Se da clic en la Gestión de SIM:");
			word.CrearImagen(driver);
			driver.switchTo().frame(driver.findElement(frame6));
			IMSI = driver.findElement(txtImsisTable).getText();
			word.CrearParrafo("Se captura el IMSI que muestra el BSS: " + IMSI);
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(frame1));
			driver.findElement(btnTitleFacturacion).click();
			Thread.sleep(1000);
			word.CrearParrafo("Se da clic en Facturación:");
			word.CrearImagen(driver);
			driver.switchTo().frame(driver.findElement(frame7));
			driver.findElement(btnTitleFreeResource).click();
			Thread.sleep(1000);
			word.CrearParrafo("Se da clic en Recursos Gratuitos:");
			word.CrearImagen(driver);
			driver.switchTo().frame(driver.findElement(frame8));
			for (int i = 0; i <= herram.getListDetOfert().get(1).size() - 1; i++) {
				String temp = "";
				String temp2 = "";
				long tempL = 0L;
				listDetOfert.get(0).add(driver.findElement(By.xpath(freeUnitP1 + i + freeUnitP2)).getText());
				word.CrearParrafo("Se captura el name que muestra el BSS: " + listDetOfert.get(0).get(i));
				temp2 = driver.findElement(By.xpath(initialAmtP1 + i + initialAmtP2)).getText().replace(",", "");
				tempL = Long.parseLong(temp2);
				temp = String.valueOf(tempL / MEGABYTE);
				listDetOfert.get(2).add(temp);
				word.CrearParrafo("Se captura el initialAmt que muestra el BSS: " + listDetOfert.get(2).get(i));
				temp2 = driver.findElement(By.xpath(unusedAmtP1 + i + unusedAmtP2)).getText().replace(",", "");
				tempL = Long.parseLong(temp2);
				temp = String.valueOf(tempL / MEGABYTE);
				listDetOfert.get(3).add(temp);
				word.CrearParrafo("Se captura el unusedAmt que muestra el BSS: " + listDetOfert.get(3).get(i));
				listDetOfert.get(4).add(driver.findElement(By.xpath(effectiveDP1 + i + effectiveDP2)).getText());
				word.CrearParrafo("Se captura el effectiveDate que muestra el BSS: " + listDetOfert.get(4).get(i));
				listDetOfert.get(5).add(driver.findElement(By.xpath(expireDP1 + i + expireDP2)).getText());
				word.CrearParrafo("Se captura el expireDate que muestra el BSS: " + listDetOfert.get(5).get(i));
			}
			driver.switchTo().defaultContent();
			driver.findElement(btnTitleSiteMap).click();
			Thread.sleep(3000);
			word.CrearParrafo("Se da clic en Mapa del Sitio");
			word.CrearImagen(driver);
			driver.switchTo().frame(driver.findElement(frame10));
			driver.findElement(btnTitleProductU).click();
			Thread.sleep(1000);
			word.CrearParrafo("Se da clic en Catálogo de Productos Unificados.");
			word.CrearImagen(driver);
			driver.findElement(btnTitleOffering).click();
			Thread.sleep(1000);
			word.CrearParrafo("Se da clic en Offering(B2C).");
			word.CrearImagen(driver);
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(frame9));
			for (int i = 0; i <= herram.getListDetOfert().get(0).size() - 1; i++) {
				driver.findElement(txtBusquedaOferta).clear();
				driver.findElement(txtBusquedaOferta).sendKeys(herram.getListDetOfert().get(0).get(i));
				Thread.sleep(1000);
				word.CrearParrafo("Se da ingresa offeringId: " + herram.getListDetOfert().get(0).get(i));
				word.CrearImagen(driver);
				driver.findElement(btnBusquedaOferta).click();
				Thread.sleep(1000);
				word.CrearParrafo("Se da clic en Buscar");
				word.CrearImagen(driver);
				listDetOfert.get(1).add(driver.findElement(txtDatosOferta).getText());
				Thread.sleep(1000);
				word.CrearParrafo("Se captura el offeringId que muestra el BSS: " + listDetOfert.get(1).get(i));
			}
			driver.close();
			word.CrearTitulo("Termina prueba de BSS con DN: " + dn);
		} catch (InterruptedException | NoSuchFrameException | InvalidFormatException | IOException e) {
			word.CrearTitulo("Hubo un error en la prueba de BSS con DN: " + dn);
			word.CrearTitulo(e.toString());
			driver.close();
		}
	}

	public void cColLis() {
		for (int i = 0; i <= 6; i++) {
			listDetOfert.add(new ArrayList<String>());
		}
	}

	public boolean ChecarObjeto(By by, WebDriver driver) {
		try {
			driver.findElement(by);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
