package com.training.coauth.controller;


import com.training.coauth.Utility.UserUtils;
import com.training.coauth.dto.CategoryRequest;
import com.training.coauth.entity.UserCategory;
import com.training.coauth.repository.UserCategoryRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    UserCategoryRepository userCategoryRepository;

    @Autowired
    UserUtils userUtils;

    @PostMapping("/updateCategory")
    public void updateUserCategory(HttpServletRequest request,@RequestBody CategoryRequest categoryRequest){

        String requestTokenHeader = request.getHeader("Authorization");
        String token = requestTokenHeader.substring(7);
        Claims claims = userUtils.loadClaimsByToken(token);

        Long userId = (Long.valueOf(String.valueOf(claims.get("id"))));
        List<Long> categoryId = categoryRequest.getCategoryId();

        for(Long id : categoryId) {
            UserCategory userCategory = new UserCategory();
            userCategory.setUserId(userId);
            userCategory.setCategoryId(id);
            userCategoryRepository.save(userCategory);
        }
    }


}
