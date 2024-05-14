package stninfo.stn_board_backend.repository.board;

import org.apache.ibatis.annotations.Mapper;
import stninfo.stn_board_backend.dto.Board;

import java.util.List;

@Mapper
public interface BoardRepository {
    void insertBoard(Board board);

    List<Board> getAllBoard();
    int count();

    List<Board> getBoardBy(int offset);

    Board getBoardIdx(Integer idx);



}
