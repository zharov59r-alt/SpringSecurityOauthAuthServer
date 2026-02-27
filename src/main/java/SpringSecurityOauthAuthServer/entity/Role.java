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
@Table(name = "role", schema = "security")
@SequenceGenerator(name = "s_role_id", sequenceName = "s_role_id", allocationSize = 1)
public class Role {

    @Id
    @Column(name = "role_id", columnDefinition = "text")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_role_id")
    private long id;

    @Column(name = "role_name", columnDefinition = "text")
    private String name;

    @OneToMany(mappedBy = "role")
    private List<UserRole> UserRole = new ArrayList<>();

    @Override
    public String toString() {
        return "role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
