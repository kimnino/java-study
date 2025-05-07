package study.java_study.encoding;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.java_study.base64.ImageFileService;

@SpringBootTest
public class ImageEnDecodingTest {

    @Autowired
    private ImageFileService imageFileService;

    @Test
    void test() {
        imageFileService.imageFileCopy();
    }
}
