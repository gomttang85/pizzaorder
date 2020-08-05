
package pizzaDemo.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@FeignClient(name="pizzadelivery", url="${api.url.pizzadelivery}")
public interface PizzadeliveryService {

    @RequestMapping(method= RequestMethod.POST, path="/cancellations")
    public void pizzadeliverycancel(@RequestBody Pizzadelivery pizzadelivery);

}
