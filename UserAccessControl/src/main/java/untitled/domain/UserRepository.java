package untitled.domain;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import untitled.domain.*;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    @Query(
        value = "select user " +
        "from User user " +
        "where(:userType is null or user.userType = :userType) and (:status is null or user.status = :status) and (:searchText is null or user.searchText like %:searchText%) and (:page is null or user.page = :page) and (:pageSize is null or user.pageSize = :pageSize)"
    )
    List<User> userListView(
        UserType userType,
        UserStatus status,
        String searchText,
        Integer page,
        Integer pageSize,
        Pageable pageable
    );
}
