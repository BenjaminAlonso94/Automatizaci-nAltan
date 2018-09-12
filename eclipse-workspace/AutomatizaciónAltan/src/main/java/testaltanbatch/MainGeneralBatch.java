package testaltanbatch;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import apisaltanbatch.tipoab.AltanBatchRecycles;
import apisaltanbatch.tipoab.AltanBatchReserve;
import apisaltanbatch.tipoc.AltanBatchActivate;
import apisaltanbatch.tipoc.AltanBatchBlockIMEI;
import apisaltanbatch.tipoc.AltanBatchChangeSIM;
import apisaltanbatch.tipoc.AltanBatchChangesPrimary;
import apisaltanbatch.tipoc.AltanBatchDeactive;
import apisaltanbatch.tipoc.AltanBatchPreDeactive;
import apisaltanbatch.tipoc.AltanBatchPurchasesSupplementary;
import apisaltanbatch.tipoc.AltanBatchReactive;
import apisaltanbatch.tipoc.AltanBatchResume;
import apisaltanbatch.tipoc.AltanBatchSuspend;
import apisaltanbatch.tipoc.AltanBatchUnBlockIMEI;
import apisaltanjson.tipoc.AltanOauth;
import herramientas.Herramientas;
import herramientas.SFTP_Descarga;
import herramientas.Word;

public class MainGeneralBatch {
	String clave = "";
	String nameApi = "";
	String proyecto = "";
	String bEjec = "";
	String cv = "";
	String amb = "";
	String ejec = "";
	String cDPrueba = "";
	String dn = "";
	String dns = "";
	String bUtilizar = "";
	String coordenadas = "";
	String offeringId = "";
	String llave = "";
	String oUser = "";
	String oPassword = "";
	String pId = "";
	String oId = "";
	String pUser = "";
	String pPass = "";
	String pHost = "";
	String pPort = "";
	String pRuta = "";
	String token = "";
	String cToken = "";
	String Type = "";
	int filaBatch = 0;
	List<List<String>> listaLog = new ArrayList<List<String>>();
	List<List<String>> listaDns = new ArrayList<List<String>>();

	public static void main(String[] args) {
		MainGeneralBatch main = new MainGeneralBatch();
		Herramientas herram = new Herramientas();
		herram.creaCarpeta("Batch");
		main.configProfile(herram);
	}

	private void configProfile(Herramientas herram) {
		try {
			AltanOauth aOauth = new AltanOauth();
			Word word = new Word();
			File fConf = new File(System.getProperty("user.dir") + "\\ConfiguraciónBatch.xlsx");
			InputStream iConf = new FileInputStream(fConf);
			XSSFWorkbook workbook = new XSSFWorkbook(iConf);
			XSSFSheet sheet = workbook.getSheetAt(0);
			XSSFSheet sheetDN = workbook.getSheetAt(1);
			XSSFRow row;
			FileInputStream fIConf = new FileInputStream(fConf);
			XSSFWorkbook nWorkbook = new XSSFWorkbook(fIConf);
			XSSFSheet nSheet = nWorkbook.getSheetAt(0);
			XSSFSheet nSheetDN = nWorkbook.getSheetAt(1);
			Cell cell = null;
			word.CrearDocumentoST();
			word.CrearTitulo("Inicia prueba Batch");
			for (int fila = 1; fila < sheet.getLastRowNum() + 1; fila++) {
				row = sheet.getRow(fila);
				for (int columna = 0; columna < row.getLastCellNum(); columna++) {
					clave = row.getCell(columna).toString();
					if (columna == 0) {
						nameApi = clave;
					}
					if (columna == 1) {
						proyecto = clave;
					}
					if (columna == 3) {
						bEjec = clave;
					}
					if (columna == 4) {
						cv = clave;
					}
					if (columna == 5) {
						amb = clave;
					}
					if (columna == 6) {
						ejec = clave;
					}
					if (columna == 7) {
						cDPrueba = clave;
					}
					if (columna == 8) {
						dn = clave;
					}
					if (columna == 9) {
						coordenadas = clave;
					}
					if (columna == 10) {
						offeringId = clave;
					}
					if (columna == 11) {
						llave = clave;
					}
					if (columna == 12) {
						oUser = clave;
					}
					if (columna == 13) {
						oPassword = clave;
					}
					if (columna == 14) {
						pId = clave;
					}
					if (columna == 15) {
						oId = clave;
					}
					if (columna == 16) {
						pHost = clave;
					}
					if (columna == 17) {
						pUser = clave;
					}
					if (columna == 18) {
						pPass = clave;
					}
					if (columna == 19) {
						pPort = clave;
					}
					if (columna == 20) {
						pRuta = clave;
					}
					if (columna == 21) {
						token = clave;
					}
					if (columna == 22) {
						cToken = clave;
					}
				}
				if (bEjec.trim().equals("SI")) {
					if (cToken.equals("1")) {
						aOauth.crearToken(herram, word, amb, llave);
						cell = nSheet.getRow(fila).getCell(21);
						cell.setCellValue(herram.getAccessToken());
						cell = nSheet.getRow(fila).getCell(22);
						cell.setCellValue("0");
					} else if (cToken.equals("0")) {
						herram.setAccessToken(token);
					}
					if (nameApi.equals("Activate")) {
						System.out.println(nameApi);
						word.crearTabla(proyecto, cv, amb, ejec, cDPrueba, herram.getFechaC());
						word.CrearTitulo("Inicia prueba de Batch para operación " + nameApi);
						word.CrearTitulo("Inicia creación de archivos " + nameApi);
						String rutaArchivoBatch = herram.getFolderS() + "\\Archivo de pruebas " + nameApi + " "
								+ herram.getFecha() + "_" + herram.getHour() + ".txt";
						crearArchivosBatch(herram, sheetDN, nSheetDN, nameApi, rutaArchivoBatch);
						word.CrearParrafoSJ("Se creó el archivo " + rutaArchivoBatch);
						word.CrearTitulo("Termina creación de archivos " + nameApi);
						AltanBatchActivate aActivate = new AltanBatchActivate();
						aActivate.consultarActivate(amb, oUser, oPassword, pId, oId, rutaArchivoBatch, herram, word);
						word.CrearTitulo("Termina prueba de Batch para operación " + nameApi);
						word.crearSaltoL();
					}
					if (nameApi.equals("BlockIMEI")) {
						System.out.println(nameApi);
						word.crearTabla(proyecto, cv, amb, ejec, cDPrueba, herram.getFechaC());
						word.CrearTitulo("Inicia prueba de Batch para operación " + nameApi);
						word.CrearTitulo("Inicia creación de archivos " + nameApi);
						String rutaArchivoBatch = herram.getFolderS() + "\\Archivo de pruebas " + nameApi + " "
								+ herram.getFecha() + "_" + herram.getHour() + ".txt";
						crearArchivosBatch(herram, sheetDN, nSheetDN, nameApi, rutaArchivoBatch);
						word.CrearParrafoSJ("Se creó el archivo " + rutaArchivoBatch);
						word.CrearTitulo("Termina creación de archivos " + nameApi);
						AltanBatchBlockIMEI aBIMEI = new AltanBatchBlockIMEI();
						aBIMEI.consultarBlockIMEI(amb, oUser, oPassword, pId, oId, rutaArchivoBatch, herram, word);
						word.CrearTitulo("Termina prueba de Batch para operación " + nameApi);
						word.crearSaltoL();
					}
					if (nameApi.equals("UnBlockIME")) {
						System.out.println(nameApi);
						word.crearTabla(proyecto, cv, amb, ejec, cDPrueba, herram.getFechaC());
						word.CrearTitulo("Inicia prueba de Batch para operación " + nameApi);
						word.CrearTitulo("Inicia creación de archivos " + nameApi);
						String rutaArchivoBatch = herram.getFolderS() + "\\Archivo de pruebas " + nameApi + " "
								+ herram.getFecha() + "_" + herram.getHour() + ".txt";
						crearArchivosBatch(herram, sheetDN, nSheetDN, nameApi, rutaArchivoBatch);
						word.CrearParrafoSJ("Se creó el archivo " + rutaArchivoBatch);
						word.CrearTitulo("Termina creación de archivos " + nameApi);
						AltanBatchUnBlockIMEI aBIMEI = new AltanBatchUnBlockIMEI();
						aBIMEI.consultarUnBlockIMEI(amb, oUser, oPassword, pId, oId, rutaArchivoBatch, herram, word);
						word.CrearTitulo("Termina prueba de Batch para operación " + nameApi);
						word.crearSaltoL();
					}
					if (nameApi.equals("ChangeSIM")) {
						System.out.println(nameApi);
						word.crearTabla(proyecto, cv, amb, ejec, cDPrueba, herram.getFechaC());
						word.CrearTitulo("Inicia prueba de Batch para operación " + nameApi);
						word.CrearTitulo("Inicia creación de archivos " + nameApi);
						String rutaArchivoBatch = herram.getFolderS() + "\\Archivo de pruebas " + nameApi + " "
								+ herram.getFecha() + "_" + herram.getHour() + ".txt";
						crearArchivosBatch(herram, sheetDN, nSheetDN, nameApi, rutaArchivoBatch);
						word.CrearParrafoSJ("Se creó el archivo " + rutaArchivoBatch);
						word.CrearTitulo("Termina creación de archivos " + nameApi);
						AltanBatchChangeSIM aSuspend = new AltanBatchChangeSIM();
						aSuspend.consultarChangeSIM(amb, oUser, oPassword, pId, oId, rutaArchivoBatch, herram, word);
						word.CrearTitulo("Termina prueba de Batch para operación " + nameApi);
						word.crearSaltoL();
					}
					if (nameApi.equals("Suspend")) {
						System.out.println(nameApi);
						word.crearTabla(proyecto, cv, amb, ejec, cDPrueba, herram.getFechaC());
						word.CrearTitulo("Inicia prueba de Batch para operación " + nameApi);
						word.CrearTitulo("Inicia creación de archivos " + nameApi);
						String rutaArchivoBatch = herram.getFolderS() + "\\Archivo de pruebas " + nameApi + " "
								+ herram.getFecha() + "_" + herram.getHour() + ".txt";
						crearArchivosBatch(herram, sheetDN, nSheetDN, nameApi, rutaArchivoBatch);
						word.CrearParrafoSJ("Se creó el archivo " + rutaArchivoBatch);
						word.CrearTitulo("Termina creación de archivos " + nameApi);
						AltanBatchSuspend aSuspend = new AltanBatchSuspend();
						aSuspend.consultarSuspend(amb, oUser, oPassword, pId, oId, rutaArchivoBatch, herram, word);
						word.CrearTitulo("Termina prueba de Batch para operación " + nameApi);
						word.crearSaltoL();
					}
					if (nameApi.equals("Resume")) {
						System.out.println(nameApi);
						word.crearTabla(proyecto, cv, amb, ejec, cDPrueba, herram.getFechaC());
						word.CrearTitulo("Inicia prueba de Batch para operación " + nameApi);
						word.CrearTitulo("Inicia creación de archivos " + nameApi);
						String rutaArchivoBatch = herram.getFolderS() + "\\Archivo de pruebas " + nameApi + " "
								+ herram.getFecha() + "_" + herram.getHour() + ".txt";
						crearArchivosBatch(herram, sheetDN, nSheetDN, nameApi, rutaArchivoBatch);
						word.CrearParrafoSJ("Se creó el archivo " + rutaArchivoBatch);
						word.CrearTitulo("Termina creación de archivos " + nameApi);
						AltanBatchResume aResume = new AltanBatchResume();
						aResume.consultarResume(amb, oUser, oPassword, pId, oId, rutaArchivoBatch, herram, word);
						word.CrearTitulo("Termina prueba de Batch para operación " + nameApi);
						word.crearSaltoL();
					}
					if (nameApi.equals("PreDeactive")) {
						System.out.println(nameApi);
						word.crearTabla(proyecto, cv, amb, ejec, cDPrueba, herram.getFechaC());
						word.CrearTitulo("Inicia prueba de Batch para operación " + nameApi);
						word.CrearTitulo("Inicia creación de archivos " + nameApi);
						String rutaArchivoBatch = herram.getFolderS() + "\\Archivo de pruebas " + nameApi + " "
								+ herram.getFecha() + "_" + herram.getHour() + ".txt";
						crearArchivosBatch(herram, sheetDN, nSheetDN, nameApi, rutaArchivoBatch);
						word.CrearParrafoSJ("Se creó el archivo " + rutaArchivoBatch);
						word.CrearTitulo("Termina creación de archivos " + nameApi);
						AltanBatchPreDeactive aDeactive = new AltanBatchPreDeactive();
						aDeactive.consultarPreDeactive(amb, oUser, oPassword, pId, oId, rutaArchivoBatch, herram, word);
						word.CrearTitulo("Termina prueba de Batch para operación " + nameApi);
						word.crearSaltoL();
					}
					if (nameApi.equals("Deactive")) {
						System.out.println(nameApi);
						word.crearTabla(proyecto, cv, amb, ejec, cDPrueba, herram.getFechaC());
						word.CrearTitulo("Inicia prueba de Batch para operación " + nameApi);
						word.CrearTitulo("Inicia creación de archivos " + nameApi);
						String rutaArchivoBatch = herram.getFolderS() + "\\Archivo de pruebas " + nameApi + " "
								+ herram.getFecha() + "_" + herram.getHour() + ".txt";
						crearArchivosBatch(herram, sheetDN, nSheetDN, nameApi, rutaArchivoBatch);
						word.CrearParrafoSJ("Se creó el archivo " + rutaArchivoBatch);
						word.CrearTitulo("Termina creación de archivos " + nameApi);
						AltanBatchDeactive aDeactive = new AltanBatchDeactive();
						aDeactive.consultarDeactive(amb, oUser, oPassword, pId, oId, rutaArchivoBatch, herram, word);
						word.CrearTitulo("Termina prueba de Batch para operación " + nameApi);
						word.crearSaltoL();
					}
					if (nameApi.equals("Reactive")) {
						System.out.println(nameApi);
						word.crearTabla(proyecto, cv, amb, ejec, cDPrueba, herram.getFechaC());
						word.CrearTitulo("Inicia prueba de Batch para operación " + nameApi);
						word.CrearTitulo("Inicia creación de archivos " + nameApi);
						String rutaArchivoBatch = herram.getFolderS() + "\\Archivo de pruebas " + nameApi + " "
								+ herram.getFecha() + "_" + herram.getHour() + ".txt";
						crearArchivosBatch(herram, sheetDN, nSheetDN, nameApi, rutaArchivoBatch);
						word.CrearParrafoSJ("Se creó el archivo " + rutaArchivoBatch);
						word.CrearTitulo("Termina creación de archivos " + nameApi);
						AltanBatchReactive aReactive = new AltanBatchReactive();
						aReactive.consultarReactive(amb, oUser, oPassword, pId, oId, rutaArchivoBatch, herram, word);
						word.CrearTitulo("Termina prueba de Batch para operación " + nameApi);
						word.crearSaltoL();
					}
					if (nameApi.equals("OfferSupplementary")) {
						System.out.println(nameApi);
						word.crearTabla(proyecto, cv, amb, ejec, cDPrueba, herram.getFechaC());
						word.CrearTitulo("Inicia prueba de Batch para operación " + nameApi);
						word.CrearTitulo("Inicia creación de archivos " + nameApi);
						String rutaArchivoBatch = herram.getFolderS() + "\\Archivo de pruebas " + nameApi + " "
								+ herram.getFecha() + "_" + herram.getHour() + ".txt";
						crearArchivosBatch(herram, sheetDN, nSheetDN, nameApi, rutaArchivoBatch);
						word.CrearParrafoSJ("Se creó el archivo " + rutaArchivoBatch);
						word.CrearTitulo("Termina creación de archivos " + nameApi);
						AltanBatchPurchasesSupplementary aSupple = new AltanBatchPurchasesSupplementary();
						aSupple.BatchPurchasesSupplementary(amb, oUser, oPassword, pId, oId, rutaArchivoBatch, herram,
								word);
						word.CrearTitulo("Termina prueba de Batch para operación " + nameApi);
						word.crearSaltoL();
					}
					if (nameApi.equals("ChangePrimary")) {
						System.out.println(nameApi);
						word.crearTabla(proyecto, cv, amb, ejec, cDPrueba, herram.getFechaC());
						word.CrearTitulo("Inicia prueba de Batch para operación " + nameApi);
						word.CrearTitulo("Inicia creación de archivos " + nameApi);
						String rutaArchivoBatch = herram.getFolderS() + "\\Archivo de pruebas " + nameApi + " "
								+ herram.getFecha() + "_" + herram.getHour() + ".txt";
						crearArchivosBatch(herram, sheetDN, nSheetDN, nameApi, rutaArchivoBatch);
						word.CrearParrafoSJ("Se creó el archivo " + rutaArchivoBatch);
						word.CrearTitulo("Termina creación de archivos " + nameApi);
						AltanBatchChangesPrimary aChange = new AltanBatchChangesPrimary();
						aChange.BatchChangesPrimary(amb, oUser, oPassword, pId, oId, rutaArchivoBatch, herram, word);
						word.CrearTitulo("Termina prueba de Batch para operación " + nameApi);
						word.crearSaltoL();
					}
					if (nameApi.equals("Reserve")) {
						System.out.println(nameApi);
						word.crearTabla(proyecto, cv, amb, ejec, cDPrueba, herram.getFechaC());
						word.CrearTitulo("Inicia prueba de Batch para operación " + nameApi);
						word.CrearTitulo("Inicia creación de archivos " + nameApi);
						String rutaArchivoBatch = herram.getFolderS() + "\\Archivo de pruebas " + nameApi + " "
								+ herram.getFecha() + "_" + herram.getHour() + ".txt";
						crearArchivosBatch(herram, sheetDN, nSheetDN, nameApi, rutaArchivoBatch);
						word.CrearParrafoSJ("Se creó el archivo " + rutaArchivoBatch);
						word.CrearTitulo("Termina creación de archivos " + nameApi);
						AltanBatchReserve aReserve = new AltanBatchReserve();
						aReserve.BatchReserve(amb, oUser, oPassword, pId, oId, rutaArchivoBatch, herram, word);
						word.CrearTitulo("Termina prueba de Batch para operación " + nameApi);
						word.crearSaltoL();
					}
					if (nameApi.equals("Recycles")) {
						System.out.println(nameApi);
						word.crearTabla(proyecto, cv, amb, ejec, cDPrueba, herram.getFechaC());
						word.CrearTitulo("Inicia prueba de Batch para operación " + nameApi);
						word.CrearTitulo("Inicia creación de archivos " + nameApi);
						String rutaArchivoBatch = herram.getFolderS() + "\\Archivo de pruebas " + nameApi + " "
								+ herram.getFecha() + "_" + herram.getHour() + ".txt";
						crearArchivosBatch(herram, sheetDN, nSheetDN, nameApi, rutaArchivoBatch);
						word.CrearParrafoSJ("Se creó el archivo " + rutaArchivoBatch);
						word.CrearTitulo("Termina creación de archivos " + nameApi);
						AltanBatchRecycles aRecycles = new AltanBatchRecycles();
						aRecycles.BatchReserve(amb, oUser, oPassword, pId, oId, rutaArchivoBatch, herram, word);
						word.CrearTitulo("Termina prueba de Batch para operación " + nameApi);
						word.crearSaltoL();
					}
					if (nameApi.equals("FTP Activate")) {
						word.CrearTitulo("Inicia descarga de log para la operación " + nameApi);
						SFTP_Descarga sftpDes = new SFTP_Descarga();
						String archFTP = "resultado-highbss-" + herram.getFechaCBatch() + "-" + pId + ".log";
						String archFTP2 = "resultado-cambio_oferta-" + herram.getFechaCBatch() + "-" + pId + ".log";
						String pPathFile = pRuta + archFTP;
						String pPathFile2 = pRuta + archFTP2;
						// String pPathFileTemp =
						// "C:\\Users\\balonsmo\\eclipse-workspace\\Altan\\Evidencias\\Batch\\Evidencia_14082018_180705\\resultado-cambio_oferta-2018-08-15-130.logno-ok.csv";
						sftpDes.DescargarPorSFTP(pUser, pPass, pHost, Integer.parseInt(pPort),
								herram.getFolderS() + "//" + archFTP, pPathFile);
						word.CrearParrafoSJ("Se descargó el archivo: " + archFTP);
						sftpDes.DescargarPorSFTP(pUser, pPass, pHost, Integer.parseInt(pPort),
								herram.getFolderS() + "//" + archFTP2, pPathFile2);
						word.CrearParrafoSJ("Se descargó el archivo: " + archFTP2);
						word.CrearTitulo("Termina descarga de log para la operación " + nameApi);
						// herram.ftpAnalisis();
					}
					if (nameApi.equals("FTP Suspend")) {
						word.CrearTitulo("Inicia descarga de log para la operación " + nameApi);
						SFTP_Descarga sftpDes = new SFTP_Descarga();
						String archFTP = "resultado-suspend-" + herram.getFechaCBatch() + "-" + pId + ".log";
						String pPathFile = pRuta + archFTP;
						sftpDes.DescargarPorSFTP(pUser, pPass, pHost, Integer.parseInt(pPort),
								herram.getFolderS() + "//" + archFTP, pPathFile);
						word.CrearParrafoSJ("Se descargó el archivo: " + archFTP);
						word.CrearTitulo("Termina descarga de log para la operación " + nameApi);
					}
					if (nameApi.equals("FTP Resume")) {
						word.CrearTitulo("Inicia descarga de log para la operación " + nameApi);
						SFTP_Descarga sftpDes = new SFTP_Descarga();
						String archFTP = "resultado-resume-" + herram.getFechaCBatch() + "-" + pId + ".log";
						String pPathFile = pRuta + archFTP;
						sftpDes.DescargarPorSFTP(pUser, pPass, pHost, Integer.parseInt(pPort),
								herram.getFolderS() + "//" + archFTP, pPathFile);
						word.CrearParrafoSJ("Se descargó el archivo: " + archFTP);
						word.CrearTitulo("Termina descarga de log para la operación " + nameApi);
					}
					if (nameApi.equals("FTP Deactive")) {
						word.CrearTitulo("Inicia descarga de log para la operación " + nameApi);
						SFTP_Descarga sftpDes = new SFTP_Descarga();
						String archFTP = "resultado-deactivate-" + herram.getFechaCBatch() + "-" + pId + ".log";
						String pPathFile = pRuta + archFTP;
						sftpDes.DescargarPorSFTP(pUser, pPass, pHost, Integer.parseInt(pPort),
								herram.getFolderS() + "//" + archFTP, pPathFile);
						word.CrearParrafoSJ("Se descargó el archivo: " + archFTP);
						word.CrearTitulo("Termina descarga de log para la operación " + nameApi);
					}
					if (nameApi.equals("FTP Reactive")) {
						word.CrearTitulo("Inicia descarga de log para la operación " + nameApi);
						SFTP_Descarga sftpDes = new SFTP_Descarga();
						String archFTP = "resultado-reactivate-" + herram.getFechaCBatch() + "-" + pId + ".log";
						String pPathFile = pRuta + archFTP;
						sftpDes.DescargarPorSFTP(pUser, pPass, pHost, Integer.parseInt(pPort),
								herram.getFolderS() + "//" + archFTP, pPathFile);
						word.CrearParrafoSJ("Se descargó el archivo: " + archFTP);
						word.CrearTitulo("Termina descarga de log para la operación " + nameApi);
					}
					if (herram.getBError() == true) {
						cell = nSheet.getRow(fila).getCell(2);
						cell.setCellValue("NOK");
					} else if (herram.getBError() == false) {
						cell = nSheet.getRow(fila).getCell(2);
						cell.setCellValue("OK");
					}
				}
			}
			word.CrearTitulo("Termina prueba Batch");
			word.CrearWord("Evidencia de Batch", herram.getFolderS());
			iConf.close();
			workbook.close();
			FileOutputStream fOConf = new FileOutputStream(fConf);
			nWorkbook.write(fOConf);
			nWorkbook.close();
			fOConf.close();
			herram.setBError(false);
		} catch (IOException | IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	public void crearArchivosBatch(Herramientas herram, XSSFSheet sheetDN, XSSFSheet nSheetDN, String nameAPIS,
			String rutaArchivoBatch) {
		try {
			XSSFRow rowDN = null;
			Cell cellDN = null;
			FileOutputStream fos = new FileOutputStream(rutaArchivoBatch);
			OutputStreamWriter archAc = new OutputStreamWriter(fos, "ISO-8859-1");
			int contador = 0;
			int veces = 0;
			for (int filaa = 1; filaa < sheetDN.getLastRowNum() + 1; filaa++) {
				if (filaBatch != 0 && veces == 0) {
					veces++;
					filaa = filaBatch + 1;
				}
				rowDN = sheetDN.getRow(filaa);
				for (int columna = 0; columna < rowDN.getLastCellNum(); columna++) {
					clave = rowDN.getCell(columna).toString();
					if (columna == 0) {
						dns = clave;
					}
					if (columna == 1) {
						Type = clave;
					}
					if (columna == 4) {
						bUtilizar = clave;
					}

				}
				if (!bUtilizar.trim().equals("SI")) {
					contador++;
					if (nameAPIS.equals("Activate") || nameAPIS.equals("changesprimary")) {
						archAc.write(dns + "|" + offeringId + "|" + coordenadas + "\n");
					}
					if (nameAPIS.equals("Reserve")) {
						archAc.write(dns + "|" + Type + "\n");
					}
					if (nameAPIS.equals("BatchPurchasesSupplementary")) {
						archAc.write(dns + "|" + offeringId + "\n");
					} else {
						archAc.write(dns + "\n");
					}
					cellDN = nSheetDN.getRow(filaa).getCell(2);
					cellDN.setCellValue(nameAPIS);
					cellDN = nSheetDN.getRow(filaa).getCell(3);
					cellDN.setCellValue(herram.getFechaC());
					cellDN = nSheetDN.getRow(filaa).getCell(4);
					cellDN.setCellValue("SI");
				}
				if (Integer.parseInt(dn) <= contador) {
					filaBatch = filaa;
					break;
				}
			}
			archAc.close();
			// herram.esperar(5);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
