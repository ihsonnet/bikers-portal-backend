package com.app.absworldxpress.controller;

import com.app.absworldxpress.dto.ApiMessageResponse;
import com.app.absworldxpress.dto.ApiResponse;
import com.app.absworldxpress.dto.request.CreateTicketRequest;
import com.app.absworldxpress.dto.request.MessageRequest;
import com.app.absworldxpress.model.TicketModel;
import com.app.absworldxpress.services.SupportService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/support")
public class SupportController {

    @Autowired
    private final SupportService supportService;

    @PostMapping
    public ResponseEntity<ApiResponse<TicketModel>> createTicket(@RequestHeader(name = "Authorization") String token,
                                                                 @RequestBody CreateTicketRequest createTicketRequest){
        return supportService.createTicket(token,createTicketRequest);
    }

    @PutMapping("/{ticketId}")
    public ResponseEntity<ApiMessageResponse> addMessage(@RequestHeader(name = "Authorization") String token,
                                                         @PathVariable String ticketId,
                                                         @RequestBody MessageRequest messageRequest){
        return supportService.addMessage(token,ticketId,messageRequest);
    }
}
