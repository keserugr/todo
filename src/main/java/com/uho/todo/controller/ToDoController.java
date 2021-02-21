package com.uho.todo.controller;

import com.uho.todo.enums.TicketStatus;
import com.uho.todo.model.RequestObjectModel;
import com.uho.todo.model.ResponseService;
import com.uho.todo.service.IToDoList;
import com.uho.todo.service.dto.TicketDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ToDoController {

    private final IToDoList iToDoList;

    @GetMapping("/todo_list")
    private ResponseService<List<TicketDTO>> getToDoList(RequestObjectModel requestObjectModel) {

        List<TicketDTO> toDoList = iToDoList.getToDoList(requestObjectModel);

        return new ResponseService<>(toDoList);
    }

    @PostMapping(value = "/create_todo_list",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    private Object createToDoList(@RequestBody RequestObjectModel requestObjectModel) {
        return iToDoList.addToDo(requestObjectModel);
    }

    @PostMapping(value = "/add_user_to_ticket",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    private ResponseService<TicketDTO> addUserToTicket(@RequestBody RequestObjectModel requestObjectModel) {

        TicketDTO ticketDTO = iToDoList.addUserToTicket(requestObjectModel);

        return new ResponseService<>(ticketDTO);
    }

    @PostMapping(value = "/delete_user_from_ticket",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    private Object deleteUserFromTicket(@RequestBody RequestObjectModel requestObjectModel) {
        return iToDoList.deleteUserFromTicket(requestObjectModel);
    }

    @PostMapping(value = "/delete_ticket",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    private Object deleteTicket(@RequestBody RequestObjectModel requestObjectModel) {

        return iToDoList.deleteTicket(requestObjectModel);

    }

    @GetMapping("/getUserTickets")
    private ResponseService<List<TicketDTO>> getUserTickets(RequestObjectModel requestObjectModel) {

        List<TicketDTO> dto = iToDoList.getUserTickets(requestObjectModel);

        return new ResponseService<>(dto);
    }

    @PostMapping(value = "/change_ticket_status",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    private ResponseService<TicketDTO> changeTicketStatus(@RequestBody RequestObjectModel requestObjectModel) {
        TicketDTO ticketDTO = iToDoList.changeTicketStatus(requestObjectModel);
        return new ResponseService<>(ticketDTO);
    }
}
