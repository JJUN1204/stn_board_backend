package stninfo.stn_board_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
public class Board {
    private Integer idx;
    private String title;
    private String writerId;
    private String pwd;
    private String email;
    private Integer isPrivate;
    private Integer isAlert;
    private String content;
    private MultipartFile[] files;
    private Integer replyIdx;
}
