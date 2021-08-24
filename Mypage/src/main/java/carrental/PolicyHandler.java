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

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReservated_MypageUpdate(@Payload Reservated reservated){

        if(!reservated.validate()) return;

        System.out.println("\n\n##### listener MypageUpdate : " + reservated.toJson() + "\n\n");



        // Sample Logic //

    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReservationCanceled_MypageUpdate(@Payload ReservationCanceled reservationCanceled){

        if(!reservationCanceled.validate()) return;

        System.out.println("\n\n##### listener MypageUpdate : " + reservationCanceled.toJson() + "\n\n");



        // Sample Logic //

    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPayed_MypageUpdate(@Payload Payed payed){

        if(!payed.validate()) return;

        System.out.println("\n\n##### listener MypageUpdate : " + payed.toJson() + "\n\n");



        // Sample Logic //

    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPayCanceled_MypageUpdate(@Payload PayCanceled payCanceled){

        if(!payCanceled.validate()) return;

        System.out.println("\n\n##### listener MypageUpdate : " + payCanceled.toJson() + "\n\n");



        // Sample Logic //

    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverContractCompleted_MypageUpdate(@Payload ContractCompleted contractCompleted){

        if(!contractCompleted.validate()) return;

        System.out.println("\n\n##### listener MypageUpdate : " + contractCompleted.toJson() + "\n\n");



        // Sample Logic //

    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
