/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import business.Event;
import business.ToDoItem;
import business.ToDoList;
import business.User;
import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.naming.NamingException;

/**
 *
 * @author turtl
 */
public class DreamTaskerDB {

    public static int insertUser(User user) throws NamingException, SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO users (email, username, password, birthday) "
                + "VALUES (?, ?, ?, ?)";

        ps = connection.prepareStatement(query);
        ps.setString(1, user.getEmail());
        ps.setString(2, user.getUsername());
        ps.setString(3, user.getPassword());
        ps.setDate(4, Date.valueOf(user.getBirthday()));

        int rows = ps.executeUpdate();
        ps.close();
        pool.freeConnection(connection);
        return rows;

    }

    public static User getUser(String username) throws NamingException, SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM users "
                + "WHERE username = ?";

        ps = connection.prepareStatement(query);
        ps.setString(1, username);
        rs = ps.executeQuery();

        if (rs.next()) {
            User user = new User();
            user.setEmail(rs.getString("email"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setBirthday(rs.getDate("birthday").toLocalDate());
            ps.close();
            pool.freeConnection(connection);
            return user;
        } else {
            ps.close();
            pool.freeConnection(connection);
            return null;
        }
    }

    public static int updateUser(User user) throws NamingException, SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "UPDATE users "
                + "SET email = ?, username = ?, password = ?"
                + "WHERE username = ?";

        ps = connection.prepareStatement(query);
        ps.setString(1, user.getEmail());
        ps.setString(2, user.getUsername());
        ps.setString(3, user.getPassword());
        ps.setString(4, user.getUsername());

        int rows = ps.executeUpdate();
        ps.close();
        pool.freeConnection(connection);

        return rows;
    }

    //Delete a user from the database
//    public static int deleteUser(User user) throws NamingException, SQLException {
//        ConnectionPool pool = ConnectionPool.getInstance();
//        Connection connection = pool.getConnection();
//        PreparedStatement ps = null;
//
//        String query = "UPDATE users "
//                + "SET email = ?, username = ?, password = ?"
//                + "WHERE username = ?";
//
//        ps = connection.prepareStatement(query);
//        ps.setString(1, user.getEmail());
//        ps.setString(2, user.getUsername());
//        ps.setString(3, user.getPassword());
//        ps.setString(4, user.getUsername());
//
//        int rows = ps.executeUpdate();
//        ps.close();
//        pool.freeConnection(connection);
//
//        return rows;
//    }
    //Inserts a list into the database
    public static int insertList(ToDoList list, User user) throws NamingException, SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        int user_id = 1;

        //this query gets the user_id from the database
        String query
                = "SELECT user_id FROM users "
                + "WHERE username = ?";

        //the query is executed and the user_id is stored in a variable
        ps = connection.prepareStatement(query);
        ps.setString(1, user.getUsername());
        rs = ps.executeQuery();

        while (rs.next()) {
            user_id = rs.getInt("user_id");
        }

        ps.close();
        rs.close();

        //this query inserts the list, using the user_id as a foreign key
        String query2
                = "INSERT INTO to_do_lists (user_id, name) "
                + "VALUES (?, ?)";

        ps2 = connection.prepareStatement(query2);
        ps2.setInt(1, user_id);
        ps2.setString(2, list.getName());

        int rows = ps2.executeUpdate();
        ps2.close();
        pool.freeConnection(connection);
        return rows;

    }

    //this gets a single list from an individual
    //this should work but not tested yet
    public static ToDoList getList(User user, String name) throws NamingException, SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        int user_id = 1;
        ArrayList<ToDoItem> items = null;

        //this query gets the user_id from the database
        String query
                = "SELECT user_id FROM users "
                + "WHERE username = ?";

        //the query is executed and the user_id is stored in a variable
        ps = connection.prepareStatement(query);
        ps.setString(1, user.getUsername());
        rs = ps.executeQuery();

        while (rs.next()) {
            user_id = rs.getInt("user_id");
        }

        ps.close();
        rs.close();

        //this query gets the list
        String query2
                = "SELECT * FROM to_do_lists "
                + "WHERE user_id = ? AND name = ?";

        ps2 = connection.prepareStatement(query2);
        ps2.setInt(1, user_id);
        ps2.setString(2, name);
        rs2 = ps2.executeQuery();

        if (rs2.next()) {
            ToDoList list = new ToDoList();
            list.setListID(rs2.getInt("list_id"));
            list.setName(rs2.getString("name"));
            items = getListsItems(list);
            list.setItems(items);
            LocalDate completedAt = rs2.getDate("completed_at") == null ? null : rs2.getDate("completed_at").toLocalDate();
            list.setCompletedAt(completedAt);
            ps2.close();
            rs2.close();
            pool.freeConnection(connection);
            return list;
        } else {
            ps2.close();
            rs2.close();
            pool.freeConnection(connection);
            return null;
        }
    }

//    public static int updateList(ToDoList list, User user) throws NamingException, SQLException {
//        ConnectionPool pool = ConnectionPool.getInstance();
//        Connection connection = pool.getConnection();
//        PreparedStatement ps = null;
//        PreparedStatement ps2 = null;
//        int user_id = 1;
//
//        String query
//                = "SELECT user_id FROM users "
//                + "WHERE username = ?";
//        String query2
//                = "INSERT INTO to_do_lists (user_id, name) "
//                + "VALUES (?, ?)";
//
//        ps2 = connection.prepareStatement(query2);
//        ps2.setInt(1, user_id);
//        ps2.setString(2, list.getName());
//
//        int rows = ps2.executeUpdate();
//        ps2.close();
//        pool.freeConnection(connection);
//        return rows;
//
//    }
    //Returns all of the users lists
    public static ArrayList<ToDoList> getUsersCompleteLists(User user) throws NamingException, SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        int user_id = 1;

        //this query gets the user_id from the database
        String query1
                = "SELECT user_id FROM users "
                + "WHERE username = ?";

        //the query is executed and the user_id is stored in a variable
        ps = connection.prepareStatement(query1);
        ps.setString(1, user.getUsername());
        rs = ps.executeQuery();

        while (rs.next()) {
            user_id = rs.getInt("user_id");
        }

        ps.close();
        rs.close();

        //this query gets all the list from the logged in user using the user_id
        String query2 = "SELECT * FROM to_do_lists "
                + "WHERE user_id = ? "
                + "ORDER BY created_at DESC";

        ps2 = connection.prepareStatement(query2);
        ps2.setInt(1, user_id);
        rs2 = ps2.executeQuery();

        //all of the lists are added into an ArrayList that is returned
        ArrayList<ToDoList> lists = new ArrayList<ToDoList>();
        while (rs2.next()) {
            ToDoList list = new ToDoList();
            list.setListID(rs2.getInt("list_id"));
            list.setName(rs2.getString("name"));
            ArrayList<ToDoItem> items = getListsItems(list);
            list.setItems(items);
            lists.add(list);
        }

        rs2.close();
        ps2.close();
        pool.freeConnection(connection);

        return lists;

    }

    //Inserts an item into a list
    public static int insertItem(ToDoItem item, ToDoList list, User user) throws NamingException, SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        int user_id = 1;
        int list_id = 1;

        //this query gets the user_id from the database
        String query
                = "SELECT user_id FROM users "
                + "WHERE username = ?";

        //the query is executed and the user_id is stored in a variable
        ps = connection.prepareStatement(query);
        ps.setString(1, user.getUsername());
        rs = ps.executeQuery();

        while (rs.next()) {
            user_id = rs.getInt("user_id");
        }

        ps.close();
        rs.close();

        //this query gets the list_id from the database
        String query2
                = "SELECT list_id FROM to_do_lists "
                + "WHERE name = ? AND user_id = ?";
        ps2 = connection.prepareStatement(query2);
        ps2.setString(1, list.getName());
        ps2.setInt(2, user_id);
        rs2 = ps2.executeQuery();

        while (rs2.next()) {
            list_id = rs2.getInt("list_id");
        }

        ps2.close();
        rs2.close();

        //this query inserts the list item into the database
        String query3
                = "INSERT INTO list_items (list_id, name, description) "
                + "VALUES (?, ?, ?)";

        ps3 = connection.prepareStatement(query3);
        ps3.setInt(1, list_id);
        ps3.setString(2, item.getName());
        ps3.setString(3, item.getDescription());

        int rows = ps3.executeUpdate();
        ps3.close();
        pool.freeConnection(connection);
        return rows;

    }

    //updates an item, such as marking complete
    public static int updateItem(ToDoList list, String itemName, String itemDescription, Boolean complete) throws NamingException, SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "UPDATE list_items "
                + "SET name = ?, description = ?, complete = ? "
                + "WHERE list_id = ? AND name = ?";

        ps = connection.prepareStatement(query);
        ps.setString(1, itemName);
        ps.setString(2, itemDescription);
        ps.setBoolean(3, complete);
        ps.setInt(4, list.getListID());
        ps.setString(5, itemName);

        int rows = ps.executeUpdate();
        ps.close();
        pool.freeConnection(connection);
        return rows;
    }

    //Gets the items in a list
    public static ArrayList<ToDoItem> getListsItems(ToDoList list) throws NamingException, SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        //this query gets all the items from a specific list
        String query = "SELECT * FROM list_items "
                + "WHERE list_id = ? ";

        ps = connection.prepareStatement(query);
        ps.setInt(1, list.getListID());

        rs = ps.executeQuery();

        //all of the items are placed in an ArrayList that is returned
        ArrayList<ToDoItem> items = new ArrayList<ToDoItem>();
        while (rs.next()) {
            ToDoItem item = new ToDoItem();
            item.setName(rs.getString("name"));
            item.setDescription(rs.getString("description"));
            boolean complete = rs.getInt("complete") == 1 ? true : false;
            item.setComplete(complete);
            items.add(item);
        }

        rs.close();
        ps.close();
        pool.freeConnection(connection);

        return items;
    }

    public static int deleteList(ToDoList list) throws NamingException, SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        int rows = 0;

        String query = "DELETE FROM list_items "
                + "WHERE list_id = ? ";

        ps = connection.prepareStatement(query);
        ps.setInt(1, list.getListID());
        rows += ps.executeUpdate();
        ps.close();

        String query2 = "DELETE FROM to_do_lists "
                + "WHERE list_id = ? ";

        ps2 = connection.prepareStatement(query2);
        ps2.setInt(1, list.getListID());
        rows += ps2.executeUpdate();
        ps2.close();

        connection.close();

        return rows;
    }

    public static int insertEvent(Event event, User user) throws NamingException, SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        int user_id = 1;

        //this query gets the user_id from the database
        String query
                = "SELECT user_id FROM users "
                + "WHERE username = ?";

        //the query is executed and the user_id is stored in a variable
        ps = connection.prepareStatement(query);
        ps.setString(1, user.getUsername());
        rs = ps.executeQuery();

        while (rs.next()) {
            user_id = rs.getInt("user_id");
        }

        ps.close();
        rs.close();

        String query2
                = "INSERT INTO events (user_id, name, description, start_day, end_day, start_time, end_time, all_day, recurring) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        ps2 = connection.prepareStatement(query2, PreparedStatement.RETURN_GENERATED_KEYS);
        ps2.setInt(1, user_id);
        ps2.setString(2, event.getName());
        ps2.setString(3, event.getDescription() != null ? event.getDescription() : null);
        ps2.setDate(4, Date.valueOf(event.getStartDay()));
        ps2.setDate(5, Date.valueOf(event.getEndDay()));
        ps2.setTimestamp(6, event.getStartTime() != null ? Timestamp.valueOf(event.getStartTime()) : null);
        ps2.setTimestamp(7, event.getEndTime() != null ? Timestamp.valueOf(event.getEndTime()) : null);
        ps2.setBoolean(8, event.isAllDay());
        ps2.setBoolean(9, event.isRecurring());
        int rows = ps2.executeUpdate();

        rs2 = ps2.getGeneratedKeys();
        int eventId = -1;
        while (rs2.next()) {
            eventId = rs2.getInt(1);
        }

        rs2.close();
        ps2.close();
        pool.freeConnection(connection);
        return eventId;
    }

    //Gets the events from a user
    public static ArrayList<Event> getEvents(User user) throws NamingException, SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        PreparedStatement ps2 = null;
        ResultSet rs2 = null;
        int user_id = 1;

        //this query gets the user_id from the database
        String query
                = "SELECT user_id FROM users "
                + "WHERE username = ?";

        //the query is executed and the user_id is stored in a variable
        ps = connection.prepareStatement(query);
        ps.setString(1, user.getUsername());
        rs = ps.executeQuery();

        while (rs.next()) {
            user_id = rs.getInt("user_id");
        }

        ps.close();
        rs.close();

        String query2 = "SELECT * FROM events "
                + "WHERE user_id = ?";

        ps2 = connection.prepareStatement(query2);
        ps2.setInt(1, user_id);
        rs2 = ps2.executeQuery();

        ArrayList<Event> events = new ArrayList<>();
        while (rs2.next()) {
            Event event = new Event();
            event.setId(rs2.getInt("event_id"));
            event.setName(rs2.getString("name"));
            event.setStartDay(rs2.getDate("start_day").toLocalDate());
            event.setEndDay(rs2.getDate("end_day").toLocalDate());
            event.setAllDay(rs2.getBoolean("all_day"));
            event.setRecurring(rs2.getBoolean("recurring"));
            events.add(event);
        }

        ps2.close();
        rs2.close();
        pool.freeConnection(connection);
        
        return events;
    }

    public static boolean isValueTaken(String fieldname, String value) throws NamingException, SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query;

        switch (fieldname) {
            case "email":
                query = "SELECT * FROM users "
                        + "WHERE email = ?";
                break;
            case "username":
                query = "SELECT * FROM users "
                        + "WHERE username = ?";
                break;
            case "listname":
                query = "SELECT * FROM to_do_list "
                        + "WHERE name = ?";
                break;
            case "itemname": {
                query = "SELECT * FROM list_items "
                        + "WHERE name = ?";
                break;
            }
            default:
                return false;
        }

        ps = connection.prepareStatement(query);
        ps.setString(1, value);
        rs = ps.executeQuery();

        if (rs.next()) {
            ps.close();
            pool.freeConnection(connection);
            return true;
        }
        ps.close();
        pool.freeConnection(connection);
        return false;
    }

}
