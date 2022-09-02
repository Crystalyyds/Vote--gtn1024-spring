package me.github.crystalyyds.fuckhomework.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "t_candidates")
public class Candidate extends BaseEntity {
    private String name;
    private String description;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<User> users;

    public Candidate() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Candidate candidate = (Candidate) o;
        return Objects.equals(name, candidate.name) && Objects.equals(description, candidate.description) && Objects.equals(users, candidate.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description, users);
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", users=" + users +
                "} " + super.toString();
    }
}
