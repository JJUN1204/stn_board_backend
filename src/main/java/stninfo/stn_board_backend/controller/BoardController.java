package stninfo.stn_board_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stninfo.stn_board_backend.dto.Board;
import stninfo.stn_board_backend.etc.PageVO;
import stninfo.stn_board_backend.etc.Result;
import stninfo.stn_board_backend.service.BoardService;

import java.util.List;

@RestController
@CrossOrigin
public class BoardController {
    private final BoardService boardService;


    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/getAllBoard")
    public ResponseEntity<PageVO<Board>> getAllBoard(Integer currentPage){
        return ResponseEntity.ok(new PageVO<Board>(boardService.Boardcount(), boardService.getBoardBy(currentPage)));
    }

    @GetMapping("/getBoardIdx")
    public ResponseEntity<Board> getBoardIdx(Integer idx){
        return ResponseEntity.ok(boardService.getBoardIdx(idx));
    }

    @PutMapping("/insertBoard")
    public ResponseEntity<Result> insertBoard(@RequestBody Board board){
        return ResponseEntity.ok(boardService.insertBoard(board));
    }

    @PutMapping("/updateBoard")
    public ResponseEntity<Result> updateBoard(@RequestBody Board board){
        System.out.println(board);
        return ResponseEntity.ok(boardService.updateBoard(board));
    }

    @GetMapping("/getBoardCount")
    public ResponseEntity<Integer> getBoardIdx(){
        return ResponseEntity.ok(boardService.Boardcount());
    }


    @GetMapping("/getEmail")
    public ResponseEntity<String> getEmail(Integer idx){
        return ResponseEntity.ok(boardService.getEmail(idx));
    }




}
