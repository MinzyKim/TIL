package lab.board.model;

public class FileInfoVO {
	private int fid;
	private int rbid;
	private String filename;
	private String filetype;

	public FileInfoVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FileInfoVO(int fid, int rbid, String filename, String filetype) {
		this.fid = fid;
		this.rbid = rbid;
		this.filename = filename;
		this.filetype = filetype;
	}
	
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public int getRbid() {
		return rbid;
	}
	public void setRbid(int rbid) {
		this.rbid = rbid;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
}
