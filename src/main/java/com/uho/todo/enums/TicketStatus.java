package com.uho.todo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TicketStatus {
    TO_DO,
    IN_PROGRESS,
    DONE;
}