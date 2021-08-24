package carrental.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


// feignclient 기술 적용
// url: http://localhost:8082 - application.yaml에 정의함

@FeignClient(name="Pay", url="${api.url.pay}")
public interface PayService {
    @RequestMapping(method = RequestMethod.POST, path = "/pays", consumes = "application/json")
    public void pay(@RequestBody Pay pay);
}