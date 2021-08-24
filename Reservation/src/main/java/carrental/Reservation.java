package carrental;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="Reservation_table")
public class Reservation {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long contractId;
    private String custName;
    private String modelName;
    private Integer amt;
    private String reservationStatus;
    private String payStatus;

    @PostPersist
    public void onPostPersist(){
        System.out.println("################### Reservation >> 예약 호출 ##############################");
        Reservated reservated = new Reservated();
        BeanUtils.copyProperties(this, reservated);
        reservated.publishAfterCommit();

    }
    @PostUpdate
    public void onPostUpdate(){
        System.out.println("################### Reservation >>  예약 취소 호출 ##############################");
        ReservationCanceled reservationCanceled = new ReservationCanceled();
        BeanUtils.copyProperties(this, reservationCanceled);
        reservationCanceled.setReservationStatus("reservationCanceled");
        reservationCanceled.publishAfterCommit();
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
    public String getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(String reservationStatus) {
        this.reservationStatus = reservationStatus;
    }
    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }




}