package rikkei.academy.service.user;

import rikkei.academy.config.Config;
import rikkei.academy.model.Role;
import rikkei.academy.model.RoleName;
import rikkei.academy.model.User;
import rikkei.academy.service.role.RoleServiceIMPL;

import java.util.*;

import static rikkei.academy.config.Config.PATH_USER_PRINCIPAL;

public class UserServiceIMPL implements IUserService{
    public static String PATH_USER = "D:\\MD2_CASE_SHOPPE_FOOD\\src\\rikkei\\academy\\database\\user.txt";
    static Config<User> config = new Config<>();
    public static List<User> userList = config.readFile(PATH_USER);

    static {
        if (userList == null || userList.size() == 0) {
            userList = new ArrayList<>();
            Set<Role> roles = new HashSet<>();
            roles.add(new RoleServiceIMPL().findByRoleName(RoleName.ADMIN));
            userList.add(
                    new User(0,
                            "Admin",
                            "admin",
                            "admin",
                            "admin@gmail.com",

                            false,
                            roles
                    )
            );
        }
    }



    @Override
    public List<User> findAll() {
        new Config<User>().writeFile(PATH_USER,userList);
        return userList;
    }

    @Override
    public void save(User user) {
        userList.add(user);
    }

    @Override
    public User findById(int id) {
        for (int i = 0; i < userList.size(); i++) {
            if (id == userList.get(i).getId()) {
                return userList.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        for (int i = 0; i < userList.size(); i++) {
            if (id==userList.get(i).getId()) {
                userList.remove(i);
            }
        }

    }

    @Override
    public void updateData() {
        config.writeFile(PATH_USER, userList);
    }

    @Override
    public void remove(int id) {
        userList.remove(findById(id));
        updateData();
    }

    @Override
    public boolean existedByUserName(String userName) {
        for (int i = 0; i < userList.size(); i++) {
            if (userName.equals(userList.get(i).getUserName())){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existByEmail(String email) {
        for (int i = 0; i < userList.size(); i++) {
            if (email.equals(userList.get(i).getEmail())){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkLogin(String userName, String password) {
        System.out.println(userList);
        for (int i = 0; i < userList.size(); i++) {
            if (userName.equals(userList.get(i).getUserName()) && password.equals(userList.get(i).getPassword())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User findByUserName(String userName) {
        for (int i = 0; i < userList.size(); i++) {
            if (userName.equals(userList.get(i).getUserName())) {
                return userList.get(i);
            }
        }
        return null;
    }

    @Override
    public User getCurrentUser() {
        if (new Config<User>().readFile(PATH_USER_PRINCIPAL)!=null) {
            if(new Config<User>().readFile(PATH_USER_PRINCIPAL).size() != 0 ){
                User user = new Config<User>().readFile(PATH_USER_PRINCIPAL).get(0);
                return user;
            }
        }
        return null;
    }

    @Override
    public void changeRole(int id, Role role) {
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        findById(id).setRoles(roles);
        updateData();
    }

    @Override
    public void changeStatus(int id) {
        User user = findById(id);
        user.setStatus(!user.isStatus());
        updateData();
    }

    @Override
    public void saveCurrentUser(User user) {
        ArrayList<User> tList = new ArrayList<>();
        tList.add(user);
        new Config<User>().writeFile(PATH_USER_PRINCIPAL, tList);
    }

}
