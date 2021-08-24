package carrental;

import carrental.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MyPageViewHandler {


    @Autowired
    private MyPageRepository myPageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenContractCompleted_then_CREATE_1 (@Payload ContractCompleted contractCompleted) {
        try {
            System.out.println("########################### 계약 호출 ########################################");
            if (!contractCompleted.validate()) return;

            // view 객체 생성
            MyPage myPage = new MyPage();
            // view 객체에 이벤트의 Value 를 set 함
            myPage.setContractId(contractCompleted.getId());
            myPage.setCustName(contractCompleted.getCustName());
            myPage.setModelName(contractCompleted.getModelName());
            myPage.setAmt(contractCompleted.getAmt());
            // view 레파지 토리에 save
            myPageRepository.save(myPage);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenPayed_then_UPDATE_1(@Payload Payed payed) {
        System.out.println("###########################  결제 호출 ########################################");
        try {
            //if (!payed.validate()) return;
                // view 객체 조회
                System.out.println("########################### 2222222222 ########################################");
                    List<MyPage> myPageList = myPageRepository.findByContractId(payed.getContractId());
                    for(MyPage myPage : myPageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    System.out.println("########################### 333333333 ########################################");
                    myPage.setPayStatus(payed.getPaystatus());
                // view 레파지 토리에 save
                myPageRepository.save(myPage);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenReservated_then_UPDATE_2(@Payload Reservated reservated) {
        System.out.println("########################### 예약 호출 ########################################");
        try {
            if (!reservated.validate()) return;
                // view 객체 조회

                    List<MyPage> myPageList = myPageRepository.findByContractId(reservated.getContractId());
                    for(MyPage myPage : myPageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    myPage.setReservationStatus(reservated.getReservationStatus());
                // view 레파지 토리에 save
                myPageRepository.save(myPage);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenPayCanceled_then_UPDATE_3(@Payload PayCanceled payCanceled) {
        System.out.println("########################### 결제 취소  ########################################");
        try {
            if (!payCanceled.validate()) return;
                // view 객체 조회

                    List<MyPage> myPageList = myPageRepository.findByContractId(payCanceled.getContractId());
                    for(MyPage myPage : myPageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    myPage.setPayStatus(payCanceled.getPaystatus());
                // view 레파지 토리에 save
                myPageRepository.save(myPage);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenReservationCanceled_then_UPDATE_4(@Payload ReservationCanceled reservationCanceled) {
        System.out.println("########################### 예약 취소  ########################################");
        try {
            if (!reservationCanceled.validate()) return;
                // view 객체 조회

                    List<MyPage> myPageList = myPageRepository.findByContractId(reservationCanceled.getContractId());
                    for(MyPage myPage : myPageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    myPage.setReservationStatus(reservationCanceled.getReservationStatus());
                // view 레파지 토리에 save
                myPageRepository.save(myPage);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

