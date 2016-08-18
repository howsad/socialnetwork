package ru.sbt.socialnetwork.server;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.sbt.socialnetwork.accountdomain.Account;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Alexander Ushakov on 17.08.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class ServerTest {
    @Mock
    Account alice;
    @Mock
    Account bob;
    @Mock
    Account chris;
    
    Server server = new Server();

    @Before
    public void setUp() {
        when(alice.getId()).thenReturn(46);
        server.setAccount(alice);
        when(bob.getId()).thenReturn(75);
        server.setAccount(bob);
        server.makeFriends(alice, bob);
        when(chris.getId()).thenReturn(18);
        server.setAccount(chris);
        server.makeFriends(alice, chris);
    }

    @Test
    public void getAccount() {
        assertEquals(alice, server.getAccount(46));
    }

    @Test (expected = IllegalArgumentException.class)
    public void getNonExistingAccount() {
        server.getAccount(33);
    }

    @Test (expected = IllegalArgumentException.class)
    public void setSameAccountTwice() {
        server.setAccount(alice);
    }

    public void areFriends(Account a, Account b, boolean expected) {
        assertEquals(expected, server.areFriends(a, b));
    }

    @Test (expected = IllegalArgumentException.class)
    public void selfFriend() {
        areFriends(alice, alice, false);
    }

    @Test
    public void arePresetFriends() {
        areFriends(alice, bob, true);
        areFriends(bob, alice, true);
        areFriends(bob, chris, false);
        areFriends(chris, bob, false);
        areFriends(chris, alice, true);
    }

    @Test
    public void makeFriends() {
        server.makeFriends(bob, chris);
        areFriends(bob, chris, true);
        areFriends(chris, bob, true);
    }

    @Test
    public void makeNotFriends() {
        server.makeNotFriends(alice, bob);
        areFriends(alice, bob, false);
        areFriends(bob, alice, false);
    }

    public void compareWithSetOfFriends(Account account, Account... query) {
        Set<Account> friends = new HashSet<>(server.getFriends(account));
        Set<Account> querySet = new HashSet<Account>(Arrays.asList(query));
        assertEquals(true, friends.equals(querySet));
    }

    @Test
    public void getFriends() {
        compareWithSetOfFriends(alice, chris, bob);
        compareWithSetOfFriends(bob, alice);
    }

}