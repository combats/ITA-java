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
    private Integer id;
    private String name;
    private Collection userCollection;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {return name;}

    public void setRoleName(String name) {this.name = name;}

    public Collection getUserCollection() {return userCollection;}

    public void setUserCollection(Collection userCollection) {this.userCollection = userCollection;}


}