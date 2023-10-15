package br.com.farley.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserRepository repository;

    @PostMapping("")
    public ResponseEntity create(@RequestBody UserModel user){
        UserModel exist =  this.repository.findByUserName(user.getUserName());
        if(exist != null ) {
            System.out.println("Usuário ja existe");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
        }
        UserModel received =  this.repository.save(user);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(received);
    }
}
