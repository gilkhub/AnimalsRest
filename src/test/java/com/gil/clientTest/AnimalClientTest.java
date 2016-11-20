package com.gil.clientTest;

import com.gil.App;
import com.gil.entities.Dog;
import com.gil.entities.Goat;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.net.URL;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(App.class)
@WebAppConfiguration
@IntegrationTest
@FixMethodOrder(MethodSorters.JVM)  //Must set the tests order, otherwise "update" before "create" would fail...
public class AnimalClientTest {

    private URL base; //Base URL of our Web Service
    private Client client; // Jersey client for testing our web service
    private Gson gson; //Helps converting Java Objects to JSONobject strings and vice versa

    @Before
    public void setUp() throws Exception {
        int port = 8113; //The required port
        this.base = new URL(String.format("http://localhost:%1$s/service/animals", port)); //the correct web service base URL
        client = Client.create();
        gson = new Gson();
    }

    //test a
    @Test
    public void createDogJoey()
    {
        String testName = "Create Dog Joey";
        Dog newDog = new Dog("Joey", "Labrador", 9, "Bruno",
                "https://en.wikipedia.org/wiki/Labrador_Retriever#/media/File:YellowLabradorLooking_new.jpg");
        String uriAfterBase = "/dog/";
        createAnimalTest(testName, newDog, uriAfterBase);
    }

    //test b
    @Test
    public void createDogSnitch()
    {
        String testName = "Create Dog Snitch";
        Dog newDog = new Dog("Snitch", "Toy Terrier", 4, "Zach",
                "http://petsfans.com/wp-content/uploads/2014/11/sd-1024x576.jpg");
        String uriAfterBase = "/dog/";
        createAnimalTest(testName, newDog, uriAfterBase);
    }

    //test c
    @Test
    public void createGoatIzza()
    {
        String testName = "Create Goat Izza";
        Goat newGoat = new Goat("Izza", "Boer", 5, "Mosh",
                "https://upload.wikimedia.org/wikipedia/commons/9/90/Boerbok.jpg");
        String uriAfterBase = "/goat/";
        createAnimalTest(testName, newGoat, uriAfterBase);
    }

    //test d
    @Test
    public void printAllAnimals()
    {
        String testName = "Print all animals";

        System.out.println("Begin Test: " + testName);

        //Compile a get request to get all current animals
        WebResource webResource = client.resource(base.toString() + "/all");
        ClientResponse response = webResource.type("application/json").get(ClientResponse.class);

        //Assert that we got status 200 / OK
        assert response.getStatus() == 200 :
                testName + ": Response code should be 200 but instead got " + response.getStatus();

        //Get the all animals from the response and print it
        System.out.println("Test: " + testName + ": Response - print all animals:\n");
        Object output = response.getEntity(Object.class);
        System.out.println(output.toString() +"\n");

        System.out.println("\n\n" + "Test Passed: " + testName + "\n");
    }

    //test e
    @Test
    public void updateDogSnitch()
    {
        String testName = "Update Dog Snitch";

        System.out.println("Begin Test: " + testName);

        //Get current Snitch
        //Compile a get request to get Snitch
        WebResource getSnitchResource = client.resource(base.toString() + "/dog/Snitch");
        ClientResponse getSnitchResponse = getSnitchResource.type("application/json").get(ClientResponse.class);
        //Assert that we got status 200 / OK
        assert getSnitchResponse.getStatus() == 200 :
                testName + ": Response code should be 200 but instead got " + getSnitchResponse.getStatus();
        Dog snitch = getSnitchResponse.getEntity(Dog.class);

        //Update Snitch's age to 8
        snitch.setAge(8);
        //Compile a get request to update Snitch
        String input = gson.toJson(snitch);
        WebResource updateSnitchResource = client.resource(base.toString() + "/dog/Snitch");
        ClientResponse updateSnitchResponse =
                updateSnitchResource.type("application/json").put(ClientResponse.class, input);
        assert updateSnitchResponse.getStatus() == 200 :
                testName + ": Response code should be 200 but instead got " + updateSnitchResponse.getStatus();

        //Get the all animals from the response and print it
        System.out.println("Test: " + testName + ": Response - print all animals:\n");
        Object output = updateSnitchResponse.getEntity(Object.class);
        System.out.println(output.toString() +"\n");

        //Assert that the updated animal is part of the updated "all animals" collection
        assert gson.toJson(output).contains(gson.toJson(snitch)) : testName + ": should contain the updated animal!";

        System.out.println("\n\n" + "Test Passed: " + testName + "\n");
    }

    //test f
    @Test
    public void updateGoatIzza()
    {
        String testName = "Update Goat Izza";

        System.out.println("Begin Test: " + testName);

        //Get current Izza
        //Compile a get request to get Izza
        WebResource getIzzaResource = client.resource(base.toString() + "/goat/Izza");
        ClientResponse getIzzaResponse = getIzzaResource.type("application/json").get(ClientResponse.class);
        //Assert that we got status 200 / OK
        assert getIzzaResponse.getStatus() == 200 :
                testName + ": Response code should be 200 but instead got " + getIzzaResponse.getStatus();
        Dog izza = getIzzaResponse.getEntity(Dog.class);

        //Update Izza's owner name to Mushon
        izza.setOwnerName("Mushon");
        //Compile a get request to update Izza
        String input = gson.toJson(izza);
        WebResource updateIzzaResource = client.resource(base.toString() + "/goat/Izza");
        ClientResponse updateIzzaResponse =
                updateIzzaResource.type("application/json").put(ClientResponse.class, input);
        assert updateIzzaResponse.getStatus() == 200 :
                testName + ": Response code should be 200 but instead got " + updateIzzaResponse.getStatus();

        //Get the all animals from the response and print it
        System.out.println("Test: " + testName + ": Response - print all animals:\n");
        Object output = updateIzzaResponse.getEntity(Object.class);
        System.out.println(output.toString() +"\n");

        //Assert that the updated animal is part of the updated "all animals" collection
        assert gson.toJson(output).contains(gson.toJson(izza)) : testName + ": should contain the updated animal!";

        System.out.println("\n\n" + "Test Passed: " + testName + "\n");
    }

    //test g
    @Test
    public void deleteDogJoey()
    {
        String testName = "Delete Dog Joey";

        System.out.println("Begin Test: " + testName);

        //Get current Joey
        //Compile a get request to get Joey
        WebResource getJoeyResource = client.resource(base.toString() + "/dog/Joey");
        ClientResponse getJoeyResponse = getJoeyResource.type("application/json").get(ClientResponse.class);
        //Assert that we got status 200 / OK
        assert getJoeyResponse.getStatus() == 200 :
                testName + ": Response code should be 200 but instead got " + getJoeyResponse.getStatus();
        Dog joey = getJoeyResponse.getEntity(Dog.class);

        //Compile a post request to delete the required animal and send it
        WebResource webResource = client.resource(base.toString() + "/dog/del/Joey");
        ClientResponse response = webResource.type("application/json").post(ClientResponse.class);

        //Assert that we got status 200 / OK
        assert response.getStatus() == 200 :
                testName + ": Response code should be 200 but instead got " + response.getStatus();

        //Get the all animals from the response and print it
        System.out.println("Test: " + testName + ": Response - print all animals:\n");
        Object output = response.getEntity(Object.class);
        System.out.println(output.toString() +"\n");

        //Assert that the required deleted animal is NOT part of the updated "all animals" collection
        assert !gson.toJson(output).contains(gson.toJson(joey)) : testName + ": should NOT contain the deleted animal!";

        System.out.println("\n\n" + "Test Passed: " + testName + "\n");
    }

    /**
     * Mutual logic method for not repeating the same code
     * @param testName The name of the actual test (e.g: "CreateDogJoey")
     * @param newAnimal The new animal (Dog / Goat) initialized object that should be created in the web service
     * @param uriAfterBase The part of the URI that comes after the base URI, to get to the correct endpoint
     */
    private void createAnimalTest(String testName, Object newAnimal, String uriAfterBase)
    {
        System.out.println("Begin Test: " + testName);

        //Create the required new animal
        //Compile a post request with the required new animal and send it
        WebResource webResource = client.resource(base.toString() + uriAfterBase);
        String input = gson.toJson(newAnimal);
        ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);

        //Assert that we got status 200 / OK
        assert response.getStatus() == 200 :
                testName + ": Response code should be 200 but instead got " + response.getStatus();

        //Get the all animals from the response and print it
        System.out.println("Test: " + testName + ": Response - print all animals:\n");
        Object output = response.getEntity(Object.class);
        System.out.println(output.toString() +"\n");

        //Assert that the required new animal is part of the updated "all animals" collection
        assert gson.toJson(output).contains(gson.toJson(newAnimal)) : testName + ": should contain the new animal!";

        System.out.println("\n\n" + "Test Passed: " + testName + "\n\n\n");
    }
}
