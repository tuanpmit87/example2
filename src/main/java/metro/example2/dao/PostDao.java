package metro.example2.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class PostDao extends JdbcDaoSupport {

    @Autowired
    public PostDao(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    /**
     * Find by post code
     *
     * @param postCode post_code
     * @return List post
     */
    public List<Map<String, Object>> findPostByPostcode(String postCode) {
        String sql = "SELECT city.`code`, pref.prefecture, city.city, area.area," +
                " opost.old_post_code, post.post_code," +
                " pref.prefecture_kana, city.city_kana, area.area_kana," +
                " post.multi_area, area.koaza_area, area.chome_area, area.multi_post_area," +
                " post.update_show, post.change_reason, pref.prefecture_code" +
                " FROM tbl_post post" +
                " INNER JOIN tbl_area area ON area.post_id = post.post_id" +
                " INNER JOIN tbl_old_post opost ON opost.old_post_id = area.old_post_id" +
                " INNER JOIN tbl_city city ON city.city_id = area.city_id" +
                " INNER JOIN tbl_prefecture pref ON pref.prefecture_id = city.prefecture_id" +
                " WHERE post.post_code = ?";

        List<Map<String, Object>> postList = this.getJdbcTemplate().queryForList(sql, new Object[] { postCode });
        return postList;
    }
}
