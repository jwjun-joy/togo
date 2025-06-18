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
// @RequestMapping(value="/restaurants")
@Transactional
public class RestaurantController {

    @Autowired
    RestaurantRepository restaurantRepository;

    @RequestMapping(
        value = "/restaurantscreatemenu",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Restaurant createMenu(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody CreateMenuCommand createMenuCommand
    ) throws Exception {
        System.out.println("##### /restaurant/createMenu  called #####");
        Restaurant restaurant = new Restaurant();
        restaurant.createMenu(createMenuCommand);
        restaurantRepository.save(restaurant);
        return restaurant;
    }

    @RequestMapping(
        value = "/restaurants/{id}/updatemenu",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Restaurant updateMenu(
        @PathVariable(value = "id") Long id,
        @RequestBody UpdateMenuCommand updateMenuCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /restaurant/updateMenu  called #####");
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(
            id
        );

        optionalRestaurant.orElseThrow(() -> new Exception("No Entity Found"));
        Restaurant restaurant = optionalRestaurant.get();
        restaurant.updateMenu(updateMenuCommand);

        restaurantRepository.save(restaurant);
        return restaurant;
    }

    @RequestMapping(
        value = "/restaurants/{id}/deletemenu",
        method = RequestMethod.DELETE,
        produces = "application/json;charset=UTF-8"
    )
    public Restaurant deleteMenu(
        @PathVariable(value = "id") Long id,
        @RequestBody DeleteMenuCommand deleteMenuCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /restaurant/deleteMenu  called #####");
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(
            id
        );

        optionalRestaurant.orElseThrow(() -> new Exception("No Entity Found"));
        Restaurant restaurant = optionalRestaurant.get();
        restaurant.deleteMenu(deleteMenuCommand);

        restaurantRepository.delete(restaurant);
        return restaurant;
    }

    @RequestMapping(
        value = "/restaurants/{id}/updaterestaurantinfo",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Restaurant updateRestaurantInfo(
        @PathVariable(value = "id") Long id,
        @RequestBody UpdateRestaurantInfoCommand updateRestaurantInfoCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println(
            "##### /restaurant/updateRestaurantInfo  called #####"
        );
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(
            id
        );

        optionalRestaurant.orElseThrow(() -> new Exception("No Entity Found"));
        Restaurant restaurant = optionalRestaurant.get();
        restaurant.updateRestaurantInfo(updateRestaurantInfoCommand);

        restaurantRepository.save(restaurant);
        return restaurant;
    }
}
//>>> Clean Arch / Inbound Adaptor
