package com.epam.spring.bootstrap;

import com.epam.spring.dao.NewsDAO;
import com.epam.spring.dao.UserDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final NewsDAO newsDAO;
    private final UserDAO userDAO;

    public DataLoader(NewsDAO newsDAO, UserDAO userDAO) {
        this.newsDAO = newsDAO;
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        System.out.println("Start to initialize user");

//        User lera = new User();
//
//        lera.setFirstName("Lera");
//        lera.setLastName("Kovaleva");
//        lera.setLogin("login");
//        lera.setPassword("password");
//        lera.setRole(Role.ADMIN);
//
//        userService.save(lera);
//
//        User roma = new User();
//
//        roma.setFirstName("Roma");
//        roma.setLastName("Onishenko");
//        roma.setLogin("login");
//        roma.setPassword("password");
//        roma.setRole(Role.AUTHOR);
//        roma.setNews(new HashSet<News>());
//
//        userService.save(roma);
//
//        System.out.println("Complete to initialize users...");
//
//        Category science = new Category();
//        science.setName("Science");
//
//        categoryService.save(science);
//
//        Category space = new Category();
//        space.setName("Space");
//
//        categoryService.save(space);
//
//        News news1 = new News();
//        news1.setTitle("Pfizer COVID-19 vaccine is 90% effective, preliminary trial data show");
//        news1.setContent("The race to greenlight a COVID-19 vaccine in the United States has entered its final sprint, with one leading candidate becoming the first to release preliminary results showing its vaccine is more than 90 percent effective at preventing people from getting sick from the coronavirus.\n" +
//                "\n" +
//                "The long-awaited announcement came in a Nov. 9 news release detailing the results from an interim analysis of an ongoing Phase III clinical trial comparing the vaccine developed by global pharmaceutical company Pfizer and German biotech company BioNTech with a placebo.\n" +
//                "\n" +
//                "COVID-19 cases are soaring globally, and some countries are reverting to lockdowns and other drastic measures to curb the virus’ spread. As of November 9, more than 50 million people have been infected worldwide — including more than 10 million in the United States — and more than 1.2 million people have died from the disease.");
//        news1.setBriefContent("The vaccine protects people from getting COVID-19, according to an analysis of 94 cases" );
//        news1.setAuthor(roma);
//        news1.getCategories().add(science);
//        news1.getCategories().add(space);
//
//        newsService.save(news1);
//
//        userService.save(roma);
//
//        News spaceNews = new News();
//        spaceNews.getCategories().add(space);
//        spaceNews.setTitle("About half of Sun-like stars could host rocky, potentially habitable planets");
//        spaceNews.setBriefContent("Since astronomers confirmed the presence of planets beyond our solar system, " +
//                "called exoplanets, humanity has wondered how many could harbor life.");
//        spaceNews.setContent("Our galaxy holds at least an estimated 300 million of these potentially habitable worlds, based on even the most conservative interpretation of the results in a new study to be published in The Astronomical Journal. Some of these exoplanets could even be our interstellar neighbors, with at least four potentially within 30 light-years of our Sun and the closest likely to be at most about 20 light-years from us. These are the minimum numbers of such planets based on the most conservative estimate that 7% of Sun-like stars host such worlds. However, at the average expected rate of 50%, there could be many more.\n" +
//                "\n" +
//                "This research helps us understand the potential for these planets to have the elements to support life. This is an essential part of astrobiology, the study of life's origins and future in our universe.\n" +
//                "\n" +
//                "The study is authored by NASA scientists who worked on the Kepler mission alongside collaborators from around the world. NASA retired the space telescope in 2018 after it ran out of fuel. Nine years of the telescope's observations revealed that there are billions of planets in our galaxy -- more planets than stars.");
//        spaceNews.setAuthor(roma);
//
//        newsService.save(spaceNews);
//
//        userService.save(roma);
//
//        System.out.println("Complete to initialize news...");
    }
}
