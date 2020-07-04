package com.example.databasedemo.myjdbc;

/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-05-29 15:16:43
 * @LastEditTime : 2020-06-20 11:08:50
 * @LastEditors  : Do not edit
 */
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JdbcController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * @description: 方法说明....
     * @param {type}
     * @return:
     * @Date: 2019-10-30 16:50:54
     */
    @GetMapping(value = { "/school", "/" })
    public List<Map<String, Object>> getMethodName() throws SQLException {
        List<Map<String, Object>> result = jdbcTemplate.queryForList("select * from school");
        return result;
    }

}