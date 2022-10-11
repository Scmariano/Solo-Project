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
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	// View all the Event
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
	
	//GetMapping for rendering a page to add an Event
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
	
	// Create an Event
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
	
	// Show an Event with a Set List of Songs and Musicians
	@GetMapping("/events/{id}")
	public String showEvents(@PathVariable("id") Long id,
	        HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/logout";
		} else {
			
			model.addAttribute("event", setServ.findSetId(id));
			model.addAttribute("songs", songServ.allSongs());
			model.addAttribute("user", userServ.findById(userId));
			model.addAttribute("like", setServ.findLikerId(userId));
			return "showEvent.jsp";
		}
	
	}
	
	//GetMapping for rendering a new page for Adding a Song
	@GetMapping("/songs/new/{id}")
	public String newSong( @PathVariable("id") Long id, HttpSession session, Model model,
	        @ModelAttribute("song") Song song) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/logout";
		}else {
			model.addAttribute("set", setServ.findSetId(id));
			model.addAttribute("user", userServ.findById(userId));
			model.addAttribute("songs", songServ.allSongs());
			return "newSong.jsp";
		}
	}
	
	
	//Create a Song!
	@PostMapping("/songs/create")
	public String createSong(@Valid @ModelAttribute("song") Song song, BindingResult result, HttpSession session,
			Model model) {
		Long userId = (Long) session.getAttribute("userId");
		User loggedUser = userServ.findById(userId);
		if(userId == null) {
			return "redirect:/logout";
		}
		if(result.hasErrors()) {
			return "newSong.jsp";
		}
		else {
			song.setCreator(loggedUser);
			songServ.createSong(song);
			loggedUser.getSongs().add(song);
			userServ.updateUser(loggedUser);
			return "redirect:/events/" + song.getSet().getId();
		}
	}

	//GetMapping for rendering a page for creating a Musician
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
	
	//Create a Musician
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
	
	// Show a song
	@GetMapping("/songs/{id}")
	public String showSong(@PathVariable("id") Long id,
			Model model, HttpSession session, @ModelAttribute("user") User user) {
		User loggedUser = userServ.findById((Long) session.getAttribute("userId"));
		if(loggedUser == null) {
			return "redirect:/logout";
		}else {
			model.addAttribute("song", songServ.findSongId(id));
			model.addAttribute("user",loggedUser );
			return "showSong.jsp";
		}
	}
	
	
	// Edit a song
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
	
	//Edit an event
	@GetMapping("events/edit/{id}")
	public String editSet(@PathVariable("id")Long id, Model model, HttpSession session) {
	    Long userId = (Long) session.getAttribute("userId");
        if(userId==null) {
            return "redirect:/logout";
        }else {
            model.addAttribute("set", setServ.findSetId(id));
            model.addAttribute("user", userServ.findById(userId));
            return "editSet.jsp";
        }
	}
	
	// Update a Song
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
	
	//Update an event
	@PutMapping("events/update/{id}")
	public String updateSet(@Valid @ModelAttribute("set")Set set, BindingResult result, HttpSession session) {
	    Long userId = (Long) session.getAttribute("userId");
        
        if(userId == null) {
            return "redirect:/logout";
        }
        
        if (result.hasErrors()) {
            return "editSet.jsp";
        }else {
            setServ.updateSet(set);
            return "redirect:/dashboard";
        }
	}
		
	
	//GetMapping for liking an event
	@GetMapping("/events/{id}/like")
	public String likeSet(@PathVariable("id") Long id, HttpSession session) {
		Set set = setServ.findSetId(id);
		Long userId = (Long) session.getAttribute("userId");
		User loggedUser = userServ.findById(userId);
		setServ.likeSet(set, loggedUser);
		return "redirect:/dashboard";
	}
	
	//GetMapping for unliking an event
	@GetMapping("/events/{id}/unLike")
	public String unLikeSet(@PathVariable("id") Long id, HttpSession session) {
		Set set = setServ.findSetId(id);
		Long userId = (Long) session.getAttribute("userId");
		User loggedUser = userServ.findById(userId);
		setServ.unLikeSet(set, loggedUser);
		return "redirect:/dashboard";
	}
	
	// Delete the entire set
	@RequestMapping("/events/{id}/delete")
	public String destroySet(@PathVariable("id")Long id, HttpSession session) {
	    Long userId = (Long) session.getAttribute("userId");
        if(userId == null) {
            return "redirect:/logout";
        }
        // make sure to delete all the songs
        for(Song song:songServ.setSongs(id)) {
            songServ.deleteSong(song);
        }
        // make sure to delete all the musicians also
        for(Musician musician:musicianServ.findMusiciansId(id)) {
            musicianServ.deleteMusicians(musician);
        }
        //execute 
        setServ.deleteSet(id);
        return "redirect:/dashboard";
	}
	
	// A Delete function on deleting a song
	@RequestMapping("/songs/{id}/{eventId}/delete" )
	public String destroySong( @PathVariable("id")Long id, 
	        @PathVariable("eventId")Long eventId, HttpSession session) {
	    Long userId = (Long) session.getAttribute("userId");

	    if(userId == null) {
            return "redirect:/logout";
        }else {
            songServ.deleteSongId(id);
            return "redirect:/events/" + eventId;
        }
	}
	
	// A Delete function on deleting a Musician
	@RequestMapping("musicians/{id}/{eventId}/delete")
	public String removeMusician(@PathVariable("id") Long id, 
	        @PathVariable("eventId")Long eventId,
	        HttpSession session) {
	    Long userId = (Long) session.getAttribute("userId");

        if(userId == null) {
            return "redirect:/logout";
        }else {
            musicianServ.deleteMusiciansId(id);
            return "redirect:/events/" + eventId;
        }
	}
	
}
