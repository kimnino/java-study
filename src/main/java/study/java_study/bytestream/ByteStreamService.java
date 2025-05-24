package study.java_study.bytestream;

import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class ByteStreamService {

    public void byteStreamExample() {
        String filePath = "temp/person.ser";
        try {
            Person person = new Person("김민혁", 34, "맨유팬");
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(person);
            System.out.println("객체를 파일에 직렬화 성공");

            Person loadPerson = null;
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);

            loadPerson = (Person) ois.readObject();
            System.out.println("객체가 파일에서 역직렬화 성공");
            System.out.println("loadPerson = " + loadPerson);


        } catch (IOException | ClassNotFoundException e) {
            System.out.println("객체 직렬화 실패");
            e.printStackTrace();
        }

    }
}
