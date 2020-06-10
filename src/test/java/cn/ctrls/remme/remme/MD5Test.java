package cn.ctrls.remme.remme;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

@SpringBootTest
public class MD5Test {
//    @Test
    public void testMD5() throws NoSuchProviderException, NoSuchAlgorithmException {
        String test = "admin";
//        MessageDigest.getInstance(test,"SHA-256");
        System.out.println(MessageDigest.getInstance(test,"MD5").toString());
    }
}
