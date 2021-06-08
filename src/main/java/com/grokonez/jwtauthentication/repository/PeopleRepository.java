package com.grokonez.jwtauthentication.repository;

//import java.util.List;

import com.grokonez.jwtauthentication.model.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<People, Long> {
    //List<People> findByNameContaining(String name);
    //List<People> findByEmailContaining(String email);
    //List<People> findByAddressContaining(String address);
    //List<People> findByPhoneContaining(String phone);
}

/*
Let’s create a repository to interact with People from the database.
In repository package, create PeopleRepository interface that extends JpaRepository.

Now we can use JpaRepository’s methods: save(), findOne(), findById(), findAll(), count(), delete(), deleteById()… without implementing these methods.

We also define custom finder methods:
– findByPublished(): returns all Tutorials with published having value as input published.
– findByTitleContaining(): returns all Tutorials which title contains input title.

The implementation is plugged in by Spring Data JPA automatically.

You can modify this Repository:
– to work with Pagination, the instruction can be found at:
Spring Boot Pagination & Filter example | Spring JPA, Pageable
– or to sort/order by multiple fields with the tutorial:
Spring Data JPA Sort/Order by multiple Columns | Spring Boot

You also find way to write Unit Test for this JPA Repository at:
Spring Boot Unit Test for JPA Repositiory with @DataJpaTest
*/