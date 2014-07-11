package com.softserveinc.ita.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*@Entity
@Table(name="roles")*/
public class Role implements Serializable {

    /*@Id
    @GeneratedValue*/
    private int id;
    private String ROLE_name;

    /*private Set<User> userCollection;*/
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {return ROLE_name;}

    public void setRoleName(String name) {this.ROLE_name = name;}


     /*  public Set<User> getUserCollection() {return userCollection;}

    public void setUserCollection(Set<User> userCollection) {this.userCollection = userCollection;}*/
}