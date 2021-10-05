package com.spring.mvc.score.repository;

import com.spring.mvc.score.domain.Score;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("mr") // 스프링에 저장소 빈으로 등록(memoryScoreRepository)
@Log4j2
// 메모리에 성적정보들을 모아서 저장해야 한다.
public class MemoryScoreRepository implements ScoreRepository {

    private static Map<Integer, Score> scoreMap = new HashMap<>();
    
    static {
        scoreMap.put(1, new Score("김철수", 99, 78, 67));
        scoreMap.put(2, new Score("박영희", 42, 53, 12));
        scoreMap.put(3, new Score("고길동", 32, 87, 76));
    }

    @Override
    public void save(Score score) {

        scoreMap.put(score.getStuNum(), score);
        log.info(scoreMap);
    }

    @Override
    public List<Score> findAll() {
        //Map에서 Score 객체를 전부 뽑아낸 후 리스트에 담아 리턴
        List<Score> scores = new ArrayList<>();
        for (Integer stuNum : scoreMap.keySet()) {
            Score score = scoreMap.get(stuNum);
            scores.add(score);
        }
        return scores;
    }

    @Override
    public Score findOne(int stuNum) {
        return null;
    }

    @Override
    public void remove(int stuNum) {

    }
}

