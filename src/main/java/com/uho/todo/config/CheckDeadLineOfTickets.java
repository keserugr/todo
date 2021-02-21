package com.uho.todo.config;


import com.uho.todo.service.IToDoList;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@RequiredArgsConstructor
@Configuration
@EnableScheduling
public class CheckDeadLineOfTickets {

    private final IToDoList iToDoList;


    // cron (second, minute, hour, day, month, weekday)
    @Scheduled(cron = "00 59 14 * * *")
    public void checkTicketStatus(){
        iToDoList.checkTicketStatus();
    }
}
