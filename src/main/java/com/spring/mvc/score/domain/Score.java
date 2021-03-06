package com.spring.mvc.score.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@ToString
public class Score {

    private int stuNum; //학번
    private String name;
    private int kor;
    private int eng;
    private int math;
    private int total;
    private double average;


    //너무 졸려요...
    // 순차적 학번 부여 정적필드
    private static int seq;

    public Score() {
        this.stuNum = ++seq;
        calcTotal();

    }

    public Score(ResultSet rs) throws SQLException {
        this.stuNum = rs.getInt("stu_num");
        this.name = rs.getString("stu_name");
        this.kor = rs.getInt("kor");
        this.eng = rs.getInt("eng");
        this.math = rs.getInt("math");
        this.total = rs.getInt("total");
        this.average = rs.getDouble("average");
    }

    public void calcTotal() {
        this.total = this.kor + this.eng + this.math;
        this.average = Math.round((this.total / 3.0) * 100) / 100.0;
    };


    public Score(String name, int kor, int eng, int math) {
        this();
        this.name = name;
        this.kor = kor;
        this.eng = eng;
        this.math = math;
    }
}
