package org.agoncal.talk.msexperience.demo02.bookweb.repository;

import org.agoncal.talk.msexperience.demo02.bookweb.domain.Authority;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
