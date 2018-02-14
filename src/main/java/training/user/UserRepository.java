package training.user;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Optional<User> findById(Long id);
    Optional<User> findByName(String name);
    List<User> findByUserType(UserType userType);
}
