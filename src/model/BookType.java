package model;
/**
 * 图书类别实体
 * @author green
 *
 */
public class BookType {
	private int id;  //定义ID
	private String bookTypeName;  //定义图书类别名称
	private String bookTypeDesc;  //定义图书类别描述
	//无参构造器
	public BookType() {

	}
	//有参构造函数
	public BookType(String bookTypeName, String bookTypeDesc) {
		super();
		this.bookTypeName = bookTypeName;
		this.bookTypeDesc = bookTypeDesc;
	}
	public BookType(int id, String bookTypeName, String bookTypeDesc) {
		super();
		this.id = id;
		this.bookTypeName = bookTypeName;
		this.bookTypeDesc = bookTypeDesc;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBookTypeName() {
		return bookTypeName;
	}
	public void setBookTypeName(String bookTypeName) {
		this.bookTypeName = bookTypeName;
	}
	public String getBookTypeDesc() {
		return bookTypeDesc;
	}
	public void setBookTypeDesc(String bookTypeDesc) {
		this.bookTypeDesc = bookTypeDesc;
	}
	@Override
	public String toString() {
		return bookTypeName;
	}
}
