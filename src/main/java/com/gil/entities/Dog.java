package com.gil.entities;

/**
 * A class for representing a Dog object
 */
public class Dog extends AbsAnimal {

    public Dog() {
        super();
    }

    public Dog(String name_, String breed_, int age_, String ownerName_, String photoLink_) {
        super(name_, breed_, age_, ownerName_, photoLink_);
    }
}
