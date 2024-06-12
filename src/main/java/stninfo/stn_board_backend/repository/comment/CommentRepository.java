package stninfo.stn_board_backend.repository.comment;

import org.apache.ibatis.annotations.Mapper;
import stninfo.stn_board_backend.dto.Comment;
import stninfo.stn_board_backend.dto.CommentVO;

import java.util.List;

@Mapper
public interface CommentRepository {

    List<CommentVO> getCommentByBoardIdx(Integer idx);
    CommentVO getCommentByIdx(Integer idx);
    void updateComment(Integer idx, String comment);

    void insertComment(Comment comment);

    void deleteCommentByIdx(Integer idx);

    String getPasswordByCommentIdx(Integer idx);
}
