package com.myweb.dao.jpa.hibernate;

import com.myweb.pojo.Timenew;
import com.myweb.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryDefinition(domainClass = Timenew.class, idClass = Integer.class)
public interface TimenewRepository extends JpaRepository<Timenew, Integer> {
    public List<Timenew> findByUserAndType(User user, Integer type);

    public void deleteAllByUser(User user);
}