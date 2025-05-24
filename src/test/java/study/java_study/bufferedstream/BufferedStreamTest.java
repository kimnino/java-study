package study.java_study.bufferedstream;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BufferedStreamTest {

    @Autowired
    private BufferedStreamService bufferedStreamService;

    @Test
    public void test() {
        bufferedStreamService.printBufferedByteStream();
        bufferedStreamService.printBufferedCharacterStream();

    }

}
