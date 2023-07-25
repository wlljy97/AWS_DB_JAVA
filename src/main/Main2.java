package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import config.DBConnectionMgr;

public class Main2 {
	
	public static void main(String[] args) {
		System.out.println(getProductByProductCode(20230707));
		insertProduct(20230707, "상품7");
		System.out.println(getProductByProductCode(20230707));
	}
	
		public static void insertProduct(int productCode, String productName) {
		DBConnectionMgr pool = DBConnectionMgr.getInstance();
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = pool.getConnection();
			
			String sql = "insert into product_tb values(?, ?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, productCode);
			pstmt.setString(2, productName);
			
			pstmt.executeUpdate();
			
			
			System.out.println("Successful");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}
	public static Map<String, Object> getProductByProductCode(int productCode){
		Map<String, Object> resultMap = new HashMap<>();
		
		DBConnectionMgr pool = DBConnectionMgr.getInstance();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = pool.getConnection();
			String sql = "select product_code, product_name from product_tb where product_code = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, productCode);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				resultMap.put("product_code", rs.getInt(1));
				resultMap.put("product_name", rs.getString(2));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return resultMap;
	}
}


//		DBConnectionMgr pool = DBConnectionMgr.getInstance();
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			con = pool.getConnection();
//			
//			String sql = "select * from product_tb";
//			
//			pstmt = con.prepareStatement(sql);
//			
//			rs = pstmt.executeQuery();
//			
//			System.out.println("코드\t|\t이름");
//			
//			while(rs.next()) {
//				
//				System.out.println(rs.getString(1) + "\t|\t" + rs.getString(2));
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			pool.freeConnection(con, pstmt);
//		}
//		
//		try {
//			con = pool.getConnection();
//			
//			String sql = "insert into product_tb values(?, ?)";
//			
//			pstmt = con.prepareStatement(sql);
//			
//			pstmt.setString(1, "20230706");
//			pstmt.setString(2, "상품6");
//			
//			
//			int successCount = pstmt.executeUpdate();
//			System.out.println("insert 성공 횟수 : " + successCount);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			pool.freeConnection(con, pstmt);
//		}
