package ru.sbt.socialnetwork.accountdomain;

import java.util.*;

/**
 * Created by Alexander Ushakov on 15.08.2016.
 */
public class Account {
    private final int id;
    private static int idCount = 0;
    private final long creationTime;
    private String firstName;
    private String lastName;
    private String password;
    private Date dateOfBirth;
    private final Map<Account, FriendRequest> sentRequests;
    private final Map<Account, FriendRequest> receivedRequests;

    public Account(String firstName, String lastName, String password) {
        this.id = idCount++;
        this.creationTime = System.currentTimeMillis();
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.sentRequests = new HashMap<>();
        this.receivedRequests = new HashMap<>();
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Account && this.id == ((Account)obj).id) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public int getId() {
        return id;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void sendFriendRequest(FriendRequest request) {
        sentRequests.put(request.getTo(), request);
    }

    public void receiveFriendRequest(FriendRequest request) {
        receivedRequests.put(request.getFrom(), request);
    }

    public void deleteSentRequest(Account to) {
        sentRequests.remove(to);
    }

    public void deleteReceivedRequest(Account from) {
        receivedRequests.remove(from);
    }

}
