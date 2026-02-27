package SpringSecurityOauthAuthServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import SpringSecurityOauthAuthServer.entity.User;

import java.util.ArrayList;
import java.util.Collection;

public interface UserRepository extends JpaRepository<User, Long> {


    User findUserByEmail(String email);


    @Query("select r.name " +
            "from UserRole ur " +
            "join ur.role r " +
            "where ur.user = :user")
    ArrayList<String> findRolesByUser(@Param("user") User user);

}
