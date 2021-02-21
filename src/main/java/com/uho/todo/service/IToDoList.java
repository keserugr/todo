package com.uho.todo.service;

import com.uho.todo.model.RequestObjectModel;
import com.uho.todo.model.ResponseService;
import com.uho.todo.service.dto.TicketDTO;

import java.util.List;

public interface IToDoList {

    List<TicketDTO> getToDoList(RequestObjectModel requestObjectModel);

    Object addToDo(RequestObjectModel toDoEntity);

    TicketDTO addUserToTicket(RequestObjectModel requestObjectModel);

    Object deleteUserFromTicket(RequestObjectModel requestObjectModel);

    Object deleteTicket(RequestObjectModel requestObjectModel);

    List<TicketDTO> getUserTickets(RequestObjectModel requestObjectModel);

    TicketDTO changeTicketStatus(RequestObjectModel requestObjectModel);

    void checkTicketStatus();
}
