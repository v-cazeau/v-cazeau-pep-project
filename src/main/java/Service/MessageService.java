package  Service;

import Model.Message;
import DAO.MessageDAO;

import java.util.*;


public class MessageService {

    public MessageDAO messageDAO;

    public MessageService() {
        messageDAO = new MessageDAO();
    }
    
    public List<Message> getAllMessagesFromUser(int posted_by) {
        return this.messageDAO.getAllMessagesFromUser(posted_by);
    }

    public List<Message> getAllMessages() {
        return this.messageDAO.getAllMessages();
    }

    public Message getAllMessagesById(int message_id) {
        return this.messageDAO.getAllMessagesById(message_id);

    }

    public Message addMessage(Message message) {
        if (message.message_text == null) {
            return null; 
        } 
        if (message.message_text == "" || message.getMessage_text().length() > 255) {
            return null; 
        }
        return this.messageDAO.postMessage(message);
    }

    public Message updateMessage(int message_id, String message_text) {
        Message updated_message = this.messageDAO.getAllMessagesById(message_id);
        if (updated_message == null || message_text.isBlank() || message_text.length() > 255) {
            return null;
        }
            return this.messageDAO.updateMessageById(message_id, message_text);
    }

    public Message deleteMessage(int message_id) {
        Message message = messageDAO.getAllMessagesById(message_id);
        if (message == null) {
            return null; 
        }
        this.messageDAO.deleteMessage(message_id);
        return message;
    }
}
