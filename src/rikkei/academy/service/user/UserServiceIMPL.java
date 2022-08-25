package rikkei.academy.service.user;

import rikkei.academy.config.Config;
import rikkei.academy.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserServiceIMPL implements IUserService{
    public static String PATH_USER = "D:\\MD2_CASE_SHOPPE_FOOD\\src\\rikkei\\academy\\database\\user.txt";
    public static List<User> userList = new Config<User>().readFile(PATH_USER);

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
        if (new Config<User>().readFile(Config.PATH_USER_PRINCIPAL)!=null) {
            if(new Config<User>().readFile(Config.PATH_USER_PRINCIPAL).size() != 0 ){
                User user = new Config<User>().readFile(Config.PATH_USER_PRINCIPAL).get(0);
                return user;
            }
        }
        return null;
    }
}
