package sino.java.tool;

import java.util.ArrayList;
import java.util.List;

public class CmdTools {

	 private int getListLen(List<byte[]> list) {
		int len = 0;
		
		for (byte[] bs : list) {
			len += bs.length;
		}
		return len;
	}
	
	private  String deleteSpace(String cmd) {
		cmd = cmd.trim();
		cmd = cmd.replace(" ","");
		cmd = cmd.replace("\n","");
		cmd = cmd.replace("\r","");
		cmd = cmd.replace("\t","");
		return cmd;
	}
	
	private  byte[] addCmdLen(byte []cmd, int len) {
		byte [] bs = new byte[2+len];
		bs[0] = (byte) ((len & 0xff00)>>8);
		bs[1] = (byte) (len & 0x00ff);
		
		for(int i=0; i<len; i++) {
			bs[i+2] = cmd[i];
		}

		return bs;
	}
	
	private  int stringToBytes(String cmd,List<byte[]> list) {
		int start,end;
		String s1,s2,s3;
		
		start = cmd.indexOf('<');
		end = cmd.indexOf('>');

		if(start==-1 || end == -1) {
			if(cmd.length()>0) {
				list.add(cmd.getBytes());
			}
			
			return getListLen(list);
		}
		else if(start > end){
			return -1;
		}
		
		s1 = cmd.substring(0, start);
		s2 = cmd.substring(start+1,end);
		s3 = cmd.substring(end+1);
		
		if(s1.length()>0) {
			list.add(s1.getBytes());
		}
		
		if(s2.length()%2==0) {
			list.add(ConversionTools.hexToByte(s2, s2.length()));
		}else {
			return -2;
		}
		
		return stringToBytes(s3,list);
	}
	
	private  byte[] combinationCmd(List<byte[]> list) {
		int len = getListLen(list);
		byte [] bs = new byte[len];
		int count = 0;
		
		for (byte[] lbs : list) {
			for(int i=0; i<lbs.length; i++,count++) {
				bs[count] = lbs[i];
			}
		}
		
		bs = addCmdLen(bs,len);
		return bs;
	}
	
	private  byte[] transformCmd(String cmd) {
		byte[] bs ;
		List<byte[]> list = new ArrayList<>();
	
		//
		cmd = deleteSpace(cmd);
		
		// 
		if((stringToBytes(cmd,list)) < 0) {
			return null;
		}
	
		//list combination bytes
		bs = combinationCmd(list);
		
		return bs;
	}
	
	public List<byte[]> getCmd(String path){
		
		List<byte[]> listByte = new ArrayList<>();
		List<String> list = new FileTools().readTestData(path);
	
		if(null==list) {
			System.out.println("CMD IS NULL!");
			System.exit(0);
		}
		
		for (String cmd : list) {
			listByte.add(new CmdTools().transformCmd(cmd));
		}
		
		return listByte;
	}
}
