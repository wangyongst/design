package com.myweb.dao.jpa.hibernate;

import com.myweb.pojo.Help;
import com.myweb.pojo.Referips;
import com.myweb.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryDefinition(domainClass = Referips.class, idClass = Integer.class)
public interface ReferipsRepository extends JpaRepository<Referips, Integer> {
    public Referips findTop1ByUserAndHelpAndIp(User user, Help help,String ip);
}