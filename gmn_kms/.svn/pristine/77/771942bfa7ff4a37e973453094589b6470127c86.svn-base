package sino.gmn.service.impl;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sino.gmn.model.Cmd;
import sino.gmn.service.CmdService;
import sino.gmn.service.FileService;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service("fileService")
public class FileServiceImpl implements FileService {

    @Autowired
    private CmdService cmdService;

    public int importFile(MultipartFile file) {

        Workbook wk = null;
        String info;
        Cmd cmd = new Cmd();
        int num;
        try {
            wk = Workbook.getWorkbook(file.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }

        Sheet sheet = wk.getSheet(0);
        int row = sheet.getRows();
        int col = sheet.getColumns();
        for(int i=0;i<row;i++) {
            num = 0;
            info = sheet.getCell(num++, i).getContents();
            cmd.setcId(Integer.parseInt(info));
            info = sheet.getCell(num++, i).getContents();
            cmd.setcCmd(info);
            info = sheet.getCell(num++, i).getContents();
            cmd.setcRequest(info);
            info = sheet.getCell(num++, i).getContents();
            cmd.setcResponse(info);
            info = sheet.getCell(num++, i).getContents();
            cmd.setcDescrible(info);
            info = sheet.getCell(num++, i).getContents();
            cmd.setcFlag(Integer.parseInt(info));
            cmdService.insertCmd(cmd);
        }
        wk.close();
        return 0;
    }

    public int exportFile(HttpServletResponse response) throws IOException, WriteException {
        int col ;
        String filename = "backup.xls";

        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader("Content-Disposition","attachment;filename="+filename);
        List<Cmd> list = cmdService.findAllCmd();
        WritableWorkbook wk = Workbook.createWorkbook(response.getOutputStream());
        WritableSheet sheet = wk.createSheet("data",0);
        for(int i=0; i<list.size();i++){
            col = 0;
            sheet.addCell(new Label(col++,i,list.get(i).getcId()+""));
            sheet.addCell(new Label(col++,i,list.get(i).getcCmd()));
            sheet.addCell(new Label(col++,i,list.get(i).getcRequest()));
            sheet.addCell(new Label(col++,i,list.get(i).getcResponse()));
            sheet.addCell(new Label(col++,i,list.get(i).getcDescrible()));
            sheet.addCell(new Label(col++,i,list.get(i).getcFlag()+""));
        }
        wk.write();
        wk.close();
        return 0;
    }

    public int importFileBatch(MultipartFile[] files) throws IOException {

        for (MultipartFile file:files) {
            importFileBatchAction(file.getInputStream());
        }
        return 0;
    }

    public void importFileBatchAction(InputStream in ) throws IOException {
        char cbuf[] = new char[2048];
        int len;
        String msg = "";
        StringBuffer sb = new StringBuffer();

        InputStreamReader read = new InputStreamReader(in);
        while((len = read.read(cbuf,0,cbuf.length)) > 0){
            msg = new String(cbuf,0,len);
            msg = msg.replace(" ","");
            msg = msg.replace("\n","");
            msg = msg.replace("\t","");
            msg = msg.replace("\r","");
            sb.append(msg);
        }
        read.close();
        in.close();

        //execute
        msg = sb.toString();
        Map<String,String> map = new HashMap<>();
        fileSubString(msg, map);
        Set<String> set =  map.keySet();
//        System.out.println(set.size());
        for (String key: set) {
//            System.out.println("key: "+key.substring(0,2));
//            System.out.println(key);
//            System.out.println(map.get(key));
              Cmd cmd = new Cmd();
              cmd.setcFlag(1);
              cmd.setcCmd(key.substring(0,2));
              cmd.setcRequest(key);
              cmd.setcResponse(map.get(key));
              cmd.setcDescrible("non");
              cmdService.insert(cmd);
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
