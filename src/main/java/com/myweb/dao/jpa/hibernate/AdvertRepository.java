package com.myweb.dao.jpa.hibernate;

import com.myweb.pojo.Advert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryDefinition(domainClass = Advert.class, idClass = Integer.class)
public interface AdvertRepository extends JpaRepository<Advert, Integer> {
    Page<Advert> findAllByOuttimeGreaterThanOrderByReferDesc(String outtime, Pageable pageable);
}