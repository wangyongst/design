package com.myweb.dao.jpa.hibernate;

import com.myweb.pojo.Advert;
import com.myweb.pojo.Buy;
import com.myweb.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryDefinition(domainClass = Buy.class, idClass = Integer.class)
public interface BuyRepository extends JpaRepository<Buy, Integer> {
    public List<Buy> findAllByUserAndAdvert(User user, Advert advert);

    public void removeAllByUser(User user);
}