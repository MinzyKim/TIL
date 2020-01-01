package lab.spring.orm.model;

public class UserVO {

		private String userid;
		private String userpwd;
		private String username;
		private String phone;
		private String email;
		private String address;
		private String job;
		
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getUserid() {
			return userid;
		}
		public void setUserid(String userid) {
			this.userid = userid;
		}
		public String getUserpwd() {
			return userpwd;
		}
		public void setUserpwd(String userpwd) {
			this.userpwd = userpwd;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getJob() {
			return job;
		}
		public void setJob(String job) {
			this.job = job;
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "userid:" + getUserid() + "\n" +
					"userpwd:" + getUserpwd() + "\n" +
					"username:" + getUsername() + "\n" +
					"phone:" + getPhone() + "\n" +
					"email:" + getEmail() + "\n" +
					"address:" + getAddress() + "\n" +
					"job:"+ getJob() + "\n";
		}
		

	}
