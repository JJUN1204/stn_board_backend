package stninfo.stn_board_backend.service;

import stninfo.stn_board_backend.dto.Board;
import stninfo.stn_board_backend.etc.Result;

import java.util.List;

public interface BoardService {

    Result insertBoard(Board board);
    List<Board> getAllBoard();

    List<Board> getBoardBy(int currentPage);

    int Boardcount();

    Board getBoardIdx(Integer idx);



}
