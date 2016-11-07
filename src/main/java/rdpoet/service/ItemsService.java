package rdpoet.service;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rdpoet.dao.ItemDAO;
import rdpoet.model.Item;

@Path("/data")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
@PermitAll
@Service
public class ItemsService {
	
	@Autowired
	private ItemDAO dao;
	
	/**
	 * Returns a list of Item objects for the userId.
	 * 
	 * @param userId used to filter list of Item objects
	 * @param start which row to start on for pagination.  25 rows are returned max
	 * @return
	 */
	@GET
	@Path("/items")
	public Response getItems(@QueryParam("userId") Long userId, 
			@DefaultValue("0") @QueryParam("start") int start) {
		
		if( userId == null || userId.equals(new Long(0)) ) {
		
			return error( Status.BAD_REQUEST, "Invalid userId parameter") ;
		}
		
		final List<Item> items = dao.getItems(userId, start);
        
		return ok(items);
	}
	
	/**
	 * Inserts a new Item object into the DB.
	 * 
	 * This inserts item as a new record, so the id should be null.
	 * 
	 * @param item return item object with populated id value
	 * @return
	 */
	@POST
	@Path("/item")
	public Response addItem( final Item item ) {
		
		if( item == null ) {
			
			return error( Status.BAD_REQUEST, "Missing item data") ;
		}
		
		dao.insertItem(item);
		
		return ok(item);
	}
	
	protected Response ok( final Object arg ) {
		
		return Response.ok(arg).build();
	}
	
	protected Response error( final Response.Status status, final Object arg ) {
		
		return Response.status(status).entity(arg).build();
	}
	
}
