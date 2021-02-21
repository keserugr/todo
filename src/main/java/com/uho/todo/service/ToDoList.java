package com.uho.todo.service;

import com.uho.todo.exception.BaseException;
import com.uho.todo.enums.TicketStatus;
import com.uho.todo.model.ResponseService;
import com.uho.todo.service.dto.TicketDTO;
import com.uho.todo.domain.TicketEntity;
import com.uho.todo.domain.UsersEntity;
import com.uho.todo.enums.ResponseCodes;
import com.uho.todo.model.RequestObjectModel;
import com.uho.todo.repository.TicketRepository;
import com.uho.todo.repository.UsersRepository;
import com.uho.todo.service.mapper.TicketMapper;
import com.uho.todo.service.mapper.UserMapper;
import com.uho.todo.utils.Extensions;
import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.uho.todo.utils.Extensions.getDate;

@Transactional
@ExtensionMethod({
        Extensions.class
})
@Service
@RequiredArgsConstructor
public class ToDoList implements IToDoList {

    private final TicketRepository ticketRepository;
    private final UsersRepository usersRepository;
    private final TicketMapper ticketMapper;
    private final UserMapper userMapper;

    @Override
    public List<TicketDTO> getToDoList(RequestObjectModel requestObjectModel) {

        Pageable pageable = PageRequest.of(requestObjectModel.getPage(), requestObjectModel.getLimit());

        List<TicketDTO> getToDoList = ticketRepository.findAllBy(pageable).stream().map(ticketMapper::entityToDTO).collect(Collectors.toList());

        return getToDoList;
    }

    @Override
    public Object addToDo(RequestObjectModel requestObjectModel) {

        TicketEntity ticketEntity = ticketMapper.dtoToEntity(requestObjectModel.getTicketDTO());

        return ticketRepository.save(ticketEntity);
    }

    @Override
    public TicketDTO addUserToTicket(RequestObjectModel requestObjectModel) {
        UsersEntity usersEntity = usersRepository.findById(requestObjectModel.getUserId()).orElseThrow(() -> new BaseException(ResponseCodes.USER_NOT_FOUND));
        TicketEntity ticketEntity = ticketRepository.findById(requestObjectModel.getTicketId()).orElseThrow(() -> new BaseException(ResponseCodes.TICKET_NOT_FOUND));

        if (ticketEntity.getUser() == null) {
            UsersEntity entity = new UsersEntity();
            entity.setId(requestObjectModel.getUserId());
            ticketEntity.setUser(entity);
        }

        TicketDTO ticketDTO = ticketMapper.entityToDTO(ticketRepository.save(ticketEntity));

        return ticketDTO;
    }

    @Override
    public Object deleteUserFromTicket(RequestObjectModel requestObjectModel) {

        UsersEntity usersEntity = usersRepository.findById(requestObjectModel.getUserId()).orElseThrow(() -> new BaseException(ResponseCodes.USER_NOT_FOUND));
        TicketEntity ticketEntity = ticketRepository.findById(requestObjectModel.getTicketId()).orElseThrow(() -> new BaseException(ResponseCodes.TICKET_NOT_FOUND));

        //ticketRepository.save(ticketEntity.get());
        return "success";
    }

    @Override
    public Object deleteTicket(RequestObjectModel requestObjectModel) {

        TicketEntity ticketEntity = ticketRepository.findById(requestObjectModel.getTicketId()).orElseThrow(() -> new BaseException(ResponseCodes.TICKET_NOT_FOUND));

        ticketRepository.deleteById(ticketEntity.getId());

        return "success";
    }

    @Override
    public List<TicketDTO> getUserTickets(RequestObjectModel requestObjectModel) {

        UsersEntity usersEntity = usersRepository.findById(requestObjectModel.getUserId()).orElseThrow(() -> new BaseException(ResponseCodes.USER_NOT_FOUND));

        Pageable pageable = PageRequest.of(requestObjectModel.getPage(), requestObjectModel.getLimit());

        List<TicketDTO> byUserId = ticketRepository.findByUserId(pageable, requestObjectModel.getUserId()).stream().map(ticketMapper::entityToDTO).collect(Collectors.toList());

        return byUserId;
    }

    @Override
    public TicketDTO changeTicketStatus(RequestObjectModel requestObjectModel) {

        TicketEntity ticketEntity = ticketRepository.findById(requestObjectModel.getTicketId()).orElseThrow(() -> new BaseException(ResponseCodes.TICKET_NOT_FOUND));
        ticketEntity.setStatus(TicketStatus.valueOf(requestObjectModel.getTicketStatus()));

        TicketDTO ticketDTO = ticketMapper.entityToDTO(ticketRepository.save(ticketEntity));

        return ticketDTO;
    }

    @Override
    public void checkTicketStatus() {
        ticketRepository.changeTicketStatus(getDate());
    }
}