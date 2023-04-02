package hello.thymeleaf.basic;

import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
