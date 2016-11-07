package rdpoet.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import rdpoet.model.Item;

/**
 * SQL mapping query list for Item objects by User
 * 
 * @author russell
 *
 */

public class ItemListMappingQuery extends MappingSqlQuery<Item> {

	public ItemListMappingQuery(DataSource ds) {
        
        super(ds, "select id, user_id, description, detail, target from t_item where user_id = ? order by target desc LIMIT ?,25");
        super.declareParameter(new SqlParameter("user_id", Types.BIGINT));
        super.declareParameter(new SqlParameter("start", Types.INTEGER));
        compile();
    }
	
	@Override
	protected Item mapRow(ResultSet rs, int index) throws SQLException {
		
		Item item = new Item();
		item.setId(rs.getLong("id"));
		item.setUserId(rs.getLong("user_id"));
		item.setDesc(rs.getString("description"));
		item.setDetail(rs.getString("detail"));
		item.setTarget(rs.getDate("target"));
		
        return item;
	        
	}
}