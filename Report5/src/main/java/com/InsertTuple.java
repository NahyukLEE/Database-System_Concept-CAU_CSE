package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertTuple {

    // 정류장 Table 튜플 삽입 메소드
    static public String stationInsert(String s1, String s2){
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("insert into " + "정류장" + " values(")
                .append("'"+s1+"'" + ",")
                .append("'"+s2+"'" + ");").toString();
        return sql;
    }

    // 경유 Table 튜플 삽입 메소드
    static public String wayPointInsert(String s1, String s2, double f1, double f2){
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("insert into " + "경유" + " values(")
                .append("'"+s1+"'" + ",")
                .append("'"+s2+"'" + ",")
                .append(f1 + ",")
                .append(f2 + ");").toString();
        return sql;
    }

    // 노선 Table 튜플 삽입 메소드
    static public String routeInsert(String s1, String s2, String s3){
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("insert into " + "노선" + " values(")
                .append("'"+s1+"'" + ",")
                .append("'"+s2+"'" + ",")
                .append("'"+s3+"'" + ");").toString();
        return sql;
    }

    // 운행스케쥴 Table 튜플 삽입 메소드
    static public String scheduleInsert(String s1, String s2, double f1, double f2, String s3){
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("insert into " + "운행스케쥴" + " values(")
                .append("'"+s1+"'" + ",")
                .append("'"+s2+"'" + ",")
                .append(f1 + ",")
                .append(f2 + ",")
                .append("'"+s3+"'" + ");").toString();
        return sql;
    }

    public static void main(String[] argv) {

        /* Retrieve DB authentication information */
        DatabaseAuthInformation db_info = new DatabaseAuthInformation();
        db_info.parse_auth_info("auth/mysql.auth");

        /* Prepare the URL for DB connection */
        String db_connection_url = String.format("jdbc:mysql://localhost:3306/bus?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetime Code=false&serverTimezone=UTC");
        try {
            // DB 연결
            Connection db_connection = DriverManager.getConnection(db_connection_url, db_info.getUsername(), db_info.getPassword());
            System.out.println("Database Successfully Connected!");
            Statement stmt = db_connection.createStatement();

            // Empty String 초기화
            String sql;

            // 정류장 Table 튜블 삽입
            sql = stationInsert("서울", "서울 용산구");
            stmt.executeUpdate(sql);
            sql = stationInsert("대전", "대전 동구");
            stmt.executeUpdate(sql);
            sql = stationInsert("대구", "대구 동구");
            stmt.executeUpdate(sql);
            sql = stationInsert("경주", "경북 경주시");
            stmt.executeUpdate(sql);
            sql = stationInsert("울산", "울산 울주군");
            stmt.executeUpdate(sql);
            sql = stationInsert("부산", "부산 동구");
            stmt.executeUpdate(sql);

            // 경유 Table 튜블 삽입
            sql = wayPointInsert("A", "대전", 9.3, 9.4);
            stmt.executeUpdate(sql);
            sql = wayPointInsert("A", "대구",11.1, 11.2);
            stmt.executeUpdate(sql);
            sql = wayPointInsert("B", "대구", 18, 18.1);
            stmt.executeUpdate(sql);
            sql = wayPointInsert("B", "대전", 19.4, 19.5);
            stmt.executeUpdate(sql);
            sql = wayPointInsert("C", "대구", 11.1, 11.2);
            stmt.executeUpdate(sql);
            sql = wayPointInsert("C", "울산", 12.2, 12.3);
            stmt.executeUpdate(sql);
            sql = wayPointInsert("D", "울산", 17.4, 17.5);
            stmt.executeUpdate(sql);
            sql = wayPointInsert("D", "대구", 19, 19.1);
            stmt.executeUpdate(sql);
            sql = wayPointInsert("E", "대전", 10.3, 10.4);
            stmt.executeUpdate(sql);
            sql = wayPointInsert("E", "경주", 13, 13.1);
            stmt.executeUpdate(sql);
            sql = wayPointInsert("F", "경주", 18.1, 18.2);
            stmt.executeUpdate(sql);
            sql = wayPointInsert("F", "대전", 20.3, 20.4);
            stmt.executeUpdate(sql);
            sql = wayPointInsert("G", "대전", 14.3, 14.4);
            stmt.executeUpdate(sql);
            sql = wayPointInsert("G", "울산", 16.5, 17);
            stmt.executeUpdate(sql);
            sql = wayPointInsert("H", "울산", 19, 19.1);
            stmt.executeUpdate(sql);
            sql = wayPointInsert("H", "대전", 22, 22.1);
            stmt.executeUpdate(sql);
            sql = wayPointInsert("I", "대전", 13.3, 13.4);
            stmt.executeUpdate(sql);
            sql = wayPointInsert("I", "대구", 15.1, 15.2);
            stmt.executeUpdate(sql);
            sql = wayPointInsert("J", "대구", 20, 20.1);
            stmt.executeUpdate(sql);
            sql = wayPointInsert("J", "대전", 21.2, 21.3);
            stmt.executeUpdate(sql);

            // 노선 Table 튜플 삽입
            sql = routeInsert("경부선상행", "부산", "서울");
            stmt.executeUpdate(sql);
            sql = routeInsert("경부선하행", "서울", "부산");
            stmt.executeUpdate(sql);

            // 운행스케쥴 Table 튜플 삽입
            sql = scheduleInsert("경부선상행", "월", 16, 21.2, "B");
            stmt.executeUpdate(sql);
            sql = scheduleInsert("경부선하행", "월", 8, 13.2, "A");
            stmt.executeUpdate(sql);
            sql = scheduleInsert("경부선상행", "화", 16.3, 21.5, "D");
            stmt.executeUpdate(sql);
            sql = scheduleInsert("경부선하행", "화", 8.3, 13.5, "C");
            stmt.executeUpdate(sql);
            sql = scheduleInsert("경부선상행", "수", 17, 22.2, "F");
            stmt.executeUpdate(sql);
            sql = scheduleInsert("경부선하행", "수", 9, 14.2, "E");
            stmt.executeUpdate(sql);
            sql = scheduleInsert("경부선상행", "목", 16.3, 21.5, "D");
            stmt.executeUpdate(sql);
            sql = scheduleInsert("경부선하행", "목", 8.3, 13.5, "C");
            stmt.executeUpdate(sql);
            sql = scheduleInsert("경부선상행", "금", 17, 22.2, "F");
            stmt.executeUpdate(sql);
            sql = scheduleInsert("경부선하행", "금", 9, 14.2, "E");
            stmt.executeUpdate(sql);
            sql = scheduleInsert("경부선상행", "토", 18, 23.2, "H");
            stmt.executeUpdate(sql);
            sql = scheduleInsert("경부선하행", "토", 13, 18.2, "G");
            stmt.executeUpdate(sql);
            sql = scheduleInsert("경부선상행", "일", 18.3, 23.5, "J");
            stmt.executeUpdate(sql);
            sql = scheduleInsert("경부선하행", "일", 12, 17.2, "I");
            stmt.executeUpdate(sql);

        }

        catch (SQLException e) {
            e.printStackTrace();
        }


    }
}