package sino.gmn.service.impl;

import org.springframework.stereotype.Service;
import sino.gmn.dao.CmdMapper;
import sino.gmn.model.Cmd;
import sino.gmn.model.Page;
import sino.gmn.service.CmdService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("cmdService")
public class CmdServiceImpl implements CmdService {

    @Resource
    private CmdMapper cmdDao;

    public List<Cmd> findAllCmd(){
        return cmdDao.findAllCmd();
    }

    public int removeCmd(int cId){
        Cmd cmd = selectByPrimaryKey(cId);
        cmd.setcFlag(0);
        return updateByPrimaryKey(cmd);
    }

    public Map<String,Object> findPage(Page page){
        Map<String,Object> map = new HashMap<>();
        int total ;
        List<Cmd> rows;

        rows = selectPageSort(page);
        total = findTotal(page);
        map.put("rows",rows);
        map.put("total",total);
        return map;
    }

    public int findTotal(Page page){
        return cmdDao.findTotal(page);
    }

    public List<Cmd> selectPageSort(Page page){
        return cmdDao.selectPageSort(page);
    }

    public int insertCmd(Cmd record) {
        String cmd = "";
        //String recvCmd = "";
        String req = record.getcRequest();
        String response = record.getcResponse();
        req = req.replace(" ","");
        response = response.replace(" ","");
        if(req.length()>=2){
            cmd = req.substring(0,2);
        }

        record.setcId(null);
        record.setcCmd(cmd);
        record.setcRequest(req);
        record.setcResponse(response);
        record.setcDescrible(record.getcDescrible()==null?cmd:record.getcDescrible());
        record.setcFlag(record.getcFlag()!=null?record.getcFlag():1);
        return insert(record);
    }

    public int deleteByPrimaryKey(Integer cId) {
        return cmdDao.deleteByPrimaryKey(cId);
    }

    public int insert(Cmd record) {
        return cmdDao.insert(record);
    }

    public int insertSelective(Cmd record) {
        return cmdDao.insertSelective(record);
    }

    public Cmd selectByPrimaryKey(Integer cId) {
        return cmdDao.selectByPrimaryKey(cId);
    }

    public int updateByPrimaryKeySelective(Cmd record) {
        return cmdDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Cmd record) {
        return cmdDao.updateByPrimaryKey(record);
    }
}
