package com.myweb.dao.jpa.hibernate;

import com.myweb.pojo.Help;
import com.myweb.pojo.Message;
import com.myweb.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryDefinition(domainClass = Message.class, idClass = Integer.class)
public interface MessageRepository extends JpaRepository<Message, Integer> {
    @Query("select message.touser from Message message where message.user = ?1 or message.touser = ?1 group by message.touser")
    public List<User> findTouserByUserOrTouser(User user);

    @Query("select message.user from Message message where message.user = ?1 or message.touser = ?1 group by message.user")
    public List<User> findUserByUserOrTouser(User user);

    public List<Message> findByUserOrTouser(User user, User touser,Pageable pageable);

    public List<Message> findByUserOrTouser(User user, User touser);
}