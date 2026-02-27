package test1.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "user_role", schema = "security")
@SequenceGenerator(name = "s_user_role_id", sequenceName = "s_user_role_id", allocationSize = 1)
public class UserRole {

    @Id
    @Column(name = "user_role_id", columnDefinition = "text")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_user_role_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    @JsonBackReference
    private Role role;

    @Override
    public String toString() {
        return "user_role{" +
                "id=" + id +
                '}';
    }
}
