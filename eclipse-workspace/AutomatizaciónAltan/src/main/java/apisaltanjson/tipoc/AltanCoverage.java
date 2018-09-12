package apisaltanjson.tipoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import herramientas.Herramientas;
import herramientas.Word;

public class AltanCoverage {
	public void consultarCoverage(String coordenadas, String cDPrueba, String ambiente, String oUser, String oPassword,
			String pId, String oId, Herramientas herram, Word word) {
		URL myURL = null;
		HttpURLConnection myURLConnection = null;
		StringBuilder response;
		String line = "";
		String estatusR = "";
		BufferedReader reader = null;
		try {
			myURL = new URL("https://altanredes-" + ambiente
					+ ".apigee.net/sqm/v1/network-services/serviceability?address=" + coordenadas);
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
				word.CrearTitulo("Termina prueba de APIGW con Coordenadas: " + coordenadas);
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
				word.CrearTitulo("Termina prueba de APIGW con Coordenadas: " + coordenadas);
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
				word.CrearTitulo("Termina prueba de APIGW con Coordenadas: " + coordenadas);
				word.CrearWord("Evidencia de Error de CP " + cDPrueba, herram.getFolderS());
				herram.setBError(true);
			}

		} catch (IOException e) {
			e.printStackTrace();
			word.CrearParrafo(e.getLocalizedMessage());
			word.CrearTitulo("Termina prueba de APIGW con Coordenadas: " + coordenadas);
			word.CrearWord("Evidencia de Error de CP " + cDPrueba, herram.getFolderS());
			herram.setBError(true);
		}
	}
	
	public void consultarCoverageG(String coordenadas, String cDPrueba, String ambiente, String oUser, String oPassword,
			String pId, String oId, Herramientas herram, Word word) {
		URL myURL = null;
		HttpURLConnection myURLConnection = null;
		StringBuilder response;
		String line = "";
		String estatusR = "";
		BufferedReader reader = null;
		try {
			myURL = new URL("https://altanredes-" + ambiente
					+ ".apigee.net/sqm/v1/network-services/serviceability?address=" + coordenadas);
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
				word.CrearTitulo("Termina prueba de APIGW con Coordenadas: " + coordenadas);
				herram.setBError(true);
			} else if (estatusR.equals("OK")) {
				word.CrearParrafo("Se obtiene el detalle OK:");
				reader = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
					word.CrearParrafoSJ(line);
				}
				word.CrearTitulo("Termina prueba de APIGW con Coordenadas: " + coordenadas);
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
				word.CrearTitulo("Termina prueba de APIGW con Coordenadas: " + coordenadas);
				herram.setBError(true);
			}

		} catch (IOException e) {
			e.printStackTrace();
			word.CrearParrafo(e.getLocalizedMessage());
			word.CrearTitulo("Termina prueba de APIGW con Coordenadas: " + coordenadas);
			herram.setBError(true);
		}
	}
}
