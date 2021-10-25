package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection conn = Util.getConnection()) {
            Statement s = conn.createStatement();
            s.executeUpdate("CREATE TABLE IF NOT EXISTS users (Id int AUTO_INCREMENT PRIMARY KEY, Name varchar(255), Lastname varchar(255), Age int);");
        } catch (SQLException ignored) {
        }
    }

    public void dropUsersTable() {
        try (Connection conn = Util.getConnection()) {
            Statement s = conn.createStatement();
            s.executeUpdate("DROP TABLE IF EXISTS users;");
        } catch (SQLException ignored) {
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection conn = Util.getConnection()){
            PreparedStatement ps = conn.prepareStatement("INSERT INTO users (Name, Lastname, Age) VALUES (?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
        } catch (SQLException ignored) {
        }
    }

    public void removeUserById(long id) {
        try (Connection conn = Util.getConnection()){
            PreparedStatement ps = conn.prepareStatement("DELETE FROM users WHERE Id = ?;");
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ignored) {
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new LinkedList<>();
        try (Connection conn = Util.getConnection()){
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM Users");
            while (rs.next()) {
                list.add(new User(rs.getString(2), rs.getString(3), rs.getByte(4)));
                list.get(list.size() - 1).setId(rs.getLong(1));
            }
        } catch (SQLException ignored) {
        }
        return list;
    }

    public void cleanUsersTable() {
        try (Connection conn = Util.getConnection()){
            Statement s = conn.createStatement();
            s.executeUpdate("TRUNCATE TABLE users;");
        } catch (SQLException ignored) {
        }
    }
}
