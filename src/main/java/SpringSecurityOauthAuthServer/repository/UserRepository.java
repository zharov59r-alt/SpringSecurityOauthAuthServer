package test1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import test1.entity.User;
import test1.entity.UserAuthDTO;

import java.util.ArrayList;
import java.util.Collection;

public interface UserRepository extends JpaRepository<User, Long> {


    User findUserByEmail(String email);

    @Query("select u.email, u.password from User u")
    Collection<UserAuthDTO> findAllActiveUsers();

    @Query("select r.name " +
            "from UserRole ur " +
            "join ur.role r " +
            "where ur.user = :user")
    ArrayList<String> findRolesByUser(@Param("user") User user);

}
