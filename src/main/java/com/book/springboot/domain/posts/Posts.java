package com.book.springboot.domain.posts;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter //lombok
@NoArgsConstructor //lombok, 기본 생성자 자동 추가
@Entity //JPA, 테이블과 링크될 클래스임을 나타냄
public class Posts { //Entity 클래스

    @Id //해당 테이블의 PK필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK의 생성규칙, auto_increment
    private Long id;

    @Column(length = 500, nullable = false) // 테이블의 칼럼, 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용. 기본 사이즈 VARCHAR(255), 사이즈 500으로 늘림
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false) //타입을 TEXT로 변경
    private String content;

    private String author;

    @Builder //lombok, 해당 클래스의 빌더 패턴 클래스 생성, 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
