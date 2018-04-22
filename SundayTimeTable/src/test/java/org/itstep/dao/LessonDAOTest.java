package org.itstep.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.itstep.ApplicationRunner;
import org.itstep.model.Group;
import org.itstep.model.Lesson;
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
public class LessonDAOTest {

	@Before
	public void setUp() throws Exception {

		// Create object for subject
		Subject sub = new Subject();
		sub.setName("Python");
		
		subject = subjectDAO.save(sub);

		// Create teacher object
		Teacher teach = new Teacher();
		teach.setLogin("Ignatenko2207");
		teach.setPassword("123456");
		teach.setFirstName("Alex");
		teach.setSecondName("Ignatenko");
		teach.setSubject(subject);
		
		teacher = teacherDAO.save(teach);
		
		//Create object for group
		Group gr = new Group();
		gr.setName("B9P1_26");
		gr.setSpecialization("Software development");
		gr.setCourse("2");
		
		group = groupDAO.save(gr);
		
		for(int i = 1; i<=3; ++i) {
			//Create object for Lesson
			Lesson less = new Lesson();
			less.setCabinet("16");
			less.setStartTime((long)45*i);
			less.setGroup(group);
			less.setTeacher(teacher);
			less.setSubject(subject);
			
			savedLessons.add(lessonDAO.save(less));
		}
		
		
	}

	@After
	public void tearDown() throws Exception {
		for(Lesson lesson:savedLessons) {
			lessonDAO.delete(lesson);
		}
		
		groupDAO.delete(group);
		teacherDAO.delete(teacher);
		subjectDAO.delete(subject);
	}

	private Subject subject;
	private Teacher teacher;
	private Group group;
	private List<Lesson> savedLessons = new ArrayList<Lesson>();

	@Autowired
	LessonDAO lessonDAO;
	@Autowired
	GroupDAO groupDAO;
	@Autowired
	TeacherDAO teacherDAO;
	@Autowired
	SubjectDAO subjectDAO;

	@Test
	public void testFindAllByStartTime() {
		List<Lesson> testLessons = lessonDAO.findAllByStartTime(0L, 100L);
		
		assertNotNull(testLessons);
		assertEquals(2,testLessons.size());
		assertEquals("Phyton", testLessons.get(0).getTeacher().getSubject().getName());
		
		
		List<Lesson> testLessonsAll = lessonDAO.findAllByStartTime(0L, 150L);
		
		assertNotNull(testLessons);
		assertEquals(3,testLessons.size());
	}

}
