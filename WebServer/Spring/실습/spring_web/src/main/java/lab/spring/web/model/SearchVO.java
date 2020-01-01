package lab.spring.web.model;

public class SearchVO {

	private String code;
	private String codeName;
	
	
	public SearchVO() {
		super();
	}
	public SearchVO(String code, String codeName) {
		super();
		this.code = code;
		this.codeName = codeName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	
	
}
