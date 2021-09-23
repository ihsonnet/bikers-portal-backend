package com.app.absworldxpress.services;

import com.app.absworldxpress.dto.ApiMessageResponse;
import com.app.absworldxpress.dto.ApiResponse;
import com.app.absworldxpress.dto.request.CreateTicketRequest;
import com.app.absworldxpress.dto.request.MessageRequest;
import com.app.absworldxpress.model.TicketModel;
import org.springframework.http.ResponseEntity;

public interface SupportService {
    ResponseEntity<ApiResponse<TicketModel>> createTicket(String token, CreateTicketRequest createTicketRequest);

    ResponseEntity<ApiMessageResponse> addMessage(String token, String ticketId, MessageRequest messageRequest);
}
