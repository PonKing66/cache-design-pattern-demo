package org.ponking.cache.service;

import org.ponking.cache.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author ponking
 * @Date 2021/3/11 16:02
 */
@Service
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insert(User user) {
        return jdbcTemplate.update("insert into tb_user(user_name,address,age) values (?,?,?)", user.getUserName(), user.getAddress(), user.getAge());
    }

    public int delete(Integer id) {
        return jdbcTemplate.update("delete from tb_user where tb_user.id = ?", id);
    }

    public int update(User user) throws Exception {
        if (user.getId() == null) {
            throw new Exception("Id is not  null");
        }
        return jdbcTemplate.update("update tb_user set tb_user.user_name = ? , tb_user.address = ? , tb_user.age = ? where id = ?", user.getUserName(), user.getAddress(), user.getAge(), user.getId());
    }

    public User getOneById(Integer id){
        String sql = "select * from tb_user where id = ?";
        return this.jdbcTemplate.queryForObject(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("user_name"));
                user.setAddress(rs.getString("address"));
                user.setAge(Integer.valueOf(rs.getString("age")));
                return user;
            }
        }, id);
    }
}
