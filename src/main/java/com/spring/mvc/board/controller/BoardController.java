package com.spring.mvc.board.controller;

import com.spring.mvc.board.domain.Board;
import com.spring.mvc.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;


    //1. 게시물 목록 조회 요청
    @GetMapping("/list")
    public String list(Model model) {
        log.info("/board/list GET 요청 발생!");
        List<Board> articles = boardService.getArticles();
        model.addAttribute("articles", articles);
        return "board/list";
    }


    //2. 게시물 상세 조회 요청
    @GetMapping("/content")
    public String content(int boardNo, Model model) {
        log.info("/board/content GET 요청 발생!");
        Board innArticles = boardService.innerArticles(boardNo);
        model.addAttribute("article", innArticles);
        return "board/content";
    }

    // 등록 페이지 나오게 요청
    @GetMapping("/write")
    public String writePage() {
        return "board/write";
    }

    //3. 게시물 등록 요청
    @PostMapping("/write")
    public String write(Board board, RedirectAttributes ra) {
        log.info("/board/write POST 요청 발생! " + board);
        if (boardService.insert(board)) {
            ra.addFlashAttribute("msg", "success");
        } else {
            ra.addFlashAttribute("msg", "fail");
        }
        return "redirect:/board/list";
    }

    //4. 게시물 삭제 요청
    @GetMapping("/delete")
    public String delete(int boardNo) {
        log.info("/board/delete GET 요청 발생! " + boardNo);
        boardService.remove(boardNo);
        return "redirect:/board/list";
    }

    // 수정 페이지 나오게 요청
    @GetMapping("/modify")
    public String modifyPage(int boardNo, Model model) {
        log.info("/board/modify GET! " + boardNo);
        Board content = boardService.innerArticles(boardNo);
        model.addAttribute("article", content);

        return  "board/modify";
    }

    //5. 게시물 수정 요청
    @PostMapping("/modify")
    public String modify(Board board) {
        log.info("/board/modify POST! " + board);
        boardService.modify(board);
        return "redirect:/board/content?boardNo=" + board.getBoardNo();
    }

}
