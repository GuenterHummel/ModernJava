package com.gh.tutorial.java.security;

import java.io.Serializable;
import java.security.Principal;
import java.util.Objects;

public class GhPrincipal implements Principal, Serializable {
    private static final long serialVersionUID = 1L;
    private String name;

    public GhPrincipal (String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof GhPrincipal that)) return false;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }
}
