package com.uho.todo.service;

import com.uho.todo.domain.TicketEntity;
import com.uho.todo.domain.UsersEntity;
import com.uho.todo.enums.TicketStatus;
import com.uho.todo.model.RequestObjectModel;
import com.uho.todo.repository.TicketRepository;
import com.uho.todo.repository.UsersRepository;
import com.uho.todo.service.dto.TicketDTO;
import com.uho.todo.service.mapper.TicketMapper;
import com.uho.todo.service.mapper.TicketMapperImpl;
import com.uho.todo.service.mapper.UserMapper;
import com.uho.todo.service.mapper.UserMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {TicketMapperImpl.class, UserMapperImpl.class})
class ToDoListTest {

    @Mock
    TicketRepository ticketRepository;

    @Mock
    UsersRepository usersRepository;

    TicketMapper iMapper = Mappers.getMapper(TicketMapper.class);
    UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    ToDoList toDoListService;

    @BeforeEach
    public void setUp() {

        toDoListService = new ToDoList(ticketRepository, usersRepository, iMapper, userMapper);
    }

    @Test
    void addToDo() {
        //arrange
        RequestObjectModel toDoEntity = new RequestObjectModel();

        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setId(1L);
        ticketDTO.setTitle("test");
        ticketDTO.setDescription("ilk test");

        toDoEntity.setTicketDTO(ticketDTO);

        //act
        Object o = toDoListService.addToDo(toDoEntity);

        // TicketEntity domain = iMapper.dtoToEntity(toDoEntity.getTicketDTO());

        //assertion
        ArgumentCaptor<TicketEntity> objectArgumentCaptor = ArgumentCaptor.forClass(TicketEntity.class);
        verify(ticketRepository).save(objectArgumentCaptor.capture());

        TicketEntity value = objectArgumentCaptor.getValue();

        // assertEquals(1L, value.getPerson());
        assertEquals("success", o);
        //assertEquals(1L,domain.getPerson());

    }

    @Test
    void addUsertoTicket() {

        //arrange

        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setId(1L);

        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setId(1L);

        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticketEntity));
        when(usersRepository.findById(1L)).thenReturn(Optional.of(usersEntity));


        RequestObjectModel requestObjectModel = new RequestObjectModel();

        requestObjectModel.setUserId(1L);
        requestObjectModel.setTicketId(1L);

        //act
        TicketDTO ticketDTO = toDoListService.addUserToTicket(requestObjectModel);

        //assertion
        ArgumentCaptor<TicketEntity> objTicket = ArgumentCaptor.forClass(TicketEntity.class);
        verify(ticketRepository).save(objTicket.capture());

        TicketEntity ticketEntity1 = objTicket.getValue();
        //assertEquals(1L,ticketEntity1.getPerson());
        Optional<TicketEntity> en = ticketRepository.findById(1L);
        assertEquals(1L, en.get().getUser().getId());
        //assertEquals(1L, ticketDTO.getUser().getId());

    }

    @Test
    void deleteUserFromTicket() {

        //arrange

        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setId(1L);
        //ticketEntity.setPerson(1L);

        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setId(1L);

        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticketEntity));
        when(usersRepository.findById(1L)).thenReturn(Optional.of(usersEntity));

        RequestObjectModel requestObjectModel = new RequestObjectModel();
        requestObjectModel.setUserId(1L);
        requestObjectModel.setTicketId(1L);

        /*TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setPerson(1L);
        ticketRepository.save(ticketEntity);*/


        //act
        Object o = toDoListService.deleteUserFromTicket(requestObjectModel);

        //assertion

        ArgumentCaptor<TicketEntity> obj = ArgumentCaptor.forClass(TicketEntity.class);
        verify(ticketRepository).save(obj.capture());

        TicketEntity ticket = obj.getValue();
        //assertEquals(null, ticket.getPerson());
        assertEquals("success", o);

    }

    @Test
    void changeTicketStatus() {

        //arrange
        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setId(1L);
        ticketEntity.setStatus(TicketStatus.valueOf("TO_DO"));

        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticketEntity));

        RequestObjectModel requestObjectModel = new RequestObjectModel();
        requestObjectModel.setTicketStatus("DONE");
        requestObjectModel.setTicketId(1L);

        //act
        TicketDTO ticketDTO = toDoListService.changeTicketStatus(requestObjectModel);

        //assertion
        ArgumentCaptor<TicketEntity> obj = ArgumentCaptor.forClass(TicketEntity.class);
        verify(ticketRepository).save(obj.capture());

        TicketEntity entity = obj.getValue();

        assertEquals("DONE", entity.getStatus().name());

    }

}