package stninfo.stn_board_backend.service;

import org.springframework.stereotype.Service;
import stninfo.stn_board_backend.dto.Board;
import stninfo.stn_board_backend.etc.Result;
import stninfo.stn_board_backend.repository.board.BoardRepository;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    @Override
    public Result updateBoard(Board board) {
        try {
            boardRepository.updateBoard(board);
            System.out.println("성공");
            return new Result("UPDATE_COMPLETE");
        } catch (Exception e) {
            System.out.println("실패: " + e.getMessage()); // Exception의 메시지를 출력
            return new Result("error");
        }
    }

    @Override
    public Result deleteBoard(Integer idx) {
        try {
            boardRepository.deleteBoard(idx);
            System.out.println("성공");
            return new Result("DELETE_COMPLETE");
        } catch (Exception e) {
            System.out.println("실패: " + e.getMessage()); // Exception의 메시지를 출력
            return new Result("error");
        }
    }

    @Override
    public List<Board> getBoardBy(int currentPage) {
        return boardRepository.getBoardBy((currentPage - 1) * 5);
    }

    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public Board getBoardIdx(Integer idx) {
        return boardRepository.getBoardIdx(idx);
    }

    @Override
    public Result insertBoard(Board board) {
        try {
            boardRepository.insertBoard(board);
            System.out.println("성공");
            return new Result("UPDATE_COMPLETE");
        } catch (Exception e) {
            System.out.println("실패: " + e.getMessage()); // Exception의 메시지를 출력
            return new Result("error");
        }
    }

    @Override
    public int Boardcount() {
        return boardRepository.count();
    }

    @Override
    public String getEmail(Integer idx) {
        return boardRepository.getEmail(idx);
    }



    @Override
    public List<Board> getAllBoard() {
//        try {
//            System.out.println("성공");
//            return boardRepository.getAllBoard();
//        }catch (Exception e){
//            System.out.println("실패" + e.getMessage());
//            return null;
//        }
        System.out.println(boardRepository.getAllBoard());
        return boardRepository.getAllBoard();
    }
}
