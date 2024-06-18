package stninfo.stn_board_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {
    /*
    private String host = "smtp.gmail.com";
    private int port = 587;
    private String username = "kai061204@gmail.com";
    private String password = "dubwsqlijdmpbwxk";
    */
    private String host = "smtp.worksmobile.com";
    private int port = 587;
    private String username = "junho@stninfotech.com";
    private String password = "CbXcZNYsCdiF";

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        // SMTP 설정
        props.put("mail.smtp.auth", "true"); // 인증 사용 설정
        props.put("mail.smtp.starttls.enable", "true"); // STARTTLS 지원 설정
        // Disable SSL verification (use only for testing)
        props.put("mail.smtp.ssl.trust", "*");
        props.put("mail.smtp.ssl.checkserveridentity", "false");
        mailSender.setJavaMailProperties(props);
        return mailSender;
    }
}
