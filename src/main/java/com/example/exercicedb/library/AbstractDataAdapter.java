package com.example.exercicedb.library;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import java.util.*;

/**
 * Abstract data adapter between source object and target object
 * 
 * @param <SourceObject> Source object to be obtained
 * @param <TargetObject> Target object to transform
 */
public abstract class AbstractDataAdapter<SourceObject, TargetObject> {

	private ObjectMapper objectMapper;

	@Autowired
	public final void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	public TargetObject fromSourceToTarget(SourceObject sourceObject) {
		return null;
	}

	public TargetObject fromSourceToTarget(SourceObject sourceObject, int index) {
		return null;
	}

	public SourceObject fromTargetToSource(TargetObject targetObject) {
		return null;
	}

	public SourceObject fromTargetToSource(TargetObject targetObject, int index) {
		return null;
	}

	public List<TargetObject> fromSourceToTargetList(Collection<SourceObject> lstSourceObject) {
		List<TargetObject> lstTargetObject = new ArrayList<>();
		TargetObject targetObject;
		if (lstSourceObject != null) {
			for (SourceObject sourceObject : lstSourceObject) {
				targetObject = fromSourceToTarget(sourceObject);
				Optional.ofNullable(targetObject).ifPresent(lstTargetObject::add);
			}
		}
		return lstTargetObject;
	}

	public Set<TargetObject> fromSourceToTargetSet(Collection<SourceObject> lstSourceObject) {
		Set<TargetObject> lstTargetObject = new HashSet<>();
		TargetObject targetObject;
		if (lstSourceObject != null) {
			for (SourceObject sourceObject : lstSourceObject) {
				targetObject = fromSourceToTarget(sourceObject);
				lstTargetObject.add(targetObject);
			}
		}
		return lstTargetObject;
	}

	public SortedSet<TargetObject> fromSourceToTargetSortedSet(Collection<SourceObject> lstSourceObject) {
		SortedSet<TargetObject> lstTargetObject = new TreeSet<>();
		TargetObject targetObject;
		if (lstSourceObject != null) {
			for (SourceObject sourceObject : lstSourceObject) {
				targetObject = fromSourceToTarget(sourceObject);
				lstTargetObject.add(targetObject);
			}
		}
		return lstTargetObject;
	}

	public List<SourceObject> fromTargetToSourceList(Collection<TargetObject> lstTargetObject) {
		List<SourceObject> lstSourceObject = new ArrayList<>();
		SourceObject sourceObject;
		if (lstTargetObject != null) {
			for (TargetObject targetObject : lstTargetObject) {
				sourceObject = fromTargetToSource(targetObject);
				Optional.ofNullable(sourceObject).ifPresent(lstSourceObject::add);
			}
		}
		return lstSourceObject;
	}

	public SortedSet<SourceObject> fromTargetToSourceSortedSet(Collection<TargetObject> lstTargetObject) {
		SortedSet<SourceObject> lstSourceObject = new TreeSet<>();
		SourceObject sourceObject;
		for (TargetObject targetObject : lstTargetObject) {
			sourceObject = fromTargetToSource(targetObject);
			lstSourceObject.add(sourceObject);
		}
		return lstSourceObject;
	}

	public Set<SourceObject> fromTargetToSourceSet(Collection<TargetObject> lstTargetObject) {
		Set<SourceObject> lstSourceObject = new HashSet<>();
		SourceObject sourceObject;
		if (lstTargetObject != null) {
			for (TargetObject targetObject : lstTargetObject) {
				sourceObject = fromTargetToSource(targetObject);
				lstSourceObject.add(sourceObject);
			}
		}
		return lstSourceObject;
	}

	public List<TargetObject> fromSourceToTargetListWithCacheIndex(Collection<SourceObject> lstSourceObject) {
		if (lstSourceObject == null)
			return null;

		List<TargetObject> lstTargetObject = new ArrayList<>();
		TargetObject targetObject;
		int index = 0;
		for (SourceObject sourceObject : lstSourceObject) {
			targetObject = fromSourceToTarget(sourceObject, index++);
			lstTargetObject.add(targetObject);
		}
		return lstTargetObject;
	}

	public List<TargetObject> fromSourceToTargetListWithCacheIndex(Collection<SourceObject> lstSourceObject,
			Pageable page) {
		if (lstSourceObject == null)
			return null;

		List<TargetObject> lstTargetObject = new ArrayList<>();

		if (page == null) {
			lstTargetObject.addAll(this.fromSourceToTargetListWithCacheIndex(lstSourceObject));
		} else {
			TargetObject targetObject;
			int index = page.getPageNumber() == 0 ? 0 : Long.valueOf(page.getOffset()).intValue();
			for (SourceObject sourceObject : lstSourceObject) {
				targetObject = fromSourceToTarget(sourceObject, index++);
				lstTargetObject.add(targetObject);
			}
		}
		return lstTargetObject;
	}

}
