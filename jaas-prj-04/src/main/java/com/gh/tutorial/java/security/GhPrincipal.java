package com.gh.tutorial.java.security;

import java.io.Serializable;
import java.security.Principal;

public class GhPrincipal implements Principal, Serializable {
    private static final long serialVersionUID = 1L;
    private String name;

    public GhPrincipal (String name){
        this.name = name;
    }

    @Override
    public String getName() {
        System.out.println("GhPrincipal.getName...");
        return name;
    }
}
