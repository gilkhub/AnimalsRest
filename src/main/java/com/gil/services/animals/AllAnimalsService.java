package com.gil.services.animals;

import com.gil.entities.AbsAnimal;
import com.gil.providers.AnimalsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Service
@Path("/animals")
/**
 * A service with an endpoint relevant to all animal services
 */
public class AllAnimalsService {

    @Autowired  //Dependency injection for animalsProvider bean
    private AnimalsProvider animalsProvider;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<AbsAnimal> getAllAnimals() {
        return animalsProvider.getAllAnimals();
    }
}
