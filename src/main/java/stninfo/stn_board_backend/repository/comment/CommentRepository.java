package stninfo.stn_board_backend.repository.comment;

import org.apache.ibatis.annotations.Mapper;
import stninfo.stn_board_backend.dto.Comment;

import java.util.List;

@Mapper
public interface CommentRepository {

    List<Comment> getCommnetByBoardIdx(Integer idx);

    void insertComment(Comment comment);

    void deleteCommentByIdx(Integer idx);
}
