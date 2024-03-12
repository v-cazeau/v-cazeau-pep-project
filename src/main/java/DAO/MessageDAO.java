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
            String sql = "INSERT INTO message (message_id, posted_by, message_text, time_posted_epoch) VALUE (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql); 

            preparedStatement.setInt(1, message.getMessage_id());
            preparedStatement.setInt(2, message.getPosted_by());
            preparedStatement.setString(3, message.getMessage_text());
            preparedStatement.setLong(4, message.getTime_posted_epoch());

            preparedStatement.executeUpdate(); 
            return message;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //Partially update a Message *** 

    //Delete a message
    public Message deleteMessage(Message message) {
        Connection connection = ConnectionUtil.getConnection();

        try {
            String sql = "DELETE FROM message WHERE message_id = ?"; 
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, message.getMessage_id());
            preparedStatement.executeQuery();
            return message;
        } catch (SQLException e) {
            System.out.println(e.deleteMessage(message)); //not sure what's going on here.
        }
    }
}
