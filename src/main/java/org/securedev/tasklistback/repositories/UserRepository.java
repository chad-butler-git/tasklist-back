package org.securedev.tasklistback.repositories;

import org.securedev.tasklistback.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {

    User findByEmail(final String email);

}
