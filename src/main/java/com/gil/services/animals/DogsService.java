package com.gil.services.animals;


import com.gil.entities.AbsAnimal;
import com.gil.entities.Dog;
import com.gil.providers.AnimalsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Service
@Path("/animals/dog")
/**
 * A service class for all Dog related endpoints
 */
public class DogsService {

    @Autowired  //Dependency injection for animalsProvider bean
    private AnimalsProvider animalsProvider;

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Dog getDogByName(@PathParam("name") String name) {
        return animalsProvider.getDog(name);
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<AbsAnimal> createDog(Dog dog) {
        animalsProvider.createDog(dog);
        return animalsProvider.getAllAnimals();
    }

    @PUT
    @Path("/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<AbsAnimal> updateDog(@PathParam("name") String name, Dog dog) {
        animalsProvider.updateDog(name, dog);
        return animalsProvider.getAllAnimals();
    }

    @POST
    @Path("/del/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<AbsAnimal> deleteDog(@PathParam("name") String name) {
        animalsProvider.deleteDog(name);
        return animalsProvider.getAllAnimals();
    }
}
