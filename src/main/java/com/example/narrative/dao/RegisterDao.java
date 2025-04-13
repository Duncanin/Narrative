package com.example.narrative.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import com.example.narrative.Service.RegisterRowMapper;
import com.example.narrative.model.Register;

@Component
public class RegisterDao {

    public static final int BATCH_SIZE = 1000;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    // public void addRegister(Register register) throws SQLException {
    //     String sql = "INSERT INFO narrative_registration_system " +
    //     "(regist_name, mail_address, phone_num, school_apart) " +
    //     "VALUE (?, ? ,? ,?)";
    // try (Connection conn = DatabaseUtil.getConnection();
    // PreparedStatement pstmt = conn.prepareStatement(sql)) {
    // }

    public Register getId (Integer registerId) {
        String sql ="SELECT id, register_name FROM narrative_registration_system WHERE id = :registerId";

        Map<String, Object> map = new HashMap<>();
        map.put("registerId", registerId);

        List<Register> list = namedParameterJdbcTemplate.query(
            sql, map, new RegisterRowMapper(

        ));

            if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public List<Register> findAllList() {
        String sql = "SELECT id, register_name FROM narrative_registration_system";
        return jdbcTemplate.query(sql, new RegisterRowMapper());
    }

    public List<Register> findAll() {
        String sql = "SELECT id, register_name FROM narrative_registration_system";
        return namedParameterJdbcTemplate.query(sql, new RegisterRowMapper());
    }

    public Register save(Register register) {
        String sql = "INSERT INTO narrative_registration_system (register_name, mail_address, phone_num, school_apart) " +
        "VALUES (:registerName, :mailAddress, :phoneNum, :schoolApart)";

        Map<String, Object> map = new HashMap<>();
        map.put("registerName", register.getRegisterName());
        map.put("mailAddress", register.getMailAddress());
        map.put("phoneNum", register.getPhoneNum());
        map.put("schoolApart", register.getSchoolApart());

        namedParameterJdbcTemplate.update(sql, map);
        return register;
    }

    public List<Register> findByMailAddress(String email) {
        String sql = "SELECT id, register_name FROM narrative_registration_system WHERE mail_address = :mailAddress";
        Map<String, Object> map = new HashMap<>();
        map.put("mailAddress", email);
        return namedParameterJdbcTemplate.query(sql, map, new RegisterRowMapper());
    }

    public void update(Register register) {
        String sql = "UPDATE narrative_registration_system SET register_name = :registerName, mail_address = :mailAddress, phone_num = :phoneNum, school_apart = :schoolApart WHERE id = :id";

        Map<String, Object> map = new HashMap<>();
        map.put("id", register.getId());
        map.put("registerName", register.getRegisterName());
        map.put("mailAddress", register.getMailAddress());
        map.put("phoneNum", register.getPhoneNum());
        map.put("schoolApart", register.getSchoolApart());

        namedParameterJdbcTemplate.update(sql, map);
    }
}
