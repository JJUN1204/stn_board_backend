package stninfo.stn_board_backend.service.email;

import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeUtility;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import stninfo.stn_board_backend.dto.Email;
import stninfo.stn_board_backend.etc.Result;

@Service
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public Result sendEmail(Email email)  {

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            String c = String.format("안녕하세요. %s 로 부터 전송된 메일 입니다. \n\n ---------------------------------------------\n\n %s", email.getFrom(), email.getContent());

            helper.setTo(email.getTo());
            helper.setSubject(email.getTitle());
            helper.setText(c, false);
            helper.setFrom("junho@stninfotech.com");
            if (email.getFiles() != null) {
                for (MultipartFile file : email.getFiles()) {
                    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                    helper.addAttachment(MimeUtility.encodeText(fileName, "UTF-8", "B"), new ByteArrayResource(IOUtils.toByteArray(file.getInputStream())));
                }
            }

            javaMailSender.send(message);
            return new Result("SENDED_EMAIL");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Result("SENDING_EMAIL_ERROR");
        }

    }

}