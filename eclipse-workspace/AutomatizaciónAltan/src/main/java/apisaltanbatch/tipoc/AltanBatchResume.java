package apisaltanbatch.tipoc;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;

import herramientas.Herramientas;
import herramientas.Word;

public class AltanBatchResume {
	public void consultarResume(String ambiente, String oUser, String oPassword, String pId, String oId,
			String rutaArchivoBatch, Herramientas herram, Word word) {
		URL myURL = null;
		HttpURLConnection myURLConnection = null;
		StringBuilder response;
		String line = "";
		String estatusR = "";
		BufferedReader reader = null;
		String boundary = "WebKitFormBoundary7MA4YWxkTrZu0gW";
		String crlf = "\r\n";
		String sixHyphens = "------";
		File archivoBatch = new File(rutaArchivoBatch);
		try {
			word.CrearTitulo("Inicia prueba de ejecución de Batch por medio de APIGW");
			MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
			reqEntity.addPart("fileToUpload", new FileBody(new File(rutaArchivoBatch)));
			myURL = new URL("https://altanredes-" + ambiente + ".apigee.net/cm/v1/subscribers/resumes");
			myURLConnection = (HttpsURLConnection) myURL.openConnection();
			myURLConnection.setRequestMethod("POST");
			myURLConnection.setRequestProperty("Authorization", "Bearer " + herram.getAccessToken());
			if (!oUser.equals("") && !oPassword.equals("") && !pId.equals("") && !oId.equals("")) {
				myURLConnection.setRequestProperty("Operation-User", oUser);
				myURLConnection.setRequestProperty("Operation-Password", oPassword);
				myURLConnection.setRequestProperty("PartnerId", pId);
				myURLConnection.setRequestProperty("OperatorId", oId);
			}
			myURLConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=----" + boundary);
			myURLConnection.setDoInput(true);
			myURLConnection.setDoOutput(true);
			DataOutputStream request = new DataOutputStream(myURLConnection.getOutputStream());
			request.writeBytes(crlf + sixHyphens + boundary + crlf);
			request.writeBytes("Content-Disposition: form-data; name=\"archivos[]\"; filename=\""
					+ archivoBatch.getName() + "\"" + crlf);
			request.writeBytes("Content-Type: text/plain; charset=UTF-8" + crlf);
			request.writeBytes(crlf);
			byte[] bytes = Files.readAllBytes(archivoBatch.toPath());
			request.write(bytes);
			request.writeBytes(crlf);
			request.writeBytes(sixHyphens + boundary + "--");
			request.flush();
			request.close();
			myURLConnection.connect();
			estatusR = myURLConnection.getResponseMessage();
			System.out.println(estatusR);
			if (estatusR.equals("Bad Request")) {
				word.CrearParrafo("Se obtiene el detalle de Bad Request:");
				reader = new BufferedReader(new InputStreamReader(myURLConnection.getErrorStream()));
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
					word.CrearParrafoSJ(line);
				}
				word.CrearTitulo("Termina prueba de ejecución de Batch por medio de APIGW");
				herram.setBError(true);
			} else if (estatusR.equals("OK")) {
				reader = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
					word.CrearParrafoSJ(line);
				}
				word.CrearTitulo("Termina prueba de ejecución de Batch por medio de APIGW");
				herram.setBError(false);
				myURLConnection.disconnect();
			} else {
				word.CrearParrafo("Se obtiene el detalle de " + estatusR + ":");
				reader = new BufferedReader(new InputStreamReader(myURLConnection.getErrorStream()));
				response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
					word.CrearParrafoSJ(line);
				}
				word.CrearTitulo("Termina prueba de ejecución de Batch por medio de APIGW");
				herram.setBError(true);
			}
		} catch (IOException e) {
			e.printStackTrace();
			word.CrearParrafo(e.getLocalizedMessage());
			word.CrearTitulo("Termina prueba de ejecución de Batch por medio de APIGW");
			herram.setBError(true);
		}
	}
}
