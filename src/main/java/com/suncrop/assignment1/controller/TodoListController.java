/**
 * This controller expose API to the third party and client can access API 
 * 
 */
package com.suncrop.assignment1.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suncrop.assignment1.data.model.ApiResponse;
import com.suncrop.assignment1.data.model.Todo;
import com.suncrop.assignment1.data.repository.TodoListRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class TodoListController {

	@Autowired
	TodoListRepository todoListRepository;

	@GetMapping("/todoList")
	public ApiResponse<Todo> getAllItems() {
		try {
			List<Todo> todo = new ArrayList<Todo>();
			todoListRepository.findAll().forEach(todo::add);

			return new ApiResponse<>(HttpStatus.OK.value(), "Item fetched successfully.",todo);
		} catch (Exception e) {
			return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something wrong in todoList model", null);
		}
	}


	@PostMapping("/todoList")
	public ApiResponse<Todo> createItem(@RequestBody Todo todo) {
		try {
			Todo todoObj = todoListRepository
					.save(todo);
			return new ApiResponse<>(HttpStatus.CREATED.value(), "Item saved successfully.",todoObj);
		} catch (Exception e) {
			return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something wrong in todoList model", null);
		}
	}

	@PutMapping("/todoList/{id}")
	public ApiResponse<Todo> updateItem(@RequestBody Todo todo) {
		Optional<Todo> todoListData = todoListRepository.findById(todo.getId());

		if (todoListData.isPresent()) {
			Todo todoObj = todoListData.get();
			todoObj.setItemName(todo.getItemName());
			todoObj.setCompleted(todo.isCompleted());
			return new ApiResponse<>(HttpStatus.OK.value(), "Item updated successfully.",todoListRepository.save(todoObj));
		} else {
			return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Record not found", null);
		}
	}

	@DeleteMapping("/todoList/{id}")
	public ResponseEntity<HttpStatus> deleteTodoList(@PathVariable("id") long id) {
		try {
			todoListRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/todoList/{id}")
    public ApiResponse<Todo> findItemById(@PathVariable long id){
        return new ApiResponse<>(HttpStatus.OK.value(), "Item fetched successfully.",todoListRepository.findById(id));
    }

}
