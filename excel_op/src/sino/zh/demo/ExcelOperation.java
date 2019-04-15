package sino.zh.demo;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class ExcelOperation {

    @Test
    public void writeTest() throws IOException, WriteException, BiffException {
        System.out.println("writeTest.");
        String path = "c://backup.xls";
        String sheetName = "backup_data";

        WritableWorkbook wk = Workbook.createWorkbook(new File(path));
        WritableSheet sheet = wk.createSheet(sheetName,0);
        int row = 10, col = 3;
        for(int i= 0; i<row; i++){
            for(int j=0; j<col;j++){
                sheet.addCell(new Label(j,i,"data"+i+" "+j));
            }
        }
        wk.write();
        wk.close();
        System.out.println("Write END");

        Workbook wk2 = Workbook.getWorkbook(new File(path));
        Sheet sheet2 = wk2.getSheet(0);
        int row2=sheet2.getRows();
        int col2=sheet2.getColumns();
        System.out.println("row:"+row2);
        System.out.println("col:"+col2);

        for(int i=0; i<row2; i++){
            for(int j=0; j<col2; j++){
                System.out.print(sheet2.getCell(j,i).getContents()+" ");
            }
            System.out.println();
        }
        System.out.println("Read END");
    }
}
