package rdpoet.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import rdpoet.dao.ItemDAOImpl;
import rdpoet.dao.ItemListMappingQuery;
import rdpoet.model.Item;

/**
 * Test fixture for ItemDAOImpl.
 * 
 * @author russell
 *
 */
@RunWith(JMockit.class)
public class ItemDAOImplTest {

	@Mocked DataSource mockDatasource;
	@Mocked ItemListMappingQuery mockItemListMQ;
	@Mocked SimpleJdbcInsert mockInsert;
	@Mocked Map<String, Object> mockInsertParams;
	
	@Test
	public void init() {
		
		final ItemDAOImpl dao = new ItemDAOImpl();
					   	
	    Deencapsulation.setField(dao, "dataSource", mockDatasource);
	    
	    new Expectations() {{
			
	    	mockInsert = new SimpleJdbcInsert(mockDatasource);
									
			mockInsert.withTableName("t_item"); result = mockInsert;
			mockInsert.usingGeneratedKeyColumns("id"); result = mockInsert;
	    }};
	    
		dao.init();
		
		Assert.assertNotNull( Deencapsulation.getField(dao, "dataSource") );
		Assert.assertNotNull( Deencapsulation.getField(dao, "itemListQuery") );
		Assert.assertNotNull( Deencapsulation.getField(dao, "insertItem") );
		
	}
	
	@Test
	public void getItems() {
		
		final ItemDAOImpl dao = new ItemDAOImpl();
		
		final List<Item> expected = new ArrayList<Item>();
		
		new Expectations() {{
			
			Deencapsulation.setField(dao, "itemListQuery", mockItemListMQ);
			
			mockItemListMQ.execute(new Long(111), 100); result = expected;
	    }};
	   		    	    
		final List<Item> result = dao.getItems(new Long(111), 100);
				
		Assert.assertSame(expected, result);
	}
	
	@Test
	public void insetItem() {
		
		final ItemDAOImpl dao = new ItemDAOImpl();
		
		final Item item = new Item();
		item.setUserId(new Long(444));
		item.setDesc("testdesc");
		item.setDetail("testdetail");
		
		final Date td = new Date();
		
		item.setTarget(td);
		
		new Expectations() {{
			
			Deencapsulation.setField(dao, "insertItem", mockInsert);
			
			mockInsertParams = new HashMap<String, Object>(4);
			mockInsertParams.put("user_id", new Long(444));
			mockInsertParams.put("description", "testdesc");
			mockInsertParams.put("detail", "testdetail");
			mockInsertParams.put("target", td);
			
			mockInsert.executeAndReturnKey(mockInsertParams); result = new Long(222);
	    }};
	   		    	    
		dao.insertItem(item);
			
		Assert.assertEquals(new Long(222), item.getId());
	}
}
