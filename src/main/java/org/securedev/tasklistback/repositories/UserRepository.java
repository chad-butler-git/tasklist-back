package org.securedev.tasklistback.repositories;

import org.securedev.tasklistback.models.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {


    User findByEmail(final String email);

}
