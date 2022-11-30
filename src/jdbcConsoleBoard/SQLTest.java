package jdbcConsoleBoard;

import java.sql.*;

public class SQLTest {
    public static void main(String[] args) {
        Connection con = null;

        String server = "localhost"; // 서버 주소
        String user_name = "root"; //  접속자 id
        String password = Password.getPassword(); // 접속자 pw

        //JDBC드라이버 로드
        try{
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC 드라이버를 로드하는데에 문제 발생" + e.getMessage());
            e.printStackTrace();
        }

        // 접속
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + "?useSSL=false", user_name, password);
            System.out.println("연결 완료!");
        } catch(SQLException e) {
            System.err.println("연결 오류" + e.getMessage());
            e.printStackTrace();
        }

        // 접속 종료
        try {
            if(con != null)
                con.close();
        } catch (SQLException e) {

        }
    }

}
