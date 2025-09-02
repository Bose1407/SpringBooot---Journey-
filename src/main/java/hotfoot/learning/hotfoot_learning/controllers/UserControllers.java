package hotfoot.learning.hotfoot_learning.controllers;


import hotfoot.learning.hotfoot_learning.entity.UserEntity;
import hotfoot.learning.hotfoot_learning.repositry.UserRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserControllers {
    @Autowired
    private UserRepositry userRepositry;
    @GetMapping("/")
    public ResponseEntity<List<UserEntity>> sayHello(){
        List<UserEntity> users = userRepositry.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserEntity user){
            if(userRepositry.existsByEmail(user.getEmail())){
                return  new ResponseEntity<>("Email Already registered, use another email",HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(userRepositry.save(user),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")

    public ResponseEntity<UserEntity> getSingleUser(@PathVariable Long id){
        return userRepositry.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id,@RequestBody UserEntity updated){
        return userRepositry.findById(id)
                .map(userEntity -> {
                    userEntity.setEmail(updated.getEmail());
                    userEntity.setName(updated.getName());
                    userEntity.setPassword(updated.getPassword());
                    UserEntity user = userRepositry.save(userEntity);
                    return new ResponseEntity<>(user,HttpStatus.OK);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        return userRepositry.findById(id)
                .map(userEntity ->{
                    userRepositry.delete(userEntity);
                    return new ResponseEntity<>("User deleted Successfully!",HttpStatus.OK);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
