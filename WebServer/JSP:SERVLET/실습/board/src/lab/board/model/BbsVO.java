package lab.board.model;

import java.sql.Date;
import java.util.Vector;

public class BbsVO {
	private int bid;
	private String subject;
	private String writer;
	private String password;
	private Date idate;
	private String contents;
	private String email;
	private String ip;
	private String fileYN;
	private int rcount;
	protected Vector<CommentVO> comments;
	
	public Vector<CommentVO> getComments() {
		return comments;
	}
	public void addComment(CommentVO a) {
		comments.add(a);
	}
	public void setComments(Vector<CommentVO> comments) {
		this.comments = comments;
	}
	public BbsVO() {
		comments = new Vector<CommentVO>();
		// TODO Auto-generated constructor stub
	}
	public BbsVO(int bid, String subject, String writer, String password, Date idate, String contents, String email,
			String ip, String fileYN, int rcount, int vcount) {
		this.bid = bid;
		this.subject = subject;
		this.writer = writer;
		this.password = password;
		this.idate = idate;
		this.contents = contents;
		this.email = email;
		this.ip = ip;
		this.fileYN = fileYN;
		this.rcount = rcount;
		this.vcount = vcount;

	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getIdate() {
		return idate;
	}
	public void setIdate(Date idate) {
		this.idate = idate;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getFileYN() {
		return fileYN;
	}
	public void setFileYN(String fileYN) {
		this.fileYN = fileYN;
	}
	public int getRcount() {
		return rcount;
	}
	public void setRcount(int rcount) {
		this.rcount = rcount;
	}
	public int getVcount() {
		return vcount;
	}
	public void setVcount(int vcount) {
		this.vcount = vcount;
	}
	private int vcount;
	
}
