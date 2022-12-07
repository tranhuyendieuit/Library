package library.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import library.model.Category;

public class CategoryRepository extends BaseRepository {

	public List<Category> findAll() {

		List<Category> categories = null;
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM librarydb.category");
			ResultSet rs = stmt.executeQuery();
			categories = new ArrayList<Category>();
			while (rs.next()) {

				// info category
				Category category = new Category();
				category.setId(rs.getInt("category_id"));
				category.setName(rs.getNString("category_name"));

				// add into list
				categories.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categories;
	}
}
