package com.example.demo;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.DAO.NextSequenceService;
import com.example.demo.model.Note;
import com.example.demo.model.User;

@Controller
public class PageController {

	@Autowired
	private UserRepository repository;

	@Autowired
	NextSequenceService sequencer;

	User currentUser;

	@GetMapping("/landing")
	public String getLandingPage(Model model, HttpSession session) {
		session.setAttribute("user", new User());
		model.addAttribute("user", new User());

		return "landing";
	}

	@GetMapping("/home")
	public String getHomePage(Model model) {
		model.addAttribute("user", currentUser);
		model.addAttribute("note", new Note());
		return "home";
	}

	@GetMapping("/loginalt")
	public String getDefaultLoginPage(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("note", new Note());
		return "loginalt";
	}

	@GetMapping("/login")
	public String getLoginPage(Model model) {
		model.addAttribute("user", currentUser);
		model.addAttribute("note", new Note());
		return "login";
	}

	@GetMapping("/getNotes")
	public String getNotes(Model model) {
		model.addAttribute("user", currentUser);
		return "notes";
	}

	@PostMapping("/landing")
	public String register(@ModelAttribute User user, @ModelAttribute Note note, Model model) {
		currentUser = repository.findByEmailId(user.getEmailId());
		model.addAttribute("user", new User());
		if (currentUser == null) {
			// Save the user's details
			user.setId(sequencer.getNextSequence("customSequences"));
			model.addAttribute("user", user);
			repository.save(user);
			return "home";
		} else {
			model.addAttribute("user", currentUser);
			return "login";
		}
	}

	@PostMapping("/login")
	public String login(@ModelAttribute User user, @ModelAttribute Note note, Model model) {
		currentUser = repository.findByUserName(user.getUserName());
		model.addAttribute("user", currentUser);
		if (currentUser.getPassword().equals(user.getPassword())) {
			model.addAttribute("user", currentUser);
			return "home";
		} else {
			return "login";
		}

	}

	@PostMapping("/home")
	public String createPost(@ModelAttribute User user, @ModelAttribute Note note, Model model) {
		int noteId = currentUser.getNotes().size();
		note.setNoteID(noteId);
		//System.out.println(noteId);
		currentUser.getNotes().put(noteId, note);
		repository.save(currentUser);
		return "redirect:/home";
	}

	
	@RequestMapping(value = "/delete_note/{noteID}", method = RequestMethod.GET)
	public String handleDeleteNote(@PathVariable int noteID, @ModelAttribute User user, @ModelAttribute Note note,Model model) {
		model.addAttribute("user", currentUser);
		currentUser.getNotes().remove(noteID);
		repository.save(currentUser);
	    return "home";
	}

	@RequestMapping(value = "/update_note/{noteID}", method = RequestMethod.GET)
	public String handleUpdateNote(@PathVariable int noteID, @ModelAttribute User user, @ModelAttribute Note note,Model model) {
		model.addAttribute("user", currentUser);
		model.addAttribute("note", note);
		//System.out.println("note : "+note.getNoteText()+" "+note.getNoteID());
		//currentUser.getNotes().replace(noteID, note);
	    return "updatenote";
	}
	
	
	  @RequestMapping(value="/setNote/{noteID}", method=RequestMethod.GET) 
	  public String setNote(@ModelAttribute User user, @ModelAttribute Note note, Model model, @PathVariable int noteID) 
	  { 
		  model.addAttribute("user", currentUser);
		  model.addAttribute("note", note);
		  currentUser.getNotes().replace(noteID, note);
		  return "home"; 
	  }
	 
}
