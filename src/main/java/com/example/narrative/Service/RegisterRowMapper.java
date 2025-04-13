package com.example.narrative.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.narrative.model.Register;

public class RegisterRowMapper implements RowMapper<Register>{

    @Override
    public Register mapRow(ResultSet rs, int rowNum) throws SQLException {
        Register register = new Register();
        register.setId(rs.getLong("id"));
        register.setRegisterName(rs.getString("register_name"));
        register.setMailAddress(rs.getString("mail_address"));
        register.setPhoneNum(rs.getString("phone_num"));
        register.setSchoolApart(rs.getString("school_apart"));
        register.setCarmediumHave(rs.getBoolean("carmedium_have"));
        register.setRegistASession(rs.getString("regist_a_session"));
        register.setRegistDate(rs.getTimestamp("regist_date").toLocalDateTime());
        register.setReserveBook(rs.getString("reserve_book"));
        register.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());


        return register;
    }
    

}
