package com.jojoldu.book.springboot.dto;

import com.jojoldu.book.springboot.web.dto.HelloResponseDto;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class HelloResponseDtoTest {
    @Test
    public void 롬북_기능_테스트() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        assertThat(dto.getName()).isEqualTo(name);  //assertThat - 검증하고 싶은 대상을 메소드 인자로 받음
        assertThat(dto.getAmount()).isEqualTo(amount); //isEqualTo - 동등 비교 메소드로 assertThat에 있는 값과 비교해서 같으면 성공
    }
}
