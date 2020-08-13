package tw.ouyang.simplebatchplatform.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import tw.ouyang.simplebatchplatform.model.Batch;
import tw.ouyang.simplebatchplatform.rowmapper.BatchRowMapper;

@Repository
public class BatchDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Batch> queryBatchsToRun() {

        String sql = "SELECT JAR_NAME, NOTE, HOUR, MINUTE, WAITING_JAR FROM DAILY_BATCH";
        return jdbcTemplate.query(sql, new BatchRowMapper());

    }

}
