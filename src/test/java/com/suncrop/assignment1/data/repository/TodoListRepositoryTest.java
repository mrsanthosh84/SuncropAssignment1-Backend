package com.suncrop.assignment1.data.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.suncrop.assignment1.data.model.Todo;

@RunWith(SpringRunner.class)
public class TodoListRepositoryTest {
	
	
	@MockBean
    private TodoListRepository todoListRepository;
	
	 @Test
     public void WhenFindAllTodoListThenReturnSizeOfTodoList() {
        Todo todo = new Todo("Task 1", false);
		List<Todo> todos = new ArrayList<Todo>();
		todos.add(todo);
	    Mockito.when(todoListRepository.findAll())
	      .thenReturn(todos);
        
        List<Todo> todoList1 = todoListRepository.findAll();
       
        assertEquals(1, todoList1.size() );    
    }
	 
	 
	 @Test
     public void WhenFindByIdThenReturnTodoList() {
        Todo todo = new Todo("Task 1", false);
        todo.setId(1);
		
	    Mockito.when(todoListRepository.findById(Long.valueOf(1)))
	      .thenReturn(Optional.of(todo));
        
        Optional<Todo> todoList1 = todoListRepository.findById(Long.valueOf(1));
       
        assertTrue(todoList1.isPresent() );    
    } 
	 
	 @Test
     public void WhenSaveTodoListThenReturnValue() {
        Todo todo = new Todo("Task 1", false);
        todo.setId(1);
		
	    Mockito.when(todoListRepository.save(Mockito.any(Todo.class)))
	      .thenReturn(todo);
        
        Todo todoList1 = todoListRepository.save(todo);
       
        assertEquals("Task 1", todoList1.getItemName());    
    } 
}
