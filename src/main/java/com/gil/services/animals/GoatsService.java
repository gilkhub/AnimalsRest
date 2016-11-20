package com.gil.services.animals;

import com.gil.entities.AbsAnimal;
import com.gil.entities.Goat;
import com.gil.providers.AnimalsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;


@Service
@Path("/animals/goat")
/**
 * A service class for all Goat related endpoints
 */
public class GoatsService {

    @Autowired  //Dependency injection for animalsProvider bean
    private AnimalsProvider animalsProvider;

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Goat getGoatByName(@PathParam("name") String name) {
        return animalsProvider.getGoat(name);
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<AbsAnimal> createGoat(Goat goat) {
        animalsProvider.createGoat(goat);

        return animalsProvider.getAllAnimals();
    }

    @PUT
    @Path("/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<AbsAnimal> updateGoat(@PathParam("name") String name, Goat goat) {
        animalsProvider.updateGoat(name, goat);

        return animalsProvider.getAllAnimals();
    }
}
