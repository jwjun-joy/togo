package untitled.domain;

import java.util.Date;
import lombok.Data;

@Data
public class UserListViewQuery {

    private UserType userType;
    private UserStatus status;
    private String searchText;
    private Integer page;
    private Integer pageSize;
}
