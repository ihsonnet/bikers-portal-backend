package com.app.absworldxpress.repository;

import com.app.absworldxpress.model.TicketModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<TicketModel,String > {
}
