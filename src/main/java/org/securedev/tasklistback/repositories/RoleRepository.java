package org.securedev.tasklistback.repositories;

import org.securedev.tasklistback.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRole(final String role);
}
