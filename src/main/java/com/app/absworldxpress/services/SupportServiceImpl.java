package com.app.absworldxpress.services;

import com.app.absworldxpress.dto.ApiMessageResponse;
import com.app.absworldxpress.dto.ApiResponse;
import com.app.absworldxpress.dto.BasicTableInfo;
import com.app.absworldxpress.dto.request.CreateTicketRequest;
import com.app.absworldxpress.dto.request.MessageRequest;
import com.app.absworldxpress.jwt.security.jwt.JwtProvider;
import com.app.absworldxpress.jwt.services.AuthService;
import com.app.absworldxpress.model.MessageModel;
import com.app.absworldxpress.model.TicketModel;
import com.app.absworldxpress.repository.MessageRepository;
import com.app.absworldxpress.repository.TicketRepository;
import com.app.absworldxpress.util.UtilService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class SupportServiceImpl implements SupportService{

    @Autowired
    MessageRepository messageRepository;
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    AuthService authService;
    @Autowired
    UtilService utilService;
    @Autowired
    JwtProvider jwtProvider;

    @Override
    public ResponseEntity<ApiResponse<TicketModel>> createTicket(String token, CreateTicketRequest createTicketRequest) {
        if (authService.isThisUser("USER", token)){
            BasicTableInfo basicTableInfo = utilService.generateBasicTableInfo(createTicketRequest.getTicketTitle(),token);

            List<MessageModel> messageModelList = new ArrayList<>();
            MessageModel messageModel = MessageModel.builder()
                    .massageId(UUID.randomUUID().toString()).
                    massage(createTicketRequest.getYourProblem())
                    .createdBy(basicTableInfo.getCreateBy())
                    .creationTime(basicTableInfo.getCreationTime())
                    .build();

            messageModelList.add(messageModel);

            TicketModel ticketModel = TicketModel.builder()
                    .ticketId(basicTableInfo.getId())
                    .ticketSKU(basicTableInfo.getSKU())
                    .ticketTitle(createTicketRequest.getTicketTitle())
                    .ticketSlug(basicTableInfo.getSlug())
                    .messageModelList(messageModelList)
                    .createdBy(basicTableInfo.getCreateBy())
                    .creationTime(basicTableInfo.getCreationTime())
                    .updatedBy(basicTableInfo.getCreateBy())
                    .updatedTime(basicTableInfo.getCreationTime())
                    .isOpen(true)
                    .build();

            ticketRepository.save(ticketModel);

            return new ResponseEntity<>(new ApiResponse<>(200,"Ticket Created",ticketModel), HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<>(new ApiResponse<>(200,"You have no permission",null), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ApiMessageResponse> addMessage(String token, String ticketId, MessageRequest messageRequest) {

        Optional<TicketModel> ticketModelOptional = ticketRepository.findById(ticketId);

        if (ticketModelOptional.isPresent()){
            if (authService.isThisUser("ADMIN", token) || ticketModelOptional.get().getCreatedBy().equalsIgnoreCase(jwtProvider.getUserNameFromJwt(token))){
                MessageModel messageModel = MessageModel.builder()
                        .massageId(UUID.randomUUID().toString())
                        .massage(messageRequest.getMessage())
                        .createdBy(jwtProvider.getUserNameFromJwt(token))
                        .creationTime(System.currentTimeMillis())
                        .build();

                TicketModel ticketModel = ticketModelOptional.get();
                List<MessageModel> messageModelList = ticketModel.getMessageModelList();
                messageModelList.add(messageModel);
                ticketModel.setMessageModelList(messageModelList);
                ticketModel.setUpdatedBy(messageModel.getCreatedBy());
                ticketModel.setUpdatedTime(messageModel.getCreationTime());

                ticketRepository.save(ticketModel);

                return new ResponseEntity<>(new ApiMessageResponse(200, "Messege Added"),HttpStatus.OK);
            }
            else
                return new ResponseEntity<>(new ApiMessageResponse(400, "You have no permission"),HttpStatus.BAD_REQUEST);
        }
        else
            return new ResponseEntity<>(new ApiMessageResponse(400, "Ticket not found"),HttpStatus.BAD_REQUEST);
    }
}
