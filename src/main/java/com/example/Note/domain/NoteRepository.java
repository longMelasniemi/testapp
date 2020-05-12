package com.example.Note.domain;


import java.util.List;

import org.springframework.data.repository.CrudRepository;



public interface NoteRepository extends CrudRepository<Note, Long> {
	
	List<Note> findByUser_userid(Long userid);


}
