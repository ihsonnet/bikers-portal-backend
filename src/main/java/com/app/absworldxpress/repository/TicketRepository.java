package com.app.absworldxpress.repository;

import com.app.absworldxpress.model.TicketModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<TicketModel,String > {
    Optional<TicketModel> findByTicketSKU(String ticketSKU);
}
