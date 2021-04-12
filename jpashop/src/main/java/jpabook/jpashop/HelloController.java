package jpabook.jpashop;


import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) { //model -> model 에 데이터를 넣어 View에 넘길수 있다.
        model.addAttribute("data", "hello!!");
        return "hello"; //View 의 typeleaf 에 자동 연결
    }

    /*@GetMapping("/test")
    public Member test() {
        Member member = new Member();
        member.setname("kim");
        return member;
    }*/

}
