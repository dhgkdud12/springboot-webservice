package com.book.springboot.domain;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest //별다른 설정 없을 경우, H2 DB 자동 실행
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After // JUnit에서 단위 테스트가 끝날 때마다 수행되는 메소드를 지정(끝날 때마다 삭제)
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder() //테이블 posts에 insert/update 쿼리 실행, id값이 있다면 update, 없다면 insert 쿼리 실행
                .title(title)
                .content(content)
                .author("abcdef@gmail.com")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll(); // 테이블 posts에 있는 모든 데이터를 조회해오는 메소드

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

    }

    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2022,2,26,0,0,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>>> createDate="+posts.getCreatedDate()+", modifiedDate="+posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
