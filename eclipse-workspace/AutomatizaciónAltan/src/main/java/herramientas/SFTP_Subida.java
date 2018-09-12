package herramientas;

import java.util.Properties;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class SFTP_Subida {

	public void SubirPorSFTP(String pUser, String pPass, String pHost, int pPort, String pOutputStream,
			String pPathFile) throws Exception {
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
			BufferedInputStream os = new BufferedInputStream(new FileInputStream(pPathFile));
			channelSftp.put(os, pOutputStream);
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			if (channelSftp.isConnected()) {
				channelSftp.disconnect();
			}
			if (session.isConnected()) {
				session.disconnect();
			}
		}
	}
}
