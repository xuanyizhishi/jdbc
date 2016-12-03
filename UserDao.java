package web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
	private static Connection conn;
	//查找
	public List searchAllUser(){
		List<User> userList=new ArrayList<User>();
		PreparedStatement psmtPreparedStatement=null;
		ResultSet rs=null;
		try{
			conn=DBCon.getConnection();
		    psmtPreparedStatement=conn.prepareStatement("SELECT * FROM user.login");
			rs=psmtPreparedStatement.executeQuery();
			while(rs.next()){
				User user=new User();
				user.setUserName(rs.getString(1));
				user.setPassWord(rs.getString(2));
				userList.add(user);
			}
			conn.commit();
			return userList;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBCon.free(rs, psmtPreparedStatement, conn);
		}
		return userList;
	}
	//插入
	public boolean saveUser(User user){
		PreparedStatement psmtPreparedStatement=null;
		try {
			conn=DBCon.getConnection();
			psmtPreparedStatement=conn.prepareStatement("insert into user.login values(?,?)");
			psmtPreparedStatement.setString(1, user.getUserName());
			psmtPreparedStatement.setString(2, user.getPassWord());
			psmtPreparedStatement.executeUpdate();
			conn.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBCon.free(null,psmtPreparedStatement, conn);
		}
		return false;
	}
	//修改
	public boolean changeUser(){
		PreparedStatement psmtPreparedStatement=null;
		try {
			conn=DBCon.getConnection();
			psmtPreparedStatement=conn.prepareStatement("update user.login set username=username");
			psmtPreparedStatement.executeUpdate();
			conn.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBCon.free(null,psmtPreparedStatement, conn);
		}
		return false;
	}
	
	public boolean deleteUser(String value,String select){
		PreparedStatement psmtPreparedStatement=null;
		try {
			conn=DBCon.getConnection();
			psmtPreparedStatement=conn.prepareStatement("delete from user.login where "+select+"="+value);
			psmtPreparedStatement.executeUpdate();
			conn.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBCon.free(null,psmtPreparedStatement, conn);
		}
		return false;
	}
	
}
