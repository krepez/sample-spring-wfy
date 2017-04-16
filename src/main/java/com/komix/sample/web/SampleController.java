package com.komix.sample.web;

import com.komix.sample.domain.Sample;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;

/**
 * @author krepela
 */
@Component
@Path("/test")
public class SampleController {

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Sample hello(@PathParam("name") String name) {
        Sample sample = new Sample();
        sample.setName("Hello, Mr. " + name);
        sample.setDate(LocalDateTime.now());
        return sample;
    }

}
