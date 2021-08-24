package carrental;

public class PayCanceled extends AbstractEvent {

    private Long id;
    private Long contractId;
    private String payStatus;

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
    public String getPaystatus() {
        return payStatus;
    }

    public void setPaystatus(String payStatus) {
        this.payStatus = payStatus;
    }
}