package com.spring.mvc.board.service;

import com.spring.mvc.board.domain.Board;
import com.spring.mvc.board.repository.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class BoardService {

    private final BoardMapper boardMapper;


    //1. 게시물 목록 조회
    public List<Board> getArticles() {
        List<Board> articles = boardMapper.getArticles();

        return articles;
    }

    //2. 게시물 상세 조회
    public Board innerArticles(int boardNO) {
        Board content = boardMapper.getContent(boardNO);
        return content;
    }


   //3. 게시물 등록
    public boolean insert(Board board) {
        return boardMapper.insertArticle(board);

    }

    //4. 게시물 삭제
    public boolean remove(int boardNo) {
        return boardMapper.deleteArticle(boardNo);
    }



}