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

    public Message getAllMessagesById() {
        return this.messageDAO.getAllMessagesById(0);
    }

    public Message addMessage() {
        Message existingMessage =  this.messageDAO.postMessage(getAllMessagesById());
        if (existingMessage != null) {
            return null; 
        } else {
            return messageDAO.postMessage(existingMessage);
        }
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

    public Message deleteMessage(){
        return this.messageDAO.deleteMessage(0);
    }
}
