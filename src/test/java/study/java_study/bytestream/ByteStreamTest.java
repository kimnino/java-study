package study.java_study.bytestream;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ByteStreamTest {

    @Autowired
    private ByteStreamService byteStreamService;

    @Test
    public void test() {
        byteStreamService.byteStreamExample();
    }
}
