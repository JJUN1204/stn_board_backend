package stninfo.stn_board_backend.service.board;

import stninfo.stn_board_backend.dto.Board;
import stninfo.stn_board_backend.etc.Result;

import java.util.List;

public interface BoardService {

    Result insertBoard(Board board);
    List<Board> getAllBoard();

    List<Board> getBoardBy(int currentPage);

    int Boardcount();

    String getEmail(Integer idx);

    Board getBoardIdx(Integer idx);

    Result updateBoard(Board board);

    Result deleteBoard(Integer idx);

    List<String> getAllFileNameByBoardIdx(int boardIdx);



}
