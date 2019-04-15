package sino.java.run;

import java.util.List;
import java.util.Map;

import sino.java.thread.MyThreadParameter;
import sino.java.thread.MyThreadStart;
import sino.java.tool.BeforeTools;
import sino.java.tool.ClockTools;
import sino.java.tool.DecimalTools;
import sino.java.tool.FileTools;

public class Running {

	public static String IP;
	public static  String DATA_PATH;
	public static  String BEFORE_PATH;
	public static  String LOG_PATH;
	public static int PORT;
	public static int TIMEOUT;
	private static int THREAD_NUM;
	private static int TOTAL;
	public static int WAIT_TIME;
	public static  boolean LOOP = false;
	public static  boolean VIEW = false;

	private void runInit(){

		Map<String, String> map = new FileTools().readConfigFile();
		String sIP = map.get("IP");
		String sDATA_PATH = map.get("DATA_PATH");
		String sBEFORE_PATH = map.get("BEFORE_PATH");
		String sLOG_PATH = map.get("LOG_PATH");
		String sPORT = map.get("PORT");
		String sTIMEOUT = map.get("TIMEOUT");
		String sTHREAD_NUM = map.get("THREAD_NUM");
		String sTOTAL = map.get("TOTAL");
		String sLOOP = map.get("LOOP");
		String sVIEW = map.get("VIEW");
		String sWAIT_TIME = map.get("WAIT_TIME");

		IP = sIP;
		DATA_PATH = sDATA_PATH;
		BEFORE_PATH = sBEFORE_PATH;
		LOG_PATH = sLOG_PATH;
		PORT = Integer.parseInt(sPORT);
		TIMEOUT = Integer.parseInt(sTIMEOUT);
		THREAD_NUM = Integer.parseInt(sTHREAD_NUM);
		WAIT_TIME = Integer.parseInt(sWAIT_TIME);
		TOTAL = Integer.parseInt(sTOTAL);
		if("true".equals(sLOOP)){
			LOOP = true;
		}
		if("true".equals(sVIEW)){
			VIEW = true;
		}

		new BeforeTools().beforeAction();
	}

	public static void main(String[] args) throws InterruptedException {
		
		Running run = new Running();
		ClockTools clockTools = new ClockTools();
		MyThreadStart myThread = new MyThreadStart();
		DecimalTools decimalTools = new DecimalTools();
		long start , end;


		run.runInit();
		MyThreadParameter.TOTAL = TOTAL;
		List<Thread> list = myThread.getMyThreadList(THREAD_NUM, IP, PORT, TIMEOUT);
		if(null==list) {
			System.out.println("connect error!");
			return ;
		}
		System.out.println("--------------------------------> program start ---------------------------");
		System.out.flush();

		//running
		start = clockTools.clock();
		myThread.threadStart(list);
		end = clockTools.clock();
		//end

		long l = MyThreadParameter.SUCCESS;
		double fps = l/clockTools.clockTimeFloat(start,end);
		System.out.println();
		System.out.println("Count:"+MyThreadParameter.COUNT);
		System.out.println("Success:"+MyThreadParameter.SUCCESS);
		System.out.println("Error:"+MyThreadParameter.ERROR);
		System.out.println("use time:"+clockTools.showClock(start, end)+"s");
		System.out.println("use fps/s:"+decimalTools.twoDecimal(fps));
	}

}
