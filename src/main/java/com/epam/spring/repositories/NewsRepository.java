package com.epam.spring.repositories;

import com.epam.spring.model.News;
import org.springframework.data.repository.CrudRepository;

public interface NewsRepository extends CrudRepository<News, Long> {
}
