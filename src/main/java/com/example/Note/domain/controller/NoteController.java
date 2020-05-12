package com.example.Note.domain.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Note.domain.CategoryRepository;
import com.example.Note.domain.Note;
import com.example.Note.domain.NoteRepository;
import com.example.Note.domain.UserRepository;

@Controller
public class NoteController {

	@Autowired
	private NoteRepository NRepository;
	@Autowired
	private CategoryRepository CRepository;
	@Autowired
	private UserRepository URepository;
	@Autowired
	private HttpServletRequest request;

	/**
	 * List all notes of current user
	 */
	@RequestMapping(value = "/notelist")
	public String noteList(Model model) {
		Principal principal = request.getUserPrincipal();
		String name = principal.getName();
		Long user_id = URepository.findByUsername(name).getUserid();
		model.addAttribute("notes", NRepository.findByUser_userid(user_id));
		System.out.print(NRepository.findByUser_userid((long) 1));
		return "Note/notelist";
	}

	/**
	 * create Form for new Note
	 */
	@RequestMapping(value = "/newnote")
	public String addnote(Model model) {
		Principal principal = request.getUserPrincipal();
		String name = principal.getName();
		System.out.print(URepository.findByUsername(name));
		Note note = new Note();
		note.setUser(URepository.findByUsername(name));
		model.addAttribute("categories", CRepository.findAll());
		model.addAttribute("note", note);
		return "Note/newnote";
	}

	/**
	 * Delete not by id Redirect to notelist.html
	 * 
	 * @params note_id
	 */
	@RequestMapping(value = "/deletenote/{note_id}", method = RequestMethod.GET)
	public String deleteNote(@PathVariable("note_id") Long note_id, Model model) {
		NRepository.deleteById(note_id);
		return "redirect:/notelist";
	}

	/**
	 * Insert/Update Note into database Redirect to notelist.html
	 */
	@RequestMapping(value = "/savenote", method = RequestMethod.POST)
	public String saveNote(Note note) {
		NRepository.save(note);
		return "redirect:/notelist";
	}

	/**
	 * Retrieve data for update Note form Redirect to notelist.html
	 */
	@RequestMapping(value = "/updatenote/{note_id}", method = RequestMethod.GET)
	public String updateNote(@PathVariable("note_id") Long note_id, Model model) {
		Optional<Note> note = NRepository.findById(note_id);
		model.addAttribute("categories", CRepository.findAll());
		model.addAttribute("note", note);
		return "Note/updatenote";
	}

	@RequestMapping(value = "/content/{note_id}", method = RequestMethod.GET)
	public String showNote(@PathVariable("note_id") Long note_id, Model model) {
//    	Optional<Note> note = NRepository.findById(note_id);
//    	model.addAttribute("categories", CRepository.findAll());
		model.addAttribute("shownote", NRepository.findById(note_id));
		return "Note/notecontent";
	}

//---------------------------------------------------------------------------------
	/**
	 * end point Json body allnote(): list all note notebyId(): list note by Id
	 * allNotebyName():list all note by username
	 */

	@RequestMapping(value = "api/noteJson", method = RequestMethod.GET)
	public @ResponseBody List<Note> allNote() {
		return (List<Note>) NRepository.findAll();
	}

	@RequestMapping(value = "api/noteJson/{note_id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Note> notebyId(@PathVariable("note_id") Long note_id) {
		return NRepository.findById(note_id);
	}

	@RequestMapping(value = "api/noteJsonName/{username}", method = RequestMethod.GET)
	public @ResponseBody List<Note> allNotebyName(@PathVariable("username") String username) {
		Long userid = URepository.findByUsername(username).getUserid();
		return NRepository.findByUser_userid(userid);
	}
}
