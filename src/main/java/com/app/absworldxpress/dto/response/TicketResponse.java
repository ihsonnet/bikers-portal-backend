package com.app.absworldxpress.dto.response;

import com.app.absworldxpress.model.MessageModel;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TicketResponse {
    private String ticketId;
    private String ticketSlug;
    private String ticketSKU;
    private String ticketTitle;

    private List<MessageModel> messageModelList;

    private Boolean isOpen;

    private String createdBy;
    private Long creationTime;
    private String updatedBy;
    private Long updatedTime;
}
