package com.uho.todo.repository;


import com.uho.todo.domain.UsersEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UsersRepository extends PagingAndSortingRepository<UsersEntity,Long> {

    Optional<UsersEntity> findById(Long id);

}
