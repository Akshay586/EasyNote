package com.example.demo;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class PageControllerTest {
	private PageController pageController;
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		pageController=new PageController();
		mockMvc=MockMvcBuilders.standaloneSetup(pageController).build();
	}

	@Test
	public void testGetLandingPage() throws Exception {
		mockMvc.perform(get("/landing"))
		.andExpect(status().isOk())
		.andExpect(view().name("landing"));
	}

	@Test
	public void testGetHomePage() throws Exception {
		mockMvc.perform(get("/home"))
		.andExpect(status().isOk())
		.andExpect(view().name("home"));
	}

	@Test
	public void testGetLoginPage() throws Exception {
		mockMvc.perform(get("/login"))
		.andExpect(status().isOk())
		.andExpect(view().name("login"));
	}

	@Test
	public void testGetNotes() throws Exception {
		mockMvc.perform(get("/getNotes"))
		.andExpect(status().isOk())
		.andExpect(view().name("notes"));
	}

	@Test
	public void testRegister() throws Exception {
		mockMvc.perform(post("/landing"))
		.andExpect(status().isOk())
		.andExpect(view().name("home"));
	}

	@Test
	public void testLogin() throws Exception {
		mockMvc.perform(post("/login"))
		.andExpect(status().isOk())
		.andExpect(view().name("home"));
	}

	@Test
	public void testCreatePost() throws Exception {
		mockMvc.perform(post("/home"))
		.andExpect(status().isOk())
		.andExpect(view().name("home"));
	}

	@Test
	public void testHandleDeleteNote() throws Exception {
		mockMvc.perform(get("/delete_note/1"))
		.andExpect(status().isOk())
		.andExpect(view().name("home"));
	}

	@Test
	public void testHandleUpdateNote() throws Exception {
		mockMvc.perform(get("/update_note/1"))
		.andExpect(status().isOk())
		.andExpect(view().name("updatenote"));
	}

	@Test
	public void testSetNote() {
		fail("Not yet implemented");
	}

}
