package dao;

public class Filter {

    private Integer pianoId;
    private String email;
    private Integer customerId;

    public boolean existTestId() {
        return pianoId != null;
    }

    public int getPianoId() {
        return pianoId;
    }

    public void setPianoId(int pianoId) {
        this.pianoId = pianoId;
    }

    public boolean existEmail() {
        return email != null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean existCustomerId() {
        return customerId != null;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
