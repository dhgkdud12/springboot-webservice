package com.jojoldu.book.springboot;

import com.jojoldu.book.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class) //SpringRunner라는 스프링 실행자 사용, 스프링 부트 테스트와 JUnit 사이에 연결자 역할
@WebMvcTest(controllers = HelloController.class) // Web(Spring MVC)에 집중할 수 있는 어노테이션
public class HelloControllerTest {

    @Autowired //스프링이 관리하는 빈 주입 받음
    private MockMvc mvc; //웹 API를 테스트할 때 사용, HTTP GET, POST 등에 대한 API 테스트할 수 있음

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) // /hello 주소로 HTTP GET 요청함
                .andExpect(status().isOk()) //mvc.perform의 결과 검증, 200인지 아닌지 검증
                .andExpect(content().string(hello)); // mvc.perform의 결과 검증, Controller에서 리턴하는 값이 hello가 맞는지 검증
    }

    @Test public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name) //param - API 테스트할 때 사용될 요청 파라미터 설정,
                        .param("amount", String.valueOf(amount))) //String 값만 허용되기 때문에 숫자/날짜 등의 데이터도 문자열로 변경해야함.
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) //jsonPath- JSON 응답값을 필드별로 검증, $를 기준으로 필드명 명시
                .andExpect(jsonPath("$.amount", is(amount)));
    }

}
