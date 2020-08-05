
package pizzaDemo.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@FeignClient(name="pizzapayment", url="${api.url.pizzapayment}")
public interface PizzapaymentService {

    @RequestMapping(method= RequestMethod.POST, path="/paymentscreations")
    public void pizzapayment(@RequestBody Pizzapayment pizzapayment);

}