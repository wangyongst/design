package com.myweb.dao.jpa.hibernate;

import com.myweb.pojo.AdminRole;
import com.myweb.pojo.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryDefinition(domainClass = AdminRole.class, idClass = Integer.class)
public interface AdminRoleRepository extends JpaRepository<AdminRole, Integer> {
}