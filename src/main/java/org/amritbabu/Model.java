package org.amritbabu;

import org.apache.solr.client.solrj.beans.Field;

import java.util.List;

public class Model {

    @Field("first_name")
    public List<String> first_name;

    @Field("last_name")
    public List<String> lastName;

    @Field("genere")
    public List<String> generes;

    @Field("language")
    public List<String> languages;

    public Model() {
    }

    public Model(List<String> firstName, List<String> lastName, List<String> generes, List<String> languages) {
        this.first_name = firstName;
        this.lastName = lastName;
        this.generes = generes;
        this.languages = languages;
    }

    public List<String> getFirstName() {
        return first_name;
    }

    public void setFirstName(List<String> firstName) {
        this.first_name = firstName;
    }

    public List<String> getLastName() {
        return lastName;
    }

    public void setLastName(List<String> lastName) {
        this.lastName = lastName;
    }

    public List<String> getGeneres() {
        return generes;
    }

    public void setGeneres(List<String> generes) {
        this.generes = generes;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    @Override
    public String toString() {
        return "Model{" +
                "firstName='" + first_name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", generes=" + generes +
                ", languages=" + languages +
                '}';
    }
}
