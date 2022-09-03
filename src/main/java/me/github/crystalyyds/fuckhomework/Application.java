package me.github.crystalyyds.fuckhomework;

import me.github.crystalyyds.fuckhomework.entity.Candidate;
import me.github.crystalyyds.fuckhomework.entity.Manager;
import me.github.crystalyyds.fuckhomework.entity.User;
import me.github.crystalyyds.fuckhomework.service.CandidateService;
import me.github.crystalyyds.fuckhomework.service.ManagerService;
import me.github.crystalyyds.fuckhomework.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import javax.inject.Inject;
import java.util.*;

@SpringBootApplication
public class Application implements CommandLineRunner {
    private static final Scanner sc = new Scanner(System.in);

    private final UserService userService;
    private final CandidateService candidateService;

    private final ManagerService managerService;

    @Inject
    public Application(UserService userService, CandidateService candidateService,ManagerService managerService) {
        this.userService = userService;
        this.candidateService = candidateService;
        this.managerService= managerService;
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
            System.out.println("******        5.管理注册      ******");
            System.out.println("******        6.管理登录      ******");
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
                case 5 :
                    System.out.println("输入管理者名字");
                    String managername = sc.next();
                    System.out.println("输入密码");
                    String managerpassword = sc.next();
                    Manager manager = managerService.newManager(managername,managerpassword);
                    if(manager.getId() != null){
                        System.out.println("注册成功");
                    }else {
                        System.out.println("注册失败");
                    }
                    break;
                case 6 :
                    System.out.println("请输入用户名：");
                    username = sc.next();
                    System.out.println("输入密码");
                    password = sc.next();
                    manager = managerService.login(username,password);
                    if (manager != null) {
                        System.out.println("登录成功！");
                        managermenu(manager);
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

    private void managermenu(Manager manager){
        while (true) {
            System.out.println("**********************************");
            System.out.println("******        1.审核参选对象   ******");
            System.out.println("******        2.选举时间发布   ******");
            System.out.println("******        3.统计投票结果   ******");
            System.out.println("******        0.退出         ******");
            System.out.println("***********************************");
            System.out.println("选择功能");
            int op = sc.nextInt();
            switch (op) {
                case 1 :
                    List<Candidate> list = candidateService.findAll();
                    for (Candidate x :
                            list) {
                        System.out.println(x.toString());
                    }
                    break;
                case 2 :
                    candidateService.findAll().forEach(System.out::println);
                    break;
                case 3 :
                    sticks();
                    break;
                default :
                    System.out.println("管理功能退出");
                    System.exit(0);
            }
        }
    }

    private void sticks(){
        while (true) {
            System.out.println("**********************************");
            System.out.println("******        1.票数        ******");
            System.out.println("******        2.排名        ******");
            System.out.println("******        3.百分比       ******");
            System.out.println("******        0.退出         ******");
            System.out.println("***********************************");
            System.out.println("选择功能");
            List<Candidate> list = candidateService.findAll();
            int op = sc.nextInt();
            switch (op) {
                case 1:
                    int cp = 1;
                    for (Candidate x :
                            list) {
                        int length = x.getUsers().size();
                        System.out.println(cp+"."+length+"票");
                        cp++;
                    }
                    break;
                case 2 :
                    int kp = 1;
                    Map<Integer,Candidate> map = new HashMap<Integer,Candidate>();
                    Set<Integer> set = new HashSet<Integer>();
                    for (Candidate x:
                         list) {
                        Integer length = x.getUsers().size();
                        map.put(length,x);
                        set.add(length);
                    }
                    for (Integer y :
                            set) {
                        System.out.println("第"+kp+"名是"+map.get(y).getName());
                        kp++;
                    }
                    break;
                case 3:
                    double count1 = 0;
                    for (Candidate x :
                            list) {
                        count1 += x.getUsers().size();
                    }
                    for (Candidate x :
                            list) {
                        double percentage  = x.getUsers().size()/count1;
                        System.out.println(x.getName()+"的百分比是"+percentage);
                    }
                    break;
                default :
                    System.out.println("管理的票的系统退出");
                    System.exit(0);
            }
        }
    }


}
