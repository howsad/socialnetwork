package ru.sbt.socialnetwork.accountclient;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.sbt.socialnetwork.accountdomain.Account;
import ru.sbt.socialnetwork.server.Server;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Alexander Ushakov on 16.08.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class AccountClientTest {
    @Mock
    Server server;
    @Mock
    Account account;


    AccountClient client;

    @Before
    public void setUp() {
        when(account.getPassword()).thenReturn("secret");
        when(server.getAccount(anyInt())).thenReturn(account);
        client = new AccountClient(server);
    }

    @Test
    public void login() {
        client.login(5, "secret");
    }

    @Test
    public void logout() {
        client.logout();
    }

    @Test
    public void newAccount() {
        client.newAccount("blah", "blah", "blah");
        verify(server).setAccount(any());
    }

}