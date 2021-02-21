package com.uho.todo.service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UsersDTO implements Serializable {

    private Long id;
    private String name;
    private String surname;
    private String mail;

    //private List<TicketEntity> tickets;
}