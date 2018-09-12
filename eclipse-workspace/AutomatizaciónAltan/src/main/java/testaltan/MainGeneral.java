package testaltan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import apisaltanjson.tipoab.AltanActivateImsi;
import apisaltanjson.tipoab.AltanConsultaCambiosVinculacion;
import apisaltanjson.tipoab.AltanDeactivateImsi;
import apisaltanjson.tipoab.AltanInactivateImsi;
import apisaltanjson.tipoab.AltanNotificacionCambioPlan;
import apisaltanjson.tipoab.AltanNotificacionCambioVinculacion;
import apisaltanjson.tipoab.AltanReactivateImsi;
import apisaltanjson.tipoab.AltanRecicladoMSISDN;
import apisaltanjson.tipoab.AltanSolicitudMSISDN;
import apisaltanjson.tipoc.AltanActivate;
import apisaltanjson.tipoc.AltanCambioSIM;
import apisaltanjson.tipoc.AltanChangePrimary;
import apisaltanjson.tipoc.AltanCoverage;
import apisaltanjson.tipoc.AltanDeactivate;
import apisaltanjson.tipoc.AltanLockIMEI;
import apisaltanjson.tipoc.AltanOauth;
import apisaltanjson.tipoc.AltanOfferSupplemenary;
import apisaltanjson.tipoc.AltanPreDeactivate;
import apisaltanjson.tipoc.AltanProfile;
import apisaltanjson.tipoc.AltanQueryOrder;
import apisaltanjson.tipoc.AltanReactivate;
import apisaltanjson.tipoc.AltanResume;
import apisaltanjson.tipoc.AltanSuspend;
import apisaltanjson.tipoc.AltanUnlockIMEI;
import herramientas.Herramientas;
import herramientas.SFTP_Descarga;
import herramientas.Word;
import seleniumaltan.AltanThreshold;
import seleniumaltan.AltanUrlRedirect;

public class MainGeneral {
	private static Runtime rt = Runtime.getRuntime();
	String clave = "";
	String nameApi = "";
	String proyecto = "";
	String bEjec = "";
	String cv = "";
	String amb = "";
	String ejec = "";
	String cDPrueba = "";
	String ligaBSS = "";
	String userBSS = "";
	String passBSS = "";
	String dn = "";
	String coordenadas = "";
	String offeringId = "";
	String imsis = "";
	String iccid = "";
	String type = "";
	String orderId = "";
	String imei = "";
	String nameOffering = "";
	String urlRedirect = "";
	String startDate = "";
	String endDate = "";
	String effectiveDate = "";
	String llave = "";
	String oUser = "";
	String oPassword = "";
	String pId = "";
	String oId = "";
	String token = "";
	String cToken = "";
	List<List<String>> listaDns = new ArrayList<List<String>>();
	List<List<String>> listaBDL = new ArrayList<List<String>>();
	List<List<String>> listaHBDL = new ArrayList<List<String>>();
	List<List<String>> listDetOfert = new ArrayList<List<String>>();

	public static void main(String[] args) {
		MainGeneral mGeneral = new MainGeneral();
		Herramientas herram = new Herramientas();
		herram.creaCarpeta("MainGeneral");
		mGeneral.configuracion(herram);
	}

	private void configuracion(Herramientas herram) {
		Word word = new Word();
		try {
			for (int i = 0; i <= 1; i++) {
				listaDns.add(new ArrayList<String>());
			}
			for (int i = 0; i <= 9; i++) {
				listaBDL.add(new ArrayList<String>());
			}
			AltanOauth aOauth = new AltanOauth();
			rt.exec("taskkill /F /IM chromedriver.exe");
			File fConf = new File(System.getProperty("user.dir") + "\\Configuración.xlsx");
			InputStream iConf = new FileInputStream(fConf);
			XSSFWorkbook workbook = new XSSFWorkbook(iConf);
			XSSFSheet sheet = workbook.getSheetAt(0);
			XSSFRow row;
			FileInputStream fIConf = new FileInputStream(fConf);
			XSSFWorkbook nWorkbook = new XSSFWorkbook(fIConf);
			XSSFSheet nSheet = nWorkbook.getSheetAt(0);
			Cell cell = null;
			word.CrearDocumentoST();
			word.CrearTitulo("Inicia prueba de E2E");
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
						ligaBSS = clave;
					}
					if (columna == 9) {
						userBSS = clave;
					}
					if (columna == 10) {
						passBSS = clave;
					}
					if (columna == 11) {
						dn = clave;
					}
					if (columna == 12) {
						coordenadas = clave;
					}
					if (columna == 13) {
						offeringId = clave;
					}
					if (columna == 14) {
						imsis = clave;
					}

					if (columna == 15) {
						iccid = clave;
					}
					if (columna == 16) {
						type = clave;
					}
					if (columna == 17) {
						nameOffering = clave;
					}
					if (columna == 18) {
						orderId = clave;
					}
					if (columna == 19) {
						imei = clave;
					}
					if (columna == 20) {
						urlRedirect = clave;
					}
					if (columna == 21) {
						startDate = clave;
					}
					if (columna == 22) {
						endDate = clave;
					}
					if (columna == 23) {
						effectiveDate = clave;
					}
					if (columna == 24) {
						llave = clave;
					}
					if (columna == 25) {
						oUser = clave;
					}
					if (columna == 26) {
						oPassword = clave;
					}
					if (columna == 27) {
						pId = clave;
					}
					if (columna == 28) {
						oId = clave;
					}
					if (columna == 29) {
						token = clave;
					}
					if (columna == 30) {
						cToken = clave;
					}
				}
				if (bEjec.trim().equals("SI")) {
					if (cToken.equals("1")) {
						aOauth.crearToken(herram, word, amb, llave);
						cell = nSheet.getRow(fila).getCell(26);
						cell.setCellValue(herram.getAccessToken());
						cell = nSheet.getRow(fila).getCell(27);
						cell.setCellValue("0");
					} else if (cToken.equals("0")) {
						herram.setAccessToken(token);
					}
					if (nameApi.equals("ProfileBSS")) {
						word.crearTabla(proyecto, cv, amb, ejec, cDPrueba, herram.getFechaC());
						word.CrearTitulo("Inicia prueba de APIGW con DN: " + dn);
						AltanProfile aProfile = new AltanProfile();
						aProfile.consultarDnsGBSS(dn, cDPrueba, amb, oUser, oPassword, pId, oId, ligaBSS, userBSS,
								passBSS, herram, word);
					}
					if (nameApi.equals("Profile")) {
						word.crearTabla(proyecto, cv, amb, ejec, cDPrueba, herram.getFechaC());
						word.CrearTitulo("Inicia prueba de APIGW con DN: " + dn);
						AltanProfile aProfile = new AltanProfile();
						aProfile.consultarDnsG(dn, cDPrueba, amb, oUser, oPassword, pId, oId, ligaBSS, userBSS, passBSS,
								herram, word);
					}
					if (nameApi.equals("Coverage")) {
						word.crearTabla(proyecto, cv, amb, ejec, cDPrueba, herram.getFechaC());
						word.CrearTitulo("Inicia prueba de APIGW con Coordenadas: " + coordenadas);
						AltanCoverage aCoverage = new AltanCoverage();
						aCoverage.consultarCoverageG(coordenadas, cDPrueba, amb, oUser, oPassword, pId, oId, herram,
								word);
					}
					if (nameApi.equals("QueryOrder")) {
						AltanQueryOrder aQOrder = new AltanQueryOrder();
						word.crearTabla(proyecto, cv, amb, ejec, cDPrueba, herram.getFechaC());
						word.CrearTitulo("Inicia prueba de APIGW Query Order");
						aQOrder.consultarQueryOrderG(orderId, cDPrueba, amb, oUser, oPassword, pId, oId, herram, word);
					}
					if (nameApi.equals("Activate")) {
						word.crearTabla(proyecto, cv, amb, ejec, cDPrueba, herram.getFechaC());
						word.CrearTitulo("Inicia prueba de APIGW con DN: " + dn);
						AltanActivate aActivate = new AltanActivate();
						aActivate.consultarActivateG(dn, cDPrueba, amb, oUser, oPassword, pId, oId, coordenadas,
								offeringId, herram, word);
						if (herram.getBError() == false) {
							AltanQueryOrder aQOrder = new AltanQueryOrder();
							word.crearTabla(proyecto, cv, amb, ejec, "Query Order", herram.getFechaC());
							word.CrearTitulo("Inicia prueba de APIGW Query Order");
							aQOrder.consultarQueryOrderG(herram.getIdOrden(), "Query Order", amb, oUser, oPassword, pId,
									oId, herram, word);
							herram.esperar(3);
							aQOrder.consultarQueryOrderG(herram.getIdOrden(), "Query Order", amb, oUser, oPassword, pId,
									oId, herram, word);
							herram.setIdOrden("");
						}
						herram.limpiarApis();
					}
					if (nameApi.equals("Suspend")) {
						word.crearTabla(proyecto, cv, amb, ejec, cDPrueba, herram.getFechaC());
						word.CrearTitulo("Inicia prueba de APIGW con DN: " + dn);
						AltanSuspend aSuspend = new AltanSuspend();
						aSuspend.consultarSuspendG(dn, cDPrueba, amb, oUser, oPassword, pId, oId, herram, word);
						if (herram.getBError() == false) {
							AltanQueryOrder aQOrder = new AltanQueryOrder();
							word.crearTabla(proyecto, cv, amb, ejec, "Query Order", herram.getFechaC());
							word.CrearTitulo("Inicia prueba de APIGW Query Order");
							aQOrder.consultarQueryOrderG(herram.getIdOrden(), "Query Order", amb, oUser, oPassword, pId,
									oId, herram, word);
							herram.esperar(3);
							aQOrder.consultarQueryOrderG(herram.getIdOrden(), "Query Order", amb, oUser, oPassword, pId,
									oId, herram, word);
							herram.setIdOrden("");
						}
						herram.limpiarApis();
					}
					if (nameApi.equals("UnlockIMEI")) {
						word.crearTabla(proyecto, cv, amb, ejec, cDPrueba, herram.getFechaC());
						word.CrearTitulo("Inicia prueba de APIGW con IMEI: " + imei);
						AltanUnlockIMEI aUnlockIMEI = new AltanUnlockIMEI();
						aUnlockIMEI.consultarUnlockIMEIG(imei, cDPrueba, amb, oUser, oPassword, pId, oId, herram, word);
					}
					if (nameApi.equals("LockIMEI")) {
						word.crearTabla(proyecto, cv, amb, ejec, cDPrueba, herram.getFechaC());
						word.CrearTitulo("Inicia prueba de APIGW con IMEI: " + imei);
						AltanLockIMEI aLockIMEI = new AltanLockIMEI();
						aLockIMEI.consultarLockIMEIG(imei, cDPrueba, amb, oUser, oPassword, pId, oId, herram, word);
					}
					if (nameApi.equals("Resume")) {
						word.crearTabla(proyecto, cv, amb, ejec, cDPrueba, herram.getFechaC());
						word.CrearTitulo("Inicia prueba de APIGW con DN: " + dn);
						AltanResume aResume = new AltanResume();
						aResume.consultarResumeG(dn, cDPrueba, amb, oUser, oPassword, pId, oId, herram, word);
						if (herram.getBError() == false) {
							AltanQueryOrder aQOrder = new AltanQueryOrder();
							word.crearTabla(proyecto, cv, amb, ejec, "Query Order", herram.getFechaC());
							word.CrearTitulo("Inicia prueba de APIGW Query Order");
							aQOrder.consultarQueryOrderG(herram.getIdOrden(), "Query Order", amb, oUser, oPassword, pId,
									oId, herram, word);
							herram.esperar(3);
							aQOrder.consultarQueryOrderG(herram.getIdOrden(), "Query Order", amb, oUser, oPassword, pId,
									oId, herram, word);
							herram.setIdOrden("");
						}
						herram.limpiarApis();
					}
					if (nameApi.equals("PreDeactive")) {
						word.crearTabla(proyecto, cv, amb, ejec, cDPrueba, herram.getFechaC());
						word.CrearTitulo("Inicia prueba de APIGW con DN: " + dn);
						AltanPreDeactivate aDeactive = new AltanPreDeactivate();
						aDeactive.consultarPreDeactiveG(dn, cDPrueba, amb, oUser, oPassword, pId, oId, herram, word);
						if (herram.getBError() == false) {
							AltanQueryOrder aQOrder = new AltanQueryOrder();
							word.crearTabla(proyecto, cv, amb, ejec, "Query Order", herram.getFechaC());
							word.CrearTitulo("Inicia prueba de APIGW Query Order");
							aQOrder.consultarQueryOrderG(herram.getIdOrden(), "Query Order", amb, oUser, oPassword, pId,
									oId, herram, word);
							herram.esperar(3);
							aQOrder.consultarQueryOrderG(herram.getIdOrden(), "Query Order", amb, oUser, oPassword, pId,
									oId, herram, word);
							herram.setIdOrden("");
						}
						herram.limpiarApis();
					}
					if (nameApi.equals("Deactive")) {
						word.crearTabla(proyecto, cv, amb, ejec, cDPrueba, herram.getFechaC());
						word.CrearTitulo("Inicia prueba de APIGW con DN: " + dn);
						AltanDeactivate aDeactive = new AltanDeactivate();
						aDeactive.consultarDeactiveG(dn, cDPrueba, amb, oUser, oPassword, pId, oId, herram, word);
						if (herram.getBError() == false) {
							AltanQueryOrder aQOrder = new AltanQueryOrder();
							word.crearTabla(proyecto, cv, amb, ejec, "Query Order", herram.getFechaC());
							word.CrearTitulo("Inicia prueba de APIGW Query Order");
							aQOrder.consultarQueryOrderG(herram.getIdOrden(), "Query Order", amb, oUser, oPassword, pId,
									oId, herram, word);
							herram.esperar(3);
							aQOrder.consultarQueryOrderG(herram.getIdOrden(), "Query Order", amb, oUser, oPassword, pId,
									oId, herram, word);
							herram.setIdOrden("");
						}
						herram.limpiarApis();
					}
					if (nameApi.equals("Reactive")) {
						word.crearTabla(proyecto, cv, amb, ejec, cDPrueba, herram.getFechaC());
						word.CrearTitulo("Inicia prueba de APIGW con DN: " + dn);
						AltanReactivate aReactivate = new AltanReactivate();
						aReactivate.consultarReactiveG(dn, cDPrueba, amb, oUser, oPassword, pId, oId, herram, word);
						if (herram.getBError() == false) {
							AltanQueryOrder aQOrder = new AltanQueryOrder();
							word.crearTabla(proyecto, cv, amb, ejec, "Query Order", herram.getFechaC());
							word.CrearTitulo("Inicia prueba de APIGW Query Order");
							aQOrder.consultarQueryOrderG(herram.getIdOrden(), "Query Order", amb, oUser, oPassword, pId,
									oId, herram, word);
							herram.esperar(3);
							aQOrder.consultarQueryOrderG(herram.getIdOrden(), "Query Order", amb, oUser, oPassword, pId,
									oId, herram, word);
							herram.setIdOrden("");
						}
						herram.limpiarApis();
					}
					if (nameApi.equals("UpdateLinking")) {
						word.CrearTitulo("Pendiente, en desarrollo");
					}
					if (nameApi.equals("OfferSupplemenary")) {
						word.crearTabla(proyecto, cv, amb, ejec, cDPrueba, herram.getFechaC());
						word.CrearTitulo("Inicia prueba de APIGW con msisdn: " + dn);
						AltanOfferSupplemenary aOSupplementary = new AltanOfferSupplemenary();
						aOSupplementary.consultarOfferSupplemenaryG(dn, offeringId, cDPrueba, amb, oUser, oPassword,
								pId, oId, herram, word);
						if (herram.getBError() == false) {
							AltanQueryOrder aQOrder = new AltanQueryOrder();
							word.crearTabla(proyecto, cv, amb, ejec, "Query Order", herram.getFechaC());
							word.CrearTitulo("Inicia prueba de APIGW Query Order");
							aQOrder.consultarQueryOrderG(herram.getIdOrden(), "Query Order", amb, oUser, oPassword, pId,
									oId, herram, word);
							herram.esperar(3);
							aQOrder.consultarQueryOrderG(herram.getIdOrden(), "Query Order", amb, oUser, oPassword, pId,
									oId, herram, word);
							herram.setIdOrden("");
						}
						herram.limpiarApis();
					}
					if (nameApi.equals("ChangePrimary")) {
						word.crearTabla(proyecto, cv, amb, ejec, cDPrueba, herram.getFechaC());
						word.CrearTitulo("Inicia prueba de APIGW con DN: " + dn);
						AltanChangePrimary aChangePrimary = new AltanChangePrimary();
						aChangePrimary.consultarChangePrimaryG(dn, coordenadas, offeringId, cDPrueba, amb, oUser,
								oPassword, pId, oId, herram, word);
						if (herram.getBError() == false) {
							AltanQueryOrder aQOrder = new AltanQueryOrder();
							word.crearTabla(proyecto, cv, amb, ejec, "Query Order", herram.getFechaC());
							word.CrearTitulo("Inicia prueba de APIGW Query Order");
							aQOrder.consultarQueryOrderG(herram.getIdOrden(), "Query Order", amb, oUser, oPassword, pId,
									oId, herram, word);
							herram.esperar(3);
							aQOrder.consultarQueryOrderG(herram.getIdOrden(), "Query Order", amb, oUser, oPassword, pId,
									oId, herram, word);
							herram.setIdOrden("");
						}
						herram.limpiarApis();
					}
					if (nameApi.equals("ProfileMatrizOfertas")) {
						System.out.println(nameApi);
						word.CrearTitulo("Inicia prueba de APIGW con DN: " + dn);
						AltanProfile aProfile = new AltanProfile();
						aProfile.consultarDnsBDL(dn, cDPrueba, amb, oUser, oPassword, pId, oId, ligaBSS, userBSS,
								passBSS, herram, word);
						word.CrearTitulo("Inicia comparación API vs MatrizOfertas:");
						word.crearTabla(proyecto, cv, amb, ejec, cDPrueba, herram.getFechaC());
						word.crearParrafoNegritas(
								"NOTA: Revisar que la información que viene en la matriz se encuentre en la tabla de APIGW");
						herram.leerMatrizOfertas(word, herram, nWorkbook, dn, userBSS, passBSS, ligaBSS, proyecto, ejec,
								cv, amb, oUser, oPassword, pId, oId, coordenadas);
						herram.limpiarApis();
					}
					if (nameApi.equals("ValidaciónThreshold")) {
						System.out.println(nameApi);
						word.CrearTitulo("Inicia prueba Threshold con IDOferta: " + offeringId);
						AltanThreshold aThreshold = new AltanThreshold();
						aThreshold.mainThreshold(word, offeringId, userBSS, passBSS, ligaBSS, orderId);
						herram.limpiarApis();
						word.crearSaltoL();
						word.CrearTitulo("Finaliza prueba Threshold con IDOferta: " + offeringId);
					}
					if (nameApi.equals("ValidaciónUrlRedirect")) {
						System.out.println(nameApi);
						word.CrearTitulo("Inicia prueba Redirect con IDOferta: " + offeringId);
						AltanUrlRedirect aRedirect = new AltanUrlRedirect();
						aRedirect.mainRedirect(word, userBSS, passBSS, ligaBSS, offeringId + "|" + nameOffering,
								urlRedirect);
						herram.limpiarApis();
						word.crearSaltoL();
						word.CrearTitulo("Finaliza prueba Redirect con IDOferta: " + offeringId);
					}
					if (nameApi.equals("BDL")) {
						String tempDn = dn.trim();
						if (tempDn.equals("")) {
							herram.setBError(true);
						} else if (tempDn.equals(tempDn)) {
							listaDns.get(0).add(tempDn);
							herram.setBError(false);
						}
					}
					if (nameApi.equals("BDLAnálisis")) {
						SFTP_Descarga sftpDes = new SFTP_Descarga();
						String pPathFile = "/data/disk0/input/evtesting/report/data/" + pId + "/" + herram.getFechaBdl()
								+ "/Testing_" + pId + "_" + herram.getFechaBdl() + ".csv";
						sftpDes.DescargarPorSFTPG("evtesting", "", "34.237.230.77", 9022,
								herram.getFolderS() + "//Testing_" + pId + "_" + herram.getFechaBdl() + ".csv",
								pPathFile);

						FileReader fr = new FileReader(
								herram.getFolderS() + "//Testing_" + pId + "_" + herram.getFechaBdl() + ".csv");
						BufferedReader br = new BufferedReader(fr);
						String linea = "";
						while ((linea = br.readLine()) != null) {
							String[] separado = linea.replace("|", "!!&%").split("!!&%");
							for (int i = 0; i < separado.length; i++) {
								listaBDL.get(i).add(separado[i]);
							}
						}
						br.close();
						word.crearTabla(proyecto, cv, amb, ejec, cDPrueba, herram.getFechaC());
						for (int ii = 0; ii <= listaDns.get(0).size() - 1; ii++) {
							System.out.println("\nDN: " + listaDns.get(0).get(ii));
							word.CrearTitulo("Inicia prueba de APIGW con DN: " + dn);
							AltanProfile aProfile = new AltanProfile();
							aProfile.consultarDnsBDL(listaDns.get(0).get(ii), cDPrueba, amb, oUser, oPassword, pId, oId,
									ligaBSS, userBSS, passBSS, herram, word);
							if (herram.getBError() == false) {
								listDetOfert = herram.getListDetOfert();
								word.CrearTitulo("Inicia comparación BDL vs API:");
								boolean bBdlH = true;
								for (int iii = 0; iii <= listDetOfert.get(0).size() - 1; iii++) {
									listaHBDL.clear();
									for (int i = 0; i <= 9; i++) {
										listaHBDL.add(new ArrayList<String>());
									}
									for (int i = 0; i <= listaBDL.get(0).size() - 1; i++) {
										if (listaBDL.get(0).get(i).equals(pId)) {
											if (listaBDL.get(1).get(i).equals("52" + listaDns.get(0).get(ii))) {
												if (listaBDL.get(3).get(i).equals(listDetOfert.get(0).get(iii))) {
													if (listaBDL.get(5).get(i).equals(listDetOfert.get(1).get(iii))) {
														int unsedAmtApi = 0;
														int unsedAmtBdl = 0;
														int consumoDia = 0;
														if (listDetOfert.get(2).get(iii).trim().equals("")) {
															unsedAmtApi = 0;
														} else {
															unsedAmtApi = Integer.parseInt(
																	String.valueOf(Math.round(Double.parseDouble(
																			listDetOfert.get(2).get(iii).trim()))));
														}
														if (listaBDL.get(7).get(i).trim().equals("")
																&& listaBDL.get(6).get(i).trim().equals("")) {
															consumoDia = 0;
															unsedAmtBdl = Integer.parseInt(listaBDL.get(5).get(i));

														} else {
															consumoDia = Integer.parseInt(
																	String.valueOf(Math.round(Double.parseDouble(
																			listaBDL.get(7).get(i).trim()))));
															unsedAmtBdl = Integer.parseInt(
																	String.valueOf(Math.round(Double.parseDouble(
																			listaBDL.get(6).get(i).trim()))));
														}
														if (unsedAmtBdl == unsedAmtApi) {
															int anoBDLEf = Integer
																	.valueOf(listaBDL.get(8).get(i).substring(0, 4));
															int mesBDLEf = Integer
																	.valueOf(listaBDL.get(8).get(i).substring(5, 7));
															int diaBDLEf = Integer
																	.valueOf(listaBDL.get(8).get(i).substring(8, 10));
															int anoBDLEx = Integer
																	.valueOf(listaBDL.get(9).get(i).substring(0, 4));
															int mesBDLEx = Integer
																	.valueOf(listaBDL.get(9).get(i).substring(5, 7));
															int diaBDLEx = Integer
																	.valueOf(listaBDL.get(9).get(i).substring(8, 10));
															Calendar calendarEf = Calendar.getInstance();
															Calendar calendarEx = Calendar.getInstance();
															calendarEf.set(anoBDLEf, mesBDLEf - 1, diaBDLEf, 1, 1);
															calendarEx.set(anoBDLEx, mesBDLEx - 1, diaBDLEx, 1, 1);
															int anoBDLEfA = Integer.valueOf(
																	listDetOfert.get(3).get(iii).substring(0, 4));
															int mesBDLEfA = Integer.valueOf(
																	listDetOfert.get(3).get(iii).substring(4, 6));
															int diaBDLEfA = Integer.valueOf(
																	listDetOfert.get(3).get(iii).substring(6, 8));
															int anoBDLExA = Integer.valueOf(
																	listDetOfert.get(4).get(iii).substring(0, 4));
															int mesBDLExA = Integer.valueOf(
																	listDetOfert.get(4).get(iii).substring(4, 6));
															int diaBDLExA = Integer.valueOf(
																	listDetOfert.get(4).get(iii).substring(6, 8));
															Calendar calendarEfA = Calendar.getInstance();
															Calendar calendarExA = Calendar.getInstance();
															calendarEfA.set(anoBDLEfA, mesBDLEfA - 1, diaBDLEfA, 1, 1);
															calendarExA.set(anoBDLExA, mesBDLExA - 1, diaBDLExA, 1, 1);
															SimpleDateFormat dateformat = new SimpleDateFormat(
																	"dd/MM/yyyy");
															if (dateformat.format(calendarEfA.getTime())
																	.equals(dateformat.format(calendarEf.getTime()))) {
																if (dateformat.format(calendarExA.getTime()).equals(
																		dateformat.format(calendarEx.getTime()))) {
																	word.CrearParrafo(listDetOfert.get(5).get(iii));
																	word.crearTablaR();
																	word.crearTablaRTBDL();
																	herram.compararBDL("be_id", listaBDL.get(0).get(i),
																			pId, word);
																	herram.compararBDL("msisdn", listaBDL.get(1).get(i),
																			"52" + listaDns.get(0).get(ii), word);
																	herram.compararBDL("offeringId",
																			listaBDL.get(3).get(i),
																			listDetOfert.get(0).get(iii), word);
																	herram.compararBDL("initialAmt",
																			listaBDL.get(5).get(i),
																			listDetOfert.get(1).get(iii), word);
																	herram.compararBDL("unusedAmt",
																			String.valueOf(unsedAmtBdl),
																			String.valueOf(unsedAmtApi), word);
																	if (consumoDia == 0) {
																		herram.compararBDL("consumoDia",
																				String.valueOf(consumoDia), "0", word);
																	} else {
																		herram.compararBDL("consumoDia",
																				String.valueOf(consumoDia),
																				String.valueOf(Integer.parseInt(
																						listDetOfert.get(1).get(iii))
																						- unsedAmtApi),
																				word);
																	}
																	herram.compararBDL("effectiveDate",
																			dateformat.format(calendarEf.getTime()),
																			dateformat.format(calendarEfA.getTime()),
																			word);
																	herram.compararBDL("expireDate",
																			dateformat.format(calendarEx.getTime()),
																			dateformat.format(calendarExA.getTime()),
																			word);
																	word.crearSaltoL();
																	bBdlH = false;
																	System.out.println("Monto unsedApi: " + unsedAmtApi
																			+ " Monto unsedBdl: " + unsedAmtBdl);
																	System.out.println("BE: " + listaBDL.get(0).get(i)
																			+ " DN: " + listaBDL.get(1).get(i)
																			+ " IMSI: " + listaBDL.get(2).get(i)
																			+ " offeringId: " + listaBDL.get(3).get(i)
																			+ " Name: " + listaBDL.get(4).get(i)
																			+ " initialAmt: " + listaBDL.get(5).get(i)
																			+ " unusedAmt: " + listaBDL.get(6).get(i)
																			+ " Consumo_dia: " + listaBDL.get(7).get(i)
																			+ " effectiveDate: "
																			+ listaBDL.get(8).get(i) + " expireDate: "
																			+ listaBDL.get(9).get(i));
																	long startTime = calendarEf.getTimeInMillis();
																	long endTime = calendarEx.getTimeInMillis();
																	long diffTime = endTime - startTime;
																	long diffDays = diffTime / (1000 * 60 * 60 * 24);
																	System.out.println(
																			"La diferencia de dias es: " + diffDays);
																	System.out.println("offeringId: "
																			+ listDetOfert.get(0).get(iii)
																			+ " initialAmt: "
																			+ listDetOfert.get(1).get(iii)
																			+ " unusedAmt: "
																			+ listDetOfert.get(2).get(iii)
																			+ " effectiveDate: "
																			+ listDetOfert.get(3).get(iii)
																			+ " expireDate: "
																			+ listDetOfert.get(4).get(iii) + " name: "
																			+ listDetOfert.get(5).get(iii));
																}
															}
														}
													}
												}
												listaHBDL.get(0).add(listaBDL.get(0).get(i));
												listaHBDL.get(1).add(listaBDL.get(1).get(i));
												listaHBDL.get(2).add(listaBDL.get(2).get(i));
												listaHBDL.get(3).add(listaBDL.get(3).get(i));
												listaHBDL.get(4).add(listaBDL.get(4).get(i));
												listaHBDL.get(5).add(listaBDL.get(5).get(i));
												listaHBDL.get(6).add(listaBDL.get(6).get(i));
												listaHBDL.get(7).add(listaBDL.get(7).get(i));
												listaHBDL.get(8).add(listaBDL.get(8).get(i));
												listaHBDL.get(9).add(listaBDL.get(9).get(i));
											}
										}
									}
								}
								herram.limpiarApis();
								if (bBdlH == true) {
									word.CrearParrafo("NO SE ENCONTRO DN EN EL REPORTE DE BDL");
									word.CrearTitulo("Termina comparación BDL vs API:");
									word.CrearTitulo("Historial del MSISDN:");
								} else if (bBdlH == false) {
									word.CrearTitulo("Termina comparación BDL vs API:");
									word.CrearTitulo("Historial del MSISDN:");
								}
								System.out.println("Tamaño de lista: " + listaHBDL.get(0).size());
								if (listaHBDL.get(0).size() > 0) {
									word.crearTablaRBdl();
									word.crearTablaRTBDLH();
									for (int x = 0; x <= listaHBDL.get(0).size() - 1; x++) {
										word.crearTablaRCH(listaHBDL.get(0).get(x), listaHBDL.get(1).get(x),
												listaHBDL.get(2).get(x), listaHBDL.get(3).get(x),
												listaHBDL.get(4).get(x), listaHBDL.get(5).get(x),
												listaHBDL.get(6).get(x), listaHBDL.get(7).get(x),
												listaHBDL.get(8).get(x), listaHBDL.get(9).get(x));
									}
									listaHBDL.clear();
									for (int i = 0; i <= 9; i++) {
										listaHBDL.add(new ArrayList<String>());
									}
								} else {
									word.CrearParrafo("NO SE ENCONTRO HISTORIAL EN EL REPORTE DE BDL");
								}
							}
						}
						herram.setBError(false);
					}
					if (nameApi.equals("NotificaciónActivateImsi")) {
						word.crearSaltoL();
						word.crearTabla(proyecto, cv, amb, ejec, cDPrueba, herram.getFechaC());
						word.CrearTitulo("Inicia prueba de notificación de activación con Imsi: " + imsis);
						AltanActivateImsi aActivateIm = new AltanActivateImsi();
						aActivateIm.ActivateImsi(amb, imsis, herram, oUser, oPassword, pId, oId, dn, coordenadas,
								offeringId, word);
						herram.limpiarApis();
					}
					if (nameApi.equals("NotificaciónDeactivateImsi")) {
						word.crearSaltoL();
						word.crearTabla(proyecto, cv, amb, ejec, cDPrueba, herram.getFechaC());
						word.CrearTitulo("Inicia prueba de notificación de desactivación con Imsi: " + imsis);
						AltanDeactivateImsi deActivateIm = new AltanDeactivateImsi();
						deActivateIm.DeactivateImsi(amb, imsis, herram, oUser, oPassword, pId, oId, dn, word);
						herram.limpiarApis();
					}
					if (nameApi.equals("NotificaciónInactivateImsi")) {
						word.crearSaltoL();
						word.crearTabla(proyecto, cv, amb, ejec, cDPrueba, herram.getFechaC());
						word.CrearTitulo("Inicia prueba de notificación de inactivación con Imsi: " + imsis);
						AltanInactivateImsi inActivate = new AltanInactivateImsi();
						inActivate.InactivateImsi(amb, imsis, herram, oUser, oPassword, pId, oId, dn, word);
						herram.limpiarApis();
					}
					if (nameApi.equals("NotificaciónReactivateImsi")) {
						word.crearSaltoL();
						word.crearTabla(proyecto, cv, amb, ejec, cDPrueba, herram.getFechaC());
						word.CrearTitulo("Inicia prueba de notificación de reactivación con Imsi: " + imsis);
						AltanReactivateImsi reActivate = new AltanReactivateImsi();
						reActivate.ReactivateImsi(amb, imsis, herram, oUser, oPassword, pId, oId, dn, word);
						herram.limpiarApis();
					}
					if (nameApi.equals("NotificacionChangePrimary")) {
						word.crearSaltoL();
						word.crearTabla(proyecto, cv, amb, ejec, cDPrueba, herram.getFechaC());
						word.CrearTitulo("Inicia prueba de notificación de cambio de plan con Imsi: " + imsis);
						AltanNotificacionCambioPlan aNotifyP = new AltanNotificacionCambioPlan();
						aNotifyP.NotificacionCambioPlan(amb, imsis, herram, oUser, oPassword, pId, oId, dn, offeringId,
								word);
						herram.limpiarApis();
					}
					if (nameApi.equals("NotificacionCambioVinculacion")) {
						word.crearSaltoL();
						word.crearTabla(proyecto, cv, amb, ejec, cDPrueba, herram.getFechaC());
						word.CrearTitulo("Inicia prueba de notificación de cambio de vinculacion con Imsi: " + imsis);
						AltanNotificacionCambioVinculacion aNotify = new AltanNotificacionCambioVinculacion();
						aNotify.NotificacionCambioVinculacion(amb, imsis, herram, oUser, oPassword, pId, oId, dn,
								coordenadas, offeringId, word);
						herram.limpiarApis();
					}
					if (nameApi.equals("SolicitudMSISDN")) {
						word.crearSaltoL();
						word.crearTabla(proyecto, cv, amb, ejec, cDPrueba, herram.getFechaC());
						word.CrearTitulo("Inicia prueba de solicitud de MSISDN con prefijo: " + dn);
						AltanSolicitudMSISDN aSDN = new AltanSolicitudMSISDN();
						aSDN.SolicitudMSISDN(amb, herram, oUser, oPassword, pId, oId, dn, type, word);
						herram.limpiarApis();
					}
					if (nameApi.equals("RecicladoMSISDN")) {
						word.crearSaltoL();
						word.crearTabla(proyecto, cv, amb, ejec, cDPrueba, herram.getFechaC());
						word.CrearTitulo("Inicia prueba de reciclado de MSISDN con DN: " + dn);
						AltanRecicladoMSISDN aReciclado = new AltanRecicladoMSISDN();
						aReciclado.RecicladoMSISDN(amb, herram, oUser, oPassword, pId, oId, dn, word);
						herram.limpiarApis();
					}
					if (nameApi.equals("ConsultaCambioVinculacion")) {
						word.crearSaltoL();
						word.crearTabla(proyecto, cv, amb, ejec, cDPrueba, herram.getFechaC());
						word.CrearTitulo("Inicia prueba de consulta de cambio de vinculacion con Imsi: " + imsis);
						AltanConsultaCambiosVinculacion acVinculacion = new AltanConsultaCambiosVinculacion();
						acVinculacion.ConsultaCambiosVinculacion(amb, imsis, herram, oUser, oPassword, pId, oId, dn,
								coordenadas, offeringId, word);
						herram.limpiarApis();
					}
					if (nameApi.equals("CambioSIM")) {
						word.crearSaltoL();
						word.crearTabla(proyecto, cv, amb, ejec, cDPrueba, herram.getFechaC());
						word.CrearTitulo("Inicia prueba de cambio de SIM con DN: " + dn);
						AltanCambioSIM aCambio = new AltanCambioSIM();
						aCambio.CambioSIM(amb, imsis, herram, oUser, oPassword, pId, oId, dn, iccid, word);
						herram.limpiarApis();
					}
					if (herram.getBError() == true) {
						cell = nSheet.getRow(fila).getCell(2);
						cell.setCellValue("NOK");
					} else if (herram.getBError() == false) {
						cell = nSheet.getRow(fila).getCell(2);
						cell.setCellValue("OK");
					}
					rt.exec("taskkill /F /IM chromedriver.exe");
				}
			}
			word.CrearTitulo("Termina prueba de E2E");
			word.CrearWord("Evidencia de flujo E2E", herram.getFolderS());
			iConf.close();
			workbook.close();
			FileOutputStream fOConf = new FileOutputStream(fConf);
			nWorkbook.write(fOConf);
			nWorkbook.close();
			fOConf.close();
			herram.setBError(false);
		} catch (Exception e) {
			word.CrearTitulo("Se encontró un error");
			System.out.println(e.getMessage());
			word.CrearParrafo(e.getMessage());
			word.CrearWord("Evidencia de flujo E2E", herram.getFolderS());
		}
	}
}