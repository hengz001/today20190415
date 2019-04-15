package sino.gmn.service;

import java.net.Socket;
import java.util.List;
import java.util.Map;

public interface SocketService {

    Socket hsmConnect(String ip ,int port, int timeout);

    void hsmClose(Socket socket);

    int cmdSend(Socket socket, byte[] cmd);

    int cmdRecv(Socket socket,byte[] recvBuf);

    byte[] hexToByte(String hex);

    String byteToHex(byte[] buf,int len);

    int findCharCount(String str,char c);

    byte[] getCmdByte(String cmd);

    int parseString(String cmd, List<byte[]> list) ;

    byte[] combinationCmd(List<byte[]> list);

    Map<String,Object> hsmAtion(String ip, int port, int timeout, String cmd);
}
