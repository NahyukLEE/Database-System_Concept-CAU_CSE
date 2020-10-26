package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {

    public static void main(String[] argv) {

        /* Retrieve DB authentication information */
        DatabaseAuthInformation db_info = new DatabaseAuthInformation();
        db_info.parse_auth_info("auth/mysql.auth");

        /* Prepare the URL for DB connection */
        String db_connection_url = String.format("jdbc:mysql://localhost:3306/bus?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetime Code=false&serverTimezone=UTC");
        try {
            //
            Connection db_connection = DriverManager.getConnection(db_connection_url, db_info.getUsername(), db_info.getPassword());
            System.out.println("Database Successfully Connected!");
            Statement stmt = db_connection.createStatement();

            // 정류장 테이블 생성
            StringBuilder sb1 = new StringBuilder();
            String sql1 = sb1.append("create table 정류장(")
                    .append("정류장명 varchar(20),")
                    .append("주소 varchar(30),")
                    .append("primary key (정류장명)")
                    .append(");").toString();
            stmt.execute(sql1);

            // 경유 테이블 생성
            StringBuilder sb2 = new StringBuilder();
            String sql2 = sb2.append("create table 경유(")
                    .append("경유코드 varchar(5),")
                    .append("정류장명 varchar(20),")
                    .append("도착시간 float(20),")
                    .append("출발시간 float(20),")
                    .append("primary key (경유코드, 정류장명),")
                    .append("foreign key(정류장명) references 정류장 (정류장명)")
                    .append(");").toString();
            stmt.execute(sql2);

            // 노선 테이블 생성
            StringBuilder sb3 = new StringBuilder();
            String sql3 = sb3.append("create table 노선(")
                    .append("노선명 varchar(20),")
                    .append("출발정류장 varchar(20),")
                    .append("도착정류장 varchar(20),")
                    .append("primary key (노선명),")
                    .append("foreign key(출발정류장) references 정류장 (정류장명),")
                    .append("foreign key(도착정류장) references 정류장 (정류장명)")
                    .append(");").toString();
            stmt.execute(sql3);

            // 운행스케쥴 테이블 생성
            StringBuilder sb4 = new StringBuilder();
            String sql4 = sb4.append("create table 운행스케쥴(")
                    .append("노선명 varchar(20),")
                    .append("요일 varchar(10),")
                    .append("출발시간 float(20),")
                    .append("도착시간 float(20),")
                    .append("경유코드 varchar(5),")
                    .append("primary key (노선명, 요일),")
                    .append("foreign key(노선명) references 노선 (노선명),")
                    .append("foreign key(경유코드) references 경유 (경유코드)")
                    .append(");").toString();
            stmt.execute(sql4);

        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}