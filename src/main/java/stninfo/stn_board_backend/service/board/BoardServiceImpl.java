package stninfo.stn_board_backend.service.board;

import org.springframework.stereotype.Service;
import stninfo.stn_board_backend.dto.Board;
import stninfo.stn_board_backend.dto.BoardVO;
import stninfo.stn_board_backend.dto.CommentVO;
import stninfo.stn_board_backend.etc.Result;
import stninfo.stn_board_backend.repository.board.BoardRepository;
import stninfo.stn_board_backend.repository.comment.CommentRepository;
import stninfo.stn_board_backend.repository.file.FileRepository;
import stninfo.stn_board_backend.service.board.BoardService;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final FileRepository fileRepository;
    private final CommentRepository commentRepository;

    public BoardServiceImpl(BoardRepository boardRepository, FileRepository fileRepository, CommentRepository commentRepository) {
        this.boardRepository = boardRepository;
        this.fileRepository = fileRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Result updateBoard(Board board) {
        try {
            boardRepository.updateBoard(board);
            return new Result("UPDATE_COMPLETE");
        } catch (Exception e) {
            return new Result("error");
        }
    }

    @Override
    public Result deleteBoard(Integer idx) {
        try {
            boardRepository.deleteBoard(idx);
            return new Result("DELETE_COMPLETE");
        } catch (Exception e) {
            List<CommentVO> commentVOList = commentRepository.getCommentByBoardIdx(idx);
            List<BoardVO> boardVOList = boardRepository.getReplyByIdx(idx);
            return new Result("댓글 : " + commentVOList.size() + "개, 답글 : " + boardVOList.size() + "개 이므로 삭제가 불가능합니다.");
        }
    }



    @Override
    public List<String> getAllFileNameByBoardIdx(int boardIdx) {
        return boardRepository.getAllFileNameByBoardIdx(boardIdx);
    }



    @Override
    public List<BoardVO> getBoardBy(Integer currentPage , String searchType
            , String searchInput
            , String startDate
            , String endDate) {
        return boardRepository.getBoardBy (
             (currentPage - 1) * 5,
                    searchType,
                    searchInput,
                    startDate != null ? startDate + " 00:00:00" : null,
                    endDate != null ? endDate + " 23:59:59" : null
        );

    }

    @Override
    public int Boardcount(String searchType, String searchInput, String startDate, String endDate) {
        return boardRepository.count(
                searchType,
                searchInput,
                startDate != null ? startDate + " 00:00:00" : null,
                endDate != null ? endDate + " 23:59:59" : null
        );
    }

    @Override
    public BoardVO getBoardIdx(Integer idx) {
        boardRepository.updateViewCount(idx);
        return boardRepository.getBoardIdx(idx);
    }

    @Override
    public Result insertBoard(Board board) {
        try {
            boardRepository.insertBoard(board);
            if(board.getFiles() != null) {
                List<String> fileNames = fileRepository.save(board.getFiles());

                for (String fileName : fileNames) {
                    boardRepository.saveFileName(board.getIdx(),fileName);
                }
            }
            return new Result("UPDATE_COMPLETE");
        } catch (Exception e) {
            return new Result("error");
        }
    }



    @Override
    public String getEmail(Integer idx) {
        return boardRepository.getEmail(idx);
    }


    @Override
    public List<BoardVO> getAlert() {
        return boardRepository.getAlert();
    }

    @Override
    public List<BoardVO> getAllBoard() {
        return boardRepository.getAllBoard();
    }
}
