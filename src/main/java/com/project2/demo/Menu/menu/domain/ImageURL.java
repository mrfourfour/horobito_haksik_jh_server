package com.project2.demo.Menu.menu.domain;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@RequiredArgsConstructor
public class ImageURL {

    private String imageURL;

    private ImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public static ImageURL create(String imageURL){
        return new ImageURL(imageURL);
    }
}
