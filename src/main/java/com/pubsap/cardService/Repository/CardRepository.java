package com.pubsap.cardService.Repository;

import com.pubsap.cardService.Model.Creditcard;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CardRepository extends JpaRepository<Creditcard, Long> {

}