package rdpoet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import rdpoet.model.Item;

/**
 * DAO implementation for Item objects.
 * 
 * @author russell
 *
 */
@Repository
@Transactional(readOnly = true)
public class ItemDAOImpl implements ItemDAO {

	@Autowired
	@Qualifier("dbDataSource")
	private DataSource dataSource;

	private ItemListMappingQuery itemListQuery;

	private SimpleJdbcInsert insertItem;

	@PostConstruct
	public void init() {

		itemListQuery = new ItemListMappingQuery(dataSource);

		this.insertItem = new SimpleJdbcInsert(dataSource)
			.withTableName("t_item")
			.usingGeneratedKeyColumns("id");
	}

	public List<Item> getItems(long userId, int first) {

		return itemListQuery.execute(userId, first);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void insertItem(Item item) {

		Map<String, Object> parameters = new HashMap<String, Object>(4);
		parameters.put("user_id", item.getUserId());
		parameters.put("description", item.getDesc());
		parameters.put("detail", item.getDetail());
		parameters.put("target", item.getTarget());

		Number newId = insertItem.executeAndReturnKey(parameters);
		item.setId(newId.longValue());

	}

}
