package com.github.dev.objectnotation.value;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Array entity.
 */
class ArrayEntityImpl implements ArrayEntity {

	private List<Entity> values = new ArrayList<>();

	@Override
	public Iterator<Entity> iterator() {
		return values.iterator();
	}

	@Override
	public Entity[] toArray() {
		return values.toArray(new Entity[values.size()]);
	}

	@Override
	public ArrayEntity add(Entity entity) {
		values.add(entity);
		return this;
	}

}
