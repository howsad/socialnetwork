package ru.sbt.socialnetwork.accountclient;

import ru.sbt.socialnetwork.accountdomain.Account;
import ru.sbt.socialnetwork.server.Server;
import ru.sbt.socialnetwork.serverlist.ServerList;

/**
 * Created by Alexander Ushakov on 15.08.2016.
 */
public class AccountClient {
    private boolean loggedIn;
    private Account account;
    private Server server;

    public AccountClient(Server server) {
        this.server = server;
    }

    public void login(int id, String password) {
        Account account = server.getAccount(id);
        if (auth(account, password)) {
            this.account = account;
            loggedIn = true;
            refresh();
        } else {
            System.out.println("Wrong password!");
        }
    }

    private void refresh() {
        if (loggedIn) {
            System.out.println("Hello, " + account + "!");
        } else {
            System.out.printf("Hello, guest!");
        }
    }

    public void logout() {
        this.account = null;
        loggedIn = false;
        refresh();
    }

    public void newAccount(String firstName, String lastName, String password) {
        Account account = new Account(firstName, lastName, password);
        server.setAccount(account);
    }

    private boolean auth(Account account, String password) {
        if (account.getPassword() == password) {
            return true;
        } else {
            return false;
        }
    }
}
