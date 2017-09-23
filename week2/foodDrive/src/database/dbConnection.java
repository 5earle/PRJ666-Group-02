package database;

import java.sql.*;

public class dbConnection {
    //mysql xampp//
    private static  final String USERNAME = "root";
    private static  final String PASS = "";
    private static  final String URL = "jdbc:mysql://localhost/school";
    //mysql xampp//

    private static final String sqliteCon = "jdbc:sqlite:C:\\Users\\dance\\IdeaProjects\\redlight.db";
    private Connection con =null;

    public void connect() throws SQLException {
        if(con != null){return;}

        try {
           // Class.forName("com.mysql.jdbc.Driver");
           Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        con =  DriverManager.getConnection(sqliteCon);
        //con =DriverManager.getConnection(URL,"root","");
    }

    public boolean isDatabaseCon(){
        try {
            connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.con != null;
    }

    public boolean isLogin(String name,String pass,String opt) throws SQLException {
        PreparedStatement checkStmt = null;
        ResultSet checkRs = null;
        String checksql = "SELECT * FROM login where email = ? and password = ? and division = ?";

        try {
            checkStmt = con.prepareStatement(checksql);
            checkStmt.setString(1,name);
            checkStmt.setString(2,pass);
            checkStmt.setString(3,opt);
            checkRs = checkStmt.executeQuery();

            if(checkRs.next()){
                return  true;
            }
            return false;

        } catch (SQLException e) {
           return  false;
        } finally {
            {
                checkStmt.close();
                checkRs.close();
            }
        }

    }
}
