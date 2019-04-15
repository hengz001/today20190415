package sino.gmn.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sino.gmn.model.Cmd;
import sino.gmn.model.Page;
import sino.gmn.service.CmdService;
import sino.gmn.service.FileService;
import sino.gmn.service.SocketService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.Socket;
import java.util.Map;

@Controller
@RequestMapping("/cmd")
public class CmdController {

    @Autowired
    private CmdService cmdService;

    @Autowired
    private SocketService socketService;

    @Autowired
    private FileService fileService;

    @RequestMapping("request")
    public String cmdRequest(){
        return "cmd/cmdRequest";
    }

    @RequestMapping("add")
    public String addPage(){
        return "cmd/cmdAdd";
    }

    @RequestMapping("insert")
    @ResponseBody
    public Object add( Cmd cmd){
       return cmdService.insertCmd(cmd);
    }

    @RequestMapping(value="show")
    public String show(){
        return "cmd/cmdShow";
    }

    @RequestMapping(value="selectPage",produces="text/html;charset=UTF-8")
    @ResponseBody
    public Object selectPage(Page page) throws JsonProcessingException {
        Map<String,Object> map = cmdService.findPage(page);
        return new ObjectMapper().writeValueAsString(map);
    }

    @RequestMapping("/update")
    public String updatePage(HttpServletRequest request ,int cId){
        Cmd cmd = cmdService.selectByPrimaryKey(cId);
        request.setAttribute("cmd",cmd);
        return "cmd/cmdUpdate";
    }

    @RequestMapping("/updateCmd")
    @ResponseBody
    public Object updateCmd(Cmd cmd){
        return cmdService.updateByPrimaryKey(cmd);
    }

    @RequestMapping("/remove")
    @ResponseBody
    public Object remove(int cId){
        return cmdService.removeCmd(cId);
    }

    @RequestMapping("/connect")
    @ResponseBody
    public Object connect(String ip, int port, HttpSession session){
        session.setAttribute("ip",ip);
        session.setAttribute("port",port);

        Socket socket = socketService.hsmConnect(ip,port,3000);
        if(null == socket){
            return -1;
        }else{
            socketService.hsmClose(socket);
        }
        return 0;
    }

    @RequestMapping("/sendCmd")
    @ResponseBody
    public Object send(String cmd, String ip, int port){
        Map<String,Object> map;
        map = socketService.hsmAtion(ip,port,3000,cmd);
        return map;
    }

    @RequestMapping("export")
    public void exportData(HttpServletResponse response) throws Exception {
        fileService.exportFile(response);
    }

    @RequestMapping("import")
    public String importPage(){
        return "cmd/cmdImport";
    }

    @RequestMapping("cmdImport")
    public void cmdImport(@RequestParam("file") MultipartFile file){
        if(null == file){
            return ;
        }
        fileService.importFile(file);
    }

    @RequestMapping("import_batch")
    public String importBatchPage(){
        return "cmd/cmdImportBatch";
    }

    @RequestMapping("cmdImportBatch")
    public void cmdImportBatch(@RequestParam("file") MultipartFile[] files) throws IOException {
//        System.out.println("------> cmdImportBatch");
//        System.out.println("------> file size: "+files.length);
//        for (MultipartFile file:files) {
//            System.out.println("------> file name: "+file.getOriginalFilename());
//        }
        fileService.importFileBatch(files);
    }
}
