package org.example;

import java.util.Objects;

public class Tags {
    private int id;
    private String name;

    public Tags(int id , String name) {
        this.id = id;
        this.name = name;
    }

    public Tags()
    {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return getId() + ", "+getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tags tags = (Tags) o;
        return id == tags.id && name.equals(tags.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
