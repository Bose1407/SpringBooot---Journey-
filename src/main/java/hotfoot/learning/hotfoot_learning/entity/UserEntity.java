package hotfoot.learning.hotfoot_learning.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.stereotype.Repository;

@Data
@Entity
@Table(name = "Users")
@Repository
public class UserEntity {

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 50)
    @NotBlank(message = "Name cant be empty")
    @Size(min = 3,max = 50)
    private  String name;

    @Column(length = 50,unique = true)
//    @NotBlank(message = "User Name cant be empty")
    @Size(min = 3,max = 50)
    private  String username;

    @Column(nullable = false,length = 50,unique = true)
    @NotBlank(message = "Enter your email")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Password cant be empty")
    @Size(min=8,max=100)
    private String  password;

    public UserEntity(Long id, String name, String email, String password,String username) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public UserEntity() {
    }
}
