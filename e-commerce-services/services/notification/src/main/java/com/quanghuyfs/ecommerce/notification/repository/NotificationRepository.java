package com.quanghuyfs.ecommerce.notification.repository;

import com.quanghuyfs.ecommerce.notification.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends MongoRepository<Notification,String> {
}
