package stninfo.stn_board_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;


@Getter
@AllArgsConstructor
public class BoardVO {
    private Integer idx;
    private String title;
    private String writerId;
    private String email;
    private String content;
    private Integer isAlert;
    private Integer isPrivate;
    private String createAt;
    private Integer fileCount;
    private Integer commentCount;
    private Integer replyIdx;
    private int view;
}
