package apisaltanjson.tipoc;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONException;
import org.json.JSONObject;

import herramientas.Herramientas;
import herramientas.Word;

public class AltanCambioSIM {
	public void CambioSIM(String ambiente, String imsis, Herramientas herram, String oUser,
			String oPassword, String pId, String oId ,String dn,String iccid,   Word word) {
		URL myURL = null;
		HttpURLConnection myURLConnection = null;
		StringBuilder response;
		String line = "";
		String estatusR = "";
		BufferedReader reader = null;
		try {
			allowMethods("PATCH");
			myURL = new URL("https://altanredes-" + ambiente + ".apigee.net/cm/v1/subscribers/" + dn + "?method=PATCH");
			myURLConnection = (HttpsURLConnection) myURL.openConnection();
			myURLConnection.setRequestMethod("PATCH");
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
			JSONObject objA = new JSONObject();
			objA.put("newIccid", iccid);
			obj.put("changeSubscriberSIM", objA);
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
				word.CrearTitulo("Termina prueba de cambio de SIM con DN: " + dn);
				herram.setBError(true);
			} else if (estatusR.equals("OK")) {
				reader = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
					word.CrearParrafoSJ(line);
				}
				word.CrearTitulo("Termina prueba de cambio de SIM con DN: " + dn);
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
				word.CrearTitulo("Termina prueba de cambio de SIM con DN: " + dn);
				herram.setBError(true);
			}
		} catch (IOException | JSONException e) {
			e.printStackTrace();
			word.CrearParrafo(e.getLocalizedMessage());
			word.CrearTitulo("Termina prueba de cambio de SIM con DN: " + dn);
			herram.setBError(true);
		}
	}

	private static void allowMethods(String... methods) {
		try {
			Field methodsField = HttpURLConnection.class.getDeclaredField("methods");
			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(methodsField, methodsField.getModifiers() & ~Modifier.FINAL);
			methodsField.setAccessible(true);
			String[] oldMethods = (String[]) methodsField.get(null);
			Set<String> methodsSet = new LinkedHashSet<>(Arrays.asList(oldMethods));
			methodsSet.addAll(Arrays.asList(methods));
			String[] newMethods = methodsSet.toArray(new String[0]);
			methodsField.set(null/* static field */, newMethods);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}
}
