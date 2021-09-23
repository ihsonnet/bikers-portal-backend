package com.app.absworldxpress.repository;

import com.app.absworldxpress.model.MessageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<MessageModel,String > {
}
