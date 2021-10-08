package com.spring.mvc.board.service;

import com.spring.mvc.board.domain.Board;
import com.spring.mvc.board.repository.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor

public class BoardService {

    private final BoardMapper boardMapper;


    //1. 게시물 목록 조회
    public List<Board> getArticles() {
        List<Board> articles = boardMapper.getArticles();

        //3분 이내 신규글 new 마크 부팅기
        for (Board article : articles) {
            //각 게시물들의 등록시간 읽어오기(밀리초)
            long regtime = article.getRegDate().getTime();

            // 현재 시간 읽어오기(밀리초)
            long now = System.currentTimeMillis();

            if(now - regtime < 60 * 3 * 1000) {
                article.setNewFlag(true);
            }

        }

        return articles;
    }

    //2. 게시물 상세 조회
    @Transactional
    public Board innerArticles(int boardNO) {
        Board content = boardMapper.getContent(boardNO);
        boardMapper.upViewCount(boardNO);
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

    //5. 게시물 수정
    public boolean modify(Board board) {
        return boardMapper.modifyArticle(board);
    }



}
