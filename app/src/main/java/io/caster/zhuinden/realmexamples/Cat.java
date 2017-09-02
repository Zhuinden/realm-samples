package io.caster.zhuinden.realmexamples;

import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.Index;
import io.realm.annotations.LinkingObjects;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Cat
        extends RealmObject {
    @PrimaryKey
    private String guid;

    @Index
    @Required
    private String name;

    @LinkingObjects("cats")
    private final RealmResults<Person> catOf = null;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmResults<Person> getCatOf() {
        return catOf;
    }
}
