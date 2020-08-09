package com.suncrop.assignment1.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.suncrop.assignment1.data.model.ApiResponse;
import com.suncrop.assignment1.data.model.Todo;
import com.suncrop.assignment1.data.repository.TodoListRepository;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(TodoListController.class)
public class TodoListControllerTest{
	
    @InjectMocks
    TodoListController todoListController;
	
	@Mock
    private TodoListRepository todoListRepository;
	
	
   @Test
   public void WhenCreateTodoItemThenReturn200ResponseCode() throws Exception {
       Todo todo = new Todo("Task 1", false);
       todo.setId(1);
		
	    Mockito.when(todoListRepository.save(Mockito.any(Todo.class)))
	      .thenReturn(todo);
       
        
       ApiResponse<Todo> responseEntity = todoListController.createItem(todo);
        
       assertThat(responseEntity.getStatus()).isEqualTo(201);
       assertThat(responseEntity.getResult()).isNotNull();
   }
   
   @Test
   public void WhenFindAllTodosThenReturn200ResponseCode() throws Exception {
       Todo todo = new Todo("Task 1", false);
       todo.setId(1);
       List<Todo> todos = new ArrayList<Todo>();
       todos.add(todo);
	   Mockito.when(todoListRepository.findAll())
	      .thenReturn(todos);
       ApiResponse<Todo> responseEntity = todoListController.getAllItems();
        
       assertThat(responseEntity.getStatus()).isEqualTo(200);
       assertThat(responseEntity.getResult()).isNotNull();
   }
   
   @Test
   public void WhenFindAllTodosThenReturn500ResponseCode() throws Exception {
       Todo todo = new Todo("Task 1", false);
       todo.setId(1);
       List<Todo> todos = new ArrayList<Todo>();
       todos.add(todo);
	   Mockito.when(todoListRepository.findAll())
	      .thenReturn(null);
       ApiResponse<Todo> responseEntity = todoListController.getAllItems();
        
       assertThat(responseEntity.getStatus()).isEqualTo(500);
       assertThat(responseEntity.getResult()).isNull();
   }
   
   @Test
   public void WhenUpdateTodoThenReturn200ResponseCode() throws Exception {
       Todo todo = new Todo("Task 1", false);
       todo.setId(1);
       Mockito.when(todoListRepository.findById(Mockito.anyLong()))
	      .thenReturn(Optional.of(todo));
       Mockito.when(todoListRepository.save(Mockito.any(Todo.class)))
	      .thenReturn(todo);
       ApiResponse<Todo> responseEntity = todoListController.updateItem(todo);
        
       assertThat(responseEntity.getStatus()).isEqualTo(200);
       assertThat(responseEntity.getResult()).isNotNull();
   }
   
   @Test
   public void WhenUpdateTodoThenReturn404ResponseCode() throws Exception {
       Todo todo = new Todo("Task 1", false);
       todo.setId(1);
       
       ApiResponse<Todo> responseEntity = todoListController.updateItem(todo);
        
       assertThat(responseEntity.getStatus()).isEqualTo(404);
       assertThat(responseEntity.getResult()).isNull();
   }
   
   @Test
   public void WhenDeleteByIdThenReturn204ResponseCode() throws Exception {
      
	   ResponseEntity<HttpStatus> responseEntity = todoListController.deleteTodoList(Long.valueOf(1));
        
       assertThat(responseEntity.getStatusCodeValue()).isEqualTo(204);
   }
}
