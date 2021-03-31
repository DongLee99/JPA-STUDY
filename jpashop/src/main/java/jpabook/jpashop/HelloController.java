package jpabook.jpashop;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) { //model -> model 에 데이터를 넣어 View에 넘길수 있다.
        model.addAttribute("data", "hello!!");
        return "hello"; //View 의 typeleaf 에 자동 연결
    }

}
