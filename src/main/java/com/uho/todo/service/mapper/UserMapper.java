package com.uho.todo.service.mapper;

import com.uho.todo.service.dto.UsersDTO;
import com.uho.todo.domain.UsersEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring",uses = TicketMapper.class)
@Component
public interface UserMapper {

    UsersDTO entityToDTO(UsersEntity usersEntity);

}
