package carrental;

import carrental.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired ReservationRepository reservationRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPayed_Reservate(@Payload Payed payed){
        System.out.println("################### Reservation / PolicyHandler >> PUB/SUB 예약 호출 ##############################");
        if(!payed.validate()) return;

        // Sample Logic //
        Reservation reservation = new Reservation();
        reservation.setContractId(payed.getContractId());
        reservation.setCustName(payed.getCustName());
        reservation.setModelName(payed.getModelName());
        reservation.setAmt(payed.getAmt());
        reservation.setPayStatus(payed.getPaystatus());
        reservation.setReservationStatus("reserved");

        reservationRepository.save(reservation);
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
