package com.myweb.dao.jpa.hibernate;

import com.myweb.pojo.Bookstore;
import com.myweb.pojo.Pay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryDefinition(domainClass = Pay.class, idClass = Integer.class)
public interface PayRepository extends JpaRepository<Pay,Integer> {
}