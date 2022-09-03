package me.github.crystalyyds.fuckhomework.service;

import me.github.crystalyyds.fuckhomework.entity.Candidate;
import me.github.crystalyyds.fuckhomework.entity.Manager;
import me.github.crystalyyds.fuckhomework.entity.User;
import me.github.crystalyyds.fuckhomework.repository.ManagerRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;


/**
 * @ClassName: ManagerService
 * @Description: TODO
 * @author: 名字
 * @date: 2022/9/3  18:20
 */
@Service
public class ManagerService {
    private final ManagerRepository managerRepository;

    @Inject

    public ManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    public Manager newManager(String name,String password) {
        Manager manager = new Manager();
        manager.setName(name);
        manager.setPassword(password);
        return managerRepository.save(manager);
    }

    List<Manager> findAllManager() {
        return managerRepository.findAll();
    }

    public Manager login(String username, String password) {
        Manager manager = managerRepository.findByName(username);
        if (manager != null && manager.getPassword().equals(password)) {
            return manager;
        }
        return null;
    }
}
