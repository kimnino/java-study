package study.java_study.characterstream;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Service
public class CharacterStreamService {

    public void printCharacterStream() {

        String originalString = "바이트스트림 -> 문자스트림 테스트😄";
        byte[] rawBytes = originalString.getBytes(StandardCharsets.UTF_8);

        System.out.println("원본 문자열: " + originalString);
        System.out.println("원본 바이트 배열 길이: " + rawBytes.length + " bytes");
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(rawBytes);
            // InputStreamReader : 바이트 스트림을 문자 스트림으로 변환합니다 (여기서 인코딩 적용)
            InputStreamReader isr = new InputStreamReader(bais, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(isr);

            StringBuilder readTextBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                readTextBuilder.append(line);
            }
            String readString = readTextBuilder.toString();

            System.out.println("읽어들인 문자열: " + readString);
            System.out.println("원본 문자열과 일치: " + originalString.equals(readString));

        } catch (IOException e) {
            System.err.println("스트림 읽기 중 오류 발생: " + e.getMessage());
            e.printStackTrace();

        }
    }


}
