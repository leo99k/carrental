package carrental;

import java.util.List;
import java.util.Optional;
import carrental.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired PayRepository payRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverContractCanceled_CancelPay(@Payload ContractCanceled contractCanceled){

        System.out.println("##################### PAY PolicyHandler >> PUB/SUB 예약 취소 호출  ###########################");

        try {
            if(!contractCanceled.validate()) return;
            

            // 객체 조회
            Optional<Pay> Optional = payRepository.findByContractId(contractCanceled.getId());

            if( Optional.isPresent()) {
                Pay pay = Optional.get();

                // 객체에 이벤트의 eventDirectValue 를 set 함
                pay.setCustName(contractCanceled.getCustName());
                pay.setModelName(contractCanceled.getModelName());
                pay.setAmt(contractCanceled.getAmt());
                // 레파지 토리에 save
                payRepository.save(pay);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
