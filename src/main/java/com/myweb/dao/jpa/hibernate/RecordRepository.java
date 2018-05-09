package com.myweb.dao.jpa.hibernate;

import com.myweb.pojo.Bookstore;
import com.myweb.pojo.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryDefinition(domainClass = Record.class, idClass = Integer.class)
public interface RecordRepository extends JpaRepository<Record,Integer> {
}