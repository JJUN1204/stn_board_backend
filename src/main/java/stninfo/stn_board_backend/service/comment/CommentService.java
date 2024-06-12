package stninfo.stn_board_backend.service.comment;

import stninfo.stn_board_backend.dto.Comment;
import stninfo.stn_board_backend.dto.CommentVO;
import stninfo.stn_board_backend.etc.Result;

import java.util.List;

public interface CommentService {

    List<CommentVO> getCommentByBoardIdx(Integer idx);

    Result insertComment(Comment comment);

    CommentVO getCommentByIdx(Integer idx);

    Result deleteCommentByIdx(Integer idx);

    Result updateComment(Integer idx, String comment);
}
