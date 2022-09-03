package me.github.crystalyyds.fuckhomework;

import me.github.crystalyyds.fuckhomework.entity.Candidate;
import me.github.crystalyyds.fuckhomework.entity.User;
import me.github.crystalyyds.fuckhomework.service.CandidateService;
import me.github.crystalyyds.fuckhomework.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import javax.inject.Inject;
import java.util.Scanner;
import java.util.Set;

@SpringBootApplication
public class Application implements CommandLineRunner {
    private static final Scanner sc = new Scanner(System.in);

    private final UserService userService;
    private final CandidateService candidateService;

    @Inject
    public Application(UserService userService, CandidateService candidateService) {
        this.userService = userService;
        this.candidateService = candidateService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        while (true) {
            System.out.println("**********************************");
            System.out.println("******        1.用户注册      ******");
            System.out.println("******        2.用户登录      ******");
            System.out.println("******        3.竞选添加      ******");
            System.out.println("******        4.竞选登录      ******");
            System.out.println("******        0.退出         ******");
            System.out.println("***********************************");
            System.out.println("选择用户种类：");
            int op = sc.nextInt();
            switch (op) {
                case 1:
                    // 用户注册
                    System.out.println("请输入用户名：");
                    String username = sc.next();
                    System.out.println("请输入密码：");
                    String password = sc.next();
                    User user = userService.newUser(username, password);
                    if (user != null) {
                        System.out.println("注册成功！");
                    } else {
                        System.out.println("注册失败！");
                    }
                    break;
                case 2:
                    // 用户登录
                    System.out.println("请输入用户名：");
                    username = sc.next();
                    System.out.println("请输入密码：");
                    password = sc.next();
                    user = userService.login(username, password);
                    if (user != null) {
                        System.out.println("登录成功！");
                        Usermenu(user);
                    } else {
                        System.out.println("登录失败！");
                    }
                    break;
                case 3:
                    // 竞选添加
                    System.out.println("请输入竞选名称：");
                    String name = sc.next();
                    System.out.println("请输入竞选描述：");
                    String description = sc.next();
                    Candidate candidate = candidateService.newCandidate(name, description);
                    if (candidate.getId() != null) {
                        System.out.println("添加成功！");
                    } else {
                        System.out.println("添加失败！");
                    }
                    break;
                case 4 :
                    System.out.println("请输入用户名：");
                    username = sc.next();
                    candidate = candidateService.login(username);
                    if (candidate != null) {
                        System.out.println("登录成功！");
                        Candidatemenu(candidate);
                    } else {
                        System.out.println("登录失败！");
                    }
                    break;
                default:
                    System.out.println("退出系统");
                    System.exit(0);
            }
        }
    }

    private void voteForSomeone(User user) {
        System.out.println("===== 进入投票程序 =====");
        candidateService.getAllCandidates().forEach(System.out::println);
        System.out.println("请输入您要投票的编号：");
        int id = sc.nextInt();
        Candidate candidate = candidateService.getCandidate(id);
        if (candidate != null) {
            Set<User> users = candidate.getUsers();
            users.add(user);
            candidate.setUsers(users);
            candidateService.updateCandidate(candidate);
            System.out.println("投票成功！");
        } else {
            System.out.println("投票失败！");
        }
    }

    private void Usermenu(User user){
        while (true) {
            System.out.println("**********************************");
            System.out.println("******        1.搜索参选对象  ******");
            System.out.println("******        2.投票         ******");
            System.out.println("******        0.退出         ******");
            System.out.println("***********************************");
            int op = sc.nextInt();
            switch (op) {
                case 1:
                    candidateService.findAll().forEach(System.out::println);
                    break;
                case 2:
                    voteForSomeone(user);
                    break;
                default :
                    System.out.println("退出用户菜单");
                    System.exit(0);
            }

        }
    }

    private void Candidatemenu(Candidate candidate){
        while (true) {
            System.out.println("**********************************");
            System.out.println("******        1.修改介绍      ******");
            System.out.println("******        2.查看个人信息    ******");
            System.out.println("******        0.退出         ******");
            System.out.println("***********************************");
            int op = sc.nextInt();
            switch (op) {
                case 1:
                    System.out.println("输入自我介绍");
                    String description = sc.next();
                    candidateService.update(candidate,description);
                    break;
                case 2 :
                    System.out.println("============自我介绍============");
                    System.out.println("竞选者名字是："+candidate.getName());
                    System.out.println("竞选者自我介绍："+candidate.getDescription());
                    System.out.println("===============================");
                    break;
                default :
                    System.out.println("退出用户菜单");
                    System.exit(0);
            }

        }
    }

}
