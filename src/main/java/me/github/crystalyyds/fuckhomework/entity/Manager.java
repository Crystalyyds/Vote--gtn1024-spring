package me.github.crystalyyds.fuckhomework.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @ClassName: Manager
 * @Description: TODO
 * @author: 名字
 * @date: 2022/9/3  18:16
 */
@Entity
@Table(name = "t_managers")
public class Manager extends BaseEntity {
    private String name;
    private String password;

    public Manager(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Manager() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Manager manager = (Manager) o;
        return Objects.equals(name, manager.name) && Objects.equals(password, manager.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, password);
    }

    @Override
    public String toString() {
        return "Manager{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
