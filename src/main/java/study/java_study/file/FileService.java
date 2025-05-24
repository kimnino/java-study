package study.java_study.file;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

@Service
public class FileService {

    public void exampleSymlink() {
        Path originalFilePath = Paths.get("temp/buffed_text_file.txt");
        Path symlinkPath = Paths.get("symlink_buffed_text_file.txt");

        try {
            Files.createSymbolicLink(symlinkPath, originalFilePath);
            System.out.println("1. 심볼릭 링크 생성: " + symlinkPath.toAbsolutePath() + " -> " + originalFilePath.getFileName());

            boolean isSymlink = Files.isSymbolicLink(symlinkPath);
            System.out.println("2. '" + symlinkPath.getFileName() + "'는 심볼릭 링크인가? " + isSymlink);

            System.out.println("3. 심볼릭 링크를 통해 파일 내용 읽기:");
            String contentViaSymlink = Files.readString(symlinkPath);
            System.out.println(contentViaSymlink);

            Path targetPath = Files.readSymbolicLink(symlinkPath);
            System.out.println("4. 심볼릭 링크 '" + symlinkPath.getFileName() + "'가 가리키는 실제 대상: " + targetPath);
            System.out.println("   실제 대상의 절대 경로: " + targetPath.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("파일 또는 심볼릭 링크 작업 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (Files.exists(symlinkPath)) {
                    Files.delete(symlinkPath);
                    System.out.println("5. 심볼릭 링크 삭제: " + symlinkPath.getFileName());
                }
            } catch (IOException e) {
                System.err.println("정리 중 오류 발생: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void exampleFilesAndPaths() {
        Path dirPath = Paths.get("temp/kim");
        Path filePath = Paths.get("temp/kim", "text_file.txt");
        Path newFilePath = Paths.get("temp/kim", "new_text_file.txt");

        try {
            Files.createDirectories(dirPath);
            System.out.println("디렉토리 생성: " + dirPath.toAbsolutePath());

            String content = "우와!! 파일!!!";
            Files.write(filePath, content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

            System.out.println("파일 생성 및 내용 작성: " + filePath.toAbsolutePath());
            System.out.println("파일 이름: " + filePath.getFileName());
            System.out.println("부모 경로: " + filePath.getParent());
            System.out.println("절대 경로: " + filePath.toAbsolutePath());
            System.out.println("존재 여부: " + Files.exists(filePath));
            System.out.println("파일인가?: " + Files.isRegularFile(filePath));
            System.out.println("디렉토리인가?: " + Files.isDirectory(filePath));
            System.out.println("파일 크기 (바이트): " + Files.size(filePath));

            File file = filePath.toFile();
            System.out.println("File 클래스로 호환된 객체 절대 경로 : " + file.getAbsolutePath());
            System.out.println("File 클래스로 호환된 객체 존재 여부 : " + file.exists());

            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            lines.forEach(System.out::println);

            Files.move(filePath, newFilePath);
            System.out.println("파일 이름 변경: " + filePath.getFileName() + " -> " + newFilePath.getFileName());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (Files.exists(newFilePath)) {
                    Files.delete(newFilePath);
                    System.out.println("파일 삭제: " + newFilePath.getFileName());
                }
                if (Files.exists(dirPath)) {
                    Files.delete(dirPath);
                    System.out.println("디렉토리 삭제: " + dirPath.getFileName());
                }
            } catch (IOException e) {
                System.err.println("파일/디렉토리 삭제 중 오류: " + e.getMessage());
            }
        }
    }


    public void exampleFile() {

        String dirName = "temp/kimminhyuk";
        String fileName = dirName + "/test.txt";
        File dir = new File(dirName);
        File file = new File(fileName);

        try {
            if (dir.mkdir()) {
                System.out.println("디렉토리 생성 완료 : " + dir.getAbsolutePath());
            } else {
                System.out.println("디렉토리 생성 실패 : " + dir.getAbsolutePath());
            }

            if (file.createNewFile()) {
                System.out.println("파일 생성 완료 : " + file.getAbsolutePath());
            } else {
                System.out.println("파일 생성 실패 : " + file.getAbsolutePath());
            }

            System.out.println("파일 이름: " + file.getName());
            System.out.println("파일 경로: " + file.getPath());
            System.out.println("절대 경로: " + file.getAbsolutePath());
            System.out.println("존재 여부: " + file.exists());
            System.out.println("파일인가?: " + file.isFile());
            System.out.println("디렉토리인가?: " + file.isDirectory());
            System.out.println("파일 크기 (바이트): " + file.length());

        } catch (IOException e) {
            System.out.println("입출력 관련 에러 발생!!");
            e.printStackTrace();
        } finally {
            if (file.delete()) {
                System.out.println("파일삭제");
            }
            if (dir.delete()) {
                System.out.println("디렉토리 삭제");
            }
        }
    }
}
