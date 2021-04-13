package com.vojislav.budgetingapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vojislav.budgetingapp.domain.Group;

public interface GroupDao extends JpaRepository<Group, Long> {

}
