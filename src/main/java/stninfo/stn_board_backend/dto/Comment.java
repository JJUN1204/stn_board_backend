package stninfo.stn_board_backend.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class Comment {
    private Integer Idx;
    private Integer boardIdx;
    private String comment;
    private String pwd;
    private LocalDateTime createAt;

    public Comment(Integer idx, Integer boardIdx, String comment, String pwd, LocalDateTime createAt) {
        Idx = idx;
        this.boardIdx = boardIdx;
        this.comment = comment;
        this.pwd = pwd;
        this.createAt = createAt;
    }
}
