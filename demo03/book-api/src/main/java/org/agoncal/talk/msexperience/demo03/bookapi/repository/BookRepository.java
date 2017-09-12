package org.agoncal.talk.msexperience.demo03.bookapi.repository;

import org.agoncal.talk.msexperience.demo03.bookapi.domain.Book;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Book entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BookRepository extends MongoRepository<Book, String> {

}
