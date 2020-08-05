package pizzaDemo;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Pizzaorder_table")
public class Pizzaorder {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String pizzaId;
    private String orderStatus;

    @PostPersist
    public void onPostPersist(){
        Pizzaordered pizzaordered = new Pizzaordered();
        BeanUtils.copyProperties(this, pizzaordered);
        pizzaordered.publishAfterCommit();

        pizzaDemo.external.Pizzapayment pizzapayment = new pizzaDemo.external.Pizzapayment();
        pizzapayment.setPizzaId(pizzaordered.getPizzaId());
        pizzapayment.setPaymentStatus("SETTLED");

        PizzaorderApplication.applicationContext.getBean(pizzaDemo.external.PizzapaymentService.class)
            .pizzapayment(pizzapayment);

    }

    @PostUpdate
    public void onPostUpdate(){
        PizzaorderCanceled pizzaorderCanceled = new PizzaorderCanceled();
        BeanUtils.copyProperties(this, pizzaorderCanceled);
        pizzaorderCanceled.setOrderStatus("CANCELED");
        pizzaorderCanceled.publishAfterCommit();

        pizzaDemo.external.Pizzadelivery pizzadelivery = new pizzaDemo.external.Pizzadelivery();
        pizzadelivery.setPizzaId(pizzaorderCanceled.getPizzaId());
        pizzadelivery.setPizzaDeliveryStatus("CANCELED");

        PizzaorderApplication.applicationContext.getBean(pizzaDemo.external.PizzadeliveryService.class)
            .pizzadeliverycancel(pizzadelivery);

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(String pizzaId) {
        this.pizzaId = pizzaId;
    }
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }




}
