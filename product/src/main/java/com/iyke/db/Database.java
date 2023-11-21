package com.iyke.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public abstract class Database {
    //Connection Configurations
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/inventory";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456.soft";

  //SQL connection object
  private Connection connection;

  private Connection connect() throws ClassNotFoundException, SQLException {
    //load the jdbc driver
    Class.forName("com.mysql.cj.jdbc.Driver");
    // Create a connection to the database.
    connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    return connection;
  }
  //Database Query
  public ResultSet getQuery(String sql) {
    ResultSet resultSet = null;
    Connection con = null;
    try {
      con = connect();//make a connection first
      if (con != null) {
        PreparedStatement pStat = con.prepareStatement(sql);
        resultSet = pStat.executeQuery();
      } else {
        System.out.println("Connection is null!");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return resultSet;
  }
  //post query
  public int postQuery(String sql, Object ...params) {
    int update = -1;
    Connection con = null;
    try {
      con = connect();// make a connection first
      if (con != null) {
        PreparedStatement pStat = con.prepareStatement(sql);
        int i = 0;
        while (params.length > 0 && i < params.length) {
          if (params[i] instanceof String || params[i] instanceof UUID) {
            pStat.setString(i + 1, params[i].toString());
          } else if (params[i] instanceof Integer) {
            pStat.setInt(i + 1, Integer.parseInt(params[i].toString()));
          } else if (params[i] instanceof Double) {
            pStat.setDouble(i + 1, Double.parseDouble(params[i].toString()));
          } else if (params[i] instanceof Long) {
            pStat.setLong(i + 1, Long.parseLong(params[i].toString()));
          } else {
            pStat.setObject(i + 1, params[i]);
          }

          i++;
        }
        update = pStat.executeUpdate();
      } else {
        System.out.println("Connection is null!");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return update;
  }
  //get Query
  public ResultSet getQuery(String sql, Object... params) {
    ResultSet resultSet = null;
    Connection con = null;
    try {
      con = connect();// make a connection first
      if (con != null) {
        PreparedStatement pStat = con.prepareStatement(sql);
        int i = 0;
        while (params.length > 0 && i < params.length) {
          if (params[i] instanceof String || params[i] instanceof UUID) {
            pStat.setString(i + 1, params[i].toString());
          } else if (params[i] instanceof Integer) {
            pStat.setInt(i + 1, Integer.parseInt(params[i].toString()));
          } else if (params[i] instanceof Double) {
            pStat.setDouble(i + 1, Double.parseDouble(params[i].toString()));
          } else if (params[i] instanceof Long) {
            pStat.setLong(i + 1, Long.parseLong(params[i].toString()));
          } else {
            pStat.setObject(i + 1, params[i]);
          }

          i++;
        }
        resultSet = pStat.executeQuery();

      } else {
        System.out.println("Connection is null!");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return resultSet;
  }

  //delete Query
  public boolean deleteQuery(String sql, Object ...params) {
    boolean  update = false;
    Connection con = null;
    try {
      con = connect();// make a connection first
      if (con != null) {
        PreparedStatement pStat = con.prepareStatement(sql);
        int i = 0;
        while (params.length > 0 && i < params.length) {
          
          if (params[i] instanceof String || params[i] instanceof UUID) {
            pStat.setString(i + 1, params[i].toString());
          } else if (params[i] instanceof Integer) {
            pStat.setInt(i + 1, Integer.parseInt(params[i].toString()));
          } else if (params[i] instanceof Double) {
            pStat.setDouble(i + 1, Double.parseDouble(params[i].toString()));
          } else if (params[i] instanceof Long) {
            pStat.setLong(i + 1, Long.parseLong(params[i].toString()));
          } else {
            pStat.setObject(i + 1, params[i]);
          }

          i ++;
        }
        update = pStat.execute();
      } else {
        System.out.println("Connection is null!");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return update;
  }

  // Close Database Connection Function
  public void closeConnection() {
    try {
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
