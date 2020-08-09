package com.suncrop.assignment1.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suncrop.assignment1.data.model.Todo;

@Repository
public interface TodoListRepository extends JpaRepository<Todo, Long> {
	
}
