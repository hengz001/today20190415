package sino.gmn.service;

import jxl.write.WriteException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface FileService {

    int importFile(MultipartFile file);

    int exportFile(HttpServletResponse response) throws IOException, WriteException;

    int importFileBatch(MultipartFile[] files) throws IOException;
}
