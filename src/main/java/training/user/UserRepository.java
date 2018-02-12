package training.user;


import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

interface UserRepository extends CrudRepository<User, Long>{
    Optional<User> findById(Long id);
}
