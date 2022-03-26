package model;

public class User {
	  protected int id;
	    protected String fname;
	    protected String lname;
	    protected String username;
	    protected String password;
	    protected int phone;
	    protected String email;
	    protected String description;
	    protected String createddate;
	    protected String updatetime;
	    protected String pin;
	    
		public User() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
		public User(int id, String fname, String lname, int phone, String email, String description) {
			super();
			this.id = id;
			this.fname = fname;
			this.lname = lname;
			this.phone = phone;
			this.email = email;
			this.description = description;
		}


		public User(String fname, String lname, int phone, String email, String description) {
			super();
			this.fname = fname;
			this.lname = lname;
			this.phone = phone;
			this.email = email;
			this.description = description;
		}

		public User(int id, String username) {
			super();
			this.id = id;
			this.username = username;
		}

		public User(String username, String password, String email, String pin) {
			super();
			this.username = username;
			this.password = password;
			this.email = email;
			this.pin = pin;
		}


		public User(String username, String password, String email) {
			super();
			this.username = username;
			this.password = password;
			this.email = email;
		}

		public User(int id, String fname, String lname, String username, String password, int phone, String email,
				String description, String createddate, String updatetime) {
			super();
			this.id = id;
			this.fname = fname;
			this.lname = lname;
			this.username = username;
			this.password = password;
			this.phone = phone;
			this.email = email;
			this.description = description;
			this.createddate = createddate;
			this.updatetime = updatetime;
		}
		
		public User(int id) {
			super();
			this.id = id;
		}


		public String getPin() {
			return pin;
		}


		public void setPin(String pin) {
			this.pin = pin;
		}


		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getFname() {
			return fname;
		}
		public void setFname(String fname) {
			this.fname = fname;
		}
		public String getLname() {
			return lname;
		}
		public void setLname(String lname) {
			this.lname = lname;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public int getPhone() {
			return phone;
		}
		public void setPhone(int phone) {
			this.phone = phone;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getCreateddate() {
			return createddate;
		}
		public void setCreateddate(String createddate) {
			this.createddate = createddate;
		}
		public String getUpdatetime() {
			return updatetime;
		}
		public void setUpdatetime(String updatetime) {
			this.updatetime = updatetime;
		}
	  
	 
}
