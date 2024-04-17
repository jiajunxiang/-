package model;

/**
 * 库存实体
 * @author jjx
 */
public class Stock {
    private String name;
    private Integer number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
    //无参构造器
	public Stock() {

	}
    //有参构造函数
    public Stock(String name,Integer number) {
        super();
        this.name=name;
        this.number=number;
	}




}
