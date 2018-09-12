
package herramientas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import apisaltanjson.tipoc.AltanChangePrimary;
import apisaltanjson.tipoc.AltanOfferSupplemenary;
import apisaltanjson.tipoc.AltanQueryOrder;
import seleniumaltan.AltanPolicyCounter;
import seleniumaltan.AltanProfileSelenium;
import seleniumaltan.AltanSpeedTest;
import seleniumaltan.AltanValidateAPN;
import seleniumaltan.AltanValidityDate;

public class Herramientas {
	private static final long MEGABYTE = 1024L * 1024L;
	private static final long GIGABYTE = 1024L;
	File folder;
	static String folderS;
	FileOutputStream fTxt;
	Writer wTxt;
	String idSubscriber = "";
	String IMSI = "";
	String ICCID = "";
	String subStatus = "";
	String accessToken = "";
	String offeringId = "";
	String idOrden = "";
	boolean bError = false;
	List<List<String>> listFreeUnit = new ArrayList<List<String>>();
	List<List<String>> listDetOfert = new ArrayList<List<String>>();
	String pOferta = "";
	int vecesPrimary = 0;
	int vecesDefault = 0;
	int vecesOptionalA = 0;
	int vecesReplace = 0;
	String numOferta = "";
	String idOferta = "";
	String nomOferta = "";
	String typeOferta = "";
	String totalData = "";
	String speedData = "";
	String firstData = "";
	String throttling = "";
	String throttling2 = "";
	String validity = "";
	String defaultMand = "";
	String defaultMand2 = "";
	String redirect = "";
	String cFirstData = "";
	String cThrottling = "";
	String cThrottling2 = "";
	String cRedirect = "";
	String counterId = "";
	String renewal = "";
	String buyBack = "";
	String replacement = "";
	String numReplacement = "";
	String optionalAtach = "";
	String numOptionalAtach = "";
	String dependency = "";
	String urlRedirect = "";
	String productType = "";
	String apn = "";
	String notification = "";
	String pcrfProfile = "";
	String statusOrderId = "";
	String codeStatus = "";
	String descriptionCode = "";
	String orderId = "";
	int sumaBolsas = 0;
	int sumaBolsasApi = 0;
	int fu = 0;
	int thro = 0;
	int thro2 = 0;
	int codeErrorHttp = 0;

	public String getIdSubscriber() {
		return idSubscriber;
	}

	public String getOrderId() {
		return orderId;
	}

	public int getCodeErrorHttp() {
		return codeErrorHttp;
	}

	public String getCodeStatus() {
		return codeStatus;
	}

	public String getDescriptionCode() {
		return descriptionCode;
	}

	public String getStatusOrderId() {
		return statusOrderId;
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

	public String getAccessToken() {
		return accessToken;
	}

	public String getFolderS() {
		return folderS;
	}

	public boolean getBError() {
		return bError;
	}

	public String getIdOrden() {
		return idOrden;
	}

	public String getOfferingId() {
		return offeringId;
	}

	public List<List<String>> getListFreeUnit() {
		return listFreeUnit;
	}

	public List<List<String>> getListDetOfert() {
		return listDetOfert;
	}

	public void setStatusOrderId(String statusOrderId) {
		this.statusOrderId = statusOrderId;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public void setBError(boolean bError) {
		this.bError = bError;
	}

	public void setIdOrden(String idOrden) {
		this.idOrden = idOrden;
	}

	public void setCodeErrorHttp(int codeErrorHttp) {
		this.codeErrorHttp = codeErrorHttp;
	}

	public void cColLis() {
		for (int i = 0; i <= 6; i++) {
			listDetOfert.add(new ArrayList<String>());
		}
		for (int i = 0; i <= 3; i++) {
			listFreeUnit.add(new ArrayList<String>());
		}
	}

	public void readArrayJson(String json) {
		try {
			JSONArray jsonArray;
			jsonArray = new JSONArray(json);
			String clave = "";
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				if (object.has("name")) {
					clave = object.getString("name");
					listFreeUnit.get(0).add(clave);
					pOferta = clave;
				}
				if (object.has("freeUnit")) {
					clave = object.getString("freeUnit");
					readObjectJson(clave);
				}
				if (object.has("detailOfferings")) {
					clave = object.getString("detailOfferings");
					readArrayJson(clave);
				}
				if (object.has("offeringId")) {
					clave = object.getString("offeringId");
					listDetOfert.get(0).add(clave);
				}
				if (object.has("initialAmt")) {
					clave = object.getString("initialAmt");
					listDetOfert.get(1).add(clave);
				}
				if (object.has("unusedAmt")) {
					clave = object.getString("unusedAmt");
					listDetOfert.get(2).add(clave);
					listDetOfert.get(5).add(pOferta);
				}
				if (object.has("effectiveDate")) {
					clave = object.getString("effectiveDate");
					listDetOfert.get(3).add(clave);
				}
				if (object.has("expireDate")) {
					clave = object.getString("expireDate");
					listDetOfert.get(4).add(clave);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void readObjectJson(String json) {
		try {
			JSONObject jsonObject;
			long piv = 0;
			jsonObject = new JSONObject(json);
			JSONArray keys = jsonObject.names();
			piv = keys.length();

			for (int i = 0; i < piv; ++i) {
				String key = keys.getString(i);
				String value = jsonObject.getString(key);
				if (key.equals("accessToken")) {
					accessToken = value;
					break;
				}
				if (key.equals("responseSubscriber")) {
					readObjectJson(value);
				}
				if (key.equals("information")) {
					readObjectJson(value);
				}
				if (key.equals("idSubscriber")) {
					idSubscriber = value;
				}
				if (key.equals("IMSI")) {
					IMSI = value;
				}
				if (key.equals("ICCID")) {
					ICCID = value;
				}
				if (key.equals("errorCode")) {
					codeStatus = value;
				}
				if (key.equals("description")) {
					descriptionCode = value;
				}
				if (key.equals("status")) {
					if (!value.contains("{") || !value.contains("}")) {
						statusOrderId = value;
					} else {
						readObjectJson(value);
					}
				}
				if (key.equals("orderId")) {
					orderId = value;
				}
				if (key.equals("subStatus")) {
					subStatus = value;
				}
				if (key.equals("primaryOffering")) {
					readObjectJson(value);
				}
				if (key.equals("offeringId")) {
					offeringId = value;
				}
				if (key.equals("freeUnits")) {
					readArrayJson(value);
				}
				if (key.equals("totalAmt")) {
					listFreeUnit.get(1).add(value);
				}
				if (key.equals("unusedAmt")) {
					listFreeUnit.get(2).add(value);
				}
				if (key.equals("order")) {
					readObjectJson(value);
				}
				if (key.equals("id")) {
					idOrden = value;
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void readJson() {
		for (int i = 0; i <= listDetOfert.get(0).size() - 1; i++) {
			System.out.println("offeringId: " + listDetOfert.get(0).get(i) + " initialAmt: "
					+ listDetOfert.get(1).get(i) + " unusedAmt: " + listDetOfert.get(2).get(i) + " effectiveDate: "
					+ listDetOfert.get(3).get(i) + " expireDate: " + listDetOfert.get(4).get(i) + " name: "
					+ listDetOfert.get(5).get(i));
		}
		for (int i = 0; i <= listFreeUnit.get(0).size() - 1; i++) {
			System.out.println("name: " + listFreeUnit.get(0).get(i) + " totalAmt: " + listFreeUnit.get(1).get(i)
					+ " unusedAmt: " + listFreeUnit.get(2).get(i));
		}
	}

	public void limpiarApis() {
		idSubscriber = "";
		IMSI = "";
		ICCID = "";
		subStatus = "";
		offeringId = "";
		listDetOfert.clear();
		listFreeUnit.clear();
		codeStatus = "";
		descriptionCode = "";
		statusOrderId = "";
		orderId = "";
	}

	public void crearTxt() {
		try {
			fTxt = new FileOutputStream(folderS + "\\Evidencia_Ejecución.html");
			wTxt = new OutputStreamWriter(fTxt, "ISO-8859-1");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public void escribirTxt(String linea) {
		try {
			wTxt.write(linea);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void cerrarTxt() {
		try {
			wTxt.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String obtCarpAct() {
		String temp = "";
		File miDir = new File(".");
		try {
			temp = miDir.getCanonicalPath();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
	}

	public void creaCarpeta(String cApi) {
		folder = new File(obtCarpAct() + "\\Evidencias\\");
		folderS = "";
		if (!folder.exists()) {
			folder.mkdir();
			folderS = folder.getAbsolutePath();
		} else {
			folderS = folder.getAbsolutePath();
		}
		folder = new File(obtCarpAct() + "\\Evidencias\\" + cApi + "\\");
		folderS = "";
		if (!folder.exists()) {
			folder.mkdir();
			folderS = folder.getAbsolutePath();
		} else {
			folderS = folder.getAbsolutePath();
		}
		folder = new File(
				obtCarpAct() + "\\Evidencias\\" + cApi + "\\Evidencia_" + getFecha() + "_" + getHour() + "\\");
		folderS = "";
		if (!folder.exists()) {
			folder.mkdir();
			folderS = folder.getAbsolutePath();
		} else {
			folderS = folder.getAbsolutePath();
		}
		// Configuracion();
	}

	public void creaCarpetaLoop(String cApi) {
		folder = new File(obtCarpAct() + "//Evidencias//");
		folderS = "";
		if (!folder.exists()) {
			folder.mkdir();
			folderS = folder.getAbsolutePath();
		} else {
			folderS = folder.getAbsolutePath();
		}
		folder = new File(obtCarpAct() + "//Evidencias//" + cApi + "//");
		folderS = "";
		if (!folder.exists()) {
			folder.mkdir();
			folderS = folder.getAbsolutePath();
		} else {
			folderS = folder.getAbsolutePath();
		}
		// Configuracion();
	}

	public String getFecha() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = dateformat.format(cal.getTime());
		return fecha.replace("/", "");
	}

	public String getFechaC() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = dateformat.format(cal.getTime());
		return fecha;
	}

	public String getFechaBatch() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		String fecha = dateformat.format(cal.getTime());
		return fecha.replace("-", "");
	}

	public String getFechaBdl() {
		Calendar calendarDesc = Calendar.getInstance();
		calendarDesc.add(Calendar.DATE, -1);
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		String fecha = dateformat.format(calendarDesc.getTime());
		return fecha.replace("-", "");
	}

	public String getFechaCBatch() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		String fecha = dateformat.format(cal.getTime());
		return fecha;
	}

	public String getHour() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat hourformat = new SimpleDateFormat("HH:mm:ss");
		String hour = hourformat.format(cal.getTime());
		return hour.substring(0, 2) + hour.substring(3, 5) + hour.substring(6, 8);
	}

	public void comparaProfile(AltanProfileSelenium alProfile, String cDPrueba, String dn, Word word) {
		word.CrearTitulo("Resultados de la prueba");
		word.crearTablaR();
		word.crearTablaRT();
		comparar("DN", dn, alProfile.getDn(), word);
		comparar("idSubscriber", idSubscriber, alProfile.getIdSubscriber(), word);
		comparar("IMSI", IMSI, alProfile.getIMSI(), word);
		comparar("ICCID", ICCID, alProfile.getICCID(), word);
		comparar("subStatus", subStatus, alProfile.getSubStatus(), word);
		for (int x = 0; x <= listFreeUnit.get(0).size() - 1; x++) {
			int totAm = 0;
			int totUns = 0;
			comparar("name", listFreeUnit.get(0).get(x), alProfile.getListDetOfert().get(0).get(x), word);
			for (int i = 0; i <= listDetOfert.get(0).size() - 1; i++) {
				if (listDetOfert.get(5).get(i).equals(listFreeUnit.get(0).get(x))) {
					comparar("offeringId", listDetOfert.get(0).get(i), alProfile.getListDetOfert().get(1).get(i), word);
					comparar("initialAmt", String.valueOf(listDetOfert.get(1).get(i)),
							String.valueOf(alProfile.getListDetOfert().get(2).get(i)), word);
					totAm = totAm + Integer.parseInt(alProfile.getListDetOfert().get(2).get(i));
					comparar("unusedAmt", String.valueOf(listDetOfert.get(2).get(i)),
							String.valueOf(alProfile.getListDetOfert().get(3).get(i)), word);
					totUns = totUns + Integer.parseInt(alProfile.getListDetOfert().get(3).get(i));
					comparar("effectiveDate", cFecha(listDetOfert.get(3).get(i)),
							String.valueOf(alProfile.getListDetOfert().get(4).get(i)), word);
					comparar("expireDate", cFecha(listDetOfert.get(4).get(i)),
							String.valueOf(alProfile.getListDetOfert().get(5).get(i)), word);
				}
			}
			comparar("totalAmtGeneral", listFreeUnit.get(1).get(x), String.valueOf(totAm), word);
			comparar("unusedAmtGeneral", listFreeUnit.get(2).get(x), String.valueOf(totUns), word);
		}
		word.crearSaltoL();
		word.CrearWord("Evidencia de " + cDPrueba + " " + dn, getFolderS());
	}

	public void comparaProfileG(AltanProfileSelenium alProfile, String cDPrueba, String dn, Word word) {
		word.CrearTitulo("Resultados de la prueba");
		word.crearTablaR();
		word.crearTablaRT();
		comparar("DN", dn, alProfile.getDn(), word);
		comparar("idSubscriber", idSubscriber, alProfile.getIdSubscriber(), word);
		comparar("IMSI", IMSI, alProfile.getIMSI(), word);
		comparar("ICCID", ICCID, alProfile.getICCID(), word);
		comparar("subStatus", subStatus, alProfile.getSubStatus(), word);
		for (int x = 0; x <= listFreeUnit.get(0).size() - 1; x++) {
			int totAm = 0;
			int totUns = 0;
			comparar("name", listFreeUnit.get(0).get(x), alProfile.getListDetOfert().get(0).get(x), word);
			for (int i = 0; i <= listDetOfert.get(0).size() - 1; i++) {
				if (listDetOfert.get(5).get(i).equals(listFreeUnit.get(0).get(x))) {
					comparar("offeringId", listDetOfert.get(0).get(i), alProfile.getListDetOfert().get(1).get(i), word);
					comparar("initialAmt", String.valueOf(listDetOfert.get(1).get(i)),
							String.valueOf(alProfile.getListDetOfert().get(2).get(i)), word);
					totAm = totAm + Integer.parseInt(alProfile.getListDetOfert().get(2).get(i));
					comparar("unusedAmt", String.valueOf(listDetOfert.get(2).get(i)),
							String.valueOf(alProfile.getListDetOfert().get(3).get(i)), word);
					totUns = totUns + Integer.parseInt(alProfile.getListDetOfert().get(3).get(i));
					comparar("effectiveDate", cFecha(listDetOfert.get(3).get(i)),
							String.valueOf(alProfile.getListDetOfert().get(4).get(i)), word);
					comparar("expireDate", cFecha(listDetOfert.get(4).get(i)),
							String.valueOf(alProfile.getListDetOfert().get(5).get(i)), word);
				}
			}
			comparar("totalAmtGeneral", listFreeUnit.get(1).get(x), String.valueOf(totAm), word);
			comparar("unusedAmtGeneral", listFreeUnit.get(2).get(x), String.valueOf(totUns), word);
		}
		word.crearSaltoL();
	}

	public void esperar(int time) {
		int horaActual = Integer.valueOf(getHour());
		int horaDespues = Integer.valueOf(getHour());

		while ((horaDespues - horaActual) < time) {
			horaDespues = Integer.valueOf(getHour());
		}
	}

	public void esperarDia() {
		int horaActual = Integer.valueOf(getHour());
		int horaDespues = 60000;

		while (horaActual != horaDespues) {
		}
		System.out.println("Son las 6am: " + getHour());
	}

	public void comparar(String nombre, String dato1, String dato2, Word word) {
		if (!nombre.equals("idSubscriber") && !nombre.equals("expireDate")) {
			if (dato1.equals(dato2)) {
				word.crearTablaRC(nombre, dato1, dato2, "CORRRECTO");
				bError = false;
			} else {
				word.crearTablaRC(nombre, dato1, dato2, "INCORRRECTO");
				bError = true;
			}
		} else {
			word.crearTablaRC(nombre, dato1, dato2, "REVISAR");
			bError = false;
		}
	}

	public void compararMatriz(String nombre, String dato1, String dato2, Word word) {
		if (!nombre.equals("Validity Days-") && !nombre.equals("")) {
			if (dato1.equals(dato2)) {
				word.crearTablaRC(nombre, dato1, dato2, "CORRRECTO");
				bError = false;
			} else {
				word.crearTablaRC(nombre, dato1, dato2, "INCORRRECTO");
				bError = true;
			}
		} else {
			word.crearTablaRC(nombre, dato1, dato2, "REVISAR");
			bError = false;
		}
	}

	public void compararBDL(String nombre, String dato1, String dato2, Word word) {
		if (!nombre.equals("") && !nombre.equals("")) {
			if (dato1.equals(dato2)) {
				word.crearTablaRC(nombre, dato1, dato2, "CORRRECTO");
				bError = false;
			} else {
				word.crearTablaRC(nombre, dato1, dato2, "INCORRRECTO");
				bError = true;
			}
		} else {
			word.crearTablaRC(nombre, dato1, dato2, "REVISAR");
			bError = false;
		}
	}

	public String cFecha(String fecha) {
		String dia;
		String mes;
		String ano;
		String hora;
		// int horaC;
		String minuto;
		String segundo;
		ano = fecha.substring(0, 4);
		mes = fecha.substring(4, 6);
		dia = fecha.substring(6, 8);
		hora = fecha.substring(8, 10);
		// horaC = Integer.valueOf(hora);
		/*
		 * if (horaC <= 14 && horaC >= 5) { horaC = horaC - 5; hora = "0" + horaC; }
		 * else if (horaC > 14) { horaC = horaC - 5; hora = "" + horaC; } else if (horaC
		 * == 0) { hora = "0" + horaC; }
		 */
		minuto = fecha.substring(10, 12);
		segundo = fecha.substring(12, 14);
		return dia + "/" + mes + "/" + ano + " " + hora + ":" + minuto + ":" + segundo;
	}

	public void provis() {
		System.out.println("Oferta primaria: " + getOfferingId());
		for (int i = 0; i <= listDetOfert.get(0).size() - 1; i++) {
			System.out.println("offeringId: " + listDetOfert.get(0).get(i) + " initialAmt: "
					+ listDetOfert.get(1).get(i) + " unusedAmt: " + listDetOfert.get(2).get(i) + " effectiveDate: "
					+ listDetOfert.get(3).get(i) + " expireDate: " + listDetOfert.get(4).get(i) + " name: "
					+ listDetOfert.get(5).get(i));
		}
	}

	public void sombrearObjeto(WebDriver driver, By objetoSeleccionado) {
		WebElement element_pc = driver.findElement(objetoSeleccionado);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].style.border='5px solid red'", element_pc);
	}

	public void leerColumnasMatrizOfertas(XSSFRow row, String clave) {
		for (int columna = 0; columna < row.getLastCellNum(); columna++) {
			clave = row.getCell(columna).toString();
			if (columna == 0) {
				numOferta = String.valueOf((int) Double.parseDouble(clave.trim()));
			}
			if (columna == 1) {
				idOferta = clave.trim();
			}
			if (columna == 2) {
				nomOferta = clave.trim();
			}
			if (columna == 3) {
				typeOferta = clave.trim();
			}
			if (columna == 4) {
				String temp = clave.trim();
				if (temp.equals("") || (temp.equals("N"))) {
					totalData = temp;
				} else {
					totalData = String.valueOf((int) Double.parseDouble(temp));
				}
			}
			if (columna == 5) {
				String temp = clave.trim();
				if (temp.equals("N")) {
					speedData = temp;
				} else if (temp.equals("BE")) {
					speedData = temp;
				} else {
					speedData = String.valueOf((int) Double.parseDouble(temp));
				}
			}
			if (columna == 6) {
				String temp = clave.trim();
				if (temp.equals("N")) {
					counterId = temp;
				} else {
					counterId = String.valueOf((int) Double.parseDouble(temp));
				}
			}
			if (columna == 7) {
				firstData = clave.trim();
			}
			if (columna == 8) {
				throttling = clave.trim();
			}
			if (columna == 9) {
				throttling2 = clave.trim();
			}
			if (columna == 10) {
				validity = clave.trim();
			}
			if (columna == 11) {
				renewal = clave.trim();
			}
			if (columna == 12) {
				String temp = clave.trim();
				if (temp.equals("") || temp.equals("N")) {
					buyBack = "N";
				} else {
					buyBack = temp;
				}
			}
			if (columna == 13) {
				replacement = clave.trim();
			}
			if (columna == 14) {
				String temp = clave.trim();
				if (temp.equals("Default")) {
					defaultMand = temp;
				} else if (temp.equals("Optional")) {
					defaultMand = temp;
				} else {
					defaultMand = String.valueOf((int) Double.parseDouble(temp));
				}
			}
			if (columna == 15) {
				optionalAtach = clave.trim();
			}
			if (columna == 16) {
				dependency = clave.trim();
			}
			if (columna == 17) {
				redirect = clave.trim();
			}
			if (columna == 18) {
				urlRedirect = clave.trim();
			}
			if (columna == 19) {
				productType = clave.trim();
			}
			if (columna == 20) {
				apn = clave.trim();
			}
			if (columna == 21) {
				String temp = clave.trim();
				if (temp.equals("") || temp.equals("N")) {
					notification = temp;
				} else {
					notification = String.valueOf((int) (Double.parseDouble(temp) * 100));
				}
			}
			if (columna == 22) {
				pcrfProfile = clave.trim();
			}
		}
	}

	public void ingresarInfoOferta(Word word) {
		word.crearSaltoL();
		word.CrearParrafo("Información de la matriz de ofertas:");
		word.CrearParrafo("Número Oferta: " + numOferta);
		word.CrearParrafo("ID Oferta: " + idOferta);
		word.CrearParrafo("Nombre de Oferta: " + nomOferta);
		word.CrearParrafo("Tipo de Oferta: " + typeOferta);
		word.CrearParrafo("Total Data: " + totalData);
		word.CrearParrafo("Speed Data: " + speedData);
		word.CrearParrafo("Policy Counter ID: " + counterId);
		word.CrearParrafo("First Data: " + firstData);
		word.CrearParrafo("Throttling: " + throttling);
		word.CrearParrafo("Throttling 128kbp: " + throttling2);
		word.CrearParrafo("Validity Days: " + validity);
		word.CrearParrafo("Renewal: " + renewal);
		word.CrearParrafo("Buyback: " + buyBack);
		word.CrearParrafo("Replacement: " + replacement);
		word.CrearParrafo("Optional Attach: " + optionalAtach);
		word.CrearParrafo("Redirect: " + redirect);
		word.CrearParrafo("URL Redirect: " + urlRedirect);
		word.CrearParrafo("Product Type: " + productType);
		word.CrearParrafo("APN: " + apn);
		word.CrearParrafo("Notificación %: " + notification);
		word.CrearParrafo("PCRF Profile: " + pcrfProfile);
		System.out.println(numOferta + " " + idOferta + " " + nomOferta + " " + typeOferta + " " + totalData + " "
				+ speedData + " " + firstData + " " + throttling + " " + throttling2 + " " + validity + " "
				+ defaultMand + " " + redirect);
	}

	public void revisarRedirect(String cRedirect, Word word) {
		cRedirect = obtenerGM(redirect);
		if (cRedirect.equals("N")) {
			for (int i = 0; i <= listDetOfert.get(0).size() - 1; i++) {
				if (listDetOfert.get(5).get(i).equals("Free Redirect")) {
					compararMatriz("Free Redirect", listDetOfert.get(1).get(i), cRedirect, word);
					AltanSpeedTest speedTest = new AltanSpeedTest();
					speedTest.capturaVelocidad(Double.parseDouble(speedData), word);
					compararMatriz("Free Redirect", listDetOfert.get(1).get(i), cRedirect, word);
					validaDifDias(validity, listDetOfert.get(3).get(i), listDetOfert.get(4).get(i), word);
				}
			}
		} else {
			for (int i = 0; i <= listDetOfert.get(0).size() - 1; i++) {
				if (listDetOfert.get(5).get(i).equals("Free Redirect") && !cRedirect.equals("")) {
					AltanSpeedTest speedTest = new AltanSpeedTest();
					speedTest.capturaVelocidad(Double.parseDouble(speedData), word);
					compararMatriz("Free Redirect", listDetOfert.get(1).get(i), cRedirect, word);
					validaDifDias(validity, listDetOfert.get(3).get(i), listDetOfert.get(4).get(i), word);
				}
			}
		}
	}

	public void revisarFreeUnits(int i, Word word) {
		if (cFirstData.equals("N") && fu == 0) {
			System.out.println("No tiene Free Units");
			if (listDetOfert.get(5).get(i).equals("Free Units")) {
				System.out.println("No deberia tener Free Units: " + listDetOfert.get(1).get(i));
			}
			fu++;
		} else {
			if (listDetOfert.get(5).get(i).equals("Free Units") && fu == 0) {
				sumaBolsas += (int) Double.parseDouble(cFirstData);
				sumaBolsasApi += (int) Double.parseDouble(listDetOfert.get(1).get(i));
				String temp = calcularGM(firstData, listDetOfert.get(1).get(i));
				compararMatriz("Free Units", temp, cFirstData, word);
				fu++;
				validaDifDias(validity, listDetOfert.get(3).get(i), listDetOfert.get(4).get(i), word);
			}
		}
	}

	public void revisarThrottling(int i, Word word) {
		if (cThrottling.equals("N") && thro == 0) {
			System.out.println("No tiene Free Throttling");
			if (listDetOfert.get(5).get(i).equals("Free Throttling")) {
				System.out.println("No deberia tener Free Throttling: " + listDetOfert.get(1).get(i));
			}
			thro++;
		} else {
			if (listDetOfert.get(5).get(i).equals("Free Throttling") && thro == 0) {
				sumaBolsas += (int) Double.parseDouble(cThrottling);
				sumaBolsasApi += (int) Double.parseDouble(listDetOfert.get(1).get(i));
				String temp = calcularGM(throttling, listDetOfert.get(1).get(i));
				compararMatriz("Free Throttling", temp, cThrottling, word);
				thro++;
				validaDifDias(validity, listDetOfert.get(3).get(i), listDetOfert.get(4).get(i), word);
			}
		}
	}

	public void revisarThrottling2(int i, Word word) {
		if (cThrottling2.equals("N") && thro2 == 0) {
			System.out.println("No tiene Free Throttling 128kbp");
			if (listDetOfert.get(5).get(i).equals("Free Throttling 128kbp")) {
				System.out.println("No deberia tener Free Throttling 128kbp: " + listDetOfert.get(1).get(i));
			}
			thro2++;
		} else {
			if (listDetOfert.get(5).get(i).equals("Free Throttling 128kbp") && thro2 == 0) {
				sumaBolsas += (int) Double.parseDouble(cThrottling2);
				sumaBolsasApi += (int) Double.parseDouble(listDetOfert.get(1).get(i));
				String temp = calcularGM(throttling2, listDetOfert.get(1).get(i));
				compararMatriz("Free Throttling 128kbp", temp, cThrottling2, word);
				thro2++;
				validaDifDias(validity, listDetOfert.get(3).get(i), listDetOfert.get(4).get(i), word);
			}
		}
	}

	public void revisarPolicyCounter(Word word, String oferta, String usuario, String password, String urlBss,
			String policyCounterMatriz) {
		if (policyCounterMatriz.equals("N")) {
			word.CrearTitulo("Inicia validación de PolicyCounter");
			word.CrearParrafo("La oferta " + oferta + " no tiene PolicyCounter");
		} else {
			AltanPolicyCounter pc = new AltanPolicyCounter();
			pc.mainPolicyCounter(word, oferta, usuario, password, urlBss, policyCounterMatriz);
		}
	}

	public void validacionFinalBuyBack(Word word, Herramientas herram, String buyback) {
		word.CrearParrafo("La matriz de ofertas nos indica que esta oferta es Renovable: " + buyback);
		if (buyback.equals("Y")) {
			if (herram.getBError() == false) {
				word.CrearParrafo(
						"El resultado de la prueba es OK ya que la oferta esta configurada como BUYBACK = " + buyback);
			} else {
				word.CrearParrafo("El resultado de la prueba es NOK ya que la oferta esta configurada como BUYBACK = "
						+ buyback + " y no permite realizar la compra de excedentes");
			}
		} else if (buyback.equals("Y(if primary exists)")) {
			if (herram.getBError() == false) {
				word.CrearParrafo(
						"El resultado de la prueba es OK ya que la oferta esta configurada como BUYBACK = " + buyback);
			} else {
				word.CrearParrafo("El resultado de la prueba es NOK ya que la oferta esta configurada como BUYBACK = "
						+ buyback + " y no permite realizar la compra de excedentes");
			}
		} else if (buyback.equals("Y(1 at a time)")) {
			if (herram.getBError() == false) {
				word.CrearParrafo(
						"El resultado de la prueba es OK ya que la oferta esta configurada como BUYBACK = " + buyback);
			} else {
				word.CrearParrafo("El resultado de la prueba es NOK ya que la oferta esta configurada como BUYBACK = "
						+ buyback + " y no permite realizar la compra de excedentes");
			}
		}
		herram.setBError(false);
	}

	public void revisarBuyBack(Word word, Herramientas herram, String buyback, String oferta, String dn,
			String proyecto, String ejec, String cv, String amb, String oUser, String oPassword, String pId,
			String oId) {
		word.CrearTitulo("Inicia validación de BuyBack");
		if (buyback.equals("N")) {
			word.CrearParrafo("La oferta " + oferta + " no volver a comprarse");
		} else {
			AltanOfferSupplemenary aOSupplementary = new AltanOfferSupplemenary();
			aOSupplementary.consultarOfferSupplemenaryG(dn, idOferta, "", amb, oUser, oPassword, pId, oId, herram,
					word);
			if (herram.getBError() == false) {
				AltanQueryOrder aQOrder = new AltanQueryOrder();
				word.crearTabla(proyecto, cv, amb, ejec, "Query Order", herram.getFechaC());
				word.CrearTitulo("Inicia prueba de APIGW Query Order");
				aQOrder.consultarQueryOrderG(herram.getIdOrden(), "Query Order", amb, oUser, oPassword, pId, oId,
						herram, word);
				herram.esperar(3);
				aQOrder.consultarQueryOrderG(herram.getIdOrden(), "Query Order", amb, oUser, oPassword, pId, oId,
						herram, word);
				herram.setIdOrden("");
				System.out.println("Es lo que tiene la banera: " + buyback);
			}
			validacionFinalBuyBack(word, herram, buyback);
		}
	}

	public void revisarValidityDate(Word word, String oferta, String usuario, String password, String urlBss,
			String mtrzRenewal, String mtrzRedirect, String mtrzValidityDate, String mtrzType) {
		AltanValidityDate vd = new AltanValidityDate();
		try {
			mtrzValidityDate = String.valueOf((int) Double.parseDouble(mtrzValidityDate));
		} catch (NumberFormatException e) {
		}
		vd.mainValidityDate(oferta, usuario, password, urlBss, word, mtrzRenewal, mtrzRedirect, mtrzValidityDate,
				mtrzType);
	}

	public void revisarAPN(Word word, String usuario, String password, String urlBss, String oferta, String tipoOferta,
			String matrizAPN) {
		AltanValidateAPN vAPN = new AltanValidateAPN();
		vAPN.mainValidityDate(word, usuario, password, urlBss, oferta, tipoOferta, matrizAPN);
	}

	public void agregarOtionalAttach(String linea, ArrayList<String> arrayList) {
		if (!linea.trim().equals("") && !linea.trim().equals("N")) {
			String[] separado = linea.trim().split(",");
			for (int i = 0; i < separado.length; i++) {
				arrayList.add(String.valueOf((int) Double.parseDouble(separado[i])));
			}
		}
	}

	public void agregarReplacement(ArrayList<String> lista, String a, String b) {
		if (!b.trim().equals("") && !b.trim().equals("N")) {
			String[] separado = b.trim().split(",");
			if (separado.length == 1) {
				for (int i = 0; i < separado.length; i++) {
					lista.add(String.valueOf((int) Double.parseDouble(separado[i])));
				}
			} else {
				for (int i = 0; i < separado.length; i++) {
					lista.add(String.valueOf((int) Double.parseDouble(separado[i])));
					lista.add(a);
				}
			}
		}
	}

	public void revisarReplacement(Word word, Herramientas herram, String oferta, String dn, String proyecto,
			String ejec, String cv, String amb, String oUser, String oPassword, String pId, String oId,
			String coordenadas) {
		word.crearSaltoL();
		word.CrearTitulo("Inicia validación de Replacement con Offerta: " + oferta);
		AltanChangePrimary aChangePrimary = new AltanChangePrimary();
		aChangePrimary.consultarChangePrimaryG(dn, coordenadas, oferta, "", amb, oUser, oPassword, pId, oId, herram,
				word);
		if (herram.getBError() == false) {
			AltanQueryOrder aQOrder = new AltanQueryOrder();
			word.crearTabla(proyecto, cv, amb, ejec, "Query Order", herram.getFechaC());
			word.CrearTitulo("Inicia prueba de APIGW Query Order");
			aQOrder.consultarQueryOrderG(herram.getIdOrden(), "Query Order", amb, oUser, oPassword, pId, oId, herram,
					word);
			herram.esperar(3);
			aQOrder.consultarQueryOrderG(herram.getIdOrden(), "Query Order", amb, oUser, oPassword, pId, oId, herram,
					word);
			herram.setIdOrden("");
		}
		validacionFinalReplacement(word, herram);
		word.CrearTitulo("Termina validación de Replacement con Offerta: " + oferta);
	}

	public void revisarOptional(Word word, Herramientas herram, String oferta, String dn, String proyecto, String ejec,
			String cv, String amb, String oUser, String oPassword, String pId, String oId, String coordenadas) {
		word.crearSaltoL();
		word.CrearTitulo("Inicia validación de OptionalAttach con Offerta: " + oferta);
		AltanOfferSupplemenary aAltanOfferSupplemenary = new AltanOfferSupplemenary();
		aAltanOfferSupplemenary.consultarOfferSupplemenaryG(dn, oferta, "", amb, oUser, oPassword, pId, oId, herram,
				word);
		if (herram.getBError() == false) {
			AltanQueryOrder aQOrder = new AltanQueryOrder();
			word.crearTabla(proyecto, cv, amb, ejec, "Query Order", herram.getFechaC());
			word.CrearTitulo("Inicia prueba de APIGW Query Order");
			aQOrder.consultarQueryOrderG(herram.getIdOrden(), "Query Order", amb, oUser, oPassword, pId, oId, herram,
					word);
			herram.esperar(3);
			aQOrder.consultarQueryOrderG(herram.getIdOrden(), "Query Order", amb, oUser, oPassword, pId, oId, herram,
					word);
			herram.setIdOrden("");
		}
		validacionFinalOptional(word, herram);
		word.CrearTitulo("Termina validación de OptionalAttach con Offerta: " + oferta);
	}

	public void validacionFinalReplacement(Word word, Herramientas herram) {
		if (herram.getBError() == false) {
			word.CrearParrafo("El resultado de la prueba es OK ya que se pudo realizar el cambio de la oferta");
		} else {
			word.CrearParrafo("El resultado de la prueba es NOK ya que NO se pudo realizar el cambio de la oferta");
		}
		herram.setBError(false);
	}

	public void validacionFinalOptional(Word word, Herramientas herram) {
		if (herram.getBError() == false) {
			word.CrearParrafo("El resultado de la prueba es OK ya que se pudo realizar la compra de bono excedente");
		} else {
			word.CrearParrafo(
					"El resultado de la prueba es NOK ya que NO se pudo realizar la compra de bono excedente");
		}
		herram.setBError(false);
	}

	public void leerMatrizOfertas(Word word, Herramientas herram, XSSFWorkbook nWorkbook, String dn, String userBSS,
			String passBSS, String ligaBSS, String proyecto, String ejec, String cv, String amb, String oUser,
			String oPassword, String pId, String oId, String coordenadas) {
		XSSFSheet nSheet = nWorkbook.getSheetAt(1);
		XSSFRow row = null;
		String clave = "";
		provis();
		vecesPrimary = 0;
		vecesDefault = 0;
		vecesOptionalA = 0;
		vecesReplace = 0;
		ArrayList<String> aListReplace = new ArrayList<String>();
		ArrayList<String> aListOptAtach = new ArrayList<String>();
		for (int fila = 1; fila < nSheet.getLastRowNum() + 1; fila++) {
			row = nSheet.getRow(fila);
			leerColumnasMatrizOfertas(row, clave);
			sumaBolsas = 0;
			sumaBolsasApi = 0;
			if (getOfferingId().equals(idOferta) && typeOferta.equals("Primary") && vecesPrimary == 0) {
				ingresarInfoOferta(word);
				vecesPrimary++;
				defaultMand2 = defaultMand;
				agregarReplacement(aListReplace, numOferta, replacement);
				agregarOtionalAttach(optionalAtach, aListOptAtach);
				revisarPolicyCounter(word, idOferta, userBSS, passBSS, ligaBSS, counterId);
				word.crearSaltoL();
				revisarValidityDate(word, idOferta, userBSS, passBSS, ligaBSS, renewal, redirect, validity, typeOferta);
				word.crearSaltoL();
				revisarAPN(word, userBSS, passBSS, ligaBSS, idOferta, typeOferta, apn);
				word.crearSaltoL();

				word.CrearTitulo(
						"Inicia validación de Free Redirect, Free Units, Free Throttling, Free Throttl	ing 128kbp, Validity Days, Total Data");
				word.crearTablaR();
				word.crearTablaRTOMatriz();
				revisarRedirect(redirect, word);
				word.crearSaltoL();

				fila = 0;
			} else if (defaultMand2.equals(numOferta) && vecesDefault == 0) {
				vecesDefault++;
				ingresarInfoOferta(word);
				cFirstData = obtenerGM(firstData);
				cThrottling = obtenerGM(throttling);
				cThrottling2 = obtenerGM(throttling2);
				fu = 0;
				thro = 0;
				thro2 = 0;
				for (int i = 0; i <= listDetOfert.get(0).size() - 1; i++) {
					revisarFreeUnits(i, word);
					revisarThrottling(i, word);
					revisarThrottling2(i, word);
				}
				compararMatriz("Total Data", calcularGM("G", String.valueOf(sumaBolsasApi)), String.valueOf(sumaBolsas),
						word);
				word.crearSaltoL();
				revisarBuyBack(word, herram, buyBack, idOferta, dn, proyecto, ejec, cv, amb, oUser, oPassword, pId,
						oId);
				revisarValidityDate(word, idOferta, userBSS, passBSS, ligaBSS, renewal, cRedirect, validity,
						typeOferta);
				fila = 0;
			} else if (vecesPrimary == 1 && vecesDefault == 1 && vecesOptionalA != aListOptAtach.size()) {
				if (aListOptAtach.get(vecesOptionalA).equals(numOferta)) {
					revisarOptional(word, herram, idOferta, dn, proyecto, ejec, cv, amb, oUser, oPassword, pId, oId,
							coordenadas);
					vecesOptionalA++;
					fila = 0;
				}
			} else if (vecesPrimary == 1 && vecesDefault == 1 && vecesOptionalA == aListOptAtach.size()
					&& vecesReplace != aListReplace.size()) {
				if (aListReplace.get(vecesReplace).equals(numOferta)) {
					revisarReplacement(word, herram, idOferta, dn, proyecto, ejec, cv, amb, oUser, oPassword, pId, oId,
							coordenadas);
					vecesReplace++;
					fila = 0;
				}
			}
		}
	}

	public String calcularGM(String dato, String dato2) {
		String temp = "";
		if (dato.replaceAll("[^a-zA-Z]", "").equals("G") && !dato.equals("N")) {
			temp = String.valueOf(Integer.parseInt(dato2.replaceAll("[^0-9]", "")) / GIGABYTE);
		} else if (dato.replaceAll("[^a-zA-Z]", "").equals("MB") && !dato.equals("N")) {
			temp = String.valueOf(Integer.parseInt(dato2.replaceAll("[^0-9]", "")) / MEGABYTE);
		}
		return temp;
	}

	public void validaDifDias(String validity, String fechaEf, String fechaEx, Word word) {
		if (!validity.equals("According with bill cycle") && !validity.equals("Depend of Default Supplementary")
				&& !validity.equals("Calendar Month")) {
			int anoBDLEfA = Integer.valueOf(fechaEf.substring(0, 4));
			int mesBDLEfA = Integer.valueOf(fechaEf.substring(4, 6));
			int diaBDLEfA = Integer.valueOf(fechaEf.substring(6, 8));
			int anoBDLExA = Integer.valueOf(fechaEx.substring(0, 4));
			int mesBDLExA = Integer.valueOf(fechaEx.substring(4, 6));
			int diaBDLExA = Integer.valueOf(fechaEx.substring(6, 8));
			Calendar calendarEfA = Calendar.getInstance();
			Calendar calendarExA = Calendar.getInstance();
			calendarEfA.set(anoBDLEfA, mesBDLEfA - 1, diaBDLEfA, 1, 1);
			calendarExA.set(anoBDLExA, mesBDLExA - 1, diaBDLExA, 1, 1);
			// SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
			long startTime = calendarEfA.getTimeInMillis();
			long endTime = calendarExA.getTimeInMillis();
			long diffTime = endTime - startTime;
			long diffDays = diffTime / (1000 * 60 * 60 * 24);
			compararMatriz("Validity Days", String.valueOf(diffDays),
					String.valueOf((int) Double.parseDouble(validity) + 1), word);
		} else {
			compararMatriz("Validity Days-", cFecha(fechaEx), validity, word);
		}
	}

	public String obtenerGM(String dato) {
		String temp = "";
		if (dato.replaceAll("[^a-zA-Z]", "").equals("G") && !dato.equals("N")) {
			temp = dato.replaceAll("[^0-9]", "");
			System.out.println(temp);
		} else if (dato.replaceAll("[^a-zA-Z]", "").equals("MB") && !dato.equals("N")) {
			temp = dato.replaceAll("[^0-9]", "");
			System.out.println(temp);
		} else {
			temp = "N";
		}
		return temp;
	}

	public void ftpAnalisis(List<List<String>> listaLog, List<List<String>> listaDns, String pPathFile, Word word) {
		try {
			for (int i = 0; i <= 7; i++) {
				listaLog.add(new ArrayList<String>());
			}
			for (int i = 0; i <= 3; i++) {
				listaDns.add(new ArrayList<String>());
			}
			// FileReader fr = new FileReader(herram.getFolderS() + "//" + archFTP2);
			FileReader fr;
			fr = new FileReader(pPathFile);
			BufferedReader br = new BufferedReader(fr);
			String linea = "";
			while ((linea = br.readLine()) != null) {
				String[] separado = linea.split(",");
				for (int i = 0; i < separado.length; i++) {
					listaLog.get(i).add(separado[i]);
				}
			}
			br.close();
			int cpOK = 0;
			int cpNOK = 0;
			List<String> temp = new ArrayList<>();
			temp.clear();
			for (int i = 0; i <= listaLog.get(1).size() - 1; i++) {
				temp.add(listaLog.get(1).get(i));
			}
			temp = temp.stream().distinct().collect(Collectors.toList());
			for (int i = 0; i <= temp.size() - 1; i++) {
				listaDns.get(1).add(temp.get(i));
			}
			int temp2 = 0;
			for (int i = 0; i <= listaLog.get(0).size() - 1; i++) {
				if (listaLog.get(7).get(i).trim().equals("ok")) {
					cpOK++;
					for (int a = 0; a <= listaDns.get(1).size() - 1; a++) {
						if (listaLog.get(1).get(i).equals(listaDns.get(1).get(a))) {
							try {
								temp2 = Integer.parseInt(listaDns.get(2).get(a));
								temp2++;
								listaDns.get(2).set(a, String.valueOf(temp2));
							} catch (IndexOutOfBoundsException e) {
								listaDns.get(2).add(String.valueOf(1));
							}
						}
					}
				} else if (listaLog.get(7).get(i).trim().equals("no-ok")) {
					cpNOK++;
					for (int a = 0; a <= listaDns.get(1).size() - 1; a++) {
						if (listaLog.get(1).get(i).equals(listaDns.get(1).get(a))) {
							try {
								temp2 = Integer.parseInt(listaDns.get(3).get(a));
								temp2++;
								listaDns.get(3).set(a, String.valueOf(temp2));
							} catch (IndexOutOfBoundsException e) {
								listaDns.get(3).add(String.valueOf(1));
							}
						}
					}
				}
				for (int x = 0; x <= 7; x++) {
					System.out.print(listaLog.get(x).get(i) + "|");
				}
				System.out.println("");
			}
			word.CrearParrafo("Casos exitosos son: " + cpOK);
			word.CrearParrafo("Casos NO exitosos son: " + cpNOK);
			for (int a = 0; a <= listaDns.get(1).size() - 1; a++) {
				System.out.print(listaDns.get(1).get(a) + "|");
				try {
					System.out.print(listaDns.get(2).get(a) + "|");
				} catch (IndexOutOfBoundsException e) {
					listaDns.get(2).add(String.valueOf(0));
					System.out.print(listaDns.get(2).get(a) + "|");
				}
				try {
					System.out.print(listaDns.get(3).get(a) + "|");
				} catch (IndexOutOfBoundsException e) {
					listaDns.get(3).add(String.valueOf(0));
					System.out.print(listaDns.get(3).get(a) + "|");
				}
				System.out.println("");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void escribirLog(String rutaArchivo, String apiOp, String pId, String dn, String ordId, String codeResp,
			String tiempoResp, int tipoMensaje, String detall) {
		NoticiacionCorreo ml = new NoticiacionCorreo();
		System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tF %1$tT] [%4$-7s] %5$s %n");
		Logger logger = Logger.getLogger("MyLog");
		FileHandler fh;
		String msjCorreo = "BE_ID: " + pId + "\n" + "MSISDN: " + dn + "\n" + "Order_ID: " + ordId + "\n" + "Operación: "
				+ apiOp + "\n" + "codeHttp: " + codeResp + "\n" + "time: " + tiempoResp;
		String msj = pId + "|" + apiOp + "|" + codeResp + "|" + tiempoResp;
		try {
			fh = new FileHandler(rutaArchivo, true);
			logger.addHandler(fh);
			logger.setLevel(Level.ALL);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
			if (tipoMensaje == 0) {
				logger.log(Level.FINE, msj);
			} else if (tipoMensaje == 1) {
				logger.log(Level.WARNING, msj);
			} else if (tipoMensaje == 2) {
				logger.log(Level.SEVERE, msj);
				ml.emailEnvio(msjCorreo + "\nDescription: " + detall);
			}
			fh.close();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void escribirLogTrazas(String rutaArchivo, String detalle) {
		System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tF %1$tT] [%4$-7s] %5$s %n");
		Logger logger = Logger.getLogger("MyLog");
		FileHandler fh;
		try {
			fh = new FileHandler(rutaArchivo, true);
			logger.addHandler(fh);
			logger.setLevel(Level.ALL);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
			logger.log(Level.FINE, detalle);
			fh.close();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean buscarCodeError(String datoBuscado) {
		String[] codigosErrores = { "1211000472", "1211000602", "1211000599", "1251000005", "2002026002", "2002026003",
				"2002026072", "2002026135", "1211000106", "1211000109", "1219080896", "2001002010", "2001030001",
				"14803005", "102050432" };
		boolean bEncontrado = false;
		for (int n = 0; n < codigosErrores.length; n++) {
			if (datoBuscado.equals(codigosErrores[n])) {
				bEncontrado = true;
				break;
			} else {
				bEncontrado = false;
			}
		}
		return bEncontrado;
	}
}