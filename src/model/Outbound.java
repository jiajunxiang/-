package model;
/**
 * 货物出库实体
 * @author jjx
 *
 */
public class Outbound {
    private Integer id;
    private String goodsName;
    private Integer goodsCategory;
    private Integer goodsNumber;
    private String goodsDate;
    /*
    private int id;//定义ID
    private String addGoodsName;//定义货物名称
    private int addGoodsCategory;//定义货物规格
    private int addGoodsNumber;//定义货物数量
    private String addGoodsDate;
    */



    //无参构造器
	public Outbound() {

	}
    //有参构造函数
    /*
	public Warehousing(String addGoodsName, int addGoodsCategory,int addGoodsNumber,String addGoodsDate) {
		super();
		this.addGoodsName = addGoodsName;
		this.addGoodsCategory = addGoodsCategory;
        this.addGoodsNumber=addGoodsNumber;
        this.addGoodsDate=addGoodsDate;
	}
    public Warehousing(int id, String addGoodsName, int addGoodsCategory,int addGoodsNumber,String addGoodsDate) {
		super();
		this.id = id;
		this.addGoodsName = addGoodsName;
		this.addGoodsCategory = addGoodsCategory;
        this.addGoodsNumber=addGoodsNumber;
        this.addGoodsDate=addGoodsDate;
	}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getAddGoodsName() {
        return addGoodsName;
    }

    public void setAddGoodsName(String addGoodsName) {
        this.addGoodsName = addGoodsName;
    }

    public int getAddGoodsCategory() {
        return addGoodsCategory;
    }

    public void setAddGoodsCategory(int addGoodsCategory) {
        this.addGoodsCategory = addGoodsCategory;
    }

    public int getAddGoodsNumber() {
        return addGoodsNumber;
    }

    public void setAddGoodsNumber(int addGoodsNumber) {
        this.addGoodsNumber = addGoodsNumber;
    }
     public String getAddGoodsDate() {
        return addGoodsDate;
    }

    public void setAddGoodsDate(String addGoodsDate) {
        this.addGoodsDate = addGoodsDate;
    }
    @Override
	public String toString() {
		return addGoodsName;
	}
     */
    public Outbound(String goodsDate,String goodsName, Integer goodsCategory, Integer goodsNumber){
        super();
        this.goodsName=goodsName;
        this.goodsCategory=goodsCategory;
        this.goodsNumber=goodsNumber;
        this.goodsDate=goodsDate;
    }
    public Outbound(Integer id, String goodsDate,String goodsName, Integer goodsCategory, Integer goodsNumber){
        super();
        this.id=id;
        this.goodsName=goodsName;
        this.goodsCategory=goodsCategory;
        this.goodsNumber=goodsNumber;
        this.goodsDate=goodsDate;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getGoodsCategory() {
        return goodsCategory;
    }

    public void setGoodsCategory(Integer goodsCategory) {
        this.goodsCategory = goodsCategory;
    }

    public Integer getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(Integer goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public String getGoodsDate() {
        return goodsDate;
    }

    public void setGoodsDate(String goodsDate) {
        this.goodsDate = goodsDate;
    }

}
