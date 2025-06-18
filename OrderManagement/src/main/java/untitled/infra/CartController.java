package untitled.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import untitled.domain.*;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/carts")
@Transactional
public class CartController {

    @Autowired
    CartRepository cartRepository;

    @RequestMapping(
        value = "/cartsaddcartitem",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Cart addCartItem(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody AddCartItemCommand addCartItemCommand
    ) throws Exception {
        System.out.println("##### /cart/addCartItem  called #####");
        Cart cart = new Cart();
        cart.addCartItem(addCartItemCommand);
        cartRepository.save(cart);
        return cart;
    }

    @RequestMapping(
        value = "/carts/{id}/removecartitem",
        method = RequestMethod.DELETE,
        produces = "application/json;charset=UTF-8"
    )
    public Cart removeCartItem(
        @PathVariable(value = "id") Long id,
        @RequestBody RemoveCartItemCommand removeCartItemCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /cart/removeCartItem  called #####");
        Optional<Cart> optionalCart = cartRepository.findById(id);

        optionalCart.orElseThrow(() -> new Exception("No Entity Found"));
        Cart cart = optionalCart.get();
        cart.removeCartItem(removeCartItemCommand);

        cartRepository.delete(cart);
        return cart;
    }

    @RequestMapping(
        value = "/carts/{id}/clearcart",
        method = RequestMethod.DELETE,
        produces = "application/json;charset=UTF-8"
    )
    public Cart clearCart(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /cart/clearCart  called #####");
        Optional<Cart> optionalCart = cartRepository.findById(id);

        optionalCart.orElseThrow(() -> new Exception("No Entity Found"));
        Cart cart = optionalCart.get();
        cart.clearCart();

        cartRepository.delete(cart);
        return cart;
    }

    @RequestMapping(
        value = "/cartscheckoutcart",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Cart checkoutCart(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody CheckoutCartCommand checkoutCartCommand
    ) throws Exception {
        System.out.println("##### /cart/checkoutCart  called #####");
        Cart cart = new Cart();
        cart.checkoutCart(checkoutCartCommand);
        cartRepository.save(cart);
        return cart;
    }
}
//>>> Clean Arch / Inbound Adaptor
