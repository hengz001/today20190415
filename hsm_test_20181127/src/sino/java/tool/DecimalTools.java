package sino.java.tool;

import java.text.DecimalFormat;

public class DecimalTools {

	public String twoDecimal(double f) 
	{
		DecimalFormat df = new DecimalFormat("#0.00");
		return df.format(f);
	}
	
}
