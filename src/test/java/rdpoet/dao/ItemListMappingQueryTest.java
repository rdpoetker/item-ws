package rdpoet.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.sql.DataSource;

import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.object.MappingSqlQuery;

import rdpoet.model.Item;

/**
 * Test fixture for ItemListMappingQuery.
 * 
 * @author russell
 *
 */
@RunWith(JMockit.class)
public class ItemListMappingQueryTest {

	@Mocked DataSource mockDatasource;
			
	@Test
	public void create() {
		
		 new MockUp<MappingSqlQuery<Item>>() 
		 { 
            @Mock(invocations = 1) 
            public void $init(DataSource ds, String sql) 
            { 
                Assert.assertNotNull(ds); 
                Assert.assertEquals("select id, user_id, description, detail, target from t_item where user_id = ? order by target desc LIMIT ?,25", sql); 
            } 
            
            @Mock(invocations = 1) 
            public void compile() 
            { 
                // just ensure it is called
            } 
		}; 
		   
			    
		new ItemListMappingQuery(mockDatasource);
				
	}
	
	@Test
	public void mapRow(@Mocked final ResultSet mockRs) throws SQLException {
		
		 new MockUp<MappingSqlQuery<Item>>() 
		 { 
            @Mock(invocations = 1) 
            public void compile() 
            { 
                // just ensure it is called
            } 
		}; 
			    
		ItemListMappingQuery query = new ItemListMappingQuery(mockDatasource);
		
		final java.sql.Date td = new java.sql.Date(new Date().getTime());
		
		new Expectations() {{
				
			 mockRs.getLong("id"); result = new Long(321);
			 mockRs.getLong("user_id"); result = new Long(876);
			 mockRs.getString("description"); result = "rettestdesc";
			 mockRs.getString("detail"); result = "rettestdetail";
			 mockRs.getDate("target"); result = td;
		 }};
		    
		final Item result = query.mapRow(mockRs, 1);
		
		Assert.assertEquals(new Long(321), result.getId());
		Assert.assertEquals(new Long(876), result.getUserId());
		Assert.assertEquals("rettestdesc", result.getDesc());
		Assert.assertEquals("rettestdetail", result.getDetail());
		Assert.assertEquals(td, result.getTarget());
	}
	
	
}
