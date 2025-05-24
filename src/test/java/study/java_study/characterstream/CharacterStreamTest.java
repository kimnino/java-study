package study.java_study.characterstream;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CharacterStreamTest {

    @Autowired
    private CharacterStreamService characterStreamService;

    @Test
    public void test() {
        characterStreamService.printCharacterStream();
    }
}
