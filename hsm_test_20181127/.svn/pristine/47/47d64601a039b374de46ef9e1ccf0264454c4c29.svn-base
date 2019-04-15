package sino.java.tool;


public class ConversionTools {

	static String byteToHex(byte []bs, int len) {
		byte c;
		StringBuffer sb;
		
		sb = new StringBuffer();
		for(int i=0; i<len; i++) {
			c = (byte) ((bs[i]&0xf0)>>4);
			sb.append((char)((c>9)?(c - 10 + 'A'):(c + '0')));
			c = (byte) (bs[i]&0x0f);
			sb.append((char)((c>9)?(c - 10 + 'A'):(c + '0')));
		}
		return sb.toString();
	}
	
	static byte[] hexToByte(String hex, int len) {
		byte b;
		int flag = 1;

		byte [] bs = new byte[len/2];
		hex = hex.toUpperCase();
		char [] cs = hex.toCharArray();
		for(int i=0; i<len; i++) {
			b = (byte) ((cs[i]>'9')?(cs[i]-'A'+10):(cs[i]-'0'));
			if(flag==1) {
				bs[i/2] = (byte) ((b&0x0f)<<4);
			}
			else {
				bs[i/2] = (byte) ((bs[i/2]&0xf0) | (b&0x0f));
			}
			flag ^= 1;	
		}
		
		return bs;
	}
}
