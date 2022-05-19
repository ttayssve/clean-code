import java.math.BigDecimal;

public class Item {
    private int idItem;
    private String category;
    private String description;
    private BigDecimal price;

    public Item(int idItem, String category, String description, BigDecimal price) {
        this.idItem = idItem;
        this.category = category;
        this.description = description;
        this.price = price;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
