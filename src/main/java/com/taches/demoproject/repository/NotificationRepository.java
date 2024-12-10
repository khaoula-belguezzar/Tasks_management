package com.taches.demoproject.repository;

import com.taches.demoproject.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    long countByUsername (String username);
}
