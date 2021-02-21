package com.uho.todo.repository;

import com.uho.todo.domain.TicketEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TicketRepository extends PagingAndSortingRepository<TicketEntity,Long>, JpaRepository<TicketEntity,Long> {

    List<TicketEntity> findAllBy(Pageable pageable);

    Optional<TicketEntity> findById(Long id);

    List<TicketEntity> findByUserId(Pageable pageable,Long id);

    void deleteById(Long id);

    @Modifying
    @Query("update ticket t set t.del = true where t.deadLine < :date")
    void changeTicketStatus(Date date);
}
