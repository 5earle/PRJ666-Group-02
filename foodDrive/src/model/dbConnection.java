package model;

import java.sql.*;

public class dbConnection {
    //mysql xampp//
    private static  final String USERNAME = "root";
    private static  final String PASS = "";
    private static  final String URL = "jdbc:mysql://localhost/credentials";
    //mysql xampp//

    private static final String sqliteCon = "jdbc:sqlite:relight.db";
    private Connection con;


    public void connect() throws SQLException {
        if(con != null){return;}

        try {
            //Class.forName(URL)
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        con =  DriverManager.getConnection(sqliteCon);
        //return DriverManager.getConnection(URL,USERNAME,PASS);
    }

    public boolean isDatabaseCon(){
        try {
            connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.con != null;
    }

    public boolean isLogin(String name,String pass,String opt)throws Exception{
       String checksql = "select * from login where email = ? and password = ? and division = ?";
        PreparedStatement checkStmt = con.prepareStatement(checksql);
        ResultSet checkRs = null;


        checkStmt.setString(1,name);
        checkStmt.setString(2,pass);
        checkStmt.setString(3,opt);

        checkRs = checkStmt.executeQuery();
        if(checkRs.next()){
            return  true;
        }

        checkStmt.close();
        return false;
    }
}
