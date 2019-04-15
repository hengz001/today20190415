package sino.java.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FileTools {
	List<String> readTestData(String pathname){
		FileInputStream fin;
		int len ;
		byte[] buf;
		File file;
		StringBuffer sb;
		String cmds;
		int p;
		int q;
		List<String> list;
		
		list = new ArrayList<>();
		buf = new byte[4096];
		sb = new StringBuffer();
		file = new File(pathname);
		if(!file.exists()) {
			System.out.println("file don't exists!");
			return null;
		}
		
		
		try {
			fin = new FileInputStream(file);
			while((len = fin.read(buf, 0, buf.length))>0) {
				sb.append(new String(buf,0,len));
			}
			fin.close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	
		cmds = sb.toString();
		cmds = cmds.trim();
		cmds = cmds.replace(" ", "");
		cmds = cmds.replace("\r", "");
		cmds = cmds.replace("\n", "");
	
		while((p = cmds.indexOf('['))!=-1 && (q = cmds.indexOf('|'))!=-1){
			list.add(cmds.substring(p+1,q));
			cmds = cmds.substring(q+1);
		}
		
		return list;
	}
	
	private void  getConfigData(String str,Map<String,String> map) {
		int i;
		String key;
		String value;
		
		str = str.trim();
		str = str.replace(" ", "");

		i = str.indexOf("=");
		if(i<0) {
			return;
		}

		//注释
		if(str.length()>0 && str.charAt(0)=='#'){
			return;
		}

		key = str.substring(0, i);
		value = str.substring(i+1);
		map.put(key, value);
	}
	
	//get ip port config
	public Map<String,String> readConfigFile() {
		String path = "./config.txt";
		File file ;
		BufferedReader br;
		String str;
		Map<String,String> map = new HashMap<>();
		
		System.out.println("> satrt.");
		file = new File(path);
		if(!file.exists()) {
			System.out.println("file not exists: "+path);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.exit(0);
		}
		
		try {
			br = new BufferedReader(new FileReader(file));
			while((str=br.readLine()) != null) {
				getConfigData(str,map);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		System.out.println("> read config success.");
		
		Set<String> set = map.keySet();
		for (String key:set) {
			System.out.println(">"+key+"="+map.get(key));
		}
		return map;
	}
	
	public void logInfo(String pathname,String msg) {
		File file ;
		FileWriter writer ;
		
		try {
			file = new File(pathname);
			if(!file.exists())
				file.createNewFile();
			
			//限制文件大小
			clearFile(file);
			
			writer = new FileWriter(file, true);
			writer.write(msg);
			writer.close();
			
		} catch (IOException e) {
			System.out.println("permission denied!");
		}
	}
	
	private void clearFile(File file) throws IOException {
		//500M
		long size = 1000 * 1000 *500;
		if(file.length() > size) {
			file.delete();
			file.createNewFile();
		}
	}
}
