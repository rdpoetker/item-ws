package rdpoet.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import rdpoet.dao.ItemDAOImpl;
import rdpoet.model.Item;

/**
 * Test fixture for ItemsService.
 * 
 * @author russell
 *
 */
@RunWith(JMockit.class)
public class ItemsServiceTest {

	@Mocked ItemDAOImpl mockedItemsDAO;
	@Mocked Response mockResponse;
	
	@Test
	public void getItems() {
		
		final ItemsService service = new ItemsService();
		
		final List<Item> expected = new ArrayList<>();
		
		new MockUp<ItemsService>() 
		 { 
           @Mock(invocations = 1)
           public Response ok( final Object arg ) {
   			
        	   Assert.assertSame(expected, arg);
	   		   return mockResponse;
	   	   }
                    
		}; 
						
		new Expectations() {{
			
			Deencapsulation.setField(service, "dao", mockedItemsDAO);
			
			mockedItemsDAO.getItems(new Long(123), 225); result = expected;
	    }};
	    
	    final Response result = service.getItems(new Long(123), 225);
	    
	    Assert.assertSame(mockResponse, result);
	}
	
	@Test
	public void getItemsTestParams() {
		
		final ItemsService service = new ItemsService();
		
		new MockUp<ItemsService>() 
		 { 
           @Mock(invocations = 2)
           public Response error( final Response.Status status, final Object arg ) {
   			
        	   Assert.assertEquals(Status.BAD_REQUEST, status);
        	   Assert.assertEquals("Invalid userId parameter", arg);
	   		   return mockResponse;
	   	   }
                    
		}; 
					    
	    Response result = service.getItems(null, 0);
	    
	    Assert.assertSame(mockResponse, result);
	    
	    result = service.getItems(new Long(0), 0);
	    
	    Assert.assertSame(mockResponse, result);
	}
	
	@Test
	public void addItem() {
		
		final ItemsService service = new ItemsService();
		
		final Item item = new Item();
		
		new MockUp<ItemsService>() 
		 { 
           @Mock(invocations = 1)
           public Response ok( final Object arg ) {
   			
        	   Assert.assertSame(item, arg);
	   		   return mockResponse;
	   	   }
                    
		}; 
						
		new Expectations() {{
			
			Deencapsulation.setField(service, "dao", mockedItemsDAO);
			
			mockedItemsDAO.insertItem(item);
	    }};
	    
	    final Response result = service.addItem(item);
	    
	    Assert.assertSame(mockResponse, result);
	}
	
	@Test
	public void addItemMissingItem() {
		
		final ItemsService service = new ItemsService();
		
		new MockUp<ItemsService>() 
		 { 
           @Mock(invocations = 1)
           public Response error( final Response.Status status, final Object arg ) {
   			
        	   Assert.assertEquals(Status.BAD_REQUEST, status);
        	   Assert.assertEquals("Missing item data", arg);
	   		   return mockResponse;
	   	   }
                    
		}; 
					    
	    Response result = service.addItem(null);
	    
	    Assert.assertSame(mockResponse, result);
	    
	}
}
