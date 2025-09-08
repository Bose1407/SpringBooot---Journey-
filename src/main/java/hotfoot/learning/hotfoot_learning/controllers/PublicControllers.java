package hotfoot.learning.hotfoot_learning.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicControllers {
    @GetMapping
    public String Index(){
        return "This is Index page";
    }
    @GetMapping("/home")
    public String Home(){
        return "This is HomePage U dont Need any auth to come here";
    }

    @GetMapping("/success")
    public  String Success(){

        return "this is a success route mate .. congrats";
    }

}
