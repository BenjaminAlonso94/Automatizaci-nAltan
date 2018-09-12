package apisaltanjson.tipoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import herramientas.Herramientas;
import herramientas.Word;

public class AltanQueryOrder {

	public void consultarQueryOrder(String dn, String cDPrueba, String ambiente, String oUser, String oPassword,
			String pId, String oId, Herramientas herram, Word word) {
		URL myURL = null;
		HttpURLConnection myURLConnection = null;
		StringBuilder response;
		String line = "";
		String estatusR = "";
		BufferedReader reader = null;
		try {
			myURL = new URL("https://altanredes-" + ambiente + ".apigee.net/ac/v1/orders/" + dn);
			myURLConnection = (HttpURLConnection) myURL.openConnection();
			myURLConnection.setRequestMethod("GET");
			myURLConnection.setRequestProperty("Authorization", "Bearer " + herram.getAccessToken());
			myURLConnection.setRequestProperty("Content-Type", "application/json");
			if (!oUser.equals("") && !oPassword.equals("") && !pId.equals("") && !oId.equals("")) {
				myURLConnection.setRequestProperty("Operation-User", oUser);
				myURLConnection.setRequestProperty("Operation-Password", oPassword);
				myURLConnection.setRequestProperty("PartnerId", pId);
				myURLConnection.setRequestProperty("OperatorId", oId);
			}
			myURLConnection.setUseCaches(false);
			myURLConnection.setDoInput(true);
			myURLConnection.setDoOutput(true);
			myURLConnection.connect();
			estatusR = myURLConnection.getResponseMessage();
			if (estatusR.equals("Bad Request")) {
				word.CrearParrafo("Se obtiene el detalle de Bad Request:");
				reader = new BufferedReader(new InputStreamReader(myURLConnection.getErrorStream()));
				herram.limpiarApis();
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
					word.CrearParrafoSJ(line);
				}
				word.CrearTitulo("Termina prueba de APIGW con IDOrder: " + dn);
				word.CrearWord("Evidencia de Error de CP " + cDPrueba, herram.getFolderS());
				herram.setBError(true);
			} else if (estatusR.equals("OK")) {
				word.CrearParrafo("Se obtiene el detalle OK:");
				reader = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
					word.CrearParrafoSJ(line);
				}
				word.CrearTitulo("Termina prueba de APIGW con IDOrder: " + dn);
				word.CrearWord("Evidencia de CP " + cDPrueba, herram.getFolderS());
				herram.setBError(false);
			} else {
				word.CrearParrafo("Se obtiene el detalle de error:");
				reader = new BufferedReader(new InputStreamReader(myURLConnection.getErrorStream()));
				herram.limpiarApis();
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
					word.CrearParrafoSJ(line);
				}
				word.CrearTitulo("Termina prueba de APIGW con IDOrder: " + dn);
				word.CrearWord("Evidencia de Error de CP " + cDPrueba, herram.getFolderS());
				herram.setBError(true);
			}

		} catch (IOException e) {
			e.printStackTrace();
			word.CrearParrafo(e.getLocalizedMessage());
			word.CrearTitulo("Termina prueba de APIGW con IDOrder: " + dn);
			word.CrearWord("Evidencia de Error de CP " + cDPrueba, herram.getFolderS());
			herram.setBError(true);
		}
	}

	public void consultarQueryOrderG(String dn, String cDPrueba, String ambiente, String oUser, String oPassword,
			String pId, String oId, Herramientas herram, Word word) {
		URL myURL = null;
		HttpURLConnection myURLConnection = null;
		StringBuilder response;
		String line = "";
		String estatusR = "";
		BufferedReader reader = null;
		try {
			myURL = new URL("https://altanredes-" + ambiente + ".apigee.net/ac/v1/orders/" + dn);
			myURLConnection = (HttpURLConnection) myURL.openConnection();
			myURLConnection.setRequestMethod("GET");
			myURLConnection.setRequestProperty("Authorization", "Bearer " + herram.getAccessToken());
			myURLConnection.setRequestProperty("Content-Type", "application/json");
			if (!oUser.equals("") && !oPassword.equals("") && !pId.equals("") && !oId.equals("")) {
				myURLConnection.setRequestProperty("Operation-User", oUser);
				myURLConnection.setRequestProperty("Operation-Password", oPassword);
				myURLConnection.setRequestProperty("PartnerId", pId);
				myURLConnection.setRequestProperty("OperatorId", oId);
			}
			myURLConnection.setUseCaches(false);
			myURLConnection.setDoInput(true);
			myURLConnection.setDoOutput(true);
			myURLConnection.connect();
			estatusR = myURLConnection.getResponseMessage();
			if (estatusR.equals("Bad Request")) {
				word.CrearParrafo("Se obtiene el detalle de Bad Request:");
				reader = new BufferedReader(new InputStreamReader(myURLConnection.getErrorStream()));
				herram.limpiarApis();
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
					word.CrearParrafoSJ(line);
				}
				word.CrearTitulo("Termina prueba de APIGW con IDOrder: " + dn);
				herram.setBError(true);
			} else if (estatusR.equals("OK")) {
				word.CrearParrafo("Se obtiene el detalle OK:");
				reader = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
					word.CrearParrafoSJ(line);
				}
				word.CrearTitulo("Termina prueba de APIGW con IDOrder: " + dn);
				herram.setBError(false);
			} else {
				word.CrearParrafo("Se obtiene el detalle de error:");
				reader = new BufferedReader(new InputStreamReader(myURLConnection.getErrorStream()));
				herram.limpiarApis();
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
					word.CrearParrafoSJ(line);
				}
				word.CrearTitulo("Termina prueba de APIGW con IDOrder: " + dn);
				herram.setBError(true);
			}

		} catch (IOException e) {
			e.printStackTrace();
			word.CrearParrafo(e.getLocalizedMessage());
			word.CrearTitulo("Termina prueba de APIGW con IDOrder: " + dn);
			herram.setBError(true);
		}
	}

	public void consultarQueryOrderLoop(String dn, String cDPrueba, String ambiente, String oUser, String oPassword,
			String pId, String oId, Herramientas herram) {
		String rutaArchivoOK = herram.getFolderS() + "//LogGenerealOK.log";
		String rutaArchivoNOK = herram.getFolderS() + "//LogGenerealNOK.log";
		String rutaArchivoTraza = herram.getFolderS() + "//LogGenerealTraza.log";
		URL myURL = null;
		HttpURLConnection myURLConnection = null;
		StringBuilder response;
		String line = "";
		String estatusR = "";
		BufferedReader reader = null;
		Calendar calendarActual = Calendar.getInstance();
		try {
			myURL = new URL("https://altanredes-" + ambiente + ".apigee.net/ac/v1/orders/" + dn);
			myURLConnection = (HttpURLConnection) myURL.openConnection();
			myURLConnection.setRequestMethod("GET");
			myURLConnection.setRequestProperty("Authorization", "Bearer " + herram.getAccessToken());
			myURLConnection.setRequestProperty("Content-Type", "application/json");
			if (!oUser.equals("") && !oPassword.equals("") && !pId.equals("") && !oId.equals("")) {
				myURLConnection.setRequestProperty("Operation-User", oUser);
				myURLConnection.setRequestProperty("Operation-Password", oPassword);
				myURLConnection.setRequestProperty("PartnerId", pId);
				myURLConnection.setRequestProperty("OperatorId", oId);
			}
			myURLConnection.setUseCaches(false);
			myURLConnection.setDoInput(true);
			myURLConnection.setDoOutput(true);
			myURLConnection.connect();
			Calendar calendarDespues = Calendar.getInstance();
			DateFormat hourFormat = new SimpleDateFormat("mm:ss:SSS");
			Date d1 = calendarActual.getTime();
			Date d2 = calendarDespues.getTime();
			Date diff = new Date(d2.getTime() - d1.getTime());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(diff);
			String tiempoResp = hourFormat.format(calendar.getTime());
			estatusR = myURLConnection.getResponseMessage();
			int codeResp = myURLConnection.getResponseCode();
			if (estatusR.equals("Bad Request")) {
				System.out.println("Se obtiene el detalle de Bad Request:");
				herram.escribirLogTrazas(rutaArchivoTraza, "Se obtiene el detalle de Bad Request:");
				reader = new BufferedReader(new InputStreamReader(myURLConnection.getErrorStream()));
				herram.limpiarApis();
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
					System.out.println(line);
				}
				herram.escribirLogTrazas(rutaArchivoTraza, response.toString());
				System.out.println("Termina prueba de APIGW con IdOrder: " + dn);
				herram.escribirLogTrazas(rutaArchivoTraza, "Termina prueba de APIGW con DN: " + dn);
				herram.setBError(true);
				herram.escribirLog(rutaArchivoNOK, "QueryOrder", pId, dn, "", String.valueOf(codeResp), tiempoResp, 1,
						"");
			} else if (estatusR.equals("OK")) {
				System.out.println("Se obtiene el detalle OK:");
				herram.escribirLogTrazas(rutaArchivoTraza, "Se obtiene el detalle OK:");
				reader = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
					System.out.println(line);
				}
				herram.escribirLogTrazas(rutaArchivoTraza, response.toString());
				System.out.println("Termina prueba de APIGW con IdOrder: " + dn);
				herram.escribirLogTrazas(rutaArchivoTraza, "Termina prueba de APIGW con IdOrder: " + dn);
				herram.readObjectJson(response.toString());
				herram.setBError(false);
				if (herram.getStatusOrderId().equals("Suspended")) {
					herram.escribirLog(rutaArchivoNOK, "QueryOrder", pId, dn, herram.getOrderId(),
							String.valueOf(codeResp), tiempoResp, 2, "Suspend");
				} else {
					herram.escribirLog(rutaArchivoOK, "QueryOrder", pId, dn, herram.getOrderId(),
							String.valueOf(codeResp), tiempoResp, 0, "");
				}
			} else {
				System.out.println("Se obtiene el detalle de error:");
				herram.escribirLogTrazas(rutaArchivoTraza, "Se obtiene el detalle de error:");
				reader = new BufferedReader(new InputStreamReader(myURLConnection.getErrorStream()));
				herram.limpiarApis();
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
					System.out.println(line);
				}
				herram.escribirLogTrazas(rutaArchivoTraza, response.toString());
				System.out.println("Termina prueba de APIGW con IdOrder: " + dn);
				herram.escribirLogTrazas(rutaArchivoTraza, "Termina prueba de APIGW con DN: " + dn);
				herram.setBError(true);
				herram.escribirLog(rutaArchivoNOK, "QueryOrder", pId, dn, herram.getOrderId(), String.valueOf(codeResp),
						tiempoResp, 1, "");
			}

		} catch (IOException e) {
			e.printStackTrace();
			herram.escribirLogTrazas(rutaArchivoTraza, e.getMessage());
			System.out.println("Termina prueba de APIGW con IDOrder: " + dn);
			herram.escribirLogTrazas(rutaArchivoTraza, "Termina prueba de APIGW con DN: " + dn);
			herram.setBError(true);
		}
	}
}
