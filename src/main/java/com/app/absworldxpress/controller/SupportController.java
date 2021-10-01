package com.app.absworldxpress.controller;

import com.app.absworldxpress.dto.ApiMessageResponse;
import com.app.absworldxpress.dto.ApiResponse;
import com.app.absworldxpress.dto.request.CreateTicketRequest;
import com.app.absworldxpress.dto.request.MessageRequest;
import com.app.absworldxpress.dto.response.TicketListResponse;
import com.app.absworldxpress.dto.response.TicketResponse;
import com.app.absworldxpress.model.TicketModel;
import com.app.absworldxpress.services.SupportService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/support")
public class SupportController {

    @Autowired
    private final SupportService supportService;

    @GetMapping("/{ticketSKU}")
    public ResponseEntity<ApiResponse<TicketResponse>> getTicketDetails(@RequestHeader(name = "Authorization") String token,
                                                                     @PathVariable String ticketSKU){
        return supportService.getTicketDetails(token,ticketSKU);
    }
    @GetMapping
    public ResponseEntity<ApiResponse<TicketListResponse>> getTickets(@RequestHeader(name = "Authorization") String token,
                                                                      @RequestParam(required = false) String ticketId,
                                                                      String ticketSKU, String createdBy,
                                                                      @RequestParam(defaultValue = "creationTime") String sortBy,
                                                                      @RequestParam(defaultValue = "ASC") Sort.Direction orderBy,
                                                                      @RequestParam(defaultValue = "20") int pageSize,
                                                                      @RequestParam(defaultValue = "0") int pageNo){
        return supportService.getTickets(token,ticketId,ticketSKU,createdBy,sortBy,orderBy,pageSize,pageNo);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TicketResponse>> createTicket(@RequestHeader(name = "Authorization") String token,
                                                                    @RequestBody CreateTicketRequest createTicketRequest){
        return supportService.createTicket(token,createTicketRequest);
    }

    @PutMapping("/{ticketId}")
    public ResponseEntity<ApiMessageResponse> addMessage(@RequestHeader(name = "Authorization") String token,
                                                         @PathVariable String ticketId,
                                                         @RequestBody MessageRequest messageRequest){
        return supportService.addMessage(token,ticketId,messageRequest);
    }

    @PutMapping("/colse/{ticketId}")
    public ResponseEntity<ApiMessageResponse> closeTicket(@RequestHeader(name = "Authorization") String token,
                                                         @PathVariable String ticketId){
        return supportService.closeTicket(token,ticketId);
    }
}
