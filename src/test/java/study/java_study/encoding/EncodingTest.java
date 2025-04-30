package study.java_study.encoding;

import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

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

}
