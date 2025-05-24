package study.java_study.bufferedstream;

import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Service
public class BufferedStreamService {

    public void printBufferedByteStream() {

        String sourceFilePath = "temp/input_binary.bin";
        String resultFilePath = "temp/output_binary.bin";
        this.createDummyBinaryFile(sourceFilePath, 1024 * 1024);

        try (
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sourceFilePath));
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(resultFilePath))) {

            int bytesRead;
            // 내부 버퍼를 활용하여 효율적으로 1바이트를 읽어오고, 씁니다.
            while ((bytesRead = bis.read()) != -1) { // 읽을때
                bos.write(bytesRead); // 쓸때
            }

            System.out.println("바이너리 파일 복사 완료!");
        } catch (IOException e) {
            System.err.println("파일 복사 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public void printBufferedCharacterStream() {
        String filePath = "temp/buffed_text_file.txt";
        String[] linesToWrite = {
                "문자스트림의 버퍼스트림을 활용한 예시",
                "BufferedReader와 BufferedWriter는 활용",
                "😊😊😊😊😊😊😊😊😊😊😊😊😊😊😊"
        };

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, StandardCharsets.UTF_8))) {
            for (String line : linesToWrite) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("파일 쓰기 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8))) {
            reader.lines().forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("파일 읽기 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }

    }

    private void createDummyBinaryFile(String path, int size) {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            byte[] data = new byte[size];
            for (int i = 0; i < size; i++) {
                data[i] = (byte) (i % 256);
            }
            fos.write(data);
        } catch (IOException e) {
            System.err.println("더미 파일 생성 중 오류: " + e.getMessage());
        }
    }
}
