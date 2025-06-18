package untitled.infra;
import untitled.domain.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/orders")
@Transactional
public class OrderController {
    @Autowired
    OrderRepository orderRepository;

    @RequestMapping(value = "/ordersrequestorder",
            method = RequestMethod.POST,
            produces = "application/json;charset=UTF-8")
    public Order requestOrder(HttpServletRequest request, HttpServletResponse response, 
        @RequestBody RequestOrderCommand requestOrderCommand) throws Exception {
            System.out.println("##### /order/requestOrder  called #####");
            Order order = new Order();
            order.requestOrder(requestOrderCommand);
            orderRepository.save(order);
            return order;
    }
    @RequestMapping(value = "/ordersplaceorder",
            method = RequestMethod.POST,
            produces = "application/json;charset=UTF-8")
    public Order placeOrder(HttpServletRequest request, HttpServletResponse response, 
        @RequestBody PlaceOrderCommand placeOrderCommand) throws Exception {
            System.out.println("##### /order/placeOrder  called #####");
            Order order = new Order();
            order.placeOrder(placeOrderCommand);
            orderRepository.save(order);
            return order;
    }
    @RequestMapping(value = "/orders/{id}/cancelorder",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8")
    public Order cancelOrder(@PathVariable(value = "id") List&lt;OrderItem&gt; id, @RequestBody CancelOrderCommand cancelOrderCommand, HttpServletRequest request, HttpServletResponse response) throws Exception {
            System.out.println("##### /order/cancelOrder  called #####");
            Optional<Order> optionalOrder = orderRepository.findById(id);
            
            optionalOrder.orElseThrow(()-> new Exception("No Entity Found"));
            Order order = optionalOrder.get();
            order.cancelOrder(cancelOrderCommand);
            
            orderRepository.save(order);
            return order;
            
    }
    @RequestMapping(value = "/orders/{id}/acceptorder",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8")
    public Order acceptOrder(@PathVariable(value = "id") List&lt;OrderItem&gt; id, HttpServletRequest request, HttpServletResponse response) throws Exception {
            System.out.println("##### /order/acceptOrder  called #####");
            Optional<Order> optionalOrder = orderRepository.findById(id);
            
            optionalOrder.orElseThrow(()-> new Exception("No Entity Found"));
            Order order = optionalOrder.get();
            order.acceptOrder();
            
            orderRepository.save(order);
            return order;
            
    }
    @RequestMapping(value = "/orders/{id}/rejectorder",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8")
    public Order rejectOrder(@PathVariable(value = "id") List&lt;OrderItem&gt; id, @RequestBody RejectOrderCommand rejectOrderCommand, HttpServletRequest request, HttpServletResponse response) throws Exception {
            System.out.println("##### /order/rejectOrder  called #####");
            Optional<Order> optionalOrder = orderRepository.findById(id);
            
            optionalOrder.orElseThrow(()-> new Exception("No Entity Found"));
            Order order = optionalOrder.get();
            order.rejectOrder(rejectOrderCommand);
            
            orderRepository.save(order);
            return order;
            
    }
    @RequestMapping(value = "/orders/{id}/prepareorder",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8")
    public Order prepareOrder(@PathVariable(value = "id") List&lt;OrderItem&gt; id, HttpServletRequest request, HttpServletResponse response) throws Exception {
            System.out.println("##### /order/prepareOrder  called #####");
            Optional<Order> optionalOrder = orderRepository.findById(id);
            
            optionalOrder.orElseThrow(()-> new Exception("No Entity Found"));
            Order order = optionalOrder.get();
            order.prepareOrder();
            
            orderRepository.save(order);
            return order;
            
    }
    @RequestMapping(value = "/orders/{id}/completepreparation",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8")
    public Order completePreparation(@PathVariable(value = "id") List&lt;OrderItem&gt; id, HttpServletRequest request, HttpServletResponse response) throws Exception {
            System.out.println("##### /order/completePreparation  called #####");
            Optional<Order> optionalOrder = orderRepository.findById(id);
            
            optionalOrder.orElseThrow(()-> new Exception("No Entity Found"));
            Order order = optionalOrder.get();
            order.completePreparation();
            
            orderRepository.save(order);
            return order;
            
    }
}
//>>> Clean Arch / Inbound Adaptor
