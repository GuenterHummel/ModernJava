package com.gh.tutorial.java.security;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.util.Map;

public class GhLoginModule implements LoginModule {
    public static final String[][] TEST_USERS = {{"user1", "password1"}, {"user2", "password2"}, {"user3", "password3"}};

    private Subject subject = null;
    private CallbackHandler callbackHandler = null;
    private GhPrincipal principal = null;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
    }

    @Override
    public boolean login() throws LoginException {
        boolean flag = false;

        Callback[] callbackArray = new Callback[2];
        callbackArray[0] = new NameCallback("User Name:");
        callbackArray[1] = new PasswordCallback("Password:", false);
        try {
            callbackHandler.handle(callbackArray);
            String name = ((NameCallback) callbackArray[0]).getName();
            String password = new String(((PasswordCallback) callbackArray[1]).getPassword());

            int i = 0;
            while (i < TEST_USERS.length) {
                if (TEST_USERS[i][0].equals(name) && TEST_USERS[i][1].equals(password)) {
                    principal = new GhPrincipal(name);
                    System.out.println("authentication success...");
                    flag = true;
                    break;
                }
                i++;
            }
            if (flag == false) throw new FailedLoginException(("authentication failure..."));
        } catch (IOException | UnsupportedCallbackException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return flag;
    }

    @Override
    public boolean commit() throws LoginException {
        boolean flag = false;
        if (subject != null && !(subject.getPrincipals().contains(principal))) {
            subject.getPrincipals().add(principal);
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean abort() throws LoginException {
        if (subject != null && principal != null && subject.getPrincipals().contains(principal)) {
            subject.getPrincipals().remove(principal);
        }
        subject = null;
        principal = null;
        return true;
    }

    @Override
    public boolean logout() throws LoginException {
        subject.getPrincipals().remove(principal);
        subject=null;
        return true;
    }
}
