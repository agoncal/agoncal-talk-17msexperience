package org.agoncal.talk.msexperience.demo02.bookapi.repository;

import org.agoncal.talk.msexperience.demo02.bookapi.domain.Book;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Book entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
