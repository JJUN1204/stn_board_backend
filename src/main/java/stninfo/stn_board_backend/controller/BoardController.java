package stninfo.stn_board_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import stninfo.stn_board_backend.dto.Board;
import stninfo.stn_board_backend.dto.Comment;
import stninfo.stn_board_backend.etc.PageVO;
import stninfo.stn_board_backend.etc.Result;
import stninfo.stn_board_backend.repository.comment.CommentRepository;
import stninfo.stn_board_backend.repository.file.FileRepository;
import stninfo.stn_board_backend.service.board.BoardService;
import stninfo.stn_board_backend.service.comment.CommentService;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
public class BoardController {
    private final BoardService boardService;
    private final FileRepository fileRepository;

    private final CommentService commentService;

    public BoardController(BoardService boardService, FileRepository fileRepository, CommentService commentService) {
        this.boardService = boardService;
        this.fileRepository = fileRepository;
        this.commentService = commentService;

    }

    @GetMapping("/getAllBoard")
    public ResponseEntity<PageVO<Board>> getAllBoard(Integer currentPage){
        return ResponseEntity.ok(new PageVO<Board>(boardService.Boardcount(), boardService.getBoardBy(currentPage)));
    }

    @GetMapping("/getBoardIdx")
    public ResponseEntity<Board> getBoardIdx(@RequestParam("idx") Integer idx) {
        return ResponseEntity.ok(boardService.getBoardIdx(idx));
    }


    @PostMapping(path ="/insertBoard")
    public ResponseEntity<Result> insertBoard(
            @RequestParam("title") String title,
            @RequestParam("writerId") String writerId,
            @RequestParam("pwd") String pwd,
            @RequestParam("email") String email,
            @RequestParam("isPrivate") Integer isPrivate,
            @RequestParam("isAlert") Integer isAlert,
            @RequestParam("content") String content,
            @RequestParam("files") MultipartFile[] files) {

        //Board board = new Board(title, writerId, pwd, email, isPrivate, isAlert, content, files);

        Board board = new Board(null,title,writerId,pwd,email,isPrivate,isAlert,content,files,null);

        return ResponseEntity.ok(boardService.insertBoard(board));
    }

    @PutMapping("/updateBoard")
    public ResponseEntity<Result> updateBoard(@RequestBody Board board){
        System.out.println(board);
        return ResponseEntity.ok(boardService.updateBoard(board));
    }

    @DeleteMapping("/deleteBoard")
    public ResponseEntity<Result> deleteBoard(Integer idx){
        return ResponseEntity.ok(boardService.deleteBoard(idx));
    }

    @DeleteMapping("/deleteCommentByIdx")
    public ResponseEntity<Result> deleteCommentByIdx(@RequestParam("idx") Integer idx) {
        return ResponseEntity.ok(boardService.deleteBoard(idx));
    }
    @GetMapping("/getBoardCount")
    public ResponseEntity<Integer> getBoardIdx(){
        return ResponseEntity.ok(boardService.Boardcount());
    }


    @GetMapping("/getEmail")
    public ResponseEntity<String> getEmail(Integer idx){
        return ResponseEntity.ok(boardService.getEmail(idx));
    }

    @GetMapping("/getFileNames")
    public ResponseEntity<List<String>> getFileNameByBoardIdx(int boardIdx) {
        return ResponseEntity.ok(boardService.getAllFileNameByBoardIdx(boardIdx));
    }

    @GetMapping("/getComment")
    public ResponseEntity<List<Comment>> getCommentByBoardIdx(Integer boardIdx) {
        return ResponseEntity.ok(commentService.getCommnetByBoardIdx(boardIdx));
    }

    @PostMapping("/addComment")
    public ResponseEntity<Result> addComment(@RequestBody Comment comment) {
        return ResponseEntity.ok(commentService.insertComment(comment));
    }

    @GetMapping("/image/download")
    public ResponseEntity<byte[]> downloadImage(String fileName) {

        try {

            byte[] fileBytes = fileRepository.getFileByFileName(fileName);

            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=\"" + fileName + "\"")
                    .body(fileBytes);
        } catch (IOException e) {

            return ResponseEntity.status(HttpStatus.OK).body(null);

        }


    }



}
