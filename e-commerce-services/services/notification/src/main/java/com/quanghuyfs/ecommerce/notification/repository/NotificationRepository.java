package com.quanghuyfs.ecommerce.notification.repository;

import com.quanghuyfs.ecommerce.notification.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification,String> {
}
