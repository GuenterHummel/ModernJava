package com.gh.tutorial.java.security;

import com.google.j2objc.annotations.OnDealloc;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.PrivilegedAction;
import java.util.concurrent.Callable;

public class Driver {
    public enum Action {action1, action2, logout};

    public static void main(String[] args)  {
        Driver driver = new Driver();
        System.setProperty("java.security.auth.login.config", "jaastutorial.config");
        LoginContext loginContext = null;

        while (true) {
            try {
                loginContext = new LoginContext("GhJaasTutorial", new GhCallbackHandler());
                loginContext.login();
                boolean flag = true;
                while (flag) {
                    flag = driver.performAction(loginContext);
                }
            }
            catch (LoginException | IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    boolean performAction(LoginContext loginContext) throws IOException, LoginException {
        boolean flag = true;
        System.out.println("Please specify acton to take (usage: action1, action2, logout)");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            switch (Action.valueOf((br.readLine()))) {
                case action1:
                    Callable<Object> privilegedAction1 = () -> {
                        System.out.println("action1 was performed");
                        return null;
                    };
                    Subject.callAs(loginContext.getSubject(), privilegedAction1);
                    System.out.println(" by " + loginContext.getSubject().getPrincipals().iterator().next().getName());
                    break;
                case action2:
                    Callable<Object> privilegedAction2 = () -> {
                        System.out.println("action2 was performed");
                        return null;
                    };
                    Subject.callAs(loginContext.getSubject(), privilegedAction2);
                    System.out.println(" by " + loginContext.getSubject().getPrincipals().iterator().next().getName());
                    break;
                case logout:
                    loginContext.logout();
                    System.out.println("You are now logged out ...");
                    flag = false;
                    break;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("invalid action specified...");
        }
        return flag;
    }
}
