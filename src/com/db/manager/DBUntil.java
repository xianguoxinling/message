package com.db.manager;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.bespeak.Bespeak;

public class DBUntil
{
    public Bespeak queryBespeakFromResultSet(ResultSet resultSet)
    {
        Bespeak bespeak = new Bespeak();
        try
        {
            bespeak.setId(resultSet.getString("id"));
            bespeak.setContent(resultSet.getString("b_content"));
            bespeak.setMagicKey(resultSet.getString("magic_key"));
            bespeak.setMessage(resultSet.getString("message"));
            bespeak.setName(resultSet.getString("name"));
            bespeak.setNumber(resultSet.getString("number"));
            bespeak.setPhone(resultSet.getString("phone"));
            bespeak.setState(resultSet.getString("state"));
            bespeak.setTime(resultSet.getString("b_time"));
            bespeak.setType(resultSet.getString("b_type"));
            bespeak.setBespeak_time(resultSet.getString("bespeak_time"));
            
        }catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return bespeak;
    }
}
