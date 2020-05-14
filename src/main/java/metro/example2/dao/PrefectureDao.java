package metro.example2.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class PrefectureDao extends JdbcDaoSupport {

    @Autowired
    public PrefectureDao(@Qualifier("dataSource") DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    /**
     * Find by prefecture code
     *
     * @param prefCode prefecture_code
     * @return List information
     */
    public List<Map<String, Object>> findPrefByPrefCode(String prefCode) {
        String sql = "SELECT city.`code`, pref.prefecture, city.city," +
                "  pref.prefecture_kana, city.city_kana, pref.prefecture_code" +
                " FROM tbl_city city" +
                " INNER JOIN tbl_prefecture pref ON pref.prefecture_id = city.prefecture_id" +
                " WHERE pref.prefecture_code = ?";

        return this.getJdbcTemplate().queryForList(sql, prefCode);
    }
}
