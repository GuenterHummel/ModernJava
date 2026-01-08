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
    public static final String TEST_USERNAME = "ghuser";
    public static final String TEST_PASSWORD = "password";
    private CallbackHandler callbackHandler = null;
    private boolean authenticationSuccessFlag = false;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.callbackHandler = callbackHandler;
    }

    @Override
    public boolean login() throws LoginException {
        System.out.println("login ...");

        Callback[] callbackArray = new Callback[2];
        callbackArray[0] = new NameCallback("User Name:");
        callbackArray[1] = new PasswordCallback("Password:", false);

        try {
            callbackHandler.handle(callbackArray);
        } catch (UnsupportedCallbackException | IOException e) {
            e.printStackTrace();
        }

        String name = ((NameCallback) callbackArray[0]).getName();
        String password = new String(((PasswordCallback) callbackArray[1]).getPassword());

        if (name.equals(TEST_USERNAME) && password.equals(TEST_PASSWORD))
        {
            System.out.println("authentication success ...");
            authenticationSuccessFlag = true;
        }
        else {
            System.out.println("authentication failed ...");
            throw new FailedLoginException("authentication failure ...");
        }

        return authenticationSuccessFlag;
    }

    @Override
    public boolean commit() throws LoginException {
        System.out.println("commit ...");
        return authenticationSuccessFlag;
    }

    @Override
    public boolean abort() throws LoginException {
        System.out.println("abort ...");
        return false;
    }

    @Override
    public boolean logout() throws LoginException {
        return false;
    }
}
