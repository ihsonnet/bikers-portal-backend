package com.app.absworldxpress.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "Ticket_table")
public class TicketModel {
    @Id
    private String ticketId;
    private String ticketSlug;
    private String ticketSKU;
    private String ticketTitle;
    @OneToMany(cascade = CascadeType.ALL)
    private List<MessageModel> messageModelList;

    private Boolean isOpen;

    private String createdBy;
    private Long creationTime;
    private String updatedBy;
    private Long updatedTime;
}
