package study.java_study.file;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FileTest {

    @Autowired
    private FileService fileService;

    @Test
    public void test() {
        fileService.exampleFile();
    }

    @Test
    public void test2() {
        fileService.exampleFilesAndPaths();
    }


    @Test
    public void test3() {
        fileService.exampleSymlink();
    }
}
