package cn.edu.hust.springbootdemo.controller;

import cn.edu.hust.springbootdemo.dao.UserRepository;
import cn.edu.hust.springbootdemo.domain.User;
import cn.edu.hust.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class MainController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate<String,User> template;
    @GetMapping("/add")
    @ResponseBody
    public String saveUser()
    {
        //保存数据到mysql
        userService.save(new User("lili",20));
        //保存到redis
        //stringRedisTemplate.opsForValue().set("aaa", "111");
        return "Saved";
    }

    @GetMapping("/findAll")
    @ResponseBody
    public List<User> findAll()
    {
        //保存数据到mysql
        List<User> users=userService.findAll();
        //保存到redis
        //stringRedisTemplate.opsForValue().set("aaa", "111");
        return users;
    }

    @GetMapping("/save")
    @ResponseBody
    public String saveUser2Redis()
    {

        //保存到redis
        //stringRedisTemplate.opsForValue().set("aaa", "111");
        User user=new User("lili",20);
        template.opsForValue().set(user.getName(), user);
        return "Saved";
    }

    @GetMapping("/login")
    public  String login()
    {
        return "login";
    }
}
