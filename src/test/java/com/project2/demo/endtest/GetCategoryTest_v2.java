package com.project2.demo.endtest;

import com.project2.demo.Menu.menu.domain.MenuRepository;
import com.project2.demo.Menu.menu.service.MenuDto;
import com.project2.demo.category.categorizedFood.domain.CategorizedFoodRepository;
import com.project2.demo.category.category.domain.CategoryRepository;
import com.project2.demo.category.category.service.CategoryDetailDto;
import com.project2.demo.category.category.service.CategoryService;
import com.project2.demo.util.RandomParametersExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@SpringBootTest
@ExtendWith(RandomParametersExtension.class)
public class GetCategoryTest_v2 {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategorizedFoodRepository categorizedFoodRepository;
    @Autowired
    MenuRepository menuRepository;


    @DisplayName("getAllDetailsTest 1. 정상 테스트 1.  cursor is not null")
    @Test
    public void getTest1(){
        CategoryService sut = new CategoryService(
                categoryRepository,
                menuRepository,
                categorizedFoodRepository
        );

        Pageable page = PageRequest.of(0, 5);



        List<CategoryDetailDto> result = sut.getAllDetails(6, page);

        for (CategoryDetailDto detailDto : result){
            System.out.println("categoryId : " + detailDto.getCategoryId());
            System.out.println(detailDto.getCategory());
            System.out.println(detailDto.getDescription());
            for (MenuDto dto : detailDto.getMenuDtoList()){
                System.out.println(dto.getId());
                System.out.println(dto.getTitle());
                System.out.println("wow"+dto.getDescription());
                System.out.println(dto.getImageURL());
                System.out.println(dto.getPrice());
                System.out.println(dto.getStartTime().toString());
                System.out.println(dto.getEndTime().toString());
            }
            System.out.println();
        }


    }

}
