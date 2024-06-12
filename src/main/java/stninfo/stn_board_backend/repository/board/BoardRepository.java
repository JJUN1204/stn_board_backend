package stninfo.stn_board_backend.repository.board;

import org.apache.ibatis.annotations.Mapper;
import stninfo.stn_board_backend.dto.Board;
import stninfo.stn_board_backend.dto.BoardVO;

import java.util.List;

@Mapper
public interface BoardRepository {
    void insertBoard(Board board);

    List<BoardVO> getAllBoard();
    Integer count(String searchType
            , String searchInput
            , String startDate
            , String endDate);

    String getEmail(Integer idx);

    List<BoardVO> getBoardBy(int offset
            , String searchType
            , String searchInput
            , String startDate
            , String endDate);
    BoardVO getBoardIdx(Integer idx);

    List<BoardVO> getAlert();

    void updateBoard(Board board);

    void deleteBoard(Integer idx);
    void saveFileName(Integer boardIdx, String fileName);
    List<String> getAllFileNameByBoardIdx(int boardIdx);

    // www
    String getPasswordByBoardIdx(Integer boardIdx);

    List<BoardVO> getReplyByIdx(Integer idx);

    void updateViewCount(Integer idx);




}
