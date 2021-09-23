package com.app.absworldxpress.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "message_table")
public class MessageModel {
    @Id
    private String massageId;
    private String massage;

    private String createdBy;
    private Long creationTime;
}
