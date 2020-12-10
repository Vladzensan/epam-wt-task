package entity;

public class OrderEntity extends Entity{
    private String status;
    private int orderId;

    public OrderEntity() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String text) {
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
