package com.example.narrative.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.narrative.model.Register;

public class RegisterRowMapper implements RowMapper<Register>{

    @Override
    public Register mapRow(ResultSet rs, int rowNum) throws SQLException {
        Register register = new Register();
        register.setRegisterName(rs.getString("register_name"));
        register.setMailAddress(rs.getString("mail_address"));
        return register;
    }
    

}
