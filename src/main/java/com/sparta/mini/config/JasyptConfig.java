package com.sparta.mini.config;

import lombok.RequiredArgsConstructor;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class JasyptConfig {
    @Value("${secret.key}")
    private String password;
    @Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();

        config.setPassword(password);
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize(2);

        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.NoIvGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        return encryptor;
    }

//    private String getJasyptEncryptorPassword() {
//        try {
//            ClassPathResource resource = new ClassPathResource("jasypt-encryptor-password.txt");
//            return Files.readAllLines(Paths.get(resource.getURI())).stream()
//                    .collect(Collectors.joining(""));
//        } catch (IOException e) {
//            throw new RuntimeException("Jasypt 비밀번호 파일을 찾지 못했습니다.");
//        }
//    }

}