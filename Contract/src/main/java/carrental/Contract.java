package carrental;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;

import carrental.external.Pay;
import carrental.external.PayService;

@Entity
@Table(name="Contract_table")
public class Contract {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String custName;
    private String modelName;
    private Integer amt;

    @PostPersist
    public void onPostPersist(){
        System.out.println("################### Contract >> 계약 생성 ##############################");
        ContractCompleted contractCompleted = new ContractCompleted();
        BeanUtils.copyProperties(this, contractCompleted);
        contractCompleted.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        System.out.println("################### Contract >> PAY REST 호출 ##############################");
        Pay pay = new Pay();

        // mappings goes here
        BeanUtils.copyProperties(this, pay);
        pay.setContractId(this.getId());
        ContractApplication.applicationContext.getBean(PayService.class).pay(pay);
    }
    @PostUpdate
    public void onPostUpdate(){
        System.out.println("###################계약 수정(취소)##############################");
        ContractCanceled contractCanceled = new ContractCanceled();
        BeanUtils.copyProperties(this, contractCanceled);
        contractCanceled.publishAfterCommit();

    }

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