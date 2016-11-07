package rdpoet.dao;

import java.util.List;

import rdpoet.model.Item;

/**
 * DAO interface for Item objects.
 * 
 * @author rdpoetker
 *
 */
public interface ItemDAO {

	/**
	 * Returns a list of Items for the userId object using first for pagination.
	 * 
	 * Returns 25 rows per result.
	 * 
	 * @param userId from this object
	 * @param first first row to return
	 * @return
	 */
	public List<Item> getItems(long userId, int first);
	
	/**
	 * Insert a new Item object into the Item DB.
	 * 
	 * @param item the Item to insert
	 * 
	 */
	public void insertItem(Item item);
}
