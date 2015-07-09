
package com.dredom.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.dredom.rest.pojo.CatDto;
import com.dredom.rest.service.CatValueService;
import com.dredom.rest.service.NotFoundException;
import com.dredom.rest.service.ValueService;

/** Example resource class hosted at the URI path "/cat"
 */
@Path("/cat")
@Component
@Scope("request")
public class CatResource {

    @Autowired
	private CatValueService valueService;

    /**
     * TODO Not working on the Response
     * @return String that will be send back as a response of type "text/plain".
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        System.out.println(getClass().getSimpleName() + " getAll()");
        CatDto[] cats;
        try {
            cats = valueService.getAll();
        } catch (NotFoundException e) {
            System.out.println(getClass().getSimpleName() + " getAll() 404");
            return Response.status(Status.NOT_FOUND).build();
        }
        GenericEntity<CatDto[]> wrapper = new GenericEntity<CatDto[]>(cats, CatDto.class);
        System.out.println(getClass().getSimpleName() + " getAll() return");
        return Response.ok(wrapper).build();
    }

	public final void setValueService(CatValueService valueService) {
		this.valueService = valueService;
	}
}
