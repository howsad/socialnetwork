package ru.sbt.socialnetwork.server;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import ru.sbt.socialnetwork.accountdomain.Account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alexander Ushakov on 15.08.2016.
 */
public class Server {
    private final Map<Integer, Account> map = new HashMap<>();
    private final Graph<Account, DefaultEdge> friends = new SimpleGraph<>(DefaultEdge.class);

    public Account getAccount(int id) {
        if (!map.containsKey(id)) {
            throw new IllegalArgumentException("There is no such account.");
        }
        return map.get(id);
    }

    public void setAccount(Account account) {
        if (map.containsKey(account.getId())) {
            throw new IllegalArgumentException("This account exists already.");
        }
        map.put(account.getId(), account);
        friends.addVertex(account);
    }

    public boolean areFriends(Account a, Account b) {
        if (a.equals(b)) {
            throw new IllegalArgumentException("Can't ask whether is friend with self.");
        }
        return friends.containsEdge(a, b);
    }

    public void makeFriends(Account a, Account b) {
        if (a.equals(b)) {
            throw new IllegalArgumentException("Can't make friends with self.");
        } else if (areFriends(a, b)) {
            throw new IllegalArgumentException("Are friends already.");
        }
        friends.addEdge(a, b);
    }

    public void makeNotFriends(Account a, Account b) {
        if (a.equals(b)) {
            throw new IllegalArgumentException("Can't make not friends with self.");
        } else if (!areFriends(a, b)) {
            throw new IllegalArgumentException("Aren't friends yet.");
        }
        friends.removeEdge(a, b);
    }

    public List<Account> getFriends(Account account) {
        List<Account> friendsOfAcc = new ArrayList<>();
        for (DefaultEdge edge : friends.edgesOf(account)) {
            Account friend = friends.getEdgeTarget(edge);
            if (friend.equals(account)) {
                friend = friends.getEdgeSource(edge);
            }
            friendsOfAcc.add(friend);
        }
        return friendsOfAcc;
    }
}
