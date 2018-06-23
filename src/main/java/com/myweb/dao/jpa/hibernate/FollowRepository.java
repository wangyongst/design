package com.myweb.dao.jpa.hibernate;

import com.myweb.pojo.Follow;
import com.myweb.pojo.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryDefinition(domainClass = Follow.class, idClass = Integer.class)
public interface FollowRepository extends JpaRepository<Follow, Integer> {

    public List<Follow> findByUserAndFollow(User user, User follow);

    public List<Follow> findByUser(User userid, Pageable pageable);

    public List<Follow> findByFollow(User touserid, Pageable pageable);
}