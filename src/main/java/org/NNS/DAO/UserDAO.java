package org.NNS.DAO;

import org.NNS.entity.User;

import java.util.List;

public interface UserDAO {
    void saveUser(User user);
    List<User> getAllUsers();

    void printAllUsers();
    User getUserByID(int userid);
    void getUserByID();

    void  getmaxSalary();
    void getmaxSalaryGroupBy();
    void printUserNameAndSalary();
    void updateUserRecord(User user);

    void deleteUserRecord(int userid);
}
