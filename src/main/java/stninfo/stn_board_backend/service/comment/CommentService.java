package stninfo.stn_board_backend.service.comment;

import stninfo.stn_board_backend.dto.Comment;
import stninfo.stn_board_backend.etc.Result;

import java.util.List;

public interface CommentService {

    List<Comment> getCommnetByBoardIdx(Integer idx);

    Result insertComment(Comment comment);

    Result deleteCommentByIdx(Integer idx);
}
