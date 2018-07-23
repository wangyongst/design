package com.myweb.dao.jpa.hibernate;

import com.myweb.pojo.User;
import com.myweb.pojo.User2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryDefinition(domainClass = User2.class, idClass = Integer.class)
public interface User2Repository extends JpaRepository<User2, Integer> {
    public List<User2> findAllByIdNot(Integer id);
}