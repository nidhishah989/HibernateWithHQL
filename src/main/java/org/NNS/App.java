package org.NNS;

import org.NNS.DAO.UserDAO;

import org.NNS.DAO.UserDAOImpl;
import org.NNS.entity.User;
import org.NNS.util.GenerateTables;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        UserDAO userdao = new UserDAOImpl();

        System.out.println("Version1: getting all users information");
        for(User user: userdao.getAllUsers()){
            System.out.println(user);
        }
        System.out.println("------------------------------");
        System.out.println("Version2: getting all users information");
        userdao.printAllUsers();

        System.out.println("-------------------------------");
        System.out.println("Version3: get all user's full name and salary");
        userdao.printUserNameAndSalary();

        System.out.println("------------------------------");
        System.out.println("Version1: get user by given id number (2)");
        userdao.getUserByID(2);

        System.out.println("------------------------------");
        System.out.println("Version2: get user whose id number >(2) order by salary desc");
        userdao.getUserByID();


        System.out.println("--------------------------------");
        System.out.println("Version1: get user maximum salary");
        userdao.getmaxSalary();

        System.out.println("--------------------------------");
        System.out.println("Version1: get city and total salary for that city");
        userdao.getmaxSalaryGroupBy();



    }
}
