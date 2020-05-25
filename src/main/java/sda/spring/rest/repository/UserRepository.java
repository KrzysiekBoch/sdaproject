package sda.spring.rest.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import sda.spring.rest.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {


}
