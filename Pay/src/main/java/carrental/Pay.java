package carrental;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import carrental.external.Reservation;
import carrental.external.ReservationService;

@Entity
@Table(name="Pay_table")
public class Pay {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long contractId;
    private String custName;
    private String modelName;
    private Integer amt;

    @PostPersist
    public void onPostPersist(){
        
        System.out.println("################### PAY >> REST 결제 호출 #############################");
        Payed payed = new Payed();
        BeanUtils.copyProperties(this, payed);
        payed.setPaystatus("paid");
        payed.publishAfterCommit();
    }


    @PostUpdate
    public void onPostUpdate(){
        System.out.println("################### PAY  >> 계약 취소 / 결제취소 호출 #############################");
        PayCanceled payCanceled = new PayCanceled();
        BeanUtils.copyProperties(this, payCanceled);
        payCanceled.publish();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        Reservation reservation = new Reservation();
        BeanUtils.copyProperties(payCanceled, reservation);
        // mappings goes here
        PayApplication.applicationContext.getBean(ReservationService.class).cancelReservation(reservation);
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




}