package apisaltanjson.tipoc;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import herramientas.Herramientas;
import herramientas.Word;

public class AltanOauth {

	public void crearToken(Herramientas herram, Word word, String amb, String aut) {
		word.CrearParrafo("Inicia creación de token");
		URL myURL = null;
		HttpsURLConnection myURLConnection = null;
		String urlParameters = "";
		StringBuilder response;
		String line = "";
		String estatusR = "";
		BufferedReader reader = null;
		try {
			myURL = new URL(
					"https://altanredes-" + amb + ".apigee.net/v1/oauth/accesstoken?grant-type=client_credentials");
			myURLConnection = (HttpsURLConnection) myURL.openConnection();
			myURLConnection.setRequestMethod("POST");
			myURLConnection.setRequestProperty("Authorization", "Basic " + aut);
			myURLConnection.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(myURLConnection.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			estatusR = myURLConnection.getResponseMessage();
			if (estatusR.equals("Bad Request")) {
				word.CrearParrafo("Se obtiene el detalle de Bad Request:");
				reader = new BufferedReader(new InputStreamReader(myURLConnection.getErrorStream()));
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
				String res = response.toString();
				herram.readObjectJson(res);
				herram.setBError(true);
			} else if (estatusR.equals("OK")) {
				reader = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
				String res = response.toString();
				herram.readObjectJson(res);
				word.CrearParrafo("Se creó el token: " + herram.getAccessToken());
				word.CrearParrafo("Termina creación de token");
				herram.setBError(false);
			} else {
				word.CrearParrafo("Se obtiene el detalle de Bad Request:");
				reader = new BufferedReader(new InputStreamReader(myURLConnection.getErrorStream()));
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
				String res = response.toString();
				herram.readObjectJson(res);
				herram.setBError(true);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void crearTokenLoop(Herramientas herram, String amb, String aut) {
		String rutaArchivoTraza = herram.getFolderS() + "\\LogGenerealTraza.log";
		System.out.println("Inicia creación de token");
		herram.escribirLogTrazas(rutaArchivoTraza, "Inicia creación de token");
		URL myURL = null;
		HttpsURLConnection myURLConnection = null;
		String urlParameters = "";
		StringBuilder response;
		String line = "";
		String estatusR = "";
		BufferedReader reader = null;
		try {
			myURL = new URL(
					"https://altanredes-" + amb + ".apigee.net/v1/oauth/accesstoken?grant-type=client_credentials");
			myURLConnection = (HttpsURLConnection) myURL.openConnection();
			myURLConnection.setRequestMethod("POST");
			myURLConnection.setRequestProperty("Authorization", "Basic " + aut);
			myURLConnection.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(myURLConnection.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			estatusR = myURLConnection.getResponseMessage();
			if (estatusR.equals("Bad Request")) {
				System.out.println("Se obtiene el detalle de Bad Request:");
				herram.escribirLogTrazas(rutaArchivoTraza, "Se obtiene el detalle de Bad Request:");
				reader = new BufferedReader(new InputStreamReader(myURLConnection.getErrorStream()));
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
				herram.escribirLogTrazas(rutaArchivoTraza, response.toString());
				System.out.println("Termina prueba de crear token:");
				herram.escribirLogTrazas(rutaArchivoTraza, "Termina prueba de crear token:");
				String res = response.toString();
				herram.readObjectJson(res);
				herram.setBError(true);
			} else if (estatusR.equals("OK")) {
				System.out.println("Se obtiene el detalle OK:");
				herram.escribirLogTrazas(rutaArchivoTraza, "Se obtiene el detalle OK:");
				reader = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
				herram.escribirLogTrazas(rutaArchivoTraza, response.toString());
				System.out.println("Termina prueba de crear token:");
				herram.escribirLogTrazas(rutaArchivoTraza, "Termina prueba de crear token:");
				String res = response.toString();
				herram.readObjectJson(res);
				System.out.println("Se creó el token: " + herram.getAccessToken());
				System.out.println("Termina creación de token");
				herram.setBError(false);
			} else {
				System.out.println("Se obtiene el detalle de error:");
				herram.escribirLogTrazas(rutaArchivoTraza, "Se obtiene el detalle de error:");
				reader = new BufferedReader(new InputStreamReader(myURLConnection.getErrorStream()));
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
				herram.escribirLogTrazas(rutaArchivoTraza, response.toString());
				System.out.println("Termina prueba de crear token:");
				herram.escribirLogTrazas(rutaArchivoTraza, "Termina prueba de crear token:");
				String res = response.toString();
				herram.readObjectJson(res);
				herram.setBError(true);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
