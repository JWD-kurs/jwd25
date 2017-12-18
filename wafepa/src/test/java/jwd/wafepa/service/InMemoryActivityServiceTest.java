package jwd.wafepa.service;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import jwd.wafepa.model.Activity;
import jwd.wafepa.service.impl.InMemoryActivityService;

public class InMemoryActivityServiceTest {

	private ActivityService activityService;
	
	@Before
	public void setUp() {
		activityService = new InMemoryActivityService();
		
		Activity swimming = new Activity("Swimming");
		Activity running = new Activity("Running");
		
		activityService.save(running);
		activityService.save(swimming);
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void testFindOne() {
		Activity a = activityService.findOne(1L);
		Assert.assertNotNull(a);
		Assert.assertEquals("Running", a.getName());
	}
	
	@Test
	public void testFindAll() {
		List<Activity> activities = activityService.findAll();
		Assert.assertEquals(2, activities.size());
	}
}
