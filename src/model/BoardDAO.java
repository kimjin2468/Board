package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BoardDAO {
	
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public void getCon() {
		
		try {
			
			con = ConnectionUtil.getConnection();
			
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public void insertBoard(BoardBean bean) {
		
		getCon();
		
		int ref=0;  //글 그룹, 쿼리를 실행시켜서 가장큰 ref갑을 가져온 후 +1더해준다
		int re_step=1;  //부모글은 무조건 1
		int re_level=1;
		
		try {
			String refsql = "select max(ref) from board";
			
			pstmt = con.prepareStatement(refsql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				ref = rs.getInt(1)+1;				
			}
			
			String sql = "insert into board(writer,email,subject,password,ref,re_step,re_level,readcount,content) values(?,?,?,?,?,?,?,0,?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, bean.getWriter());
			pstmt.setString(2, bean.getEmail());
			pstmt.setString(3, bean.getSubject());
			pstmt.setString(4, bean.getPassword());
			pstmt.setInt(5, ref);
			pstmt.setInt(6, re_step);
			pstmt.setInt(7, re_level);
			pstmt.setString(8, bean.getContent());
			
			pstmt.executeUpdate();
			
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public ArrayList<BoardBean> getAllboard(){
		
		ArrayList<BoardBean> arr = new ArrayList<>();
		
		getCon();
		
		try {
			
			String sql = "select * from board order by ref desc , re_step asc";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				BoardBean bean = new BoardBean();
				bean.setNo(rs.getInt(1));			
				bean.setWriter(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setSubject(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDate(rs.getString(6));
				bean.setRef(rs.getInt(7));
				bean.setRe_step(rs.getInt(8));
				bean.setRe_level(rs.getInt(9));
				bean.setReadcount(rs.getInt(10));
				bean.setContent(rs.getString(11));
				
				arr.add(bean);
			}
			
			con.close();			
			
		} catch (Exception e) {e.printStackTrace();}		
		return arr;
	}
	
	
	public BoardBean getOneBoard(int no) {
		
		BoardBean bean = new BoardBean();
		
		getCon();
		
		try {
			
			String readsql = "update board set readcount = readcount +1 where no=?";
			
			pstmt = con.prepareStatement(readsql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
			
			String sql = "select * from board where no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bean.setNo(rs.getInt(1));			
				bean.setWriter(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setSubject(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDate(rs.getString(6));
				bean.setRef(rs.getInt(7));
				bean.setRe_step(rs.getInt(8));
				bean.setRe_level(rs.getInt(9));
				bean.setReadcount(rs.getInt(10));
				bean.setContent(rs.getString(11));				
			}
			
			con.close();			
		} catch (Exception e) {e.printStackTrace();}
		
		return bean;
	}
	
	
	public void reWriteBoard(BoardBean bean) {
		
		//부모글 그룹과 글레벨 글스탭 읽어옴
		int ref = bean.getRef();
		int re_step = bean.getRe_step();
		int re_level = bean.getRe_level();
		
		getCon();
		
		try {
			
			/////핵심 코드///////
			 //부모글보다 큰 re_level의 값을 전부 1씩 증가시켜줌
			String levelsql = "update board set re_level=re_level+1 where ref=? and re_level > ?";
			
			pstmt = con.prepareStatement(levelsql);
			pstmt.setInt(1, ref);
			pstmt.setInt(2, re_level);
			
			pstmt.executeUpdate();
			
			String sql = "insert into board(writer,email,subject,password,ref,re_step,re_level,readcount,content) values(?,?,?,?,?,?,?,0,?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, bean.getWriter());
			pstmt.setString(2, bean.getEmail());
			pstmt.setString(3, bean.getSubject());
			pstmt.setString(4, bean.getPassword());
			pstmt.setInt(5, bean.getRef());  //부모의 ref값을 그대로 넣어줌 (원본 게시글)
			pstmt.setInt(6, re_step+1);  //답글이기에 부모 글에 +1 더해서 넣어줌.
			pstmt.setInt(7, re_level+1);
			pstmt.setString(8, bean.getContent());
			
			pstmt.executeUpdate();
			
			con.close();			
		} catch (Exception e) {e.printStackTrace();}
	
	}
	
	
	public void updateBoard(BoardBean bean) {
		
		getCon();
		
		try {
			
			String sql = "update board set content = ? where no = ?";
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, bean.getContent());
			pstmt.setInt(2, bean.getNo());
			
			pstmt.executeUpdate();
			
			con.close();
			
		} catch (Exception e) {e.printStackTrace();}
	}
	
}
