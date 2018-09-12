/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package herramientas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;

/**
 *
 * @author BA998464
 */
public class Word {
	public static XWPFDocument documento;
	XWPFTable tableR;

	public XWPFDocument getDocumento() {
		return documento;
	}

	public void CrearDocumentoST() {
		try {
			String archivin = System.getProperty("user.dir") + "\\template\\FormatoEvidencias.docx";
			documento = new XWPFDocument(new FileInputStream(new File(archivin)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void crearTablaThreshold() {
		XWPFTableRow tableRow;
		tableRow = tableR.getRow(0);
		tableRow.getCell(0).setText("Nombre");
		tableRow.addNewTableCell().setText("MatrizOferta");
		tableRow.addNewTableCell().setText("BSS");
		tableRow.addNewTableCell().setText("Resultado");
	}
	
	public void crearTablaRedirect() {
	    XWPFTableRow tableRow;
	    tableRow = tableR.getRow(0);
	    tableRow.getCell(0).setText("IDOFerta");
	    tableRow.addNewTableCell().setText("Razón Redirect");
	    tableRow.addNewTableCell().setText("MatrizOferta");
	    tableRow.addNewTableCell().setText("BSS");
	    tableRow.addNewTableCell().setText("Resultado");
	  }

	public void CrearDocumento(String proyecto, String cV, String ambiente, String ejecutor, String casoP,
			String fEjecucion) {
		try {
			String archivin = System.getProperty("user.dir") + "\\template\\FormatoEvidencias.docx";
			documento = new XWPFDocument(new FileInputStream(new File(archivin)));
			crearTabla(proyecto, cV, ambiente, ejecutor, casoP, fEjecucion);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void crearTabla(String proyecto, String cV, String ambiente, String ejecutor, String casoP,
			String fEjecucion) {
		XWPFTable table = documento.createTable();
		table.setRowBandSize(2);
		setTableAlign(table, ParagraphAlignment.CENTER);
		XWPFTableRow tableRowOne = table.getRow(0);
		tableRowOne.getCell(0).setText("Proyecto: " + proyecto);
		tableRowOne.addNewTableCell().setText("CV: " + cV);
		tableRowOne.addNewTableCell().setText("Ambiente: " + ambiente);
		XWPFTableRow tableRowTwo = table.createRow();
		tableRowTwo.getCell(0).setText("Ejecutor: " + ejecutor);
		tableRowTwo.getCell(1).setText("Caso de prueba: " + casoP);
		tableRowTwo.getCell(2).setText("Fecha de ejecución: " + fEjecucion);
		XWPFParagraph parrafo = documento.createParagraph();
		parrafo.setAlignment(ParagraphAlignment.BOTH);
		XWPFRun r2 = parrafo.createRun();
		r2.addCarriageReturn();
	}
	
	public void crearTablaRC2(String dato1, String dato2, String dato3, String dato4, String resultado) {
	    XWPFTableRow tableRow;
	    tableRow = tableR.createRow();
	    tableRow.getCell(0).setText(dato1);
	    tableRow.getCell(1).setText(dato2);
	    tableRow.getCell(2).setText(dato3);
	    tableRow.getCell(3).setText(dato4);
	    tableRow.getCell(4).setText(resultado);
	  }

	public void crearTablaR() {
		tableR = documento.createTable();
		tableR.setRowBandSize(5);
		setTableAlign(tableR, ParagraphAlignment.CENTER);
	}

	public void crearTablaRBdl() {
		tableR = documento.createTable();
		tableR.setRowBandSize(10);
		setTableAlign(tableR, ParagraphAlignment.CENTER);
	}

	public void crearTablaRT() {
		XWPFTableRow tableRow;
		tableRow = tableR.getRow(0);
		tableRow.getCell(0).setText("Nombre");
		tableRow.addNewTableCell().setText("APIGW");
		tableRow.addNewTableCell().setText("BSS");
		tableRow.addNewTableCell().setText("Resultado");
	}

	public void crearTablaRTBDL() {
		XWPFTableRow tableRow;
		tableRow = tableR.getRow(0);
		tableRow.getCell(0).setText("Nombre");
		tableRow.addNewTableCell().setText("BDL");
		tableRow.addNewTableCell().setText("APIGW");
		tableRow.addNewTableCell().setText("Resultado");
	}

	public void crearTablaRTOMatriz() {
		XWPFTableRow tableRow;
		tableRow = tableR.getRow(0);
		tableRow.getCell(0).setText("Nombre");
		tableRow.addNewTableCell().setText("APIGW/GB");
		tableRow.addNewTableCell().setText("Matriz/GB");
		tableRow.addNewTableCell().setText("Resultado");
	}

	public void crearTablaRTBDLH() {
		XWPFTableRow tableRow;
		tableRow = tableR.getRow(0);
		tableRow.getCell(0).setText("BE_ID");
		tableRow.addNewTableCell().setText("MSISDN");
		tableRow.addNewTableCell().setText("IMSI");
		tableRow.addNewTableCell().setText("OFFERING_ID");
		tableRow.addNewTableCell().setText("NAME");
		tableRow.addNewTableCell().setText("INITIAL_AMT");
		tableRow.addNewTableCell().setText("UNUSED_AMT");
		tableRow.addNewTableCell().setText("CONSUMPTION_DAY");
		tableRow.addNewTableCell().setText("EFFECTIVE_DATE");
		tableRow.addNewTableCell().setText("EXPIRE_DATE");
	}

	public void crearTablaPolicyCounter() {
		XWPFTableRow tableRow;
		tableRow = tableR.getRow(0);
		tableRow.getCell(0).setText("Nombre");
		tableRow.addNewTableCell().setText("MatrizOferta");
		tableRow.addNewTableCell().setText("BSS");
		tableRow.addNewTableCell().setText("Resultado");
	}

	public void crearTablaRC(String nombre, String dato1, String dato2, String resultado) {
		XWPFTableRow tableRow;
		tableRow = tableR.createRow();
		tableRow.getCell(0).setText(nombre);
		tableRow.getCell(1).setText(dato1);
		tableRow.getCell(2).setText(dato2);
		tableRow.getCell(3).setText(resultado);
	}

	public void crearTablaRCH(String BE_ID, String MSISDN, String IMSI, String OFFERING_ID, String NAME,
			String INITIAL_AMT, String UNUSED_AMT, String CONSUMPTION_DAY, String EFFECTIVE_DATE, String EXPIRE_DATE) {
		XWPFTableRow tableRow;
		tableRow = tableR.createRow();
		tableRow.getCell(0).setText(BE_ID);
		tableRow.getCell(1).setText(MSISDN);
		tableRow.getCell(2).setText(IMSI);
		tableRow.getCell(3).setText(OFFERING_ID);
		tableRow.getCell(4).setText(NAME);
		tableRow.getCell(5).setText(INITIAL_AMT);
		tableRow.getCell(6).setText(UNUSED_AMT);
		tableRow.getCell(7).setText(CONSUMPTION_DAY);
		tableRow.getCell(8).setText(EFFECTIVE_DATE);
		tableRow.getCell(9).setText(EXPIRE_DATE);
	}

	public void crearSaltoL() {
		XWPFParagraph parrafo = documento.createParagraph();
		parrafo.setAlignment(ParagraphAlignment.BOTH);
		XWPFRun r2 = parrafo.createRun();
		r2.addCarriageReturn();
	}

	public void setTableAlign(XWPFTable table, ParagraphAlignment align) {
		CTTblPr tblPr = table.getCTTbl().getTblPr();
		CTJc jc = (tblPr.isSetJc() ? tblPr.getJc() : tblPr.addNewJc());
		STJc.Enum en = STJc.Enum.forInt(align.getValue());
		jc.setVal(en);
	}

	public void CrearImagen(WebDriver driver) throws InvalidFormatException, IOException {
		XWPFParagraph parrafo = documento.createParagraph();
		parrafo.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun r1 = parrafo.createRun();
		File imagen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		InputStream pic = new FileInputStream(imagen);
		r1.addPicture(pic, Document.PICTURE_TYPE_JPEG, "1", Units.toEMU(441.90), Units.toEMU(238));
	}

	public void CrearTitulo(String tituloDocumento) {
		XWPFParagraph titulo_doc = documento.createParagraph();
		titulo_doc.setAlignment(ParagraphAlignment.CENTER);
		titulo_doc.setVerticalAlignment(TextAlignment.TOP);
		XWPFRun r1 = titulo_doc.createRun();
		r1.setBold(true);
		r1.setText(tituloDocumento);
		r1.setFontFamily("Arial");
		r1.setFontSize(10);
		r1.setTextPosition(10);
		r1.setUnderline(UnderlinePatterns.SINGLE);
		r1.addCarriageReturn();
	}

	public void CrearParrafo(String contenidoParrafo) {
		XWPFParagraph parrafo = documento.createParagraph();
		parrafo.setAlignment(ParagraphAlignment.BOTH);
		XWPFRun r2 = parrafo.createRun();
		r2.setText(contenidoParrafo);
		r2.setFontSize(10);
		r2.addCarriageReturn();
	}

	public void crearParrafoNegritas(String contenidoParrafo) {
		XWPFParagraph parrafo = documento.createParagraph();
		parrafo.setAlignment(ParagraphAlignment.BOTH);
		XWPFRun r2 = parrafo.createRun();
		r2.setBold(true);
		r2.setText(contenidoParrafo);
		r2.setFontSize(10);
		r2.addCarriageReturn();
	}

	public void CrearParrafoSJ(String contenidoParrafo) {
		XWPFParagraph parrafo = documento.createParagraph();
		XWPFRun r2 = parrafo.createRun();
		r2.setText(contenidoParrafo);
		r2.setFontSize(10);
		r2.addCarriageReturn();
	}

	public void CrearWord(String tituloDocumento, String path) {
		try {
			FileOutputStream word = new FileOutputStream(new File(path + "\\" + tituloDocumento + ".docx"));
			documento.write(word);
			word.close();
		} catch (IOException ex) {
			Logger.getLogger(Word.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
