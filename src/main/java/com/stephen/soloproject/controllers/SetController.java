package com.stephen.soloproject.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.stephen.soloproject.models.Musician;
import com.stephen.soloproject.models.Set;
import com.stephen.soloproject.models.Song;
import com.stephen.soloproject.models.User;
import com.stephen.soloproject.services.MusicianServ;
import com.stephen.soloproject.services.SetServ;
import com.stephen.soloproject.services.SongServ;
import com.stephen.soloproject.services.UserServ;

@Controller
public class SetController {
	@Autowired UserServ userServ;
	@Autowired SetServ setServ;
	@Autowired SongServ songServ;
	@Autowired MusicianServ musicianServ;
	
	
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/logout";
		}else {
			model.addAttribute("user", userServ.findById(userId));
			model.addAttribute("events", setServ.allEvents());
			return "dashboard.jsp";
		}
	}
	
	@GetMapping("/events/new")
	public String newEvent(@ModelAttribute("sets") Set set, HttpSession session, Model model) {
		User loggedUser = userServ.findById((Long) session.getAttribute("userId"));
		if(loggedUser == null) {
			return "redirect:/logout";
		}else {
			model.addAttribute("user", loggedUser);
			return "newEvent.jsp";
		}
	}
	
	@PostMapping("/events/create")
	public String createEvent(@Valid @ModelAttribute("sets")Set set, BindingResult result,
			HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/logout";
		}else {
			if(result.hasErrors()) {
				return "newEvent.jsp";
			}else {
				setServ.createSet(set);
				return "redirect:/dashboard";
			}
		}
	}
	
	@GetMapping("/events/{id}")
	public String showEvents(@PathVariable("id") Long id, HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/logout";
		} else {
			model.addAttribute("event", setServ.findSetId(id));
			model.addAttribute("songs", songServ.allSongs());
			model.addAttribute("user", userServ.findById(userId));
			return "showEvent.jsp";
		}
	
	}
	
	@GetMapping("/songs/new/{id}")
	public String newSong(@ModelAttribute("song") Song song, @PathVariable("id") Long id, HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/logout";
		}else {
			model.addAttribute("set", setServ.findSetId(id));
			return "newSong.jsp";
		}
	}
	
	@PostMapping("/songs/create")
	public String createSong(@Valid @ModelAttribute("song") Song song, BindingResult result, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/logout";
		}
		if(result.hasErrors()) {
			return "newSong.jsp";
		}
		else {
			songServ.createSong(song);
			return "redirect:/events/" + song.getSet().getId();
		}
	}
	
	@GetMapping("/musicians/new/{id}")
	public String newMusician(@ModelAttribute("musician") Musician musician, @PathVariable("id")Long id, HttpSession session,
			Model model) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/logout";
		}else {
			model.addAttribute("set", setServ.findSetId(id));
			return "newMusician.jsp";
		}
	}
	
	@PostMapping("/musicians/create")
	public String createMusician(@Valid @ModelAttribute("musician")Musician musician, BindingResult result, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/logout";
		}
		if(result.hasErrors()) {
			return "newMusician.jsp";
		}else {
			musicianServ.createMusician(musician);
			return "redirect:/events/" + musician.getSet().getId();
		}
		
	}
	
	
	@GetMapping("/songs/{id}")
	public String showSong(@PathVariable("id") Long id, Model model, HttpSession session) {
		User loggedUser = userServ.findById((Long) session.getAttribute("userId"));
		if(loggedUser == null) {
			return "redirect:/logout";
		}else {
			model.addAttribute("song", songServ.findSongId(id));
			model.addAttribute("user", loggedUser);
			return "showSong.jsp";
		}
	}
	
	
	
	@GetMapping("/songs/edit/{id}")
	public String editSong(@PathVariable("id")Long id, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId==null) {
			return "redirect:/logout";
		}else {
			model.addAttribute("song", songServ.findSongId(id));
			model.addAttribute("user", userServ.findById(userId));
			return "editSong.jsp";
		}
	}
	
		
	@PutMapping("/songs/update/{id}")
	public String updateSong(@Valid @ModelAttribute("song") Song song, BindingResult result, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		
		if(userId == null) {
			return "redirect:/logout";
		}
		
		if (result.hasErrors()) {
    		return "editSong.jsp";
    	}else {
    		songServ.updateSong(song);
    		return "redirect:/events/" + song.getSet().getId();
    	}
	}
		

	
	

	
	
	
	
}
