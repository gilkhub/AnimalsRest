package com.gil.entities;

/**
 * Class that serves as interface and holds all mutual implementations for an Animal (Dog / Goat)
 */
public abstract class AbsAnimal {

    //Members
    protected String name;
    protected String breed;
    protected int age;
    protected String ownerName;
    protected String photoLink;

    /**
     * Default empty constructor
     */
    AbsAnimal() {}

    AbsAnimal(String name_, String breed_, int age_, String ownerName_, String photoLink_) {
        this.name = name_;
        this.breed = breed_;
        this.age = age_;
        this.ownerName = ownerName_;
        this.photoLink = photoLink_;
    }

    //Getters / Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    @Override
    public String toString() {
        return String.format("[%1$s %2$s %3$s %4$s %5$s]",
                this.getClass().getSimpleName(), this.name, this.breed, this.age, this.ownerName, this.photoLink);
    }
}
