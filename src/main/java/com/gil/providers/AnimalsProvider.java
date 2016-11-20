package com.gil.providers;

import com.gil.entities.AbsAnimal;
import com.gil.entities.Dog;
import com.gil.entities.Goat;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;


/**
 * The AnimalsProvider class exposes convenience methods for managing the data (animals).
 * A Singleton class to make sure the data is handled in one place only.
 */
@Component  //Component makes sure the provider is a Singleton class
public class AnimalsProvider {

    /**
     * the data (animals) will be cached in this HashSet - won't waste space on duplicates, quick search...
     */
    private HashSet<AbsAnimal> cachedAnimals = new HashSet<AbsAnimal>();

    /**
     * Empty constructor to override default empty constructor (Singleton)
     */
    public AnimalsProvider() {
        //Empty Constructor
    }

    /**
     * @return a collection of all current cached animals
     */
    public Collection<AbsAnimal> getAllAnimals() {
        return cachedAnimals;
    }

    // a
    public boolean createDog(Dog newDog) {
        return cachedAnimals.add(newDog);
    }

    // b
    public boolean createGoat(Goat newGoat) {
        return cachedAnimals.add(newGoat);
    }

    // c
    public Dog getDog(String name) {
        return findCachedDogByName(name);
    }

    // d
    public Goat getGoat(String name) { return findCachedGoatByName(name); }

    // e
    public boolean updateDog(String name, Dog updatedObj) {
        return updateCachedAnimal(name, Dog.class, updatedObj);
    }

    // f
    public boolean updateGoat(String name, Goat updatedObj) {
        return updateCachedAnimal(name, Goat.class, updatedObj);
    }

    // g
    public boolean deleteDog(String name) {
        Dog cachedDog = findCachedDogByName(name);
        if (cachedDog != null) {
            return cachedAnimals.remove(cachedDog);
        }
        return false;
    }

    // h
    public boolean deleteGoat(String name) {
        Goat cachedGoat = findCachedGoatByName(name);
        if (cachedGoat != null) {
            return cachedAnimals.remove(cachedGoat);
        }
        return false;
    }

    /**
     * @param name name of the dog to find in the current cached animals
     * @return the required Dog instance or null if not found
     */
    private Dog findCachedDogByName(String name) {
        return findCachedAnimal(name, Dog.class);
    }

    /**
     * @param name name of the goat to find in the current cached animals
     * @return the required Goat instance or null if not found
     */
    private Goat findCachedGoatByName(String name) { return findCachedAnimal(name, Goat.class); }

    /**
     * @param name name of the required animal to find in cached animals
     * @param type a Class object that defines the real type of the required animal (Dog.class or Goat.class)
     * @param <T> Representation for a class that extends AbsAnimal.class (Dog.class or Goat.class)
     * @return
     */
    private <T extends AbsAnimal> T findCachedAnimal(String name, Class<T> type) {
        for (AbsAnimal animal : cachedAnimals) {
            if (type.isInstance(animal) && animal.getName().equals(name)) {
                return type.cast(animal);
            }
        }
        return null;
    }

    /**
     * Finds the animal by it's name in the cached animals set and updates it.
     *
     * @param name       name of the animal that should be updated
     * @param type       the class type of the animal that should be updated
     * @param updatedObj the updated object of the animal that should replace the object we want to update.
     * @param <T>        a class type that must extend AbsAnimal
     * @return True if update succeeded, False otherwise
     */
    private <T extends AbsAnimal> boolean updateCachedAnimal(String name, Class<T> type, T updatedObj) {
        T cachedObj = findCachedAnimal(name, type);

        if (cachedObj != null) {
            if (cachedAnimals.remove(cachedObj)) {
                return cachedAnimals.add(updatedObj);
            }
        }
        return false;
    }
}
