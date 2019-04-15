package sino.java.thread;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import sino.java.run.Running;
import sino.java.tool.CmdTools;
import sino.java.tool.SockTools;

public class MyThreadStart {

	public List<Thread> getMyThreadList(int thread_num,String hostname,int port,int timeout) {
		SockTools sockTools = new SockTools();
		List<byte[]> list;
		List<Thread> listThread = new ArrayList<>();
		//
		list = new CmdTools().getCmd(Running.DATA_PATH);

		for(int i=0; i<thread_num; i++) {
			//socket
			Socket sockfd = sockTools.getSocket(hostname, port, timeout);
			if(null == (sockfd)) {
				return null;
			}
			//thread
			Thread thread = new Thread(new MyThread(sockfd,list));
			listThread.add(thread);
		}
		
		return listThread;
	}

	public void threadStart(List<Thread> list) throws InterruptedException {
		
		for (Thread thread : list) {
			thread.start();
		}
		
		for (Thread thread : list) {
			thread.join();
		}
	}
}
