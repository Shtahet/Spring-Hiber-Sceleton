package org.itstep.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.itstep.ApplicationRunner;
import org.itstep.model.Subject;
import org.itstep.model.Teacher;
import org.junit.After;

import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationRunner.class)
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TeacherDAOTest {

	private Subject subjectInDB;
	private Teacher teacherInDB;
	private Teacher teacherInDB2;
	
	@Autowired
	SubjectDAO subjectDAO;
	
	@Autowired
	TeacherDAO teacherDAO;
	
	@Test
	public void testFindAllBySubject() {
		List<Teacher> teachers = teacherDAO.findAllBySubject(subjectInDB.getName());
		
		assertTrue(teachers != null);
		assertEquals(teachers.size(), 1);
		assertEquals(teachers.get(0).getSubject().getName(), "Delphi");
	}
	
	/*
	 * @BeforeClass
	 * @AfterClass
	 * Нотация которая позволяет создавать методы которые будут отработанны перед созданием и после удаления класса
	*/
	
	/*Код который отрабатывается до создания объекта для тестов*/
	@Before
	public void setUpBefore() throws Exception {
		Subject subject = new Subject();
		subject.setName("Delphi");
		subjectInDB = subjectDAO.save(subject);
		
		Teacher teacher = new Teacher();
		teacher.setLogin("Ignatenko2207");
		teacher.setPassword("123456");
		teacher.setFirstName("Alex");
		teacher.setSecondName("Ignatenko");
		teacher.setSubject(subjectInDB);
		
		teacherInDB = teacherDAO.save(teacher);
		
		Teacher teacher2 = new Teacher();
		teacher2.setLogin("Chumak");
		teacher2.setPassword("123456");
		teacher2.setFirstName("Dmitriy");
		teacher2.setSecondName("Chumak");
		teacher2.setSubject(subjectInDB);
		
		teacherInDB2 = teacherDAO.save(teacher2);
	}

	/*Код который отрабатывается после удаления объекта для тестов*/
	@After
	public void tearDownAfter() throws Exception {
		teacherDAO.delete(teacherInDB);
		teacherDAO.delete(teacherInDB2);
		subjectDAO.delete(subjectInDB);
	}

}
