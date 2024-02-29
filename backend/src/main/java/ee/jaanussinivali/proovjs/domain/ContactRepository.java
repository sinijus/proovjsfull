package ee.jaanussinivali.proovjs.domain;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
    @Query("select (count(c) > 0) from Contact c where upper(c.firstName) = upper(?1) and upper(c.lastName) = upper(?2)")
    boolean existsByFirstNameAndLastName(String firstName, String lastName);

    @Query("""
            select c from Contact c
            where upper(c.firstName) like upper(concat('%', ?1, '%')) or
            upper(c.lastName) like upper(concat('%', ?1, '%')) or
            upper(c.codename) like upper(concat('%', ?1, '%'))""")
    List<Contact> findAllContactsContaining(String name);

    @Query("""
            select c from Contact c
            where upper(c.firstName) like upper(concat('%', ?1, '%')) or
            upper(c.lastName) like upper(concat('%', ?1, '%')) or
            upper(c.codename) like upper(concat('%', ?1, '%'))""")
    List<Contact> findAllContactsContainingNameAndOrdered(String name, Sort sort);

}