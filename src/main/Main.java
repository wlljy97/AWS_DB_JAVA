package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import config.DBConnectionMgr;

public class Main {
	
	public static void main(String[] args) {
		
		DBConnectionMgr pool = DBConnectionMgr.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// java 와 db를 연결함
			con = pool.getConnection(); // 데이터베이스의 연결이 끊길 수도 있기 때문에 예외처리
			
			// 실행할 쿼리문 작성
			String sql = "select * from user_tb";
			
			// 작성한 쿼리문을 가공
			pstmt = con.prepareStatement(sql); // 연결된 데이터베이스에 sql을 넘겨줌
			
			// 가공된 쿼리문을 실행 -> 결과를 ResultSet으로 변환
			rs = pstmt.executeQuery();
			
			// 결과가 담긴 ResultSet을 반복작업을 통해 데이터 조회
			System.out.println("번호\t|\t아이디\t|\t비밀번호");
			while(rs.next()) { // rs는 list형태로 되어있다. 결과를 set형태로 담아둠 next가 호출되면 행을 가져옴
				// getInt() -> 정수
				// getString() -> 문자열
				System.out.println(rs.getInt(1) + " \t|\t " + rs.getString(2) + " \t|\t " + rs.getString(3));
				
//				System.out.println(rs.getString(2)); // 2번 컬럼을 나타낸다. 열을 가져온다
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 생성된 rs, pstmt, con 객체 소멸(데이터베이스의 연결 해제)
			pool.freeConnection(con, pstmt, rs); // 예외처리 에서 끊기면 안되기 때문에 마지막에 나타낸다.
		}
		
		// 여기까지 한 세트
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		try {
			// 데이터베이스 연결
			con = pool.getConnection();
			
			// 쿼리문 작성
			String sql = "insert into user_tb values(0, ?, ?)";
			// '0' 은 autoIncrement 값 user_id 값 
			// '?' 부분에 대체를 바로 시켜줄려면 \'ttt\' , \'1234\' 식으로 나타내야한다.
			// pstmt 부분에는 문자열을 가지고 있다.
			// String sql = "insert into user_tb values(0, ?, ?, ?)";
			
			
			// 쿼리문 가공 준비
			pstmt = con.prepareStatement(sql);
			
			// 쿼리문 가공
			pstmt.setString(1, "ttt"); // 쿼리문 작성의 첫번째 '?' 대체
			pstmt.setString(2, "1234"); // 쿼리문 작성의 두번째 '?' 대체
			// 3번째 '?'가 int값이면 pstmt.setInt(3, 10); 이렇게 나타낼 수 있다.
			
			
			// 쿼리문 실행
			int successCount = pstmt.executeUpdate();
			System.out.println("insert 성공 횟수 : " + successCount);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		
		// 여기까지 한세트
		////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		try {
			// java 와 db를 연결함
			con = pool.getConnection(); // 데이터베이스의 연결이 끊길 수도 있기 때문에 예외처리
			
			// 실행할 쿼리문 작성
			String sql = "select * from user_tb";
			
			// 작성한 쿼리문을 가공
			pstmt = con.prepareStatement(sql); // 연결된 데이터베이스에 sql을 넘겨줌
			
			// 가공된 쿼리문을 실행 -> 결과를 ResultSet으로 변환
			rs = pstmt.executeQuery();
			
			// 결과가 담긴 ResultSet을 반복작업을 통해 데이터 조회
			System.out.println("번호\t|\t아이디\t|\t비밀번호");
			while(rs.next()) { // rs는 list형태로 되어있다. 결과를 set형태로 담아둠 next가 호출되면 행을 가져옴
				// getInt() -> 정수
				// getString() -> 문자열
				System.out.println(rs.getInt(1) + " \t|\t " + rs.getString(2) + " \t|\t " + rs.getString(3));
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 생성된 rs, pstmt, con 객체 소멸(데이터베이스의 연결 해제)
			pool.freeConnection(con, pstmt, rs); // 예외처리 에서 끊기면 안되기 때문에 마지막에 나타낸다.
		}
		// 여기까지 한세트
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	}
}
