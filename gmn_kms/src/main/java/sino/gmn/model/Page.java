package sino.gmn.model;


public class Page {
    private Integer page;
    private Integer rows;
    private Integer first;

    private String sort;
    private String order;

    private String name;
    private String value;

    public Integer getFirst() {
        if(page != null && rows != null){
            return page>0? (page-1)*rows:0;
        }
        return 0;
    }

    public void setFirst(Integer first) {
        this.first = first;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
