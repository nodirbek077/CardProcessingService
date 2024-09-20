package uz.asgardia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.asgardia.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM users u WHERE u.id = ?1")
    User findByUserId(Long userId);
}
