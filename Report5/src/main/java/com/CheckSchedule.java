package com;

import java.sql.*;
import java.util.Scanner;

public class CheckSchedule {

    public static void main(String[] argv){
        try {
            /* Retrieve DB authentication information */
            DatabaseAuthInformation db_info = new DatabaseAuthInformation();
            db_info.parse_auth_info("auth/mysql.auth");

            /* Prepare the URL for DB connection */
            String db_connection_url = String.format("jdbc:mysql://localhost:3306/bus?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetime Code=false&serverTimezone=UTC");
            Connection db_connection = DriverManager.getConnection(db_connection_url, db_info.getUsername(), db_info.getPassword());
            System.out.println("Database Successfully Connected!");

            // Scanner 객체 생성
            Scanner input = new Scanner(System.in);

            // 입력 받기
            System.out.print("승차정류장명: ");
            String stationIn = input.nextLine();
            System.out.print("하차정류장명: ");
            String stationOut = input.nextLine();
            System.out.print("시간: ");
            double time = input.nextDouble();

            // 경유지 여부 체크
            String[] waypoint = {"대전", "대구", "경주", "울산", "부산"};
            boolean boolIn = false; // 출발지가 경유지인지 체크
            boolean boolOut = false; // 도착지가 경유지인지 체크
            for (String point : waypoint){
                if (stationIn.equals(point))
                    boolIn = true;
                if (stationOut.equals(point)){
                    boolOut = true;
                }
            }

            // 출발지(서울) to 도착지(부산) Case
            if (stationIn.equals("서울") && stationOut.equals("부산")) {
                double temp = time;
                String query = "select 노선명, 요일, 출발시간, 도착시간 from 운행스케쥴 where 노선명 = '경부선하행' and 출발시간 >= ?";
                PreparedStatement stmt = db_connection.prepareStatement(query);
                stmt.setDouble(1, temp);
                ResultSet rset = stmt.executeQuery();
                System.out.println("  노선명  요일 출발시간 도착시간");
                if (rset.next() == false)
                    System.out.println("해당하는 시간표가 존재하지 않습니다.");
                while (rset.next()) {
                    System.out.println(rset.getString("노선명") + "  " + rset.getString(2) + "   " + rset.getFloat(3) + "    " + rset.getFloat(4));
                }
            }

            // 도착지(부산) to 출발지(서울) Case
            else if (stationIn.equals("부산") && stationIn.equals("서울")) {
                double temp = time;
                String query = "select 노선명, 요일, 출발시간, 도착시간 from 운행스케쥴 where 노선명 = '경부선상행' and 출발시간 >= ?";
                PreparedStatement stmt = db_connection.prepareStatement(query);
                stmt.setDouble(1, temp);
                ResultSet rset = stmt.executeQuery();
                System.out.println("  노선명  요일 출발시간 도착시간");
                if (rset.next() == false)
                    System.out.println("해당하는 시간표가 존재하지 않습니다.");
                while (rset.next()) {
                    System.out.println(rset.getString("노선명") + "  " + rset.getString(2) + "   " + rset.getFloat(3) + "    " + rset.getFloat(4));
                }
            }

            // 출발지(서울) to 경유지 Case
            else if (boolOut == true && stationIn.equals("서울")){
                String temp1 = stationOut;
                double temp2 = time;
                String query = "select 노선명, 요일, 운행스케쥴.출발시간, 경유.도착시간 from 운행스케쥴,경유 where 운행스케쥴.경유코드 = 경유.경유코드 and 노선명 = '경부선하행' and 정류장명 = ? and 운행스케쥴.출발시간 >= ?";
                PreparedStatement stmt = db_connection.prepareStatement(query);
                stmt.setString(1, temp1);
                stmt.setDouble(2, temp2);
                ResultSet rset = stmt.executeQuery();
                System.out.println("  노선명  요일 출발시간 도착시간");
                if (rset.next() == false)
                    System.out.println("해당하는 시간표가 존재하지 않습니다.");
                while (rset.next()) {
                    System.out.println(rset.getString(1) + "  " + rset.getString(2) + "   " + rset.getFloat(3) + "    " + rset.getFloat(4));
                }
            }

            // 경유지 to 도착지(부산) Case
            else if (boolIn == true && stationOut.equals("부산")){
                String temp1 = stationIn;
                double temp2 = time;
                String query = "select 노선명, 요일, 경유.출발시간, 운행스케쥴.도착시간 from 운행스케쥴,경유 where 운행스케쥴.경유코드 = 경유.경유코드 and 노선명 = '경부선하행' and 정류장명 = ? and 경유.출발시간 >= ?";
                PreparedStatement stmt = db_connection.prepareStatement(query);
                stmt.setString(1, temp1);
                stmt.setDouble(2, temp2);
                ResultSet rset = stmt.executeQuery();
                System.out.println("  노선명  요일 출발시간 도착시간");
                if (rset.next() == false)
                    System.out.println("해당하는 시간표가 존재하지 않습니다.");
                while (rset.next()) {
                    System.out.println(rset.getString(1) + "  " + rset.getString(2) + "   " + rset.getFloat(3) + "    " + rset.getFloat(4));
                }
            }

            // 경유지 to 출발지(서울) Case
            else if (boolIn == true && stationOut.equals("서울")){
                String temp1 = stationIn;
                double temp2 = time;
                String query = "select 노선명, 요일, 경유.출발시간, 운행스케쥴.도착시간 from 운행스케쥴,경유 where 운행스케쥴.경유코드 = 경유.경유코드 and 노선명 = '경부선상행' and 정류장명 = ? and 경유.출발시간 >= ?";
                PreparedStatement stmt = db_connection.prepareStatement(query);
                stmt.setString(1, temp1);
                stmt.setDouble(2, temp2);
                ResultSet rset = stmt.executeQuery();
                System.out.println("  노선명  요일 출발시간 도착시간");
                if (rset.next() == false)
                    System.out.println("해당하는 시간표가 존재하지 않습니다.");
                while (rset.next()) {
                    System.out.println(rset.getString(1) + "  " + rset.getString(2) + "   " + rset.getFloat(3) + "    " + rset.getFloat(4));
                }
            }

            // 도착지(부산) to 경유지 Case
            else if (boolOut == true && stationIn.equals("부산")){
                String temp1 = stationOut;
                double temp2 = time;
                String query = "select 노선명, 요일, 운행스케쥴.출발시간, 경유.도착시간 from 운행스케쥴,경유 where 운행스케쥴.경유코드 = 경유.경유코드 and 노선명 = '경부선상행' and 정류장명 = ? and 운행스케쥴.출발시간 >= ?";
                PreparedStatement stmt = db_connection.prepareStatement(query);
                stmt.setString(1, temp1);
                stmt.setDouble(2, temp2);

                ResultSet rset = stmt.executeQuery();
                System.out.println("  노선명  요일 출발시간 도착시간");
                if (rset.next() == false)
                    System.out.println("해당하는 시간표가 존재하지 않습니다.");
                while (rset.next()) {
                    System.out.println(rset.getString(1) + "  " + rset.getString(2) + "   " + rset.getFloat(3) + "    " + rset.getFloat(4));
                }
            }

            // 경유지 to 경유지 (상행) Case
            else if (boolIn == true && boolOut == true) {
                String temp1 = stationIn;
                String temp2 = stationOut;
                double temp3 = time;
                String query ="select distinct 노선명, 요일, 경1.출발시간, 경2.도착시간\n" +
                        "from 경유 as 경1, 경유 as 경2, 운행스케쥴\n" +
                        "where 경1.경유코드 = 경2.경유코드 and 경1.경유코드 = 운행스케쥴.경유코드 and 경1.정류장명 <> 경2.정류장명 and 경1.출발시간 < 경2.도착시간 and 노선명 = '경부선상행' and 경1.정류장명 = ? and 경2.정류장명 = ? and 경1.출발시간 >= ?";
                PreparedStatement stmt = db_connection.prepareStatement(query);
                stmt.setString(1, temp1);
                stmt.setString(2, temp2);
                stmt.setDouble(3, time);

                ResultSet rset = stmt.executeQuery();
                System.out.println("  노선명  요일 출발시간 도착시간");
                if (rset.next() == false)
                    System.out.println("해당하는 시간표가 존재하지 않습니다.");
                while (rset.next()) {
                    System.out.println(rset.getString(1) + "  " + rset.getString(2) + "   " + rset.getFloat(3) + "    " + rset.getFloat(4));
                }
            }

            // 경유지 to 경유지 (하행) Case
            else if (boolIn == true && boolOut == true) {
                String temp1 = stationIn;
                String temp2 = stationOut;
                double temp3 = time;
                String query ="select distinct 노선명, 요일, 경1.출발시간, 경2.도착시간\n" +
                        "from 경유 as 경1, 경유 as 경2, 운행스케쥴\n" +
                        "where 경1.경유코드 = 경2.경유코드 and 경1.경유코드 = 운행스케쥴.경유코드 and 경1.정류장명 <> 경2.정류장명 and 경1.출발시간 < 경2.도착시간 and 노선명 = '경부선하행' and 경1.정류장명 = ? and 경2.정류장명 = ? and 경1.출발시간 >= ?";
                PreparedStatement stmt = db_connection.prepareStatement(query);
                stmt.setString(1, temp1);
                stmt.setString(2, temp2);
                stmt.setDouble(3, time);

                ResultSet rset = stmt.executeQuery();
                System.out.println("  노선명  요일 출발시간 도착시간");
                if (rset.next() == false)
                    System.out.println("해당하는 시간표가 존재하지 않습니다.");
                while (rset.next()) {
                    System.out.println(rset.getString(1) + "  " + rset.getString(2) + "   " + rset.getFloat(3) + "    " + rset.getFloat(4));
                }
            }


        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
