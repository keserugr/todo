package com.uho.todo.model;

import com.uho.todo.service.dto.TicketDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@EqualsAndHashCode(callSuper=false)
public class RequestObjectModel extends PageModel{

    private TicketDTO ticketDTO;

    private Long userId;
    private Long ticketId;
    private String ticketStatus;
}
