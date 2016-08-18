package ru.sbt.socialnetwork.friendslist;

import ru.sbt.socialnetwork.accountdomain.Account;
import ru.sbt.socialnetwork.accountdomain.FriendRequest;
import ru.sbt.socialnetwork.server.Server;
import ru.sbt.socialnetwork.serverlist.ServerList;

/**
 * Created by Alexander Ushakov on 16.08.2016.
 */
public class FriendsList {
    private static Server server;

    public FriendsList(Server server) {
        this.server = server;
    }

    public static Account find(String firstName, String lastName){
        return new Account(firstName, lastName, "password");
    }

    public static void sendFriendRequest(Account from, Account to, String message) {
        FriendRequest request = new FriendRequest(from, to, message);
        request.send();
    }

    public static void cancelFriendRequest(FriendRequest request) {
        request.delete();
    }

    public static void acceptFriendRequest(FriendRequest request) {
        server.makeFriends(request.getFrom(), request.getTo());
        request.delete();
    }

    public static void deleteFromFriends(Account from, Account to) {
        server.makeNotFriends(from, to);
    }
}
