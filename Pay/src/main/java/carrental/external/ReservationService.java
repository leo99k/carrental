package carrental.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

// feignclient 기술 적용
// url: http://localhost:8083 - application.yaml에 정의함
@FeignClient(name="reservation", url="${api.url.reservation}")
public interface ReservationService {
    @RequestMapping(method= RequestMethod.POST, path="/reservations")
    public void cancelReservation(@RequestBody Reservation reservation);

}

