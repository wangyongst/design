package com.myweb.dao.jpa.hibernate;

import com.myweb.pojo.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryDefinition(domainClass = Report.class, idClass = Integer.class)
public interface ReportRepository extends JpaRepository<Report, Integer> {
}