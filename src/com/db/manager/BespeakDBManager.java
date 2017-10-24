package com.db.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.model.bespeak.Bespeak;
import com.until.errorcode.MAGICCODE;
import com.until.queryterm.QueryTerm;

public class BespeakDBManager
{
    protected DBManager dbManager = null;

    public int addBespeak(Bespeak bespeak)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "INSERT INTO bespeak(name,phone,number,b_type,b_content,magic_key,message,b_time,bespeak_time) VALUES (?,?,?,?,?,?,?,?,?)";
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, bespeak.getName());
            statement.setString(2, bespeak.getPhone());
            statement.setString(3, bespeak.getNumber());
            statement.setString(4, bespeak.getType());
            statement.setString(5, bespeak.getContent());
            statement.setString(6, bespeak.getMagicKey());
            statement.setString(7, bespeak.getMessage());
            statement.setTimestamp(8, new java.sql.Timestamp(new java.util.Date().getTime()));
            statement.setString(9, bespeak.getBespeak_time());
            statement.executeUpdate();

        } catch (SQLException e)
        {
            e.printStackTrace();
            return MAGICCODE.DB_ERROR;
        } finally
        {
            try
            {
                if (null != connection)
                {
                    connection.close();
                }

            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return MAGICCODE.OK;
    }
    
    public int queryBespeak(List<Bespeak> bespeakList,String magicKey,QueryTerm queryTerm)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "select * from bespeak where magic_key = ? limit ?,?";
        Bespeak bespeak = null;
        DBUntil dbUntil = new DBUntil();
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, magicKey);
            statement.setInt(2, queryTerm.queryBegin);
            statement.setInt(3, queryTerm.queryNum);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                bespeak = dbUntil.queryBespeakFromResultSet(resultSet);
                bespeakList.add(bespeak);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
            return MAGICCODE.DB_ERROR;
        } finally
        {
            try
            {
                if (null != connection)
                {
                    connection.close();
                }

            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return MAGICCODE.OK;
    }
    
    public int updateBespeakState(String id,String state,String magicKey)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        
        String sql = "update bespeak set state = ? where id = ? and magic_key = ?";

        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, state);
            statement.setLong(2, Long.parseLong(id));
            statement.setString(3, magicKey);
            statement.executeUpdate();

        } catch (SQLException e)
        {
            e.printStackTrace();
            return MAGICCODE.DB_ERROR;
        } finally
        {
            try
            {
                if (null != connection)
                {
                    connection.close();
                }

            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return MAGICCODE.OK;
    }
    
    public int queryBespeakByState(List<Bespeak> bespeakList,String state,String magicKey,QueryTerm queryTerm)
    {
        dbManager = DBManager.getInstance();
        Connection connection = null;
        String sql = "select * from bespeak where magic_key = ? and state = ? limit ?,?";
        Bespeak bespeak = null;
        DBUntil dbUntil = new DBUntil();
        try
        {
            connection = dbManager.getConection();
            connection.setAutoCommit(true);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, magicKey);
            statement.setString(2, state);
            statement.setInt(3, queryTerm.queryBegin);
            statement.setInt(4, queryTerm.queryNum);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                bespeak = dbUntil.queryBespeakFromResultSet(resultSet);
                bespeakList.add(bespeak);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
            return MAGICCODE.DB_ERROR;
        } finally
        {
            try
            {
                if (null != connection)
                {
                    connection.close();
                }

            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return MAGICCODE.OK;
    }
    
    
}
