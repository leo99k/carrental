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
        
        System.out.println("################### 111111 PAY >> REST 결제 호출 #############################  " + this.getId());
        Payed payed = new Payed();
        BeanUtils.copyProperties(this, payed);
        payed.setPaystatus("paid");
        
        System.out.println("################### 111111 PAY >> REST 결제 호출 payed.toJson() #############################  " + payed.toJson());
        
        payed.publishAfterCommit();
        
        try {
        	System.out.println("################### 지연 설정 처리  #############################  ");
            Thread.currentThread().sleep((long) (400 + Math.random() * 220));
        } catch (InterruptedException e) {
        	System.out.println("################### 지연 설정 처리 에러남  #############################  ");
            e.printStackTrace();
        }
        
        
        
        
    }


    @PostUpdate
    public void onPostUpdate(){
        System.out.println("################### PAY  >> 계약 취소 / 결제취소 호출 this.getId() ############################# " + this.getId());
        System.out.println("################### PAY  >> 계약 취소 / 결제취소 호출 this.getContractId() ############################# " + this.getContractId());
        this.setContractId(this.getId());
        PayCanceled payCanceled = new PayCanceled();
        BeanUtils.copyProperties(this, payCanceled);
        payCanceled.setPaystatus("payCanceled");
        System.out.println("################### PAY  >> 계약 취소 / 결제취소 호출 payCanceled.toJson() ############################# " + payCanceled.toJson() );
        
        payCanceled.publish();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        Reservation reservation = new Reservation();
        BeanUtils.copyProperties(this, reservation);
        
        reservation.setPayStatus("payCanceled");
        
        System.out.println("################### PAY  >> 계약 취소 / 결제취소 호출 this.getId() ############################# " + reservation.getContractId());
        System.out.println("################### PAY  >> 계약 취소 / 결제취소 호출 this.getId() ############################# " + reservation.getReservationStatus());
        System.out.println("################### PAY  >> 계약 취소 / 결제취소 호출 this.getId() ############################# " + reservation.getPayStatus() );
        
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