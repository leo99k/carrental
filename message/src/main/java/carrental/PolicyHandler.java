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
    @Autowired MessageRepository messageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverContractCompleted_SendMessage(@Payload ContractCompleted contractCompleted){
        
        System.out.println("################### message >> PUB/SUB 계약 호출 ##############################");

        if ("ContractCompleted".equals(contractCompleted.getEventType())) {
            System.out.println("################### message >> PUB/SUB 계약 호출 ##############################  " + contractCompleted.toJson());

            if(!contractCompleted.validate()) return;

            Message message = new Message();

            message.setContractId(contractCompleted.getId());
            message.setCustName(contractCompleted.getCustName());
            message.setModelName(contractCompleted.getModelName());
            message.setmessageTxt("[담당자 메시지 발송] 계약번호 " + message.getContractId().toString() + "번의 " + message.getCustName() + "님이 " + message.getModelName() + " 차량 계약을 하였습니다.");
            messageRepository.save(message);

            System.out.println("[담당자 메시지 발송] 계약번호 " + message.getContractId().toString() + "번의 " + message.getCustName() + "님이 " + message.getModelName() + " 차량 계약을 하였습니다.");

        } else {
            System.out.println("################### message >> PUB/SUB 계약 호출 pass ##############################");
        }

    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReservated_SendMessage(@Payload Reservated reservated){

        System.out.println("################### message >> PUB/SUB 예약완료 호출 ##############################");

        if ("Reservated".equals(reservated.getEventType())) {
            System.out.println("################### message >> PUB/SUB 예약완료 호출 ##############################  " + reservated.toJson());

            if(!reservated.validate()) return;

            Message message = new Message();

            message.setContractId(reservated.getId());
            message.setCustName(reservated.getCustName());
            message.setModelName(reservated.getModelName());
            message.setmessageTxt("[고객 메시지 발송] 계약번호 " + message.getContractId().toString() + "번의 " + message.getCustName() + "님 " + message.getModelName() + " 차량 예약이 완료되었습니다.");
            messageRepository.save(message);

            System.out.println("[고객 메시지 발송] 계약번호 " + message.getContractId().toString() + "번의 " + message.getCustName() + "님 " + message.getModelName() + " 차량 예약이 완료되었습니다.");

        } else {
            System.out.println("################### message >> PUB/SUB 예약 호출 pass ##############################");
        }

    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
