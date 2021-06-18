package com.project2.demo.user.domain;

import com.project2.demo.order.domain.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Embedded
    public Username username;


    private User(Username username) {
        this.username = username;
    }

    public static User create(Username username) {
        return new User(username);
    }
}
