package study.java_study.encoding;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;

public class EncodingTest {

    @Test
    void encoding_test() throws UnsupportedEncodingException {
        String name = "김민혁";

        byte[] bytes = name.getBytes();
        byte[] utf16bytes = name.getBytes(StandardCharsets.UTF_16);
        byte[] utf8Bytes = name.getBytes(StandardCharsets.UTF_8);
        byte[] isoBytes = name.getBytes(StandardCharsets.ISO_8859_1);
        byte[] usAsciiBytes = name.getBytes(StandardCharsets.US_ASCII);
        byte[] euckrBytes = name.getBytes("EUC-KR");

        System.out.println(Arrays.toString(bytes));
        System.out.println(Arrays.toString(utf16bytes));
        System.out.println(Arrays.toString(utf8Bytes));
        System.out.println(Arrays.toString(isoBytes));
        System.out.println(Arrays.toString(usAsciiBytes));
        System.out.println(Arrays.toString(euckrBytes));

        for (byte b : bytes) {
            System.out.printf("0x%02X ", b & 0xFF);
        }
    }

    @Test
    void encoding_base64_test() {
        String token1 = Base64.getEncoder().encodeToString("1q2w3e4r!`".getBytes(StandardCharsets.UTF_8));
        String token2 = Base64.getEncoder().encodeToString("1q2w3e4r!`".getBytes(StandardCharsets.UTF_8));
        System.out.println(token1);
        System.out.println(token2);
    }

    @Test
    void encoding_test_2() {
        String text = "자바 인코딩";

        byte[] utf8Bytes = text.getBytes(StandardCharsets.UTF_8);
        byte[] eucKrBytes = text.getBytes(Charset.forName("EUC-KR"));

        System.out.println("UTF-8 Bytes: " + Arrays.toString(utf8Bytes));
        System.out.println("EUC-KR Bytes: " + Arrays.toString(eucKrBytes));

        String decodedUtf8 = new String(utf8Bytes, StandardCharsets.UTF_8);
        String decodedEucKr = new String(eucKrBytes, Charset.forName("EUC-KR"));

        System.out.println("Decoded UTF-8: " + decodedUtf8);
        System.out.println("Decoded EUC-KR: " + decodedEucKr);

        String misMatchDecodedUtf8 = new String(utf8Bytes, Charset.forName("EUC-KR"));
        System.out.println("Mismatch Decoded UTF-8: " + misMatchDecodedUtf8);

    }

    @Test
    void base64_test() throws IOException, ClassNotFoundException {

        ClassPathResource resource = new ClassPathResource("images/manchester_icon.jpg");
        byte[] bytes = resource.getInputStream().readAllBytes();
        String base64Data = Base64.getEncoder().encodeToString(bytes);
        //System.out.println(base64Data);

        String payload = "{\"sub\":\"kimminhyuk\",\"exp\":1710000000,\"role\":\"admin\"}";
        String encodedPayload = Base64.getUrlEncoder().withoutPadding().encodeToString(payload.getBytes());
        //System.out.println(encodedPayload);

        // /9j/4AAQSkZJRgABAQAAAQABAAD/4gKgSUNDX1BST0ZJTEUAAQEAAAKQbGNtcwQwAABtbnRyUkdCIFhZWiAAAAAAAAAAAAAAAABhY3NwQVBQTAAAAAAAAAA ....
        byte[] dataBytes = Base64.getDecoder().decode(base64Data);
        Files.write(Paths.get("temp/manchester_icon2.jpg"), dataBytes);


        Person person = new Person("김민혁", 34);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(person);
        oos.close();
        String serializePerson = Base64.getEncoder().encodeToString(baos.toByteArray());
        System.out.println(serializePerson);
        // rO0ABXNyACBzdHVkeS5qYXZhX3N0dWR5LmVuY29kaW5nLlBlcnNvbnJBuna81OR7AgACSQADYWdlTAAEbmFtZXQAEkxqYXZhL2xhbmcvU3RyaW5nO3hwAAAAInQACeq5gOuvvO2YgQ==
        byte[] personBytes = Base64.getDecoder().decode(serializePerson);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(personBytes));
        Person decodePerson = (Person) ois.readObject();
        ois.close();
        System.out.println(decodePerson);


        String query = "인코딩 테스트 & 이름=123";
        String encoded = URLEncoder.encode(query, StandardCharsets.UTF_8);
        System.out.println("인코딩 결과: " + encoded);
        String decoded = URLDecoder.decode(encoded, StandardCharsets.UTF_8);
        System.out.println("디코딩 결과: " + decoded);
    }
}
