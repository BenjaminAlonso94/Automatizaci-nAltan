package testaltan;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import apisaltanjson.tipoc.AltanActivate;
import apisaltanjson.tipoc.AltanChangePrimary;
import apisaltanjson.tipoc.AltanOauth;
import apisaltanjson.tipoc.AltanOfferSupplemenary;
import apisaltanjson.tipoc.AltanPreDeactivate;
import apisaltanjson.tipoc.AltanQueryOrder;
import apisaltanjson.tipoc.AltanReactivate;
import apisaltanjson.tipoc.AltanResume;
import apisaltanjson.tipoc.AltanSuspend;
import herramientas.Herramientas;

public class MainGeneralLoop {
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
	List<List<String>> listaDns = new ArrayList<List<String>>();
	List<List<String>> listaBDL = new ArrayList<List<String>>();
	List<List<String>> listaHBDL = new ArrayList<List<String>>();
	List<List<String>> listDetOfert = new ArrayList<List<String>>();

	public static void main(String[] args) {
		MainGeneralLoop mGeneral = new MainGeneralLoop();
		Herramientas herram = new Herramientas();
		herram.creaCarpetaLoop("MainGeneralLoop");
		mGeneral.configuracion(herram);
	}

	private void configuracion(Herramientas herram) {
		String rutaArchivoNOK = herram.getFolderS() + "//LogGenerealNOK.log";
		String rutaArchivoTraza = herram.getFolderS() + "//LogGenerealTraza.log";
		try {
			for (int i = 0; i <= 1; i++) {
				listaDns.add(new ArrayList<String>());
			}
			for (int i = 0; i <= 9; i++) {
				listaBDL.add(new ArrayList<String>());
			}
			AltanOauth aOauth = new AltanOauth();
			File fConf = new File(herram.obtCarpAct() + "//ConfiguraciónLoop.xlsx");
			InputStream iConf = new FileInputStream(fConf);
			XSSFWorkbook workbook = new XSSFWorkbook(iConf);
			XSSFSheet sheet = workbook.getSheetAt(0);
			XSSFRow row;
			FileInputStream fIConf = new FileInputStream(fConf);
			XSSFWorkbook nWorkbook = new XSSFWorkbook(fIConf);
			XSSFSheet nSheet = nWorkbook.getSheetAt(0);
			Cell cell = null;
			System.out.println("Inicia prueba de E2E");
			herram.escribirLogTrazas(rutaArchivoTraza, "Inicia prueba de E2E");
			Calendar calendarActual = Calendar.getInstance();
			Calendar calendarManana = Calendar.getInstance();
			calendarManana.set(calendarActual.get(Calendar.YEAR), calendarActual.get(Calendar.MONTH),
					calendarActual.get(Calendar.DAY_OF_MONTH) + 1, 1, 1);
			SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
			String fechaActual = dateformat.format(calendarActual.getTime());
			String fechaManana = dateformat.format(calendarManana.getTime());
			int tokenVeces = 0;
			int activateVeces183 = 0;
			int activateVeces190 = 0;
			int activateVeces106 = 0;
			int activateVeces175 = 0;
			int reintentos = 0;
			int loop1 = 0;
			int loop2 = 0;
			while (loop1 == loop2) {
				for (int fila = 1; fila < sheet.getLastRowNum() + 1; fila++) {
					row = sheet.getRow(fila);
					calendarActual = Calendar.getInstance();
					fechaActual = dateformat.format(calendarActual.getTime());
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
							token = clave;
						}
					}
					if (bEjec.trim().equals("SI")) {
						if (fechaActual.equals(fechaManana)) {
							calendarManana.set(calendarActual.get(Calendar.YEAR), calendarActual.get(Calendar.MONTH),
									calendarActual.get(Calendar.DAY_OF_MONTH) + 1, 1, 1);
							fechaManana = dateformat.format(calendarManana.getTime());
							aOauth.crearTokenLoop(herram, amb, llave);
							cell = nSheet.getRow(fila).getCell(16);
							cell.setCellValue(herram.getAccessToken());
						} else if (herram.getAccessToken().equals("") && tokenVeces == 0) {
							aOauth.crearTokenLoop(herram, amb, llave);
							cell = nSheet.getRow(fila).getCell(16);
							cell.setCellValue(herram.getAccessToken());
							tokenVeces++;
						}
						if (nameApi.equals("Activate") && activateVeces183 == 0 && pId.equals("183")) {
							System.out.println("Inicia prueba de APIGW Activate con DN: " + dn);
							herram.escribirLogTrazas(rutaArchivoTraza, "Inicia prueba de APIGW Activate con DN: " + dn);
							AltanActivate aActivate = new AltanActivate();
							aActivate.consultarActivateLoop(dn, cDPrueba, amb, oUser, oPassword, pId, oId, coordenadas,
									offeringId, herram);
							if (herram.getBError() == false) {
								activateVeces183++;
								AltanQueryOrder aQOrder = new AltanQueryOrder();
								System.out.println("Inicia prueba de APIGW Query Order");
								herram.escribirLogTrazas(rutaArchivoTraza, "Inicia prueba de APIGW Query Order");
								while (!herram.getStatusOrderId().equals("Completed") && reintentos <= 4) {
									herram.esperar(3);
									aQOrder.consultarQueryOrderLoop(herram.getIdOrden(), "Query Order", amb, oUser,
											oPassword, pId, oId, herram);
									if (herram.getStatusOrderId().equals("Suspended")) {
										break;
									}
									reintentos++;
								}
								herram.setStatusOrderId("");
								herram.setIdOrden("");
							}
						}
						if (nameApi.equals("Activate") && activateVeces190 == 0 && pId.equals("190")) {
							System.out.println("Inicia prueba de APIGW Activate con DN: " + dn);
							herram.escribirLogTrazas(rutaArchivoTraza, "Inicia prueba de APIGW Activate con DN: " + dn);
							AltanActivate aActivate = new AltanActivate();
							aActivate.consultarActivateLoop(dn, cDPrueba, amb, oUser, oPassword, pId, oId, coordenadas,
									offeringId, herram);
							if (herram.getBError() == false) {
								activateVeces190++;
								AltanQueryOrder aQOrder = new AltanQueryOrder();
								System.out.println("Inicia prueba de APIGW Query Order");
								herram.escribirLogTrazas(rutaArchivoTraza, "Inicia prueba de APIGW Query Order");
								while (!herram.getStatusOrderId().equals("Completed") && reintentos <= 4) {
									herram.esperar(3);
									aQOrder.consultarQueryOrderLoop(herram.getIdOrden(), "Query Order", amb, oUser,
											oPassword, pId, oId, herram);
									if (herram.getStatusOrderId().equals("Suspended")) {
										break;
									}
									reintentos++;
								}
								herram.setStatusOrderId("");
								herram.setIdOrden("");
							}
						}
						if (nameApi.equals("Activate") && activateVeces106 == 0 && pId.equals("106")) {
							System.out.println("Inicia prueba de APIGW Activate con DN: " + dn);
							herram.escribirLogTrazas(rutaArchivoTraza, "Inicia prueba de APIGW Activate con DN: " + dn);
							AltanActivate aActivate = new AltanActivate();
							aActivate.consultarActivateLoop(dn, cDPrueba, amb, oUser, oPassword, pId, oId, coordenadas,
									offeringId, herram);
							if (herram.getBError() == false) {
								activateVeces106++;
								AltanQueryOrder aQOrder = new AltanQueryOrder();
								System.out.println("Inicia prueba de APIGW Query Order");
								herram.escribirLogTrazas(rutaArchivoTraza, "Inicia prueba de APIGW Query Order");
								while (!herram.getStatusOrderId().equals("Completed") && reintentos <= 4) {
									herram.esperar(3);
									aQOrder.consultarQueryOrderLoop(herram.getIdOrden(), "Query Order", amb, oUser,
											oPassword, pId, oId, herram);
									if (herram.getStatusOrderId().equals("Suspended")) {
										break;
									}
									reintentos++;
								}
								herram.setStatusOrderId("");
								herram.setIdOrden("");
							}
						}
						if (nameApi.equals("Activate") && activateVeces175 == 0 && pId.equals("")) {
							System.out.println("Inicia prueba de APIGW Activate con DN: " + dn);
							herram.escribirLogTrazas(rutaArchivoTraza, "Inicia prueba de APIGW Activate con DN: " + dn);
							AltanActivate aActivate = new AltanActivate();
							aActivate.consultarActivateLoop(dn, cDPrueba, amb, oUser, oPassword, pId, oId, coordenadas,
									offeringId, herram);
							if (herram.getBError() == false) {
								activateVeces175++;
								AltanQueryOrder aQOrder = new AltanQueryOrder();
								System.out.println("Inicia prueba de APIGW Query Order");
								herram.escribirLogTrazas(rutaArchivoTraza, "Inicia prueba de APIGW Query Order");
								while (!herram.getStatusOrderId().equals("Completed") && reintentos <= 4) {
									herram.esperar(3);
									aQOrder.consultarQueryOrderLoop(herram.getIdOrden(), "Query Order", amb, oUser,
											oPassword, pId, oId, herram);
									if (herram.getStatusOrderId().equals("Suspended")) {
										break;
									}
									reintentos++;
								}
								herram.setStatusOrderId("");
								herram.setIdOrden("");
							}
						}
						if (nameApi.equals("Suspend")) {
							System.out.println("Inicia prueba de APIGW Suspend con DN: " + dn);
							herram.escribirLogTrazas(rutaArchivoTraza, "Inicia prueba de APIGW Suspend con DN: " + dn);
							AltanSuspend aSuspend = new AltanSuspend();
							aSuspend.consultarSuspendLoop(dn, cDPrueba, amb, oUser, oPassword, pId, oId, herram);
							if (herram.getBError() == false) {
								AltanQueryOrder aQOrder = new AltanQueryOrder();
								System.out.println("Inicia prueba de APIGW Query Order");
								herram.escribirLogTrazas(rutaArchivoTraza, "Inicia prueba de APIGW Query Order");
								while (!herram.getStatusOrderId().equals("Completed") && reintentos <= 4) {
									herram.esperar(3);
									aQOrder.consultarQueryOrderLoop(herram.getIdOrden(), "Query Order", amb, oUser,
											oPassword, pId, oId, herram);
									if (herram.getStatusOrderId().equals("Suspended")) {
										break;
									}
									reintentos++;
								}
								herram.setStatusOrderId("");
								herram.setIdOrden("");
							}
						}
						if (nameApi.equals("Resume")) {
							System.out.println("Inicia prueba de APIGW Resume con DN: " + dn);
							herram.escribirLogTrazas(rutaArchivoTraza, "Inicia prueba de APIGW Resume con DN: " + dn);
							AltanResume aResume = new AltanResume();
							aResume.consultarResumeLoop(dn, cDPrueba, amb, oUser, oPassword, pId, oId, herram);
							if (herram.getBError() == false) {
								AltanQueryOrder aQOrder = new AltanQueryOrder();
								System.out.println("Inicia prueba de APIGW Query Order");
								herram.escribirLogTrazas(rutaArchivoTraza, "Inicia prueba de APIGW Query Order");
								while (!herram.getStatusOrderId().equals("Completed") && reintentos <= 4) {
									herram.esperar(3);
									aQOrder.consultarQueryOrderLoop(herram.getIdOrden(), "Query Order", amb, oUser,
											oPassword, pId, oId, herram);
									if (herram.getStatusOrderId().equals("Suspended")) {
										break;
									}
									reintentos++;
								}
								herram.setStatusOrderId("");
								herram.setIdOrden("");
							}
						}
						if (nameApi.equals("PreDeactive")) {
							System.out.println("Inicia prueba de APIGW PreDeactive con DN: " + dn);
							herram.escribirLogTrazas(rutaArchivoTraza,
									"Inicia prueba de APIGW PreDeactive con DN: " + dn);
							AltanPreDeactivate aDeactive = new AltanPreDeactivate();
							aDeactive.consultarPreDeactiveLoop(dn, cDPrueba, amb, oUser, oPassword, pId, oId, herram);
							if (herram.getBError() == false) {
								AltanQueryOrder aQOrder = new AltanQueryOrder();
								System.out.println("Inicia prueba de APIGW Query Order");
								herram.escribirLogTrazas(rutaArchivoTraza, "Inicia prueba de APIGW Query Order");
								while (!herram.getStatusOrderId().equals("Completed") && reintentos <= 4) {
									herram.esperar(3);
									aQOrder.consultarQueryOrderLoop(herram.getIdOrden(), "Query Order", amb, oUser,
											oPassword, pId, oId, herram);
									if (herram.getStatusOrderId().equals("Suspended")) {
										break;
									}
									reintentos++;
								}
								herram.setStatusOrderId("");
								herram.setIdOrden("");
							}
						}
						if (nameApi.equals("Reactive")) {
							System.out.println("Inicia prueba de APIGW Reactive con DN: " + dn);
							herram.escribirLogTrazas(rutaArchivoTraza, "Inicia prueba de APIGW Reactive con DN: " + dn);
							AltanReactivate aReactivate = new AltanReactivate();
							aReactivate.consultarReactiveLoop(dn, cDPrueba, amb, oUser, oPassword, pId, oId, herram);
							if (herram.getBError() == false) {
								AltanQueryOrder aQOrder = new AltanQueryOrder();
								System.out.println("Inicia prueba de APIGW Query Order");
								herram.escribirLogTrazas(rutaArchivoTraza, "Inicia prueba de APIGW Query Order");
								while (!herram.getStatusOrderId().equals("Completed") && reintentos <= 4) {
									herram.esperar(3);
									aQOrder.consultarQueryOrderLoop(herram.getIdOrden(), "Query Order", amb, oUser,
											oPassword, pId, oId, herram);
									if (herram.getStatusOrderId().equals("Suspended")) {
										break;
									}
									reintentos++;
								}
								herram.setStatusOrderId("");
								herram.setIdOrden("");
							}
						}
						if (nameApi.equals("OfferSupplemenary")) {
							System.out.println("Inicia prueba de APIGW OfferSupplemenary con msisdn: " + dn);
							herram.escribirLogTrazas(rutaArchivoTraza,
									"Inicia prueba de APIGW OfferSupplemenary con DN: " + dn);
							AltanOfferSupplemenary aOSupplementary = new AltanOfferSupplemenary();
							aOSupplementary.consultarOfferSupplemenaryLoop(dn, offeringId, cDPrueba, amb, oUser,
									oPassword, pId, oId, herram);
							if (herram.getBError() == false) {
								AltanQueryOrder aQOrder = new AltanQueryOrder();
								System.out.println("Inicia prueba de APIGW Query Order");
								herram.escribirLogTrazas(rutaArchivoTraza, "Inicia prueba de APIGW Query Order");
								while (!herram.getStatusOrderId().equals("Completed") && reintentos <= 4) {
									herram.esperar(3);
									aQOrder.consultarQueryOrderLoop(herram.getIdOrden(), "Query Order", amb, oUser,
											oPassword, pId, oId, herram);
									if (herram.getStatusOrderId().equals("Suspended")) {
										break;
									}
									reintentos++;
								}
								herram.setStatusOrderId("");
								herram.setIdOrden("");
							}
						}
						if (nameApi.equals("ChangePrimary")) {
							System.out.println("Inicia prueba de APIGW ChangePrimary con DN: " + dn);
							herram.escribirLogTrazas(rutaArchivoTraza,
									"Inicia prueba de APIGW ChangePrimary con DN: " + dn);
							AltanChangePrimary aChangePrimary = new AltanChangePrimary();
							aChangePrimary.consultarChangePrimaryLoop(dn, coordenadas, offeringId, cDPrueba, amb, oUser,
									oPassword, pId, oId, herram);
							if (herram.getBError() == false) {
								AltanQueryOrder aQOrder = new AltanQueryOrder();
								System.out.println("Inicia prueba de APIGW Query Order");
								herram.escribirLogTrazas(rutaArchivoTraza, "Inicia prueba de APIGW Query Order");
								while (!herram.getStatusOrderId().equals("Completed") && reintentos <= 7) {
									herram.esperar(3);
									aQOrder.consultarQueryOrderLoop(herram.getIdOrden(), "Query Order", amb, oUser,
											oPassword, pId, oId, herram);
									if (herram.getStatusOrderId().equals("Suspended")) {
										break;
									}
									reintentos++;
								}
								herram.setStatusOrderId("");
								herram.setIdOrden("");
							}
						}
						if (nameApi.equals("Tiempo")) {
							int tiempoEsp = Integer.parseInt(dn);
							tiempoEsp = tiempoEsp * 60000;
							Thread.sleep(tiempoEsp);
						}
						if (herram.getBError() == true) {
							cell = nSheet.getRow(fila).getCell(2);
							cell.setCellValue("NOK");
							if (herram.getCodeErrorHttp() == 500
									|| herram.buscarCodeError(herram.getCodeStatus()) == true
									|| herram.getStatusOrderId().equals("Suspended")) {
								System.exit(0);
							}
						} else if (herram.getBError() == false) {
							cell = nSheet.getRow(fila).getCell(2);
							cell.setCellValue("OK");
							if (herram.getCodeErrorHttp() == 500
									|| herram.buscarCodeError(herram.getCodeStatus()) == true
									|| herram.getStatusOrderId().equals("Suspended")) {
								System.exit(0);
							}
						}
						if (reintentos > 4 && !nameApi.equals("ChangePrimary")) {
							herram.escribirLog(rutaArchivoNOK, nameApi, pId, dn, herram.getOrderId(), "",
									"Reintentos en operación", 2,
									"Se alcanzó el número de intentos para esperar que termine la operación de QueryOrder"
											+ herram.getOrderId());
							// System.exit(0);
						} else if (reintentos > 7 && nameApi.equals("ChangePrimary")) {
							herram.escribirLog(rutaArchivoNOK, nameApi, pId, dn, herram.getOrderId(), "",
									"Reintentos en operación", 2,
									"Se alcanzó el número de intentos para esperar que termine la operación de QueryOrder"
											+ herram.getOrderId());
							// System.exit(0);
						}
						reintentos = 0;
						herram.setCodeErrorHttp(0);
						herram.limpiarApis();
					}
				}
			}
			System.out.println("Termina prueba de E2E");
			herram.escribirLogTrazas(rutaArchivoTraza, "Termina prueba de E2E");
			iConf.close();
			workbook.close();
			FileOutputStream fOConf = new FileOutputStream(fConf);
			nWorkbook.write(fOConf);
			nWorkbook.close();
			fOConf.close();
			herram.setBError(false);
		} catch (Exception e) {
			System.out.println("Se encontró un error");
			herram.escribirLogTrazas(rutaArchivoTraza, "Se encontró un error");
			herram.escribirLogTrazas(rutaArchivoTraza, e.getMessage());
			e.printStackTrace();
		}
	}
}