//package sino.gmn.test;
//
//import jxl.Sheet;
//import jxl.Workbook;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import sino.gmn.model.Cmd;
//import sino.gmn.service.CmdService;
//import sino.gmn.service.SocketService;
//
//import java.io.File;
//import java.net.Socket;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({"classpath:spring-mybatis.xml"})
//public class CmdTest {
////    @Autowired
////    private CmdMapper cmdDao;
//
//    @Autowired
//    private CmdService cmdService;
//
//    @Autowired
//    private SocketService socketService;
//
//    @Test
//    public void cmdTest(){
//        int id = 1;
////        Cmd cmd = cmdDao.selectByPrimaryKey(id);
//        Cmd cmd = cmdService.selectByPrimaryKey(id);
//    }
//
//    //insert data
//    @Test
//    public void cmdDataInput(){
//        java.lang.String path = "D:\\obj\\gmn_kms\\src\\main\\resources\\sql\\sjl22_cmd.xls";
//        System.out.println("Start...");
//
//        List<List<String>> datas = new ArrayList<List<String>>();
//
//        try {
//            Workbook wb = Workbook.getWorkbook(new File(path));
//            int sheet_size = wb.getNumberOfSheets();
//            System.out.println("sheet_size: "+sheet_size);
//            if(sheet_size==0){
//                return ;
//            }
//            Sheet sheet = wb.getSheet(0);
//            for(int i=0; i<sheet.getRows(); i++){
//                List<String> data = new ArrayList<String>();
//                for(int j=0; j<sheet.getColumns(); j++){
//                    String cell = sheet.getCell(j,i).getContents();
//
//                    if(i!=0){
//                        if(j==2 || j==3 || j==8){
//                            System.out.print(cell+" ");
//                            data.add(cell);
//                        }
//                    }
//                    if(cell.isEmpty()){
//                        continue;
//                    }
//                }
//                System.out.println();
//                datas.add(data);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("End.");
//
//        for(int i=0; i<datas.size(); i++) {
//            List<String> data = datas.get(i);
//            for (int j=0; j<data.size(); j++){
//                System.out.println(j+": "+data.get(j));
//            }
//            System.out.println("-----------------------------"+data.size());
//            if(data.size()==3){
//                Cmd cmd = new Cmd();
//                cmd.setcRequest(data.get(0));
//                cmd.setcResponse(data.get(1));
//                cmd.setcDescrible(data.get(2));
//                cmdService.insertCmd(cmd);
//            }
//        }
//    }
//
//    @Test
//    public void sendCmdTest(){
//        java.lang.String cmd = "303132333435363738490123456789ABCDEF";
//        byte[] buf = socketService.hexToByte(cmd);
//        System.out.println(buf==null?"NULL":"len:"+buf.length);
//        for(int i=0; i<buf.length;i++){
//            System.out.printf("%02X",buf[i]);
//        }
//        System.out.println();
//        String recv = socketService.byteToHex(buf,buf.length);
//        System.out.println(recv.equals(cmd)+" "+recv);
//    }
//
//    @Test
//    public void cmdStringTest(){
//        int start, end;
//        String cmd = " <4E> \n <43> ";
//        cmd = cmd.replace("\n","");
//        cmd = cmd.replace(" ","");
//        start = socketService.findCharCount(cmd, '<');
//        System.out.println("COUNT:" + start);
//        end = socketService.findCharCount(cmd, '>');
//        System.out.println("COUNT:" + end);
//        if (start != end) {
//            return ;
//        }
//        List<byte[]> list = new ArrayList<>();
//
//       int rc =  socketService.parseString(cmd,list);
//        System.out.println("RC:"+rc);
//        System.out.println("LIST SIZE:"+list.size());
//        for(int i=0;i<list.size();i++){
//            System.out.println(new String(list.get(i)));
//        }
//        byte[] bsRc = socketService.combinationCmd(list);
//        System.out.println(socketService.byteToHex(bsRc,bsRc.length));
//
//        Socket fd =socketService.hsmConnect("192.168.1.149",8000,3000);
//        if(fd == null){
//            System.out.println("socket connect fail!");
//            return;
//        }
//        rc = socketService.cmdSend(fd,bsRc);
//        if(rc<0){
//            System.out.println("socket send fail!");
//            return;
//        }
//        System.out.println("socket send success. "+rc);
//
//        byte[] recvBuf = new byte[8192];
//        rc = socketService.cmdRecv(fd,recvBuf);
//        if(rc<0){
//            System.out.println("socket recv fail!");
//            return;
//        }
//        System.out.println("socket recv success. "+rc+" "+new String(recvBuf,2,rc-2));
//    }
//
//    @Test
//    public void mapText(){
//        Map<String,Object> map = new HashMap<>();
//        map.put("rc",0);
//        map.put("rc",1);
//        map.put("rc",2);
//        map.put("rc",3);
//        System.out.println(map.get("rc"));
//
//        map = socketService.hsmAtion("192.168.1.149",8000,3000,"<4E><43>00");
//        System.out.println(map.get("rc"));
//        System.out.println(map.get("cmd"));
//        System.out.println(map.get("cmd_hex"));
//    }
//}
