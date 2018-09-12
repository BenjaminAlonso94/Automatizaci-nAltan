package herramientas;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SFTP_Descarga {

	static Connection conn = null;
	static Statement stmt = null;
	static ResultSet rset = null;

	public void DescargarPorSFTP(String pUser, String pPass, String pHost, int pPort, String pOutputStream,
			String pPathFile){
		JSch sftp = new JSch();
		Session session = null;
		ChannelSftp channelSftp = null;
		try {
			sftp.addIdentity(System.getProperty("user.dir") + "\\driver\\chromedriver");
			session = sftp.getSession(pUser, pHost, pPort);
			session.setPassword(pPass);
			Properties prop = new Properties();
			prop.put("StrictHostKeyChecking", "no");
			session.setConfig(prop);
			session.connect();
			channelSftp = (ChannelSftp) session.openChannel("sftp");
			channelSftp.connect();
			OutputStream os = new BufferedOutputStream(new FileOutputStream(pOutputStream));
			channelSftp.get(pPathFile, os);
		} catch (Exception e) {
				e.printStackTrace();

		} finally {
			if (channelSftp.isConnected()) {
				channelSftp.disconnect();
			}
			if (session.isConnected()) {
				session.disconnect();
			}
		}
	}
	
	public void DescargarPorSFTPG(String pUser, String pPass, String pHost, int pPort, String pOutputStream,
			String pPathFile){
		JSch sftp = new JSch();
		Session session = null;
		ChannelSftp channelSftp = null;
		try {
			sftp.addIdentity(System.getProperty("user.dir") + "\\driver\\altan-evtesting-edge");
			session = sftp.getSession(pUser, pHost, pPort);
			session.setPassword(pPass);
			Properties prop = new Properties();
			prop.put("StrictHostKeyChecking", "no");
			session.setConfig(prop);
			session.connect();
			channelSftp = (ChannelSftp) session.openChannel("sftp");
			channelSftp.connect();
			OutputStream os = new BufferedOutputStream(new FileOutputStream(pOutputStream));
			channelSftp.get(pPathFile, os);
		} catch (Exception e) {
				e.printStackTrace();

		} finally {
			if (channelSftp.isConnected()) {
				channelSftp.disconnect();
			}
			if (session.isConnected()) {
				session.disconnect();
			}
		}
	}

	public static void main(String[] args) throws SQLException {
		try {
			//DescargarPorSFTP("centos", "centos", "52.5.233.163", 22,
				//	"C:\\Users\\balonsmo\\Desktop\\resultado-cambio_oferta-2018-07-08-.log",
					//"/opt/blackbird/log/eventos/resultado-cambio_oferta-2018-07-08-.log");
			//System.out.println("descarga satisfactoria...");
			FileReader fr = new FileReader("C:\\Users\\balonsmo\\eclipse-workspace\\Altan\\Evidencias\\MainGeneral\\Evidencia_16072018_120734\\Testing_20180716.csv");
			BufferedReader br = new BufferedReader(fr);
			String linea = "";
			List<List<String>> lista = new ArrayList<List<String>>();
			for (int i = 0; i <= 14; i++) {
				lista.add(new ArrayList<String>());
			}
			while ((linea = br.readLine()) != null) {
			    String[] separado = linea.replace("|", "!!&%").split("!!&%");
			    for (int i = 0; i < separado.length; i++) {
			        //System.out.print(separado[i]);
			        lista.get(i).add(separado[i]);
			    }
			    //System.out.println("");
			    //System.out.println("\179");
			}
			br.close();
			for (int i = 0; i <= lista.get(0).size() - 1; i++) {
				System.out.println("DN: " + lista.get(0).get(i) + " BE: "
						+ lista.get(1).get(i) + " ID Oferta: " + lista.get(2).get(i) + " Nombre: "
						+ lista.get(3).get(i) + " InitialAmount: " + lista.get(4).get(i) + " Consumo: " + lista.get(5).get(i)+" unusedAmt: "+ lista.get(6).get(i)
						+" effectiveDate: "+ lista.get(7).get(i)+" expireDate: "+ lista.get(8).get(i)+" Velocidad de subida: "+ lista.get(9).get(i)
						+" Velocidad bajada: "+ lista.get(10).get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void ConexionDB(String fecha) throws SQLException {
		//DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@172.16.60.94:1521/mitafore", "CIERREN_CE_APP",
				"aPp16#53");
		stmt = conn.createStatement();
		System.out.println("Conexion extablecida");
		String query = "INSERT INTO CIERREN_CE.TCAFOGRAL_VALOR_ACCION (FCN_ID_VALOR_ACCION, FCD_FEH_ACCION, FCN_VALOR_ACCION,    FCN_ID_SIEFORE, FCN_ID_REGIMEN, FCD_FEH_CRE,    FCC_USU_CRE, FCD_FEH_ACT, FCC_USU_ACT) VALUES (?,?,?,?,?,?,?,?,?)";
		String query2 = "delete from CIERREN_CE.TCAFOGRAL_VALOR_ACCION where FCN_ID_SIEFORE in(82,78,75,83,78,76,77,76,74,81,81,75,74,77) and FCD_FEH_ACCION like '%"
				+ fecha + "%'";
		PreparedStatement pstmt2 = conn.prepareStatement(query2);
		pstmt2.executeQuery();
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setNull(1, java.sql.Types.NUMERIC);
		pstmt.setString(2, fecha + " 11:48:27,767211000");
		pstmt.setDouble(3, 2.791828);
		pstmt.setInt(4, 82);
		pstmt.setInt(5, 140);
		pstmt.setString(6, fecha + " 11:48:27,767211000");
		pstmt.setString(7, "SIS");
		pstmt.setNull(8, java.sql.Types.TIMESTAMP);
		pstmt.setNull(9, java.sql.Types.VARCHAR);
		pstmt.executeQuery();
		pstmt.executeQuery();
		pstmt.close();
		stmt.close();
		System.out.println("Conexion cerrada");
	}
}
