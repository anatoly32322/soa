package com.example.resource;

import com.example.models.Person;
import com.example.retrofit.PersonController;
import com.example.wrapper.CountWrapper;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;


@Path("/demography")
public class MyResource {

    @GET
//    @Path("/nationality/{nationality}/hair-color/{hairColor}")
    @Produces(MediaType.APPLICATION_JSON)
    public CountWrapper getPersonsCount(
            @PathParam("nationality") String nationality,
            @PathParam("hairColor") String hairColor
    ) {
        PersonController personController = new PersonController();
        personController.start();
        int count = 1;
        return new CountWrapper(count);
    }

    @GET
    @Path("/hair-color/{hairColor}")
    @Produces(MediaType.APPLICATION_JSON)
    public CountWrapper getPersonsCountByHairColor(
            @PathParam("hairColor") String hairColor
    ) {
        int count = 0;
        return new CountWrapper(count);
    }
}