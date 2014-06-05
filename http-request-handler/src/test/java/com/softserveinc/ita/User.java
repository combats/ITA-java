package com.softserveinc.ita;

public class User {

    private String id;
    private String name;
    private String lastname;

    public User(String id, String name, String lastname) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
    }




    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!id.equals(user.id)) return false;
        if (!lastname.equals(user.lastname)) return false;
        if (!name.equals(user.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + lastname.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "com.khoroshevskiy.service.com.softserveinc.ita.User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}

