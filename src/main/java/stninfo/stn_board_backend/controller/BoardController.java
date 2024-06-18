package stninfo.stn_board_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import stninfo.stn_board_backend.dto.Board;
import stninfo.stn_board_backend.dto.BoardVO;
import stninfo.stn_board_backend.dto.Comment;
import stninfo.stn_board_backend.dto.CommentVO;
import stninfo.stn_board_backend.etc.PageVO;
import stninfo.stn_board_backend.etc.Result;
import stninfo.stn_board_backend.repository.board.BoardRepository;
import stninfo.stn_board_backend.repository.comment.CommentRepository;
import stninfo.stn_board_backend.repository.file.FileRepository;
import stninfo.stn_board_backend.service.board.BoardService;
import stninfo.stn_board_backend.service.comment.CommentService;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class BoardController {
    private final BoardService boardService;
    private final FileRepository fileRepository;
    private BoardRepository boardRepository;
    private final CommentService commentService;
    private CommentRepository commentRepository;

    public BoardController(BoardService boardService, FileRepository fileRepository, CommentService commentService, BoardRepository boardRepository, CommentRepository commentRepository) {
        this.boardService = boardService;
        this.fileRepository = fileRepository;
        this.commentService = commentService;
        this.boardRepository = boardRepository;
        this.commentRepository = commentRepository;
    }

    @GetMapping("/getAllBoard")
    public ResponseEntity<PageVO<BoardVO>> getAllBoard(Integer currentPage, String searchType, String searchInput, String startDate, String endDate) {
        return ResponseEntity.ok(new PageVO<>(boardService.Boardcount(searchType, searchInput, startDate, endDate),
                boardService.getBoardBy(currentPage, searchType, searchInput, startDate, endDate)));
    }

    @GetMapping("/getBoardIdx")
    public ResponseEntity<BoardVO> getBoardIdx(@RequestParam("idx") Integer idx) {
        return ResponseEntity.ok(boardService.getBoardIdx(idx));
    }

    @GetMapping("/getCommentByIdx")
    public ResponseEntity<CommentVO> getCommentByIdx(@RequestParam("idx") Integer idx) {
        return ResponseEntity.ok(commentService.getCommentByIdx(idx));
    }

    @PostMapping("/insertBoard")
    public ResponseEntity<Result> insertBoard(
            @RequestParam("title") String title,
            @RequestParam("writerId") String writerId,
            @RequestParam("pwd") String pwd,
            @RequestParam("email") String email,
            @RequestParam("isPrivate") Integer isPrivate,
            @RequestParam("isAlert") Integer isAlert,
            @RequestParam("content") String content,
            @RequestParam(value = "files", required = false) MultipartFile[] files,
            @RequestParam(value = "replyIdx", required = false) Integer replyIdx) {

        System.out.println(replyIdx);
        Board board = new Board(null, title, writerId, pwd, email, isPrivate, isAlert, content, files, replyIdx);
        return ResponseEntity.ok(boardService.insertBoard(board));
    }

    @PutMapping("/updateBoard")
    public ResponseEntity<Result> updateBoard(
            @RequestParam("idx") int idx,
            @RequestParam("title") String title,
            @RequestParam("writerId") String writerId,
            @RequestParam("email") String email,
            @RequestParam("isPrivate") int isPrivate,
            @RequestParam("isAlert") int isAlert,
            @RequestParam("content") String content,
            @RequestParam(value = "files", required = false) MultipartFile[] files) {

        Board board = new Board(idx, title, writerId,null, email, isAlert, isPrivate,content, files, null);
        return ResponseEntity.ok(boardService.updateBoard(board));
    }



    @PutMapping("/updateComment")
    public ResponseEntity<Result> updateComment(@RequestParam Integer idx, @RequestParam String comment, @RequestParam String pwd) {
        String targetPassword = commentRepository.getPasswordByCommentIdx(idx);
        if (pwd.equals(targetPassword)) {
            return ResponseEntity.ok(commentService.updateComment(idx, comment));
        } else {
            return ResponseEntity.ok(new Result("false"));
        }
    }

    @DeleteMapping("/deleteBoard")
    public ResponseEntity<Result> deleteBoard(Integer idx) {
        return ResponseEntity.ok(boardService.deleteBoard(idx));
    }

    @DeleteMapping("/deleteCommentByIdx")
    public ResponseEntity<Result> deleteCommentByIdx(@RequestParam("idx") Integer idx) {
        return ResponseEntity.ok(commentService.deleteCommentByIdx(idx));
    }

    @PostMapping("/processAction")
    public ResponseEntity<Result> processAction(@RequestParam String action,
                                                @RequestParam String password,
                                                @RequestParam Integer boardIdx,
                                                @RequestParam(required = false) Integer commentIdx) {
        String targetPassword = commentIdx != null ?
                commentRepository.getPasswordByCommentIdx(commentIdx) :
                boardRepository.getPasswordByBoardIdx(boardIdx);

        if (!password.equals(targetPassword)) {
            return ResponseEntity.ok(new Result("PASSWORDERROR"));
        }

        switch (action) {
            case "private":
                return ResponseEntity.ok(new Result("PRIVATE_ACCESS"));

            case "file":
                return ResponseEntity.ok(new Result("FILE_ACCESS"));

            case "update":
                return ResponseEntity.ok(new Result("UPDATE_ACCESS"));

            case "delete":
                Result deleteResult = boardService.deleteBoard(boardIdx);
                return ResponseEntity.ok(deleteResult);

            case "deleteComment":
                Result deleteCommentResult = commentService.deleteCommentByIdx(commentIdx);
                return ResponseEntity.ok(deleteCommentResult);

            default:
                return ResponseEntity.ok(new Result("오류"));
        }
    }

    @GetMapping("/getEmail")
    public ResponseEntity<String> getEmail(Integer idx) {
        return ResponseEntity.ok(boardService.getEmail(idx));
    }

    @GetMapping("/getAlert")
    public ResponseEntity<List<BoardVO>> getAlert() {
        return ResponseEntity.ok(boardService.getAlert());
    }

    @GetMapping("/getFileNames")
    public ResponseEntity<List<String>> getFileNameByBoardIdx(Integer boardIdx) {
        return ResponseEntity.ok(boardService.getAllFileNameByBoardIdx(boardIdx));
    }

    @GetMapping("/getCommentByBoardIdx")
    public ResponseEntity<List<CommentVO>> getCommentByBoardIdx(Integer boardIdx) {
        return ResponseEntity.ok(commentService.getCommentByBoardIdx(boardIdx));
    }

    @PostMapping("/addComment")
    public ResponseEntity<Result> addComment(@RequestBody Comment comment) {
        return ResponseEntity.ok(commentService.insertComment(comment));
    }

    @GetMapping("/image/download")
    public ResponseEntity<byte[]> downloadImage(String fileName) {
        try {
            byte[] fileBytes = fileRepository.getFileByFileName(fileName);
            return ResponseEntity.ok().body(fileBytes);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // 파일 업로드
    @PostMapping("/files/upload")
    public ResponseEntity<List<String>> uploadFiles(@RequestParam("files") MultipartFile[] files) {
        try {
            List<String> fileNames = fileRepository.save(files);
            return ResponseEntity.ok(fileNames);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // 파일 삭제
    @DeleteMapping("/files/delete")
    public ResponseEntity<Boolean> deleteFile(@RequestParam("fileName") String fileName) {
        try {
            boolean deleted = fileRepository.deleteFileByFileName(fileName);
            return ResponseEntity.ok(deleted);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    // 파일 업데이트
    @PutMapping("/files/update")
    public ResponseEntity<List<String>> updateFiles(@RequestParam("newFiles") MultipartFile[] newFiles, @RequestParam("oldFileNames") String[] oldFileNames) {
        try {
            List<String> updatedFileNames = fileRepository.updateFiles(newFiles, oldFileNames);
            return ResponseEntity.ok(updatedFileNames);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
