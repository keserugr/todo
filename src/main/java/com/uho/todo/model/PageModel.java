package com.uho.todo.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@RequiredArgsConstructor
public class PageModel implements Serializable {

    private Integer page;
    private Integer limit;

}
