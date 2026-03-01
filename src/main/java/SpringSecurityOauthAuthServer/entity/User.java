package SpringSecurityOauthAuthServer.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "user", schema = "security")
@SequenceGenerator(name = "s_user_id", sequenceName = "s_user_id", allocationSize = 1)
public class User {

    @Id
    @Column(name = "user_id", columnDefinition = "text")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_user_id")
    private long id;

    @Column(name = "user_name", columnDefinition = "text")
    private String name;

    @Column(name = "user_email", columnDefinition = "text")
    private String email;

    @Column(name = "user_password", columnDefinition = "text")
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
