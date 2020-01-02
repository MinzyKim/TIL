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
			//전체 글 개수 조회해서 페이지 개수 계산해서 리턴
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
					max=rs.getInt(1);//첫번째 인덱스에 해당하는 값 저장
				}
			
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				dbClose(con, stat, null);
			}
			int pageCount=(int) Math.ceil(max/(double)numPerPage);
			pageCount = Math.max(pageCount,1); //0일 때를 방지하기 위해서 max를 1로 설정
			return pageCount; 	
		}
		
		public int insertBbs(BbsVO form) {
			//게시글 저장
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
			// 페이지 번호에 해당하는 게시글 10개 검색해서 리턴
			ArrayList<BbsVO> articles = new ArrayList();
			Connection con = null;
			PreparedStatement stmt = null;
			StringBuffer sql = new StringBuffer();
			int start = (page-1)*numPerPage; //4페이지의 글목록
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
			//글번호유지, 수정 내용에 해당하는거(제목,컨텐츠 수정) 파라미터 vo로 넘기기
			//insert랑 비슷하게, 수행하는쿼리만 달라지게//게시글 저장했던것과 유사, update문만 수행
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
		//글번호, 제목, 코멘트 다 받아서 삭제
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
		//글번호, 페이지번호 가지고 가고, 내용(comment table에)
			int rows=0;
			BbsVO bbs = null;
			ResultSet rs = null;
			PreparedStatement stmt = null;
			Connection con = null;
			String pwd = null;
			String sql = "insert into bbs_comment ( cmid, rbid, writer, idate, contents, password, ip )"
					+ " values ( comment_seq.nextval, ?, ?, sysdate, ?, ?, '70.12' )";
			//bbs 에서 bid를 가져와서 rbid에 넣고, 나머지는 달린대로 집어넣기
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
