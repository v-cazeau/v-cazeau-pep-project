package  Service;

import Model.Message;
import DAO.MessageDAO;

import java.util.*;


public class MessageService {

    public MessageDAO messageDAO;

    public MessageService() {
        messageDAO = new MessageDAO();
    }
    
    public List<Message> getAllMessages() {
        return this.messageDAO.getAllMessages();
    }

    public Message getAllMessagesById(int message_id) {
        return this.messageDAO.getAllMessagesById(message_id);

    }

    public Message addMessage(Message message) {
        // Message existingMessage =  this.messageDAO.postMessage(message);
        if (message.message_text == null) {
            return null; 
        } 
        if (message.message_text == "" || message.getMessage_text().length() > 255) {
            return null; 
        }
        return this.messageDAO.postMessage(message);
    }

    public Message updateMessage(int message_id, Message message) {
        Message updated_message = this.messageDAO.getAllMessagesById(message_id);
        if (updated_message != null) {
            this.messageDAO.patchMessage(message_id, message);
            return updated_message;
        } else {
            return null;
        }
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
