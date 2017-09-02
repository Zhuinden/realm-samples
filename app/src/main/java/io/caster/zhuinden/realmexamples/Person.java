package io.caster.zhuinden.realmexamples;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.Index;
import io.realm.annotations.LinkingObjects;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Person
        extends RealmObject {
    @PrimaryKey
    private String guid;

    @Required
    private String firstName;
    @Required
    private String lastName;

    @Index
    private String fullName;

    private long height;

    private Date birthday;

    private Person spouse;

    private RealmList<Person> parents;

    private RealmList<Cat> cats;

    @LinkingObjects("parents")
    private final RealmResults<Person> childOf = null;

    @LinkingObjects("spouse")
    private final RealmResults<Person> spouseOf = null;

    public Person() {
        this.setBirthday(new Date());
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        setFullName();
    }

    private void setFullName() {
        this.fullName = this.firstName + " " + this.lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        setFullName();
    }

    public String getFullName() {
        return fullName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Person getSpouse() {
        return spouse;
    }

    public void setSpouse(Person spouse) {
        this.spouse = spouse;
    }

    public RealmList<Person> getParents() {
        return parents;
    }

    public void setParents(RealmList<Person> parents) {
        this.parents = parents;
    }

    public RealmResults<Person> getChildOf() {
        return childOf;
    }

    public RealmResults<Person> getSpouseOf() {
        return spouseOf;
    }

    public RealmList<Cat> getCats() {
        return cats;
    }

    public void setCats(RealmList<Cat> cats) {
        this.cats = cats;
    }
}
