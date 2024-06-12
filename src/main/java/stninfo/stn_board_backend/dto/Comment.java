package stninfo.stn_board_backend.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
public class Comment {
    private Integer idx;
    private Integer boardIdx;
    private String comment;
    private String pwd;
    private String createAt;

    public Comment(Integer idx, Integer boardIdx, String comment, String pwd, String createAt) {
        this.idx = idx;
        this.boardIdx = boardIdx;
        this.comment = comment;
        this.pwd = pwd;
        this.createAt = createAt;
    }
}
