package com.app.absworldxpress.services;

import com.app.absworldxpress.dto.ApiMessageResponse;
import com.app.absworldxpress.dto.ApiResponse;
import com.app.absworldxpress.dto.request.CreateTicketRequest;
import com.app.absworldxpress.dto.request.MessageRequest;
import com.app.absworldxpress.dto.response.TicketListResponse;
import com.app.absworldxpress.dto.response.TicketResponse;
import com.app.absworldxpress.model.TicketModel;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

public interface SupportService {
    ResponseEntity<ApiResponse<TicketResponse>> createTicket(String token, CreateTicketRequest createTicketRequest);

    ResponseEntity<ApiMessageResponse> addMessage(String token, String ticketId, MessageRequest messageRequest);

    ResponseEntity<ApiMessageResponse> closeTicket(String token, String ticketId);

    ResponseEntity<ApiResponse<TicketListResponse>> getTickets(String token, String ticketId, String ticketSKU, String createdBy, String sortBy, Sort.Direction orderBy, int pageSize, int pageNo);

    ResponseEntity<ApiResponse<TicketResponse>> getTicketDetails(String token, String ticketSKU);
}
