package cn.atm.springframework.core.io;

import lombok.Getter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class FileSystemResource implements Resource {
    private final File file;
    // 返回path 变量
    @Getter
    private final String path;


    // 构造方法 参数file
    public FileSystemResource(File file) {
        this.file = file;
        this.path = file.getAbsolutePath();
    }

    // 构造方法 参数path
    public FileSystemResource(String path) {
        this.path = path;
        this.file = new File(path);
    }


    @Override
    public InputStream getInputStream() throws IOException {

        if (this.file.exists()) {
            return Files.newInputStream(this.file.toPath());
        }
        return null;
    }

}
