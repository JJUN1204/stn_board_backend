package stninfo.stn_board_backend.service.board;

import stninfo.stn_board_backend.dto.Board;
import stninfo.stn_board_backend.dto.BoardVO;
import stninfo.stn_board_backend.etc.Result;

import java.util.List;

public interface BoardService {

    Result insertBoard(Board board);
    List<BoardVO> getAllBoard();

    List<BoardVO> getBoardBy(Integer currentPage
            , String searchType
            , String searchInput
            , String startDate
            , String endDate);

    int Boardcount(
            String searchType
            , String searchInput
            , String startDate
            , String endDate
    );

    List<BoardVO> getAlert();

    String getEmail(Integer idx);

    BoardVO getBoardIdx(Integer idx);

    Result updateBoard(Board board);

    Result deleteBoard(Integer idx);

    List<String> getAllFileNameByBoardIdx(int boardIdx);



}
