package jwd.wafepa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jwd.wafepa.model.Activity;
import jwd.wafepa.service.ActivityService;

public class InMemoryActivityService 
	implements ActivityService {
	
	private Map<Long, Activity> activities = new HashMap<>();
	private long nextId = 1L;
	
	@Override
	public List<Activity> findAll() {
//		List<Activity> retVal = new ArrayList<>();
//		
//		for(Activity a : activities.values()) {
//			retVal.add(a);
//		}
//		
//		return retVal;
		
		return new ArrayList<>(activities.values());
		
	}

	@Override
	public Activity findOne(Long id) {
		
		return activities.get(id);
	}

	@Override
	public Activity save(Activity activty) {
		if(activty.getId() == null) {
			activty.setId(nextId);
			nextId += 1;
		}
		
		activities.put(activty.getId(), activty);
		
		return activty;
	}

	@Override
	public Activity remove(Long id) {
		Activity toRemove = activities.get(id);
		if(toRemove == null) {
			throw new IllegalArgumentException("Tried to remove"
					+ " non-existant activity.");
		}
		activities.remove(id);
		
		return toRemove;
	}

}
