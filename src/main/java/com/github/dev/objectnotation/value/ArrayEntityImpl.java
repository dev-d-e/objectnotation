package com.github.dev.objectnotation.value;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Array entity.
 */
class ArrayEntityImpl implements ArrayEntity {

	private List<Entity> values = new ArrayList<>();

	private PrimitiveTypeEntity primitiveTypeEntity;

	@Override
	public ArrayEntity accept(char c) {
		if (c == ',') {
			if (primitiveTypeEntity != null) {
				primitiveTypeEntity.finish();
				add(primitiveTypeEntity);
				primitiveTypeEntity = null;
			}
		} else {
			if (primitiveTypeEntity == null) {
				primitiveTypeEntity = EntityFactory.createPrimitiveTypeEntity();
			}
			primitiveTypeEntity.accept(c);
		}
		return this;
	}

	@Override
	public void finish() {
		if (primitiveTypeEntity != null) {
			primitiveTypeEntity.finish();
			add(primitiveTypeEntity);
			primitiveTypeEntity = null;
		}
	}

	@Override
	public boolean isEmpty() {
		return values.isEmpty();
	}

	@Override
	public Iterator<Entity> iterator() {
		return values.iterator();
	}

	@Override
	public int size() {
		return values.size();
	}

	@Override
	public Entity[] toArray() {
		return values.toArray(new Entity[values.size()]);
	}

	@Override
	public ArrayEntity add(Entity entity) {
		if (entity instanceof ArrayEntity) {
			((ArrayEntity) entity).forEach(e -> values.add(e));
		} else {
			values.add(entity);
		}
		return this;
	}

}
