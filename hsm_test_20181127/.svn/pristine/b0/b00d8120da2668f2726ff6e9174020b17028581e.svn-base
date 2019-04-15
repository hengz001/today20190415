package sino.java.tool;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

import sino.java.run.Running;

public class BeforeTools {

	public void beforeAction() {
		SockTools stools = new SockTools();
		CmdTools ctools = new CmdTools();
		List<byte[]> list;
		Socket sock;
		byte [] obuf= new byte[8192];
		
		list = ctools.getCmd(Running.BEFORE_PATH);
		sock = stools.getSocket(Running.IP, Running.PORT, Running.TIMEOUT);
		
		for (byte[] b : list) {
			try {
				stools.sendCmd(sock.getInputStream(),sock.getOutputStream(), b, b.length, obuf);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
//		System.exit(0);
	}
}
