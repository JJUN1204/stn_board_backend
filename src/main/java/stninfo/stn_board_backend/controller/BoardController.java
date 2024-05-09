package stninfo.stn_board_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stninfo.stn_board_backend.dto.Board;
import stninfo.stn_board_backend.etc.Result;
import stninfo.stn_board_backend.service.BoardService;

import java.util.List;

@RestController
@CrossOrigin("*")
public class BoardController {
    private final BoardService boardService;


    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/getAllBoard")
    public ResponseEntity<List<Board>> getAllBoard(){
        return ResponseEntity.ok(boardService.getAllBoard());
    }


    @PutMapping("/insertBoard")
    public ResponseEntity<Result> insertBoard(@RequestBody Board board){
        return ResponseEntity.ok(boardService.insertBoard(board));
    }

}