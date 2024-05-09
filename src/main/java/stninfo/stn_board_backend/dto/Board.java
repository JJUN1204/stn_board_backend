package stninfo.stn_board_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@NoArgsConstructor
@Getter
public class Board {
    private Integer idx;
    private String title;
    private String writerId;
    private String pwd;
    private String email;
    private Integer isPrivate;
    private Integer isAlert;
    private String content;

    private LocalDateTime createAt;

    public Board(Integer idx, String title, String writerId, String pwd, String email, Integer isPrivate, Integer isAlert, String content, LocalDateTime createAt) {
        this.idx = idx;
        this.title = title;
        this.writerId = writerId;
        this.pwd = pwd;
        this.email = email;
        this.isPrivate = isPrivate;
        this.isAlert = isAlert;
        this.content = content;
        this.createAt = createAt;
    }
}
