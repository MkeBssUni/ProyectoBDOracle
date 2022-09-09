package com.example.jsp4.model.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    Connection con;

    public Conexion() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            /* con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "caprez"); */
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "usuariop", "password");
            /*con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "usuariog", "password");*/
            
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
    }

    public Connection getConnection() {
        return con;
    }

    public static void main(String[] args){
        Connection con = new Conexion() {
        }.getConnection();
        if (con != null) {
            System.out.println("Conexión realizada");
            try {
                con.close();
            } catch (SQLException e) {

            }
        } else {
            System.out.println("Conexión fallida");
        }
    }
}