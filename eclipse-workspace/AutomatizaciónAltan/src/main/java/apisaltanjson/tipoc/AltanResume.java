package apisaltanjson.tipoc;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

import herramientas.Herramientas;
import herramientas.Word;

public class AltanResume {

	public void consultarResume(String dn, String cDPrueba, String ambiente, String oUser, String oPassword, String pId,
			String oId, Herramientas herram, Word word) {
		URL myURL = null;
		HttpURLConnection myURLConnection = null;
		StringBuilder response;
		String line = "";
		String estatusR = "";
		BufferedReader reader = null;
		String urlParameters = "";
		try {
			myURL = new URL("https://altanredes-" + ambiente + ".apigee.net/cm/v1/subscribers/" + dn + "/resume");
			myURLConnection = (HttpsURLConnection) myURL.openConnection();
			myURLConnection.setRequestMethod("POST");
			myURLConnection.setRequestProperty("Authorization", "Bearer " + herram.getAccessToken());
			if (!oUser.equals("") && !oPassword.equals("") && !pId.equals("") && !oId.equals("")) {
				myURLConnection.setRequestProperty("Operation-User", oUser);
				myURLConnection.setRequestProperty("Operation-Password", oPassword);
				myURLConnection.setRequestProperty("PartnerId", pId);
				myURLConnection.setRequestProperty("OperatorId", oId);
			}
			myURLConnection.setDoOutput(true);
			myURLConnection.setDoInput(true);
			DataOutputStream wr = new DataOutputStream(myURLConnection.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();
			myURLConnection.connect();
			estatusR = myURLConnection.getResponseMessage();
			if (estatusR.equals("Bad Request")) {
				word.CrearParrafo("Se obtiene el detalle de Bad Request:");
				reader = new BufferedReader(new InputStreamReader(myURLConnection.getErrorStream()));
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
					word.CrearParrafoSJ(line);
				}
				word.CrearTitulo("Termina prueba de APIGW con DN: " + dn);
				word.CrearWord("Evidencia de Error de CP " + cDPrueba, herram.getFolderS());
				herram.setBError(true);
			} else if (estatusR.equals("OK")) {
				reader = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
					word.CrearParrafoSJ(line);
				}
				word.CrearTitulo("Termina prueba de APIGW con DN: " + dn);
				word.CrearWord("Evidencia de CP " + cDPrueba, herram.getFolderS());
				herram.setBError(false);
			} else {
				word.CrearParrafo("Se obtiene el detalle de Bad Request:");
				reader = new BufferedReader(new InputStreamReader(myURLConnection.getErrorStream()));
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
					word.CrearParrafoSJ(line);
				}
				word.CrearTitulo("Termina prueba de APIGW con DN: " + dn);
				word.CrearWord("Evidencia de Error de CP " + cDPrueba, herram.getFolderS());
				herram.setBError(true);
			}

		} catch (IOException e) {
			e.printStackTrace();
			word.CrearParrafo(e.getLocalizedMessage());
			word.CrearTitulo("Termina prueba de APIGW con DN: " + dn);
			word.CrearWord("Evidencia de Error de CP " + cDPrueba, herram.getFolderS());
			herram.setBError(true);
		}
	}

	public void consultarResumeG(String dn, String cDPrueba, String ambiente, String oUser, String oPassword,
			String pId, String oId, Herramientas herram, Word word) {
		URL myURL = null;
		HttpURLConnection myURLConnection = null;
		StringBuilder response;
		String line = "";
		String estatusR = "";
		BufferedReader reader = null;
		String urlParameters = "";
		try {
			myURL = new URL("https://altanredes-" + ambiente + ".apigee.net/cm/v1/subscribers/" + dn + "/resume");
			myURLConnection = (HttpsURLConnection) myURL.openConnection();
			myURLConnection.setRequestMethod("POST");
			myURLConnection.setRequestProperty("Authorization", "Bearer " + herram.getAccessToken());
			if (!oUser.equals("") && !oPassword.equals("") && !pId.equals("") && !oId.equals("")) {
				myURLConnection.setRequestProperty("Operation-User", oUser);
				myURLConnection.setRequestProperty("Operation-Password", oPassword);
				myURLConnection.setRequestProperty("PartnerId", pId);
				myURLConnection.setRequestProperty("OperatorId", oId);
			}
			myURLConnection.setDoOutput(true);
			myURLConnection.setDoInput(true);
			DataOutputStream wr = new DataOutputStream(myURLConnection.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();
			myURLConnection.connect();
			estatusR = myURLConnection.getResponseMessage();
			if (estatusR.equals("Bad Request")) {
				word.CrearParrafo("Se obtiene el detalle de Bad Request:");
				reader = new BufferedReader(new InputStreamReader(myURLConnection.getErrorStream()));
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
					word.CrearParrafoSJ(line);
				}
				word.CrearTitulo("Termina prueba de APIGW con DN: " + dn);
				herram.setBError(true);
			} else if (estatusR.equals("OK")) {
				reader = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
					word.CrearParrafoSJ(line);
				}
				word.CrearTitulo("Termina prueba de APIGW con DN: " + dn);
				herram.readObjectJson(response.toString());
				herram.setBError(false);
			} else {
				word.CrearParrafo("Se obtiene el detalle de Bad Request:");
				reader = new BufferedReader(new InputStreamReader(myURLConnection.getErrorStream()));
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
					word.CrearParrafoSJ(line);
				}
				word.CrearTitulo("Termina prueba de APIGW con DN: " + dn);
				herram.setBError(true);
			}

		} catch (IOException e) {
			e.printStackTrace();
			word.CrearParrafo(e.getLocalizedMessage());
			word.CrearTitulo("Termina prueba de APIGW con DN: " + dn);
			herram.setBError(true);
		}
	}

	public void consultarResumeLoop(String dn, String cDPrueba, String ambiente, String oUser, String oPassword,
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
		String urlParameters = "";
		Calendar calendarActual = Calendar.getInstance();
		try {
			myURL = new URL("https://altanredes-" + ambiente + ".apigee.net/cm/v1/subscribers/" + dn + "/resume");
			myURLConnection = (HttpsURLConnection) myURL.openConnection();
			myURLConnection.setRequestMethod("POST");
			myURLConnection.setRequestProperty("Authorization", "Bearer " + herram.getAccessToken());
			if (!oUser.equals("") && !oPassword.equals("") && !pId.equals("") && !oId.equals("")) {
				myURLConnection.setRequestProperty("Operation-User", oUser);
				myURLConnection.setRequestProperty("Operation-Password", oPassword);
				myURLConnection.setRequestProperty("PartnerId", pId);
				myURLConnection.setRequestProperty("OperatorId", oId);
			}
			myURLConnection.setDoOutput(true);
			myURLConnection.setDoInput(true);
			DataOutputStream wr = new DataOutputStream(myURLConnection.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();
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
			herram.setCodeErrorHttp(codeResp);
			if (estatusR.equals("Bad Request")) {
				System.out.println("Se obtiene el detalle de Bad Request:");
				herram.escribirLogTrazas(rutaArchivoTraza, "Se obtiene el detalle de Bad Request:");
				reader = new BufferedReader(new InputStreamReader(myURLConnection.getErrorStream()));
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
					System.out.println(line);
				}
				herram.escribirLogTrazas(rutaArchivoTraza, response.toString());
				System.out.println("Termina prueba de APIGW Resume con DN: " + dn);
				herram.escribirLogTrazas(rutaArchivoTraza, "Termina prueba de APIGW Resume con DN: " + dn);
				herram.setBError(true);
				herram.readObjectJson(response.toString());
				if (herram.buscarCodeError(herram.getCodeStatus()) == true) {
					herram.escribirLog(rutaArchivoNOK, "Resume", pId, dn, herram.getOrderId(), String.valueOf(codeResp),
							tiempoResp, 2, herram.getDescriptionCode());
					herram.escribirLog(rutaArchivoOK, "Resume", pId, dn, herram.getOrderId(), String.valueOf(codeResp),
							tiempoResp, 2, herram.getDescriptionCode());
				} else {
					herram.escribirLog(rutaArchivoNOK, "Resume", pId, dn, herram.getOrderId(), String.valueOf(codeResp),
							tiempoResp, 1, "");
					herram.escribirLog(rutaArchivoOK, "Resume", pId, dn, herram.getOrderId(), String.valueOf(codeResp),
							tiempoResp, 1, "");
				}
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
				System.out.println("Termina prueba de APIGW Resume con DN: " + dn);
				herram.escribirLogTrazas(rutaArchivoTraza, "Termina prueba de APIGW Resume con DN: " + dn);
				herram.readObjectJson(response.toString());
				herram.setBError(false);
				herram.escribirLog(rutaArchivoOK, "Resume", pId, dn, herram.getOrderId(), String.valueOf(codeResp),
						tiempoResp, 0, "");
			} else {
				System.out.println("Se obtiene el detalle de error:");
				herram.escribirLogTrazas(rutaArchivoTraza, "Se obtiene el detalle de error:");
				reader = new BufferedReader(new InputStreamReader(myURLConnection.getErrorStream()));
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
					System.out.println(line);
				}
				herram.escribirLogTrazas(rutaArchivoTraza, response.toString());
				System.out.println("Termina prueba de APIGW Resume con DN: " + dn);
				herram.escribirLogTrazas(rutaArchivoTraza, "Termina prueba de APIGW Resume con DN: " + dn);
				herram.setBError(true);
				herram.readObjectJson(response.toString());
				if (codeResp == 500) {
					herram.escribirLog(rutaArchivoNOK, "Resume", pId, dn, herram.getOrderId(), String.valueOf(codeResp),
							tiempoResp, 2, herram.getDescriptionCode());
					herram.escribirLog(rutaArchivoOK, "Resume", pId, dn, herram.getOrderId(), String.valueOf(codeResp),
							tiempoResp, 2, herram.getDescriptionCode());
				} else {
					herram.escribirLog(rutaArchivoNOK, "Resume", pId, dn, herram.getOrderId(), String.valueOf(codeResp),
							tiempoResp, 1, "");
					herram.escribirLog(rutaArchivoOK, "Resume", pId, dn, herram.getOrderId(), String.valueOf(codeResp),
							tiempoResp, 1, "");
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			herram.escribirLogTrazas(rutaArchivoTraza, e.getMessage());
			System.out.println("Termina prueba de APIGW Resume con DN: " + dn);
			herram.escribirLogTrazas(rutaArchivoTraza, "Termina prueba de APIGW Resume con DN: " + dn);
			herram.setBError(true);
		}
	}
}
