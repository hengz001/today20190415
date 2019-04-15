package sino.gmn.service;

import sino.gmn.model.Cmd;
import sino.gmn.model.Page;

import java.util.List;
import java.util.Map;

public interface CmdService {

    List<Cmd> findAllCmd();

    int removeCmd(int cId);

    Map<String,Object> findPage(Page page);

    List<Cmd> selectPageSort(Page page);

    int findTotal(Page page);

    int insertCmd(Cmd record);

    int deleteByPrimaryKey(Integer cId);

    int insert(Cmd record);

    int insertSelective(Cmd record);

    Cmd selectByPrimaryKey(Integer cId);

    int updateByPrimaryKeySelective(Cmd record);

    int updateByPrimaryKey(Cmd record);
}
