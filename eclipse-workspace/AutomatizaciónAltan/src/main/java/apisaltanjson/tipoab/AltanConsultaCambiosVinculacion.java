package apisaltanjson.tipoab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import herramientas.Herramientas;
import herramientas.Word;

public class AltanConsultaCambiosVinculacion {
	public void ConsultaCambiosVinculacion(String ambiente, String imsis, Herramientas herram, String oUser,
			String oPassword, String pId, String oId, String dn, String coordenadas, String offeringId, Word word) {
		URL myURL = null;
		HttpURLConnection myURLConnection = null;
		StringBuilder response;
		String line = "";
		String estatusR = "";
		BufferedReader reader = null;
		try {
			myURL = new URL("https://altanredes-" + ambiente + ".apigee.net/ac/v1/imsis/" + imsis + "/linkings?be_id="
					+ pId + "&msisdn=" + dn);
			myURLConnection = (HttpsURLConnection) myURL.openConnection();
			myURLConnection.setRequestMethod("GET");
			myURLConnection.setRequestProperty("Authorization", "Bearer " + herram.getAccessToken());
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
				word.CrearTitulo("Termina prueba de consulta de cambio de vinculacion con Imsi: " + imsis);
				herram.setBError(true);
			} else if (estatusR.equals("OK")) {
				word.CrearParrafo("Se obtiene el detalle OK:");
				reader = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
					word.CrearParrafoSJ(line);
				}
				String res = response.toString();
				herram.cColLis();
				herram.readObjectJson(res);
				word.CrearTitulo("Termina prueba de consulta de cambio de vinculacion con Imsi: " + imsis);
			} else {
				word.CrearParrafo("Se obtiene el detalle de error:");
				reader = new BufferedReader(new InputStreamReader(myURLConnection.getErrorStream()));
				herram.limpiarApis();
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
					word.CrearParrafoSJ(line);
				}
				word.CrearTitulo("Termina prueba de consulta de cambio de vinculacion con Imsi: " + imsis);
				herram.setBError(true);
			}

		} catch (IOException e) {
			e.printStackTrace();
			word.CrearTitulo("Termina prueba de consulta de cambio de vinculacion con Imsi: " + imsis);
			herram.setBError(true);
		}
	}
}
