package com.uho.todo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","tickets"})
public class UsersEntity implements Serializable {


    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String name;
    private String surname;
    private String mail;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL)
    private List<TicketEntity> tickets;

}
