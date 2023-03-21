package com.sparta.mini.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class JasyptConfigTest {
    @Test
    void jasyptTest(){
        String t1 = "";

        StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
        jasypt.setPassword("");

        String encryptedText = jasypt.encrypt(t1);
        String decryptedText = jasypt.decrypt(encryptedText);
        assertEquals(decryptedText, t1);
        System.out.println(encryptedText);
        System.out.println(decryptedText);
    }

}