package apisaltanjson.tipoab;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONException;
import org.json.JSONObject;

import herramientas.Herramientas;
import herramientas.Word;

public class AltanReactivateImsi {
	public void ReactivateImsi(String ambiente, String imsis, Herramientas herram, String oUser, String oPassword,
			String pId, String oId, String dn, Word word) {
		URL myURL = null;
		HttpURLConnection myURLConnection = null;
		StringBuilder response;
		String line = "";
		String estatusR = "";
		BufferedReader reader = null;
		try {
			myURL = new URL("https://altanredes-" + ambiente + ".apigee.net/ac/v1/imsis/" + imsis + "/reactivate");
			myURLConnection = (HttpsURLConnection) myURL.openConnection();
			myURLConnection.setRequestMethod("POST");
			myURLConnection.setRequestProperty("Authorization", "Bearer " + herram.getAccessToken());
			myURLConnection.setRequestProperty("Content-Type", "application/json");
			if (!oUser.equals("") && !oPassword.equals("") && !pId.equals("") && !oId.equals("")) {
				myURLConnection.setRequestProperty("Operation-User", oUser);
				myURLConnection.setRequestProperty("Operation-Password", oPassword);
				myURLConnection.setRequestProperty("PartnerId", pId);
				myURLConnection.setRequestProperty("OperatorId", oId);
			}
			myURLConnection.setDoOutput(true);
			myURLConnection.setDoInput(true);
			DataOutputStream wr = new DataOutputStream(myURLConnection.getOutputStream());
			JSONObject obj = new JSONObject();
			obj.put("be_id", pId);
			obj.put("msisdn", dn);
			word.CrearParrafo("Se utilizara el siguiente Body: ");
			word.CrearParrafo(obj.toString());
			wr.writeBytes(obj.toString());
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
				word.CrearTitulo("Termina prueba de notificación de reactivación con Imsi: " + imsis);
				herram.setBError(true);
			} else if (estatusR.equals("OK")) {
				reader = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
					word.CrearParrafoSJ(line);
				}
				word.CrearTitulo("Termina prueba de notificación de reactivación con Imsi: " + imsis);
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
				word.CrearTitulo("Termina prueba de notificación de reactivación con Imsi: " + imsis);
				herram.setBError(true);
			}
		} catch (IOException | JSONException e) {
			e.printStackTrace();
			word.CrearParrafo(e.getLocalizedMessage());
			word.CrearTitulo("Termina prueba de notificación de reactivación con Imsi: " + imsis);
			herram.setBError(true);
		}
	}
}
