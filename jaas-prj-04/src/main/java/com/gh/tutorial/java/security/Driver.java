package com.gh.tutorial.java.security;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

public class Driver {
    public static void main(String[] args) throws LoginException{
        System.setProperty("java.security.auth.login.config", "jaastutorial.config");
        LoginContext loginContext = new LoginContext("GhJaasTutorial", new GhCallbackHandler());
        loginContext.login();
    }
}
