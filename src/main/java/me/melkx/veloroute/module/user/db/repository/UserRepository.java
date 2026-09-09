package me.melkx.veloroute.user.db.repository;

import me.melkx.veloroute.user.db.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    @Query("SELECT COUNT(u.email) > 0 FROM UserEntity u WHERE u.email = :email")
    boolean containsEmail(@Param("email") String email);
}