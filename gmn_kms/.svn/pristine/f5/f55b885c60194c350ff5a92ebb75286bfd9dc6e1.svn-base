package sino.gmn.service.impl;

import org.springframework.stereotype.Service;
import sino.gmn.service.SocketService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("socketService")
public class SocketServiceImpl implements SocketService {

    public Socket hsmConnect(String ip , int port, int timeout){
        Socket socket = new Socket();
        InetSocketAddress address = new InetSocketAddress(ip,port);
        try {
            socket.connect(address,timeout);
        } catch (IOException e) {
            //e.printStackTrace();
            return null;
        }
        return socket;
    }

    public void hsmClose(Socket socket){
        if(null != socket){
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int cmdSend(Socket socket, byte[] cmd) {
        try {
            OutputStream out = socket.getOutputStream();
            out.write(cmd,0,cmd.length);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    public int cmdRecv(Socket socket, byte[] recvBuf) {
        int rc;
        try {
            InputStream in = socket.getInputStream();
            rc = in.read(recvBuf,0,recvBuf.length);
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
        return rc;
    }

   /*
    map rc=recv code cmd=recv cmd cmd_hex= recv cmd hex
     recv code -1.connect error -2.cmd error  -3.send error  -4.recv error
    */
    public Map<String,Object> hsmAtion(String ip, int port, int timeout, String cmd){
        int rc;
        Socket fd = null;
        String recvStr;
        byte[] bsCmd;
        byte[]  recvCmd = new byte[8192];
        Map<String,Object> map = new HashMap<>();

        map.put("rc",0);
        map.put("cmd","");
        map.put("cmd_hex","");

        try {
            fd=hsmConnect(ip,port,timeout);
            if(null == fd){
                map.put("rc",-1);
                return map;
            }

            bsCmd = getCmdByte(cmd);
            if(null == bsCmd){
                map.put("rc",-2);
                return map;
            }
            rc = cmdSend(fd,bsCmd);
            if(rc<0){
                map.put("rc",-3);
                return map;
            }
            rc = cmdRecv(fd,recvCmd);
            if(rc<2){
                map.put("rc",-4);
                return map;
            }
        }finally {
            hsmClose(fd);
        }

        recvStr = new String(recvCmd,2,rc-2);
        map.put("cmd",recvStr);
        recvStr = byteToHex(recvCmd,rc);
        map.put("cmd_hex",recvStr);
        return map;
    }

    public byte[] hexToByte(String hex) {

        int len = hex.length();
        hex = hex.toUpperCase();
        byte[] buf = new byte[len/2];
        int num;

        if(len%2 != 0){
           return null;
        }
        for(int i=0;i<len;i++){
            num = hex.charAt(i)>'9' ? hex.charAt(i)-'A'+10 : hex.charAt(i)-'0';
            if(i%2==0){
                buf[i/2] = (byte) (buf[i/2]&0xf0 | num); //清零低四位
            }else{
                buf[i/2] = (byte) (((buf[i/2]&0x0f)<<4) | num); //清零高四位 后 低位转移高位
            }
        }
        return buf;
    }

    public String byteToHex(byte[] buf,int len) {
        char h,l;
        StringBuffer sb;

        sb = new StringBuffer();
        for(int i=0; i<len; i++){
            h = (char) ((buf[i]&0xf0)>>4);
            l = (char) (buf[i]&0x0f);
            h = (char) (h>9? h-10+'A' : h+'0');
            l = (char) (l>9? l-10+'A' : l+'0');
            sb.append(h);
            sb.append(l);
        }
        return sb.toString();
    }

    public int findCharCount(String str,char c) {
        int count = 0;

        for(int i=0; i<str.length();i++){
            if(str.charAt(i) == c){
                count++;
            }
        }
        return count;
    }

    public byte[] getCmdByte(String cmd){
        int start, end;
        List<byte[]> list = new ArrayList<>();

        cmd = cmd.replace("\n","");
        cmd = cmd.replace(" ","");

        start = findCharCount(cmd, '<');
        end = findCharCount(cmd, '>');
        if (start != end) {
            return null;
        }

        if(parseString(cmd,list)<0){
            return null;
        }

        return combinationCmd(list);
    }

    public int parseString(String cmd,List<byte[]> list) {
        int start, end;
        String a,b,c;

        start = cmd.indexOf("<");
        end = cmd.indexOf(">");
        if(start==-1 || end==-1){
            list.add(cmd.getBytes());
            return 0;
        }else if(start>end){
            return -1;
        }

        a = cmd.substring(0,start);
        b = cmd.substring(start+1,end);
        c = cmd.substring(end+1);
        if(a.length()>0){
            list.add(a.getBytes());
        }
        if(b.length()>0){
            list.add(hexToByte(b));
        }
        if(c.length()>0){
            return parseString(c,list);
        }
        return 0;
    }

    public byte[] combinationCmd(List<byte[]> list){
        int count = 0;
        byte[] buf ;

        for(int i=0; i<list.size();i++){
            count += list.get(i).length;
        }
        buf = new byte[2+count];
        buf[0] = (byte) ((count &0xff00)>> 8);
        buf[1] = (byte) (count & 0xff);

        count = 2;
        for(int i=0; i<list.size();i++){
            byte[] bs = list.get(i);
            for(int j=0;j<bs.length;j++){
                buf[count] = bs[j];
                count++;
            }
        }
        return buf;
    }
}
