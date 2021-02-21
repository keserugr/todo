package com.uho.todo.service.dto;

import com.uho.todo.enums.TicketStatus;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
public class TicketDTO implements Serializable {

    private Long id;
    private String title;
    private String description;
    private Date createdDate;
    private Date endDate;
    private Date deadLine;
    private boolean del;
    private TicketStatus status;
    private UsersDTO user;
}
