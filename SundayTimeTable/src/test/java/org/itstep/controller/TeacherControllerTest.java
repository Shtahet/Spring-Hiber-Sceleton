package org.itstep.controller;

import static org.junit.Assert.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.itstep.ApplicationRunner;
import org.itstep.dao.TeacherDAO;
import org.itstep.model.Subject;
import org.itstep.model.Teacher;
import org.itstep.service.TeacherService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TeacherControllerTest {

	@Autowired
	TestRestTemplate testRestTemplate;
	
	private Subject subjectInDB;
	
	private List<Teacher> teachers = new ArrayList<Teacher>();
	
	@MockBean
	TeacherService teacherService;
	
	@Before
	public void setUpBefore() {
		Subject subject = new Subject();
		subject.setName("Delphi");
		subjectInDB = subject;
	}
	
	@Test
	public void testFindAllBySubject() throws Exception {
		
		//Arrange
		
		
		//Act
		
		
		//Assert
		
		
		
		RequestEntity request = new RequestEntity<String>("Delphi", HttpMethod.GET, new URI("/teacher/get-by-subject/"));
		
		Mockito.when(teacherService.findAllBySubject(Mockito.anyString())).thenReturn(teachers);
		
		ResponseEntity<List> response = testRestTemplate.exchange(request, List.class);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
		Mockito.verify(teacherService).findAllBySubject(Mockito.anyString());
	}

}
