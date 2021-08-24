package carrental;

public class Payed extends AbstractEvent {

    private Long id;
    private Long contractId;
    private String custName;
    private String modelName;
    private Integer amt;
    private String payStatus;

    public Payed(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
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
    public String getPaystatus() {
        return payStatus;
    }

    public void setPaystatus(String payStatus) {
        this.payStatus = payStatus;
    }
}
