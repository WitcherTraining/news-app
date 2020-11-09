package com.epam.spring.bootstrap;

import com.epam.spring.model.News;
import com.epam.spring.model.Role;
import com.epam.spring.model.User;
import com.epam.spring.services.CategoryService;
import com.epam.spring.services.NewsService;
import com.epam.spring.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Slf4j
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final UserService userService;
    private final NewsService newsService;
    private final CategoryService categoryService;

    public DataLoader(UserService userService, NewsService newsService, CategoryService categoryService) {
        this.userService = userService;
        this.newsService = newsService;
        this.categoryService = categoryService;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        System.out.println("Start to initialize user");

        User lera = new User();

        lera.setId(1L);
        lera.setFirstName("Lera");
        lera.setLastName("Kovaleva");
        lera.setLogin("login");
        lera.setPassword("password");
        lera.setRole(Role.ADMIN);
        lera.setNews(new HashSet<News>());

        userService.save(lera);

        User roma = new User();

        roma.setId(2L);
        roma.setFirstName("Roma");
        roma.setLastName("Onishenko");
        roma.setLogin("login");
        roma.setPassword("password");
        roma.setRole(Role.AUTHOR);
        roma.setNews(new HashSet<News>());

        userService.save(roma);

        System.out.println("Complete to initialize users...");

        News news1 = new News();

        news1.setId(1L);
        news1.setTitle("Incredible news");
        news1.setContent("Huge text Huge textHuge textHuge textHuge textHuge textHuge textHuge textHuge " +
                "textHuge textHuge textHuge textHuge textHuge textHuge textHuge textHuge textHuge text");
        news1.setBriefContent("brief text brief text brief text brief text brief text brief text ");
        news1.setAuthor(roma);

        newsService.save(news1);

        System.out.println("Complete to initialize news...");
    }
}
