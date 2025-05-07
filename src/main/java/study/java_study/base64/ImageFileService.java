package study.java_study.base64;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

@Component
public class ImageFileService {

    public void imageFileCopy() {
        try {
            // 1. InputStream으로 이미지 읽기
            ClassPathResource resource = new ClassPathResource("images/manchester_icon.jpg");
            InputStream inputStream = resource.getInputStream();
            byte[] imageBytes = inputStream.readAllBytes();

            // 2. Base64 인코딩
            String encodedImage = Base64.getEncoder().encodeToString(imageBytes);
            System.out.println("Base64 Encoded Image:\n" + encodedImage);

            inputStream.close();

            byte[] decodedImageBytes = Base64.getDecoder().decode(encodedImage);

            Path outputPath = Path.of("temp/decoded_new_image.jpg"); // 프로젝트 루트에 저장
            Files.write(outputPath, decodedImageBytes);

            System.out.println("복원된 이미지 저장 완료: decoded_new_image.jpg");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
