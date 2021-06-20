package com.project2.demo.Menu.menu.service;


import com.project2.demo.Menu.menu.controller.MenuParameter;
import com.project2.demo.Menu.menu.domain.MenuRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class GettingMenuInfoTest {

    @Mock
    MenuRepository menuRepository;



    MenuParameter menuParameter
            = new MenuParameter("pizza"
            , 50000,
            "this is pizza",
            LocalTime.parse("08:00:00", DateTimeFormatter.ofPattern("HH:mm:ss")),
            LocalTime.parse("20:00:00", DateTimeFormatter.ofPattern("HH:mm:ss")),30,
            "pizza url"
    );
}