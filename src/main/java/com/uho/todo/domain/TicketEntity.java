package com.uho.todo.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.uho.todo.enums.TicketStatus;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity(name = "ticket")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "user"})
public class TicketEntity implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String title;
    private String description;
    private Date createdDate;
    private Date endDate;
    private Date deadLine;
    public boolean del = Boolean.FALSE;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsersEntity user;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;
}
