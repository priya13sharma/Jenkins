package com.nagarro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nagarro.entities.Library;
import com.nagarro.service.LibraryService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private LibraryService libraryService;
	
	@RequestMapping("/index")
	public String dashboard(@ModelAttribute Library e, Model m)
	{
		List<Library> lib = libraryService.getLibraryList();
		m.addAttribute("lib", lib);
		return "details_page";
	}

}

