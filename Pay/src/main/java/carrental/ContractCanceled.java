package carrental;

public class ContractCanceled extends AbstractEvent {

    private Long id;
    private String custName;
    private String modelName;
    private Integer amt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }
    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
    public Integer getAmt() {
        return amt;
    }

    public void setAmt(Integer amt) {
        this.amt = amt;
    }
}