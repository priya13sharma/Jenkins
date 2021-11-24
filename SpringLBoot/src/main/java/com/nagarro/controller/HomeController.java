package com.nagarro.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.lowagie.text.DocumentException;
import com.nagarro.entities.Library;
import com.nagarro.entities.UserPDFExporter;

import com.nagarro.entities.Librarian;

import com.nagarro.repository.UserRepository;
import com.nagarro.service.LibraryService;

@Controller
public class HomeController {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LibraryService libraryService;

	@GetMapping("/")
	public String signUp(Model model) {

		model.addAttribute("title", "SignUp page");
		model.addAttribute("librarian", new Librarian());
		return "signup";
	}

	/**
	 * For registration of new user
	 * 
	 * @param librarian
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/do_register", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("librarian") Librarian librarian, Model model, HttpSession session) {

		try {

			librarian.setPassword(passwordEncoder.encode(librarian.getPassword()));
			librarian.setRole("ROLE_USER");
			this.userRepository.save(librarian);
			model.addAttribute("librarian", new Librarian());
			return "details_page";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("librarian", librarian);
			return "signup";
		}

	}

	/**
	 * Handler for custom login
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/signin")
	private String customLogin(Model model) {
		model.addAttribute("title", "Login Page");
		return "login";
	}

	/**
	 * Handler for adding new librarian
	 * 
	 * @return
	 */
	@GetMapping("/addlib")
	public String addLib() {
		return "add";
	}

	@PostMapping("/register")
	public String empRegister(@ModelAttribute Library e, Model m) {
		libraryService.uploadLibrary(e);
		List<Library> lib = libraryService.getLibraryList();
		m.addAttribute("lib", lib);
		return "details_page";
	}

	/**
	 * This method works when the user clicks the "Edit" Button to edit a particular
	 * librarian details and redirects the user to the page where he can fill new
	 * details of that librarian
	 * 
	 * @param e
	 * @param bookCode
	 * @param m
	 * @return
	 */
	@GetMapping("/edit/{bookCode}")
	public String edit(@ModelAttribute Library e, @PathVariable Long bookCode, Model m) {
		Library e1 = libraryService.getLibraryById(bookCode);
		LibraryService.updateLibrary(e1);
		m.addAttribute("lib", e1);
		return "edit";

	}

	/**
	 * To update a particular librarian data and reload the existing page with
	 * modification(update) as done by the user
	 * 
	 * @param e
	 * @return
	 */
	@PostMapping("/update")
	public String updateLib(@ModelAttribute Library e) {
		libraryService.uploadLibrary(e);
		return "redirect:/user/index";
	}

	/**
	 * To reload the same page(Librarian Details Page) after he clicks "Delete"
	 * Button and deletes a particular librraian data
	 * 
	 * @param bookCode
	 * @return
	 */
	@GetMapping("/delete/{bookCode}")
	public String deleteLib(@PathVariable Long bookCode) {
		boolean deleteLibrary = libraryService.deleteLibrary(bookCode);
		if (deleteLibrary) {
			return "redirect:/user/index";
		}
		return "details_page";

	}
	/**
	 * To create a pdf of the data of all librarian
	 * 
	 * @param response
	 * @throws DocumentException
	 * @throws IOException
	 */
	@GetMapping("/export")
	public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");

		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";

		response.setHeader(headerKey, headerValue);

		List<Library> listUsers = libraryService.getLibraryList();

		UserPDFExporter exporter = new UserPDFExporter(listUsers);
		exporter.export(response);
	}


	
}

