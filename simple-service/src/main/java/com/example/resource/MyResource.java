package com.example.resource;

import com.example.models.Country;
import com.example.models.Person;
import com.example.retrofit.PersonController;
import com.example.wrapper.CountWrapper;
import com.example.wrapper.PercentWrapper;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.util.List;
import java.util.Objects;


@Path("/demography")
public class MyResource {

    //    @Path("/nationality/{nationality}/hair-color/{hairColor}")
    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getPing() {
        return Response.ok().entity("ping").build();
    }

    @GET
    @Path("/nationality/{nationality}/hair-color/{hair-color}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonsCount(
            @PathParam("nationality") String country,
            @PathParam("hair-color") String hairColor
            ) throws IOException {
        PersonController personController = new PersonController();
        List<Person> personList = personController.start();
        Country countryEnum = null;
        try {
            countryEnum = Country.valueOf(country.toUpperCase());
        } catch (IllegalArgumentException err) {
            Response.serverError().entity("Nationality must be one of the following: Germany, USA, Italy, Thailand");
        }
        int cnt = 0;
        for (int i = 0; i < personList.size(); i++) {
            if (personList.get(i).getNationality() != countryEnum) {
                continue;
            }
            if (!Objects.equals(personList.get(i).getHairColor().toLowerCase(), hairColor.toLowerCase())) {
                continue;
            }
            cnt++;
        }
        return Response.ok().entity(new PercentWrapper(cnt/personList.size() * 100)).build();
    }

    @GET
    @Path("/hair-color/{hairColor}")
    @Produces(MediaType.APPLICATION_JSON)
    public CountWrapper getPersonsCountByHairColor(
            @PathParam("hairColor") String hairColor
    ) throws IOException {
        PersonController personController = new PersonController();
        List<Person> personList = personController.start();
        int cnt = 0;
        for (int i = 0; i < personList.size(); i++) {
            if (!Objects.equals(personList.get(i).getHairColor().toLowerCase(), hairColor.toLowerCase())) {
                continue;
            }
            cnt++;
        }
        return new CountWrapper(cnt);
    }
}