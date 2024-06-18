package stninfo.stn_board_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import stninfo.stn_board_backend.dto.Email;
import stninfo.stn_board_backend.dto.MailDto;
import stninfo.stn_board_backend.etc.Result;
import stninfo.stn_board_backend.service.MailService;
import stninfo.stn_board_backend.service.email.EmailService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class EmailController {

    private final EmailService emailService;
    private final MailService mailService;

    @Autowired
    public EmailController(EmailService emailService, MailService mailService) {
        this.emailService = emailService;
        this.mailService = mailService;
    }

    /**
     * 이메일 전송 엔드포인트
     *
     * from    발신자 이메일 주소
     * to      수신자 이메일 주소
     * title   이메일 제목
     * content 이메일 내용
     * files   첨부 파일
     * Result 객체 (전송 성공 여부 및 메시지 포함)
     */
    @PostMapping("/sendMail")
    public Result sendEmail(@RequestParam(value = "from") String from,
                            @RequestParam(value = "to") String to,
                            @RequestParam(value = "title") String title,
                            @RequestParam(value = "content") String content,
                            @RequestParam(value = "files", required = false) MultipartFile[] files) {

        return emailService.sendEmail(new Email(to, from, title, content, files));
    }


    @PostMapping("/contact/send")
    @ResponseBody
    public String mail(@RequestBody MailDto data) {
        String res = this.mailService.sendSimpleMessage(data);
        System.out.println(res);
        return res;
    }
}
