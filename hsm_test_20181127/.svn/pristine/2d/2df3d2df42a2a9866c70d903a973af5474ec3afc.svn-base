package sino.java.tool;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import sino.java.run.Running;

public class SockTools {
	public  void closeSocket(Socket sockfd) {
		InputStream in ;
		OutputStream out ;
		
		try {
			if(null != sockfd) {
				in = sockfd.getInputStream();
				out = sockfd.getOutputStream();
				
				if(null != in) {
					in.close();
				}
				
				if(null != out) {
					out.close();
				}
				sockfd.close();
			}

		} catch (IOException e) {
			System.out.println("close socket error!");
		}
	}
	
	public Socket getSocket(String hostname, int port, int timeout) {
		Socket sockfd;
		InetSocketAddress sockAddr;
		
		sockfd = new Socket();
		sockAddr = new InetSocketAddress(hostname, port);
		try {
			sockfd.connect(sockAddr, timeout);
		} catch (IOException e) {
			System.out.println("open socket error!");
			return null;
		}
		
		return sockfd;
	}
	
	public Socket restartSocket(String hostname, int port, int timeout) {
		return getSocket(hostname, port, timeout);
	}
	
	
	public int sendCmd(	InputStream in ,OutputStream out , byte[] ibuf,int ilen, byte[] obuf) {
		int rc ;

		try {
			out.write(ibuf, 0, ilen);
			if((rc = in.read(obuf, 0, obuf.length)) <= 0) {
				return -201;
			}
			
			if(Running.VIEW) {
				System.out.println(new String(ibuf, 2, ilen-2));
				System.out.println(new String(obuf, 2, rc-2));
				System.out.flush();
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
			return -202;
		}
		
		if(obuf[4]=='0' && obuf[5]=='0') {
			return rc;
		}else {
			return -1*((obuf[4]-'0')*10 + (obuf[5]-'0'));
		}
	}
}
