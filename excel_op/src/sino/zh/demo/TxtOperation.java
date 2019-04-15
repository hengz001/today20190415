package sino.zh.demo;

import org.junit.Test;


import java.io.*;
import java.util.*;

public class TxtOperation {

    @Test
    public void myMain() throws IOException {
        System.out.println("txt operation.");
        myFile();
    }

    public void myFile() throws IOException {
        String filePath = "D:\\zhuheng\\zh20171027\\temp\\folder\\SJL22测试数据\\SJL22测试数据\\racal_O6测试数据\\TEST_DATA\\ALL_128_INDEX_RACAL_06E.txt";
        char cbuf[] = new char[2048];
        int len;
        String msg = "";
        StringBuffer sb = new StringBuffer();

        File file = new File(filePath);
        InputStream in = new FileInputStream(file);
        InputStreamReader read = new InputStreamReader(in);
        while((len = read.read(cbuf,0,cbuf.length)) > 0){
            msg = new String(cbuf,0,len);
            msg = msg.replace(" ","");
            msg = msg.replace("\n","");
            msg = msg.replace("\t","");
            msg = msg.replace("\r","");
//            System.out.println(msg);
            sb.append(msg);
        }
        read.close();
        in.close();

        //execute
        msg = sb.toString();
        Map<String,String> map = new HashMap<String, String>();
        fileSubString(msg, map);
        Set<String> set =  map.keySet();
        System.out.println(set.size());
        for (String key: set) {
            System.out.println("key: "+key.substring(0,2));
            System.out.println(key);
            System.out.println(map.get(key));

        }
    }

    public void fileSubString(String msg,Map<String,String> map){
        int start,end;
        String data;

        start = msg.indexOf("[");
        end = msg.indexOf("]");
        if(start<0 || end<0 || end<start){
            return;
        }
        data = msg.substring(start+1,end);
        start = data.indexOf("|");
        if(start < 0){
            return;
        }
//        System.out.println("---------->start;  "+data.substring(0,start));
//        System.out.println("---------->end: "+data.substring(start+1));
        map.put(data.substring(0,start),data.substring(start+1));
        fileSubString(msg.substring(end+1),map);
    }
}
