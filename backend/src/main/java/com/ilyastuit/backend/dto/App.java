package com.ilyastuit.backend.dto;

import java.util.Objects;

public class App {
    private String name;
    private String version;

    public App(String name, String version) {
        this.name = name;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        App app = (App) o;
        return Objects.equals(name, app.name) && Objects.equals(version, app.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, version);
    }
}
