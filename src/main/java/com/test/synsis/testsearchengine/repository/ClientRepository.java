package com.test.synsis.testsearchengine.repository;

import com.test.synsis.testsearchengine.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
}
