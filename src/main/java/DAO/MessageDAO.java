package DAO;

import Util.ConnectionUtil;
import Model.Message;

import java.sql.*;
import java.util.*;

/* GET localhost:8080/messages : retrieve all messages
 *  GET localhost:8080/messages/{message_id} : retrieve all messages by Id
 *  POST localhost:8080/messages : create a new message
 *  PATCH localhost:8080/messages/{message_id} : update a message
 *  DELETE localhost:8080/messages/{message_id} : delete message
*/


public class MessageDAO {

    //Retrieve all posts by user
    
    public List<Message> getAllMessagesFromUser(int posted_by) {
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();

        try {
            String sql = "SELECT * FROM message WHERE posted_by = ?"; 
            PreparedStatement preparedStatement = connection.prepareStatement(sql); 

            preparedStatement.setInt(1, posted_by);

            ResultSet rs = preparedStatement.executeQuery(); 

            while (rs.next()) {
                Message message = new Message(rs.getInt("message_id"), 
                                rs.getInt("posted_by"), 
                                rs.getString("message_text"), 
                                rs.getLong("time_posted_epoch")); 
                messages.add(message); 
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return messages; 
    }

    //Retrieve all messages

    public List<Message> getAllMessages() {
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();

        try {
            String sql = "SELECT * FROM message";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Message message = new Message(rs.getInt("message_id"),
                                rs.getInt("posted_by"),
                                rs.getString("message_text"),
                                rs.getLong("time_posted_epoch"));
                messages.add(message);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return messages;
    }
    
    //Retrieve all messages by id

    public Message getAllMessagesById(int message_id) {
        Connection connection = ConnectionUtil.getConnection(); 
        
        try {
            String sql = "SELECT * FROM message WHERE message_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql); 

            preparedStatement.setInt(1, message_id);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                Message message = new Message (rs.getInt("message_id"), 
                                rs.getInt("posted_by"),
                                rs.getString("message_text"),
                                rs.getLong("time_posted_epoch")); 
                return message;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage()); 
        }
        return null; 
    }

    //Create a new message

    public Message postMessage(Message message) {
        Connection connection = ConnectionUtil.getConnection(); 

        try {
            String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 

            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());

            preparedStatement.executeUpdate(); 
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                int generated_account_key = (int) rs.getLong(1);
                return new Message(generated_account_key, message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //Partially update a Message

    public Message updateMessageById(int message_id, String message_text) {
        Connection connection = ConnectionUtil.getConnection();

        try {
            String sql = "UPDATE message SET message_text = ? WHERE message_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, message_text);
            preparedStatement.setInt(2, message_id);

            preparedStatement.executeUpdate();
            return getAllMessagesById(message_id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //Delete a message
    public void deleteMessage(int message_id) {
        Connection connection = ConnectionUtil.getConnection();

        try {
            String sql = "DELETE FROM message WHERE message_id = ?"; 
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, message_id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
