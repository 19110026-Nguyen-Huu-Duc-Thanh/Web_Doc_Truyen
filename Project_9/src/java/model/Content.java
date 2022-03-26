package model;

public class Content {
	 protected int id;
	 protected String title;
	 protected String brief;
	 protected String content;
	 protected String createddate;
	 protected String updatetime;
	 protected int sort;
	 protected int authorid;
		private int NumPage = 0;
		private int NumId = 0;
	public Content() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Content(int id, String title, String brief, String content, int sort, int authorid) {
		super();
		this.id = id;
		this.title = title;
		this.brief = brief;
		this.content = content;
		this.sort = sort;
		this.authorid = authorid;
	}

	public Content(int id, String title, String brief, String content, String createddate, String updatetime, int sort,
			int authorid) {
		super();
		this.id = id;
		this.title = title;
		this.brief = brief;
		this.content = content;
		this.createddate = createddate;
		this.updatetime = updatetime;
		this.sort = sort;
		this.authorid = authorid;
	}
	
	public Content(String title, String brief, String content, int authorid) {
		super();
		this.title = title;
		this.brief = brief;
		this.content = content;
		this.authorid = authorid;
	}
	
	public Content(int id, String title, String brief, String createddate) {
		super();
		this.id = id;
		this.title = title;
		this.brief = brief;
		this.createddate = createddate;
	}

	public int getNumPage() {
		return NumPage;
	}

	public void setNumPage(int numPage) {
		NumPage = numPage;
	}

	public int getNumId() {
		return NumId;
	}

	public void setNumId(int numId) {
		NumId = numId;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getAuthorid() {
		return authorid;
	}
	public void setAuthorid(int authorid) {
		this.authorid = authorid;
	}
	 
}
