
package com.lvl.au.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.lvl.au.rest.service.ValueService;

/** Example resource class hosted at the URI path "/cat"
 */
@Path("/cat")
@Component
@Scope("request")
public class CatResource {

	private ValueService valueService;

    /** Method processing HTTP GET requests, producing "text/plain" MIME media
     * type.
     * @return String that will be send back as a response of type "text/plain".
     */
    @GET
    @Produces("text/plain")
    public String getIt() {
        return "Hi there! " + valueService.getValue();
    }

	public final void setValueService(ValueService valueService) {
		this.valueService = valueService;
	}
}
