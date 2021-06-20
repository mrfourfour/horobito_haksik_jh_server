package com.project2.demo.Menu.menu.service;


import com.project2.demo.Menu.menu.domain.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

@ExtendWith(MockitoExtension.class)
public class AddAndSubtractAndModifyAmountTest {

    @Mock
    MenuRepository menuRepository;

    DateTimeFormatter fmt = new DateTimeFormatterBuilder()
            .append(DateTimeFormatter.ISO_LOCAL_TIME)
            .parseDefaulting(ChronoField.EPOCH_DAY, 0)
            .toFormatter();

    Menu menu = MenuHelper.create(
            Long.parseLong("1"),
            Title.create("testFood"),
            Price.create(10000),
            MenuDescription.create("for Test"),
            Time.create(LocalDateTime.parse("08:00:00", fmt), LocalDateTime.parse("20:00:00", fmt)),
            AmountOfFoodLeft.create(10),
            ImageURL.create("testURL")
    );
}
