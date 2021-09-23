package com.app.absworldxpress.dto.response;

import com.app.absworldxpress.model.TicketModel;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TicketListResponse {
    int pageSize;
    int pageNo;
    int ticketCount;
    boolean isLastPage;
    Long totalTicket;
    int totalPages;

    List<TicketModel> ticketList;
}
