package com.lesson9.server.repository;


import com.lesson9.server.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ClientRepository extends JpaRepository<Client, Integer> {
}
