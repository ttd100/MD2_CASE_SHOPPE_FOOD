package rikkei.academy.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class User implements Serializable {
    private int id;
    private String name;
    private String userName;
    private String email;
    private String password;
    private String address;
    private String phoneNumber;
    private String avatar;
    private boolean status;
    private Set<Role> roles = new HashSet<>();


    public User(int id, String name, String userName, String email, String password, String address, String phoneNumber, String avatar, boolean status, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
        this.status = status;
        this.roles = roles;
    }
    public User(int id, String name, String username, String password, String email, boolean status, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.userName = username;
        this.password = password;
        this.email = email;
        this.status = status;
        this.roles = roles;
    }

    public User(int id, String name, String username, String email, String password, String address, String phoneNumber, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.userName = username;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.avatar = "";
        this.roles = roles;
    }

    public User(String newName, String newUsername, String newEmail, String newPassword, String newAddress, String newPhone) {
        this.name = newName;
        this.userName = newUsername;
        this.email = newEmail;
        this.password = newPassword;
        this.address = newAddress;
        this.phoneNumber = newPhone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Set<Role> getRoles() {
        return roles;
    }
    public RoleName getRoleName() {
        for (Role role : roles) {
            if (role.getName() == RoleName.ADMIN) return RoleName.ADMIN;
            if (role.getName() == RoleName.USER) return RoleName.USER;
            if (role.getName() == RoleName.DRIVER) return RoleName.DRIVER;
            if (role.getName() == RoleName.SHOP)return RoleName.SHOP;
        }
        return null;
    }


    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", avatar='" + avatar + '\'' +
                ", status=" + status +
                ", roles=" + roles +
                '}';
    }
}
