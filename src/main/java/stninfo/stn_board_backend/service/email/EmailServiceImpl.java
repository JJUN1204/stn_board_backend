package stninfo.stn_board_backend.service.email;

import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import stninfo.stn_board_backend.dto.Email;
import stninfo.stn_board_backend.etc.Result;

import org.apache.commons.io.IOUtils;

import java.io.IOException;

/**
 * JavaMailSender를 이용하여 이메일을 전송하는 EmailService 인터페이스의 구현체입니다.
 */
@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /**
     * 첨부 파일을 포함한 이메일을 전송합니다.
     *
     * @param email 이메일 객체로, 발신자, 수신자, 제목, 내용 및 선택적 첨부 파일 등의 정보를 포함합니다.
     * @return 이메일 전송 작업의 성공 또는 실패를 나타내는 Result 객체를 반환합니다.
     */
    @Override
    public Result sendEmail(Email email) {

        try {
            // MimeMessage와 MimeMessageHelper를 생성하여 MIME 메시지를 처리합니다.
            MimeMessage message = javaMailSender.createMimeMessage(); //MIME 양한 부분(텍스트, HTML, 첨부 파일 등)을 표현 데이터 전송
            MimeMessageHelper helper = new MimeMessageHelper(message, true); //true는 murtiPart 허용

            // 이메일 내용을 구성
            String formattedContent = String.format("안녕하세요. %s 로 부터 전송된 메일 입니다. \n\n ---------------------------------------------\n\n %s",
                    email.getFrom(), email.getContent());

            // 수신자, 제목, 내용을 설정
            helper.setTo(email.getTo());
            helper.setSubject(email.getTitle());
            helper.setText(formattedContent, false);

            // 발신자 주소를 설정
            helper.setFrom("junho@stninfotech.com");

            // 첨부 파일이 있는 경우 추가합니다.
            if (email.getFiles() != null) {
                for (MultipartFile file : email.getFiles()) {
                    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                    // 파일 이름을 인코딩하고 첨부 파일로 추가합니다.
                    helper.addAttachment(MimeUtility.encodeText(fileName, "UTF-8", "B"),
                            new ByteArrayResource(IOUtils.toByteArray(file.getInputStream())));
                }
            }

            // 이메일을 전송합니다.
            javaMailSender.send(message);
            return new Result("SENDED_EMAIL"); //성공했을 때
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Result("SENDING_EMAIL_ERROR"); //실패했을 때
        }
    }
}
