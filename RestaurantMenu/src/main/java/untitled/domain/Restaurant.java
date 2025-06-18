package untitled.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import untitled.RestaurantMenuApplication;

@Entity
@Table(name = "Restaurant_table")
@Data
//<<< DDD / Aggregate Root
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long restaurantId;

    private RestaurantInfo restaurantInfo;

    @Enumerated(EnumType.STRING)
    private MenuList menuList;

    public static RestaurantRepository repository() {
        RestaurantRepository restaurantRepository = RestaurantMenuApplication.applicationContext.getBean(
            RestaurantRepository.class
        );
        return restaurantRepository;
    }

    //<<< Clean Arch / Port Method
    public void createMenu(CreateMenuCommand createMenuCommand) {
        //implement business logic here:

        MenuCreated menuCreated = new MenuCreated(this);
        menuCreated.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void updateMenu(UpdateMenuCommand updateMenuCommand) {
        //implement business logic here:

        MenuUpdated menuUpdated = new MenuUpdated(this);
        menuUpdated.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void deleteMenu(DeleteMenuCommand deleteMenuCommand) {
        //implement business logic here:

        MenuDeleted menuDeleted = new MenuDeleted(this);
        menuDeleted.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void updateRestaurantInfo(
        UpdateRestaurantInfoCommand updateRestaurantInfoCommand
    ) {
        //implement business logic here:

        RestaurantInfoChanged restaurantInfoChanged = new RestaurantInfoChanged(
            this
        );
        restaurantInfoChanged.publishAfterCommit();
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
