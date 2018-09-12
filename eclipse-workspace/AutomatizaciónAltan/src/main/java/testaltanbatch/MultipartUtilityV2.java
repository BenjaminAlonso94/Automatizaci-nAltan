package testaltanbatch;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;

public class MultipartUtilityV2 {
	private HttpURLConnection httpConn;
	private DataOutputStream request;
	private final String boundary = "WebKitFormBoundary7MA4YWxkTrZu0gW";
	private final String crlf = "\r\n";
	private final String twoHyphens = "------";

	public static void main(String args[]) {
		try {
			MultipartUtilityV2 multV2 = new MultipartUtilityV2(
					"https://altanredes-prod.apigee.net/cm/v1/subscribers/activations");
			File archivoBatch = new File(
					"C:\\Users\\balonsmo\\eclipse-workspace\\Altan\\Evidencias\\Batch\\Evidencia_13072018_103238\\Archivo.txt");
			System.out.println("Nombre del arhivo"+archivoBatch.getName());
			multV2.addFormField("archivos[]", "Archivo.txt");
			multV2.addFilePart("archivos[]", archivoBatch);
			System.out.println("El estatus fue: " + multV2.finish());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This constructor initializes a new HTTP POST request with content type is set
	 * to multipart/form-data
	 *
	 * @param requestURL
	 * @throws IOException
	 */
	public MultipartUtilityV2(String requestURL) throws IOException {
		// creates a unique boundary based on time stamp
		URL url = new URL(requestURL);
		httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setRequestMethod("POST");
		httpConn.setRequestProperty("Authorization", "Bearer AJl71cIVuRXcdU1oT7wWPRo8yXU2");
		//httpConn.setRequestProperty("Connection", "Keep-Alive");
		//httpConn.setRequestProperty("Cache-Control", "no-cache");
		httpConn.setRequestProperty("Operation-User", "Test_1");
		httpConn.setRequestProperty("Operation-Password", "5Hzxgzy$");
		httpConn.setRequestProperty("PartnerId", "130");
		httpConn.setRequestProperty("OperatorId", "870");
		httpConn.setRequestProperty("Content-Type", "multipart/form-data; boundary=----" +this.boundary);
		httpConn.setDoInput(true);
		httpConn.setDoOutput(true);
		//httpConn.setUseCaches(false);
		request = new DataOutputStream(httpConn.getOutputStream());
	}

	/**
	 * Adds a form field to the request
	 *
	 * @param name
	 *            field name
	 * @param value
	 *            field value
	 */
	public void addFormField(String name, String value) throws IOException {
		//request.writeBytes(this.twoHyphens + this.boundary + this.crlf);
		//request.writeBytes("Content-Disposition: form-data; name=\"" + name + "\"" + this.crlf);
		//request.writeBytes("Content-Type: text/plain; charset=UTF-8" + this.crlf);
		request.writeBytes("Content-Type: text/plain; charset=UTF-8" + this.crlf);
		request.writeBytes(this.crlf);
		request.writeBytes(value + this.crlf);
		request.flush();
	}

	/**
	 * Adds a upload file section to the request
	 *
	 * @param fieldName
	 *            name attribute in <input type="file" name="..." />
	 * @param uploadFile
	 *            a File to be uploaded
	 * @throws IOException
	 */
	public void addFilePart(String fieldName, File uploadFile) throws IOException {
		//String fileName = uploadFile.getName();
		request.writeBytes(this.crlf+this.twoHyphens + this.boundary + this.crlf);
		request.writeBytes(
				"Content-Disposition: form-data; name=\"archivos[]\"; filename=\"Archivo.txt\"" + this.crlf);
		request.writeBytes("Content-Type: text/plain; charset=UTF-8" + this.crlf);
		request.writeBytes(this.crlf);
		//request.writeBytes(this.crlf);
		//request.writeBytes(this.twoHyphens + this.boundary + "--");
		byte[] bytes = Files.readAllBytes(uploadFile.toPath());
		System.out.println(uploadFile.toPath());
		request.write(bytes);
	}

	/**
	 * Completes the request and receives response from the server.
	 *
	 * @return a list of Strings as response in case the server returned status OK,
	 *         otherwise an exception is thrown.
	 * @throws IOException
	 */
	public String finish() throws IOException {
		String response = "";
		request.writeBytes(this.crlf);
		request.writeBytes(this.twoHyphens + this.boundary + "--");
		request.flush();
		request.close();
		// checks server's status code first
		int status = httpConn.getResponseCode();
		System.out.println(httpConn.getResponseMessage());
		if (status == HttpURLConnection.HTTP_OK) {
			InputStream responseStream = new BufferedInputStream(httpConn.getInputStream());
			BufferedReader responseStreamReader = new BufferedReader(new InputStreamReader(responseStream));
			String line = "";
			StringBuilder stringBuilder = new StringBuilder();
			while ((line = responseStreamReader.readLine()) != null) {
				stringBuilder.append(line).append("\n");
			}
			responseStreamReader.close();
			response = stringBuilder.toString();
			httpConn.disconnect();
		} else {
			throw new IOException("Server returned non-OK status: " + status);
		}
		return response;
	}
}