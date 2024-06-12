package stninfo.stn_board_backend.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class CommentVO {
    private Integer idx;
    private Integer boardIdx;
    private String comment;
    private String createAt;

    public CommentVO(Integer idx, Integer boardIdx, String comment, String createAt) {
        this.idx = idx;
        this.boardIdx = boardIdx;
        this.comment = comment;
        this.createAt = createAt;
    }
}
