package seleniumaltan;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import herramientas.Herramientas;
import herramientas.Word;

public class AltanSpeedTest {
	private WebDriver driver = null;
	private String chromDriver = System.getProperty("user.dir") + "\\driver\\chromedriver.exe";
	private By btnSpeed = By.xpath("//a[@class='js-start-test']");
	private By txtResultUpload = By.xpath("//span[@class='result-data-large number result-data-value upload-speed']");
	private By txtResultDownload = By
			.xpath("//span[@class='result-data-large number result-data-value download-speed']");

	public void capturaVelocidad(double velocidadP, Word word) {
		try {
			word.crearSaltoL();
			word.CrearTitulo("Inicia validación de SpeedTest");
			String speed = "";
			System.setProperty("webdriver.chrome.driver", chromDriver);
			ChromeOptions opciones = new ChromeOptions();
			opciones.addArguments("--start-maximized");
			driver = new ChromeDriver(opciones);
			driver.get("http://www.speedtest.net");
			Thread.sleep(1000);
			driver.findElement(btnSpeed).click();
			Thread.sleep(2000);
			checarObjeto(txtResultUpload, driver);
			Thread.sleep(2000);
			speed = driver.findElement(txtResultUpload).getText();
			while (speed.trim().equals("")) {
				speed = driver.findElement(txtResultUpload).getText().trim();
			}
			speed = driver.findElement(txtResultDownload).getText().trim();
			System.out.println("Velocidad de descarga: " + speed);
			Thread.sleep(1000);
			Herramientas herram = new Herramientas();
			herram.sombrearObjeto(driver, txtResultDownload);
			verificPorcentaje(speed.replace(",", "."), velocidadP, word);
			word.CrearImagen(driver);
			driver.close();
		} catch (InterruptedException | IOException | InvalidFormatException e) {
			e.printStackTrace();
		}
	}

	public String verificPorcentaje(String velocidad, double velocidadP, Word word) {
		double velo = Double.parseDouble(velocidad);
		double temp = 0;
		temp = velocidadP * 0.8;
		String resp = "";
		word.CrearParrafo("El 80% para ser aceptada la velocidad es: " + temp);
		if (velo >= temp) {
			word.CrearParrafo("La velocidad esta dentro del 80% aceptable: " + velo);
		} else {
			word.CrearParrafo("La velocidad NO esta dentro del 80% aceptable: " + velo);
		}
		return resp;
	}

	public void checarObjeto(By by, WebDriver driver) {
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
}
