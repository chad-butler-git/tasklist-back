package org.securedev.tasklistback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.securedev.tasklistback.models.Tasks;

public interface TasksRepository extends JpaRepository<Tasks, Long> {

    Tasks findByDescription(final String description);
}
