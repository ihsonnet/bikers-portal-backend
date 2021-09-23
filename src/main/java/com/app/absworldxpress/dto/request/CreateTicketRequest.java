package com.app.absworldxpress.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateTicketRequest {
    String ticketTitle;
    String yourProblem;
}
