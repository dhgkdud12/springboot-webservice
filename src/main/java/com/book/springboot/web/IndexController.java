package com.book.springboot.web;

import com.book.springboot.config.auth.LoginUser;
import com.book.springboot.config.auth.dto.SessionUser;
import com.book.springboot.service.posts.PostsService;
import com.book.springboot.web.dto.PostsResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;
    
    @GetMapping("/")
//    public String index(Model model) { //서버 템플릿 엔진에서 사용할 수 있는 객체 저장
    public String index(Model model, @LoginUser SessionUser user) { // 기존에 httpSession.getAttribute("user")로 가져오던 세션 정보 값이 개선됨, 어느 컨트롤러든지 @LoginUser만 사용하면 세션 정보를 가져올 수 있음.
        model.addAttribute("posts", postsService.findAllDesc()); //posts로 index.mustache에 전달

//        SessionUser user = (SessionUser) httpSession.getAttribute("user"); //CustomOAuth2UserService에서 로그인 성공 시 세션에 SessionUser를 저장하도록 구성, 로그인 성공 시 httpSession.getAttribute("user")에서 값을 가져올 수 있음

        if (user != null) { // 세션에 저장된 값이 있을 때만
            model.addAttribute("loginName", user.getName()); //model에 userName으로 등록
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {

        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
