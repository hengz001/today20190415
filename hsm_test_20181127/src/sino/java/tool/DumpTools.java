package sino.java.tool;

import java.io.PrintStream;

public class DumpTools {

	static void hexDumpBuffer(PrintStream fd, byte[] bs, int len) {
		int LINE_LEN = 16;
		int line;
		
		PrintStream fdOld = System.out;
		if(fd != null) {
			System.setOut(fd);
		}
		
		line = len/LINE_LEN + ((len%LINE_LEN==0)?(0):(1));
	
		System.out.println("LEN:"+len);
		for(int i=0; i<line; i++) {
			hexDumpLine(fd,bs,LINE_LEN,len,i);
		}
		
		System.setOut(fdOld);
		System.out.println("---------------------------------------------------------------");
	}
	
	private static void hexDumpLine(PrintStream fd, byte[] bs,int len, int count,int line) {
		System.out.printf("%06x [",line);
		for(int i=0; i<len; i++) {
			if(line*len+i<count) {
				System.out.printf("%02X ",bs[line*len+i]);
			}else {
				System.out.print("   ");
			}
		}
		System.out.print("][");

		for(int i=0; i<len; i++) {
			if(line*len+i < count) {
				if(bs[line*len+i] <32 ) {
					System.out.print(".");
				}else {
					System.out.printf("%c",bs[line*len+i]);
				}
			}else {
				System.out.print(" ");
			}
		}
		System.out.println("]");
	}
	
}
