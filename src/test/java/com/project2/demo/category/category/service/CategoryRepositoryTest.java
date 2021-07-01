package com.project2.demo.category.category.service;


import com.project2.demo.category.category.domain.Category;
import com.project2.demo.category.category.domain.CategoryName;
import com.project2.demo.category.category.domain.CategoryRepository;
import com.project2.demo.category.category.domain.Description;
import com.project2.demo.util.RandomParametersExtension;
import com.project2.demo.util.RandomParametersExtension.Random;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@SpringBootTest
@ExtendWith(RandomParametersExtension.class)
public class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;





    @Test
    public void saveTest(){
        Category categoryForTest
                = Category.create(
                CategoryName.create("test"),
                Description.create("for Test"));
        Category savedCategory = categoryRepository.save(categoryForTest);

        Category result = categoryRepository.findCategoryById(savedCategory.getId());
        assertNotNull("save success", result);
//        System.out.println(categoryForTest.equals(result));
        assertEquals("id check", savedCategory.getId(), result.getId());
        assertEquals("categoryName check", savedCategory.getCategoryName(), result.getCategoryName());
        assertEquals("description check", savedCategory.getDescription(), result.getDescription());

    }

    @Test
    public void test3(){
        categoryRepository.saveAll(IntStream.range(1, 10).mapToObj(it -> Category.create(
                CategoryName.create(getRandomString()),
                Description.create(getRandomString())
        )).collect(Collectors.toList()));


    }


    @RepeatedTest(100)
    @DisplayName("테스트 이름")
    public void test2(@Random String categoryName, @Random String description){
        Category categoryForTest
                = Category.create(
                CategoryName.create(categoryName),
                Description.create(description));
//        List<Category> categoryList = new ArrayList<>();
//        setList(categoryList, 10);
        categoryRepository.save(categoryForTest);

    }





    @AfterEach
    public void cleanUp(){
        categoryRepository.deleteAll();
    }

    private String getRandomString()
    {
        java.util.Random random = new java.util.Random();
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }
}
