package com.spring.mvc.board.repository;

import com.spring.mvc.board.domain.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 테스트시에 스프링 컨테이너를 사용한다.
class BoardMapperTest {

    @Autowired
    BoardMapper boardMapper;

    @Test
    @DisplayName("300개의 게시물을 등록해야 한다.")
    void bulkInsert() {
        for (int i = 1; i <= 300; i++) {
            Board board = new Board();

            board.setTitle("테스트 제목 " + i);
            board.setContent("테스트내용이다다ㅏㅏㅏㅏㅏ다 " + i);
            board.setWriter("user " + i );

            boardMapper.insertArticle(board);

        }
        System.out.println("게시물 등록 성공!!");

    }

    @Test
    @DisplayName("전체 게시물을 글번호 내림차순으로 조회해야 한다.")
    void selectAll() {
        List<Board> articles = boardMapper.getArticles();
        // 데이터베이스에 300개에 있나 단언해보는 것테스
        assertTrue(articles.size() == 300);




    }

    @Test
    @DisplayName("글번호를 통해 1개의 게시물을 상세 조회해야 한다.")
    void selectOne() {
        Board content = boardMapper.getContent(30);

        assertEquals("USER30", content.getWriter());
    }

    @Test
    @DisplayName("글번호를 통해 게시물을 1개 삭제해야 한다.")
    @Transactional
    @Rollback
    void delete() {
        boolean result = boardMapper.deleteArticle(100);
        Board content = boardMapper.getContent(100);

        System.out.println("content = " + content);
        System.out.println("result = " + result);

        assertTrue(result);
        assertNull(content);
    }

    @Test
    @DisplayName("글번호를 통해 게시물의 제목과 내용을 수정해야 한다.")
    void modify() {
        Board board = new Board();
        board.setBoardNo(50);
        board.setTitle("수정수정");
        board.setContent("메롱메롱메롱");

        boolean result = boardMapper.modifyArticle(board);
        Board content = boardMapper.getContent(50);

        assertTrue(result);
        assertEquals("수정수정", content.getTitle());

    }

}