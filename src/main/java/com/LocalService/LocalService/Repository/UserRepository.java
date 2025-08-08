package com.LocalService.LocalService.Repository;

import com.LocalService.LocalService.Entity.Role;
import com.LocalService.LocalService.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    Optional <User> findByPhone(String phone);
    List<User> findByRole(Role role);


}
