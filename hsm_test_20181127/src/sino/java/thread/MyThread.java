package sino.java.thread;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import sino.java.run.Running;
import sino.java.tool.ClockTools;
import sino.java.tool.FileTools;
import sino.java.tool.SockTools;

public class MyThread implements Runnable{
	
	private SockTools sockTools =new SockTools();
	private FileTools ftools = new FileTools();
	private ClockTools ctools = new ClockTools();
	private String pid;
	private Socket sockfd ;
	private List<byte[]> list;
    private byte[] recvbuf= new byte[8192];
    private InputStream in;
    private OutputStream out;

	MyThread(Socket sockfd, List<byte[]> list) {
		this.sockfd = sockfd;
		this.list = list;
		this.pid = Thread.currentThread().getId()+"";
        try {
            this.in = sockfd.getInputStream();
            this.out = sockfd.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public void run() {
		int rc ;

		while(true) {

			if(MyThreadParameter.TOTAL >0){
                if((rc=action(recvbuf)) < 0) {
                    count(rc,recvbuf);
                }else {
                    count(rc,recvbuf);
                }
            }else{
                if(Running.LOOP){
                    MyThreadParameter.TOTAL = 10000;
                    MyThreadParameter.COUNT = 0;
                    MyThreadParameter.SUCCESS = 0;
                }else{
                    sockTools.closeSocket(sockfd);
                    return;
                }
            }
		}
	}
	
	private  void  count(int flag,byte[] recvbuf) {
		int show_info;
		String msg;
		
		synchronized (Running.class) {
			//处理完毕
			if(MyThreadParameter.TOTAL<=0) {
				return;
			}
			
			//添加计数   flag<0  出错
			if(flag<0) {
				MyThreadParameter.TOTAL--;
				MyThreadParameter.COUNT++;
				MyThreadParameter.ERROR++;
				
				//log
				switch(flag) {
					case -201:
					case -202:
						System.out.println("socket read write error!");
						break;
					default:
						msg = "["+pid+"] "+
								"["+ctools.getDateTime()+"] "+
								"["+Running.IP+"] "+
								"["+new String(recvbuf,2,4)+"] "+
								'\n';
						ftools.logInfo(Running.LOG_PATH, msg);
						break;
				}
				try {
					Thread.sleep(Running.WAIT_TIME);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				while(sockfd == null) {
					this.sockfd=sockTools.restartSocket(Running.IP, Running.PORT, Running.TIMEOUT);
                    try {
                        this.in = sockfd.getInputStream();
                        this.out = sockfd.getOutputStream();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
				}

				
			}else {
				MyThreadParameter.TOTAL--;
				MyThreadParameter.COUNT++;
				MyThreadParameter.SUCCESS++;
			
				//打印信息时间
				if (!Running.VIEW) {
					show_info = 100000;
					//打印1000000信息
					if(MyThreadParameter.COUNT%show_info==0){
						System.out.println("> PID:"+pid+
								" COUNT:"+MyThreadParameter.COUNT+
								" SUCCESS:"+MyThreadParameter.SUCCESS+
								" ERROR:"+MyThreadParameter.ERROR+
								" IP:"+Running.IP+
								" DATE:"+ctools.getDateTime()
						);
					}
				}
			}
		}
	}
	
	private int action(byte[] recvbuf) {
		int rc ;

		for (byte[] bs : list) {
			if((rc = sockTools.sendCmd(in,out,bs, bs.length, recvbuf)) < 0) {
				return rc;
			}
		}
		return 0;
	}
}
