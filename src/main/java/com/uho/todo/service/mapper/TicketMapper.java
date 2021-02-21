package com.uho.todo.service.mapper;

import com.uho.todo.service.dto.TicketDTO;
import com.uho.todo.domain.TicketEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", uses = UserMapper.class)
@Component
public interface TicketMapper {

    TicketDTO entityToDTO(TicketEntity ticketEntity);

    TicketEntity dtoToEntity(TicketDTO ticketDTO);

}
