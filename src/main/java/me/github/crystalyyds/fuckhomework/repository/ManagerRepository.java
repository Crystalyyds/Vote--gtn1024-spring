package me.github.crystalyyds.fuckhomework.repository;

import me.github.crystalyyds.fuckhomework.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName: ManagerRepository
 * @Description: TODO
 * @author: 名字
 * @date: 2022/9/3  18:20
 */

public interface ManagerRepository extends JpaRepository<Manager,Integer> {
    Manager findByName(String name);
}
