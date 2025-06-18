package untitled.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import untitled.OrderManagementApplication;

@Entity
@Table(name = "Cart_table")
@Data
//<<< DDD / Aggregate Root
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;

    private Long customerId;

    private Date createdAt;

    @ElementCollection
    private List<CartItem> cartItems;

    public static CartRepository repository() {
        CartRepository cartRepository = OrderManagementApplication.applicationContext.getBean(
            CartRepository.class
        );
        return cartRepository;
    }

    //<<< Clean Arch / Port Method
    public void addCartItem(AddCartItemCommand addCartItemCommand) {
        //implement business logic here:

        CartItemAdded cartItemAdded = new CartItemAdded(this);
        cartItemAdded.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void removeCartItem(RemoveCartItemCommand removeCartItemCommand) {
        //implement business logic here:

        CartItemRemoved cartItemRemoved = new CartItemRemoved(this);
        cartItemRemoved.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void clearCart() {
        //implement business logic here:

        CartCleared cartCleared = new CartCleared(this);
        cartCleared.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void checkoutCart(CheckoutCartCommand checkoutCartCommand) {
        //implement business logic here:

        CartCheckedOut cartCheckedOut = new CartCheckedOut(this);
        cartCheckedOut.publishAfterCommit();
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
