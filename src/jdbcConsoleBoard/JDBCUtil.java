package jdbcConsoleBoard;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//jdbc에 mysql 연동 전에 테이블 생성하기
public class JDBCUtil {
    private static JDBCUtil instance;

    public static JDBCUtil getInstance() {
        if (instance == null) {
            instance = new JDBCUtil();
        }
        return instance;
    }

    //db 접속정보
    private String url = "jdbc:mysql://localhost:3306/CONSOLE_BOARD";
    private String userName = "root";
    private String password = Password.getPassword();

    //db와 연결 객체
    Connection con = null;
    //SQL 구문을 실행시키는 객체
    PreparedStatement ps = null;
    //select의 결과값을 저장하는 객체
    ResultSet rs = null;

    // 조회 결과 여러 줄, ? 없음
    public List<Map<String, Object>> selectList(String sql) {
        List<Map<String, Object>> list = new ArrayList<>();

        try {
            // 입력한 정보로 데이터베이스 접속
            con = DriverManager.getConnection(url, userName, password);
            // 입력 받은 SQL 구문을 실행
            ps = con.prepareStatement(sql);
            // select : ResultSet을 리턴
            rs = ps.executeQuery();

            ResultSetMetaData metaData = rs.getMetaData(); // 메타데이터 : 데이터에 대한 데이터
            int columnCount = metaData.getColumnCount(); // 컬럼 수

            while (rs.next()) {
                HashMap<String, Object> map = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    map.put(metaData.getColumnName(i), rs.getObject(i));
                    // 키, 값을 맵에 저장(인덱스가 1부터 시작한다.)
                }
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally { // 반드시 닫아줘야 하므로 finally에서 실행한다.
            if (rs != null) try { rs.close(); } catch (Exception e) {}
            if (ps != null) try { ps.close(); } catch (Exception e) {}
            if (con != null) try { con.close(); } catch (Exception e) {}
        }
        return list;
    }

//    public static void main(String[] args) throws SQLException {
//
//        DriverManager.getConnection(url, userName, password);
//        while (rs.next()) {
//            String name = rs.getString("name");
//            System.out.println(name);
//        }
//
//        rs.close();
//        st.close();
//        con.close();
//    }

}
