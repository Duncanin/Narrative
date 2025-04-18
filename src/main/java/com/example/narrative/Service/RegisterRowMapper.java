package com.example.narrative.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.narrative.entity.RegistRecord;

public class RegisterRowMapper implements RowMapper<RegistRecord>{

    @Override
    public RegistRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
        RegistRecord registRecord = new RegistRecord();
        registRecord.setId(rs.getInt("id"));
        registRecord.setRegisterName(rs.getString("register_name"));
        registRecord.setMailAddress(rs.getString("mail_address"));
        registRecord.setPhoneNum(rs.getString("phone_num"));
        registRecord.setSchoolApart(rs.getString("school_apart"));
        registRecord.setRegistDate(rs.getTimestamp("regist_date").toLocalDateTime());
        registRecord.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());


        return registRecord;
    }
    

}
