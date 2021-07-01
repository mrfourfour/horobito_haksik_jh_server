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

    @Embedded
    public Password password;


    private User(Username username, Password password) {
        this.username = username;
        this.password = password;
    }

    public static User create(Username username, Password password) {
        return new User(username, password);
    }

    public String getUsername(){
        return this.username.getUsername();
    }
}
