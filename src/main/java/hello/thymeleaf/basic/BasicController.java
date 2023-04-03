package hello.thymeleaf.basic;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/basic")
public class BasicController {

    @GetMapping("text-basic")
    public String textBasic(Model model) {
        model.addAttribute("data","SimSimHae");
        return "basic/text-basic";
    }
    @GetMapping("text-unescaped")
    public String textUnescped(Model model) {
        model.addAttribute("data","Sim<b>Sim</b>Hae");
        return "basic/text-unescaped";
    }

    @GetMapping("variable")
    public String variable(Model model) {
        User user1 = new User("user1", 20);
        User user2 = new User("user2", 26);

         List<User> list= new ArrayList<>();
         list.add(user1);
         list.add(user2);

         Map<String,User> map  = new HashMap<>();
         map.put("user1",user1);
         map.put("user2",user2);

         model.addAttribute("user", user1);
         model.addAttribute("users", list);
         model.addAttribute("userMap", map);

         return "basic/variable";
    }

    @GetMapping("/basic-objects")
    public String basicObject(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        session.setAttribute("sessionData", "Hello Session");
        model.addAttribute("request", request);
        model.addAttribute("response", response);
        model.addAttribute("servletContext", request.getServletContext());
        return "basic/basic-objects";
    }

    @Component("helloBean")
    static class HelloBean {
        public String hello(String data) {
            return "Hello " + data;
        }
    }

    @GetMapping("/date")
    public String date(Model model) {
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "basic/date";
        // 타임리프 유틸리티 객체들
        //#message : 메시지, 국제화 처리
        //#uris : URI 이스케이프 지원
        //#dates : java.util.Date 서식 지원
        //#calendars : java.util.Calendar 서식 지원
        //#temporals : 자바8 날짜 서식 지원
        //#numbers : 숫자 서식 지원
        //#strings : 문자 관련 편의 기능
        //#objects : 객체 관련 기능 제공
        //#bools : boolean 관련 기능 제공
        //#arrays : 배열 관련 기능 제공
        //#lists , #sets , #maps : 컬렉션 관련 기능 제공
        //#ids : 아이디 처리 관련 기능 제공
    }

    @Data
    static class User{
        private String username;
        private int age;

        public User(String username, int age) {
            this.username = username;
            this.age = age;
        }
    }
}
