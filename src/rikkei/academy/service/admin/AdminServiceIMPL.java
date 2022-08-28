package rikkei.academy.service.admin;

import rikkei.academy.config.Config;
import rikkei.academy.model.Admin;

import java.util.List;

public class AdminServiceIMPL implements IAdminService {
    public static final String PATH_ADMIN = "D:\\MD2_CASE_SHOPPE_FOOD\\src\\rikkei\\academy\\database\\admin.txt";
    public static List<Admin> adminList = new Config<Admin>().readFile(PATH_ADMIN);

    @Override
    public List<Admin> findAll() {
        new Config<Admin>().writeFile(PATH_ADMIN, adminList);
        return adminList;
    }

    @Override
    public void save(Admin admin) {
        adminList.add(admin);
    }

    @Override
    public Admin findById(int id) {
        for (int i = 0; i < adminList.size(); i++) {
            if (id == adminList.get(i).getId()) {
                return adminList.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        for (int i = 0; i < adminList.size(); i++) {
            if (id == adminList.get(i).getId()) {
                adminList.remove(i);
            }
        }
    }

    @Override
    public void updateData() {

    }

    @Override
    public void remove(int id) {

    }
}
