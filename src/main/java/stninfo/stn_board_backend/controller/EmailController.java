package stninfo.stn_board_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import stninfo.stn_board_backend.dto.Email;
import stninfo.stn_board_backend.etc.Result;
import stninfo.stn_board_backend.service.email.EmailService;

@RestController
@CrossOrigin
@RequestMapping("/api/mail")
public class    EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/sendMail")
    public Result sendEmail(@RequestParam(value = "from") String from,
                            @RequestParam(value = "to") String to,
                            @RequestParam(value = "title") String title,
                            @RequestParam(value = "content") String content,
                            @RequestParam(value = "files", required = false) MultipartFile[] files)  {

        return emailService.sendEmail(new Email(from, to, title, content, files));

    }
}