package rdpoet.model;

import java.util.Date;

import mockit.integration.junit4.JMockit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Test fixture for Item.
 * 
 * @author russell
 *
 */
@RunWith(JMockit.class)
public class ItemTest {

	@Test
	public void create() {
		
		final Item item = new Item();
		
		Assert.assertNull(item.getId());
		Assert.assertNull(item.getUserId());
		Assert.assertNull(item.getDesc());
		Assert.assertNull(item.getDetail());
		Assert.assertNull(item.getTarget());
	}
	
	@Test
	public void properties() {
		
		final Item item = new Item();
		item.setId(new Long(111));
		item.setUserId(new Long(222));
		item.setDesc("testdesc");
		item.setDetail("testdetail");
		
		final Date td = new Date();
		item.setTarget(td);
		
		Assert.assertEquals(new Long(111), item.getId());
		Assert.assertEquals(new Long(222), item.getUserId());
		Assert.assertEquals("testdesc", item.getDesc());
		Assert.assertEquals("testdetail", item.getDetail());
		Assert.assertEquals(td, item.getTarget());
	}
}
