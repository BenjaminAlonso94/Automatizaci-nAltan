package apisaltanjson.tipoc;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import herramientas.Herramientas;
import herramientas.Word;

public class AltanLockIMEI {
	public void consultarLockIMEI(String imei, String cDPrueba, String ambiente, String oUser, String oPassword,
			String pId, String oId, Herramientas herram, Word word) {
		URL myURL = null;
		HttpURLConnection myURLConnection = null;
		StringBuilder response;
		String line = "";
		String estatusR = "";
		BufferedReader reader = null;
		String urlParameters = "";
		try {
			myURL = new URL("https://altanredes-" + ambiente + ".apigee.net/cm/v1/imei/" + imei + "/lock");
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
				word.CrearTitulo("Termina prueba de APIGW con IMEI: " + imei);
				word.CrearWord("Evidencia de Error de CP " + cDPrueba, herram.getFolderS());
				herram.setBError(true);
			} else if (estatusR.equals("OK")) {
				reader = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
					word.CrearParrafoSJ(line);
				}
				word.CrearTitulo("Termina prueba de APIGW con IMEI: " + imei);
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
				word.CrearTitulo("Termina prueba de APIGW con IMEI: " + imei);
				word.CrearWord("Evidencia de Error de CP " + cDPrueba, herram.getFolderS());
				herram.setBError(true);
			}

		} catch (IOException e) {
			e.printStackTrace();
			word.CrearParrafo(e.getLocalizedMessage());
			word.CrearTitulo("Termina prueba de APIGW con IMEI: " + imei);
			word.CrearWord("Evidencia de Error de CP " + cDPrueba, herram.getFolderS());
			herram.setBError(true);
		}
	}
	
	public void consultarLockIMEIG(String imei, String cDPrueba, String ambiente, String oUser, String oPassword,
			String pId, String oId, Herramientas herram, Word word) {
		URL myURL = null;
		HttpURLConnection myURLConnection = null;
		StringBuilder response;
		String line = "";
		String estatusR = "";
		BufferedReader reader = null;
		String urlParameters = "";
		try {
			myURL = new URL("https://altanredes-" + ambiente + ".apigee.net/cm/v1/imei/" + imei + "/lock");
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
				word.CrearTitulo("Termina prueba de APIGW con IMEI: " + imei);
				herram.setBError(true);
			} else if (estatusR.equals("OK")) {
				reader = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
					word.CrearParrafoSJ(line);
				}
				word.CrearTitulo("Termina prueba de APIGW con IMEI: " + imei);
				herram.setBError(false);
			} else {
				word.CrearParrafo("Se obtiene el detalle de Bad Request:");
				reader = new BufferedReader(new InputStreamReader(myURLConnection.getErrorStream()));
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
					word.CrearParrafoSJ(line);
				}
				word.CrearTitulo("Termina prueba de APIGW con IMEI: " + imei);
				herram.setBError(true);
			}

		} catch (IOException e) {
			e.printStackTrace();
			word.CrearParrafo(e.getLocalizedMessage());
			word.CrearTitulo("Termina prueba de APIGW con IMEI: " + imei);
			herram.setBError(true);
		}
	}
}
