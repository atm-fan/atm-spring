package cn.atm.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

public interface Resource {
    // 返回资源对应的输入流
    InputStream getInputStream() throws IOException;
}
