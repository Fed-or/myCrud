package model;

import java.util.Objects;

public class User {
    private long id;
    private String name;
    private String password;

    public User(long id, String name, String password) {
        this.name = name;
        this.password = password;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof model.User)) return false;
        model.User user = (model.User) o;
        return id == user.id &&
                name.equals(user.name) &&
                password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
