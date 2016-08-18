package ru.sbt.socialnetwork.accountdomain;

import ru.sbt.socialnetwork.accountdomain.Account;

/**
 * Created by Alexander Ushakov on 16.08.2016.
 */
public class FriendRequest {
    private Account from;
    private Account to;
    private String message;

    public FriendRequest(Account from, Account to, String message) {
        this.from = from;
        this.to = to;
        this.message = message;
    }

    public Account getFrom() {
        return from;
    }

    public Account getTo() {
        return to;
    }

    public String getMessage() {
        return message;
    }

    public void send() {
        from.sendFriendRequest(this);
        to.receiveFriendRequest(this);
    }

    public void delete() {
        from.deleteSentRequest(this.to);
        to.deleteReceivedRequest(this.from);
    }
}
