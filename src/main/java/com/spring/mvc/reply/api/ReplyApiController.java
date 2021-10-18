package com.spring.mvc.reply.api;

import com.spring.mvc.reply.domain.Reply;
import com.spring.mvc.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/reply")
@Log4j2
@RequiredArgsConstructor
@CrossOrigin
public class ReplyApiController {

    /*
    *
    * */
    private final ReplyService replyService;

    // 댓글 목록 조회 요청 처리
    @GetMapping("/{boardNo}")
    public ResponseEntity<List<Reply>> list(
            @PathVariable int boardNo) {

        log.info("/api/v1/reply" + boardNo + "GET!");
        List<Reply> replyList = replyService.getList(boardNo);

        return new ResponseEntity<>(replyList, OK);
    }

    // 댓글 등록 처리 요청
    @PostMapping("")
    // @RequestBody: 클라이언트에서 전달한 JSON데이터를 자바객체로 변환해 줌!
    public ResponseEntity<String> create(@RequestBody Reply reply) {
        log.info("api/v1/reply POST! - " + reply);


        return replyService.register(reply)
                ? new ResponseEntity<>("insertSuccess", OK)
                : new ResponseEntity<>("insertFall", INTERNAL_SERVER_ERROR);
    }

    // 댓글 수정 요청 처리
    @PutMapping("/{rno}")
    public ResponseEntity<String> modify(@PathVariable("rno") int replyNo, @RequestBody Reply reply) {
        reply.setReplyNo(replyNo);
        log.info("api/v1/reply/ " + replyNo + " PUT - " + reply);

        return replyService.modify(reply)
                ? new ResponseEntity<>("modSuccess", OK)
                : new ResponseEntity<>("modFail", INTERNAL_SERVER_ERROR);
    }

    // 댓글 삭제 요청 처리
    @DeleteMapping("/{rno}")
    public ResponseEntity<String> remove(@PathVariable("rno") int replyNo) {
        log.info("/api/v1/reply/" +replyNo + "DELECT !!");

        return replyService.delete(replyNo)
                ? new ResponseEntity<>("delSuccess", OK)
                : new ResponseEntity<>("delFail", INTERNAL_SERVER_ERROR);
    }
}
