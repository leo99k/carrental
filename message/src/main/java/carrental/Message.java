package carrental;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="Message_table")
public class Message {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long contractId;
    private String custName;
    private String modelName;
    private String messageTxt;

    @PostPersist
    public void onPostPersist(){
        MessageSent messageSent = new MessageSent();
        BeanUtils.copyProperties(this, messageSent);
        
        System.out.println("시스템 메시지 발송 문구 = " + this.messageTxt);
        
        messageSent.publishAfterCommit();

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
    public String getmessageTxt() {
        return messageTxt;
    }

    public void setmessageTxt(String messageTxt) {
        this.messageTxt = messageTxt;
    }
}