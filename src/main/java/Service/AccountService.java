package Service;

import Model.Account;
import DAO.AccountDAO;

import java.util.*;

public class AccountService {

    private AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    public void AccountDAO(AccountDAO accountDAO) {
        this.accountDAO = accountDAO; 
    }

    public List<Account> getAllMessagesFromUser() {
        return this.accountDAO.getAllMessagesFromUser();
    }
    
    public Account insertAccount(Account account) {
        if (account.username == null || account.username == "") {
            return null; 
        }
        if (account.password == null || account.password.length() < 4 ) {
            return null; 
        }
        return this.accountDAO.postAccount(account);
    }

    public Account validateAccount(Account account) {
        return this.accountDAO.postAccount(account);
    }

    
}
