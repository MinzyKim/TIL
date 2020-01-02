package lab.board.model;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class BbsDAO {
		
	public Connection dbCon() {
			Connection con=null;
			try {
				Properties prop = new Properties();
				prop.load(new FileInputStream("C:/workspace2/web2/WebContent/WEB-INF/db.properties"));
				Class.forName(prop.getProperty("driver"));
				con = DriverManager.getConnection(prop.getProperty("url"),
						prop.getProperty("user"), prop.getProperty("pwd"));
			}catch(Exception e) {
				e.printStackTrace();
			}
			return con;
		}
		public void dbClose(Connection con, Statement stat, ResultSet rs) {
			try {
				if(rs != null) rs.close();
				if(stat != null) stat.close();
				if(con != null) con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		public int getPageCount(int numPerPage) {
			//��ü �� ���� ��ȸ�ؼ� ������ ���� ����ؼ� ����
			int max=0;
			Connection con = null;
			PreparedStatement stat = null;
			String sql = "select count(*) from bbs ";
			ResultSet rs=null;
			try {
				con=dbCon();
				stat = con.prepareStatement(sql);
				rs= stat.executeQuery();
				if(rs.next()) {
					max=rs.getInt(1);//ù��° �ε����� �ش��ϴ� �� ����
				}
			
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				dbClose(con, stat, null);
			}
			int pageCount=(int) Math.ceil(max/(double)numPerPage);
			pageCount = Math.max(pageCount,1); //0�� ���� �����ϱ� ���ؼ� max�� 1�� ����
			return pageCount; 	
		}
		
		public int insertBbs(BbsVO form) {
			//�Խñ� ����
			int rows=0;
			StringBuffer sql = null;
			int cnt = -1;
			PreparedStatement stmt = null;
			Connection con = null;
			ResultSet rs=null;
			
			sql= new StringBuffer();
			sql.append("insert into bbs (bid, subject, writer, ")
				.append(" password, idate, fileyn, contents, email, ip) ")
				.append(" values (bbs_seq.nextval, ?, ?, ?, sysdate, ")
				.append(" ?, ?, ?, ?)");
			
			try {
				con=dbCon();
				stmt=con.prepareStatement(sql.toString());
				stmt.setString(1, form.getSubject() );
				stmt.setString(2, form.getWriter() );
				stmt.setString(3,form.getPassword() );
				stmt.setString(4,form.getFileYN() );
				stmt.setString(5,form.getContents());
				stmt.setString(6,form.getEmail() );
				stmt.setString(7,form.getIp() );
				rows=stmt.executeUpdate();
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				dbClose(con, stmt, rs);
			}
			return rows;
		}
			
		
		public ArrayList<BbsVO> getBbsList(int page, int numPerPage){
			// ������ ��ȣ�� �ش��ϴ� �Խñ� 10�� �˻��ؼ� ����
			ArrayList<BbsVO> articles = new ArrayList();
			Connection con = null;
			PreparedStatement stmt = null;
			StringBuffer sql = new StringBuffer();
			int start = (page-1)*numPerPage; //4�������� �۸��
			int end = page * numPerPage;
			
			sql.append("select num, bid, subject, writer, idate, rcount ");
				sql.append(" from (select rownum num, bid, subject, writer, idate, rcount ");
				sql.append(" from ( select bid, subject, writer, idate, rcount ");
				sql.append(" from bbs ");
				sql.append(" order by bid )");
				sql.append(" order by num desc )");
				sql.append(" where num > ? and num <= ? ");
			ResultSet rs=null;
			try {
				con=dbCon();
				stmt = con.prepareStatement(sql.toString());
				stmt.setInt(1, start);
				stmt.setInt(2, end);
				rs=stmt.executeQuery();
				while(rs.next()) {
					BbsVO bbs = new BbsVO();
					bbs.setBid(rs.getInt("bid"));
					bbs.setSubject(rs.getString("subject"));
					bbs.setWriter(rs.getString("writer"));
					bbs.setIdate(rs.getDate("idate"));
					bbs.setRcount(rs.getInt("rcount"));
					articles.add(bbs);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				dbClose(con, stmt, rs);
			}
			return articles;
		}
	
		public BbsVO getArticle(int bid) {
			
		Connection con = null;
		PreparedStatement stmt=null;
		ResultSet rs = null;
		BbsVO bbs = null;
		StringBuffer sql=new StringBuffer();
		sql.append("select bid, subject, writer, password, idate, ")
			.append(" contents, email, fileyn, ip, rcount, vcount ")
			.append(" from bbs ")
			.append(" where bid = ? ");
		try {
			con=dbCon();
			stmt=con.prepareStatement( sql.toString(),
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_UPDATABLE);
			stmt.setInt(1, bid);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				bbs = new BbsVO();
				bbs.setBid(rs.getInt("bid"));
				bbs.setSubject(rs.getString("subject"));
				bbs.setWriter(rs.getString("writer"));
				bbs.setPassword(rs.getString("password"));
				bbs.setIdate(rs.getDate("idate"));
				
				bbs.setFileYN(rs.getString("fileyn"));
				bbs.setContents(rs.getString("contents"));
				bbs.setEmail(rs.getString("email"));
				
				bbs.setIp(rs.getString("ip"));
				int rcount = rs.getInt("rcount");
				rs.updateLong("rcount", rcount+1);
				rs.updateRow();
		
				bbs.setRcount(rcount+1);
				bbs.setVcount(rs.getInt("vcount"));		
				}
		
		StringBuffer sql2 = new StringBuffer();
		sql2.append("select cmid, writer, idate, contents,")
			.append(" password, ip from bbs_comment ")
			.append(" where rbid = ? ");
		
		stmt = con.prepareStatement(sql2.toString());
		stmt.setInt(1,bid);
		rs=stmt.executeQuery();
		while(rs.next()) {
			CommentVO ba = new CommentVO();
			ba.setCmid(rs.getInt("cmid"));
			ba.setRbid(bid);
			ba.setWriter(rs.getString("writer"));
			ba.setIdate(rs.getDate("idate"));
			ba.setContents(rs.getString("contents"));
			ba.setPassword(rs.getString("password"));
			ba.setIp(rs.getString("ip"));
			bbs.addComment(ba);
		}
		System.out.println();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose(con, stmt, rs);
		}
		return bbs;
		}
		
		public int updateBbs(BbsVO bbs) {
			//�۹�ȣ����, ���� ���뿡 �ش��ϴ°�(����,������ ����) �Ķ���� vo�� �ѱ��
			//insert�� ����ϰ�, �����ϴ������� �޶�����//�Խñ� �����ߴ��Ͱ� ����, update���� ����
			String sql = null;
			int cnt = -1;
			PreparedStatement stmt = null;
			Connection con = null;
			sql = "update bbs set subject=?, contents=? where bid=? ";
			try {
				con=dbCon();
				stmt=con.prepareStatement(sql.toString());
				stmt.setString(1,  bbs.getSubject());
				stmt.setString(2, bbs.getContents());
				stmt.setInt(3, bbs.getBid());
				cnt=stmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				dbClose(con, stmt, null);
			}
			return cnt;
		}
		
		public int deleteBbs(int bid) {
		//�۹�ȣ, ����, �ڸ�Ʈ �� �޾Ƽ� ����
			String sql1 = null;
			String sql2=null;
			int rows=0;
			PreparedStatement stmt = null;
			Connection con = null;
			sql1 = "delete from bbs where bid=? ";
			sql2 = "delete from bbs_comment where rbid=? ";
			try {
				con=dbCon();
				stmt=con.prepareStatement(sql2);
				stmt.setInt(1, bid);
				rows=stmt.executeUpdate();
				
				stmt=con.prepareStatement(sql1);
				stmt.setInt(1, bid);
				rows=stmt.executeUpdate();
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				dbClose(con, stmt, null);
			}
			return rows;
			
		}
		
		public String getBbsPassword(int bid) {
			ResultSet rs = null;
			PreparedStatement stmt = null;
			Connection con = null;
			String pwd = null;
			String sql = "select password from bbs where bid = ? ";
			try {
				con=dbCon();
				stmt=con.prepareStatement(sql);
				stmt.setInt(1, bid);
				rs=stmt.executeQuery();
				if(rs.next()) {
					pwd = rs.getString("password");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				dbClose(con, stmt, null);
			}
			return pwd;
		}
		
		public int insertBbsComment(CommentVO comment, int bid) {
		//�۹�ȣ, ��������ȣ ������ ����, ����(comment table��)
			int rows=0;
			BbsVO bbs = null;
			ResultSet rs = null;
			PreparedStatement stmt = null;
			Connection con = null;
			String pwd = null;
			String sql = "insert into bbs_comment ( cmid, rbid, writer, idate, contents, password, ip )"
					+ " values ( comment_seq.nextval, ?, ?, sysdate, ?, ?, '70.12' )";
			//bbs ���� bid�� �����ͼ� rbid�� �ְ�, �������� �޸���� ����ֱ�
			try {
				con=dbCon();
				stmt=con.prepareStatement(sql);
				stmt.setInt(1, bid);
				stmt.setString(2, comment.getWriter());
				stmt.setString(3,  comment.getContents());
				stmt.setString(4, comment.getPassword());
		
				rows=stmt.executeUpdate();
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				dbClose(con, stmt, rs);
			}
			return rows;
					
		}
		
		public int deleteBbsComment(int cmid) {
			int rows=0;
			CommentVO comment=null;
			ResultSet rs = null;
			PreparedStatement stmt = null;
			Connection con = null;
			String sql="delete from bbs_comment where cmid = ? ";
			
			try {
				con=dbCon();
				stmt=con.prepareStatement(sql);
				stmt.setInt(1, comment.getCmid());
				rows=stmt.executeUpdate();
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				dbClose(con, stmt, rs);
			}
			return rows;
		}
		
		public ArrayList<BbsVO> searchBbs(int page, int numPerPage, String key, String word) {
			String sql = null;
			ArrayList<BbsVO> articles = new ArrayList();
			ResultSet rs = null;
			int start = (page-1)*numPerPage;
			int end = page * numPerPage;
			PreparedStatement stmt = null;
			Connection con = null;
			sql ="select num , bid , subject, writer, idate, rcount " + 
					" from (select rownum num,  bid  , subject, writer, idate, rcount " + 
					" from (select bid  , subject, writer, idate, rcount " + 
					" from bbs where" + key +" like '"+"%"+word+"%' "+  
					" order by bid ) " +  
					" order by num desc ) " + 
					" where num > ? and num<= ? ";
			try {
				con=dbCon();
				stmt=con.prepareStatement(sql.toString());
				stmt.setInt(1, start);
				stmt.setInt(2, end);
			
				rs= stmt.executeQuery();
				while(rs.next()) {
					BbsVO bbs = new BbsVO();
					bbs.setBid(rs.getInt("bid"));
					bbs.setSubject(rs.getString("subject"));
					bbs.setWriter(rs.getString("writer"));
					bbs.setIdate(rs.getDate("idate"));
					bbs.setRcount(rs.getInt("rcount"));
					articles.add(bbs);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				dbClose(con, stmt, null);
			}
			return articles;
		}
}
