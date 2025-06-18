package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class CreateMenuCommand {

    private String name;
    private String description;
    private Integer price;
    private Integer stock;
    private String allergyInfo;
}
