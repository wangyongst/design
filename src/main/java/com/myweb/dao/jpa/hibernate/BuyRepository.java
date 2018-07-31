package com.myweb.dao.jpa.hibernate;

import com.myweb.pojo.Buy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryDefinition(domainClass = Buy.class, idClass = Integer.class)
public interface BuyRepository extends JpaRepository<Buy, Integer> {

}