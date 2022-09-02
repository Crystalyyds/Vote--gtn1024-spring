package me.github.crystalyyds.fuckhomework.repository;

import me.github.crystalyyds.fuckhomework.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
