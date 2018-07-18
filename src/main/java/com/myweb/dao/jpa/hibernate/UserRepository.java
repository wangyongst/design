package com.myweb.dao.jpa.hibernate;

import com.myweb.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@RepositoryDefinition(domainClass = User.class, idClass = Integer.class)
public interface UserRepository extends JpaRepository<User, Integer> {

    public List<User> findByUsername(String username);

    public List<User> findByEmail(String email);

    public Page<User> findAllByIdNot(Integer id, Pageable pageable);

    public Page<User> findByRefer(Integer refer, Pageable pageable);

    public List<User> findByRefer(Integer refer);

    public List<User> findByUsernameAndPassword(String username, String password);

    public List<User> findByEmailAndPassword(String email, String password);

    public Page<User> findAllByUsernameOrNicknameOrSexOrJobs(String keword, Pageable pageable);
}