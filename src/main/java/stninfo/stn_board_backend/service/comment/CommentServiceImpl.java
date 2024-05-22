package stninfo.stn_board_backend.service.comment;

import org.springframework.stereotype.Service;
import stninfo.stn_board_backend.dto.Comment;
import stninfo.stn_board_backend.etc.Result;
import stninfo.stn_board_backend.repository.comment.CommentRepository;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    @Override
    public Result deleteCommentByIdx(Integer idx) {
        try {
            commentRepository.deleteCommentByIdx(idx);
            return new Result("DELETE_COMMENT_COMPLETE");
        } catch (Exception e) {
            System.out.println("실패: " + e.getMessage()); // Exception의 메시지를 출력
            return new Result("error");
        }
    }

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> getCommnetByBoardIdx(Integer idx) {
        return commentRepository.getCommnetByBoardIdx(idx);
    }

    @Override
    public Result insertComment(Comment comment) {
        try {
            commentRepository.insertComment(comment);
            return new Result("ADD_COMMENT_COMPLETE");
        } catch (Exception e) {

            System.out.println("실패: " + e.getMessage()); // Exception의 메시지를 출력
            return new Result("error");
        }
    }
}
