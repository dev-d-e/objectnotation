package com.github.dev.objectnotation;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

import com.github.dev.objectnotation.value.ArrayEntity;
import com.github.dev.objectnotation.value.Entity;
import com.github.dev.objectnotation.value.PrimitiveTypeEntity;

class ClassProperty {

	private final Map<String, Property> map = new HashMap<>();

	ClassProperty(Class<?> c) {
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(c);
			PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor descriptor : descriptors) {
				if (!"class".equals(descriptor.getName())) {
					Property beanProperty = new Property(descriptor);
					map.put(beanProperty.getName(), beanProperty);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Property getProperty(String name) {
		return map.get(name);
	}

	class Property {

		private final String name;
		private Class<?> type;
		private Method readMethod;
		private Method writeMethod;
		private Type[] genericTypes;
		private Class<?>[] genericTypes0;

		Property(PropertyDescriptor propertyDescriptor) {
			this.name = propertyDescriptor.getName();
			this.type = propertyDescriptor.getPropertyType();
			this.readMethod = propertyDescriptor.getReadMethod();
			this.writeMethod = propertyDescriptor.getWriteMethod();
			if (writeMethod != null) {
				Type t = writeMethod.getGenericParameterTypes()[0];
				if (t instanceof ParameterizedType) {
					ParameterizedType pt = (ParameterizedType) t;
					genericTypes = pt.getActualTypeArguments();
					genericTypes0 = new Class<?>[genericTypes.length];
					for (int i = 0; i < genericTypes.length; i++) {
						if (genericTypes[i] instanceof Class) {
							genericTypes0[i] = (Class<?>) genericTypes[i];
						} else {
							genericTypes0[i] = Object.class;
						}
					}
				}
			}
		}

		public String getName() {
			return name;
		}

		public Class<?> getType() {
			return type;
		}

		public Object invokeReadMethod(Object obj) {
			if (readMethod != null) {
				try {
					return readMethod.invoke(obj);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return null;
		}

		public boolean invokeWriteMethod(Object obj, Object arg) {
			if (writeMethod != null) {
				try {
					writeMethod.invoke(obj, arg);
					return true;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return false;
		}

		public boolean invokeWriteMethodByEntity(Object object, PrimitiveTypeEntity entity) {
			if (writeMethod != null) {
				try {
					invokeWriteMethodByEntity0(object, entity);
					return true;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return false;
		}

		private void invokeWriteMethodByEntity0(Object object, PrimitiveTypeEntity entity) throws Exception {
			if (boolean.class == type || Boolean.TYPE == type) {
				writeMethod.invoke(object, entity.booleanValue());
			} else if (byte.class == type || Byte.TYPE == type) {
				writeMethod.invoke(object, entity.byteValue());
			} else if (char.class == type || Character.TYPE == type) {
				writeMethod.invoke(object, entity.getValue().charAt(0));
			} else if (int.class == type || Integer.TYPE == type) {
				writeMethod.invoke(object, entity.intValue());
			} else if (long.class == type || Long.TYPE == type) {
				writeMethod.invoke(object, entity.longValue());
			} else if (short.class == type || Short.TYPE == type) {
				writeMethod.invoke(object, entity.shortValue());
			} else if (float.class == type || Float.TYPE == type) {
				writeMethod.invoke(object, entity.floatValue());
			} else if (double.class == type || Double.TYPE == type) {
				writeMethod.invoke(object, entity.doubleValue());
			} else if (BigInteger.class == type) {
				writeMethod.invoke(object, entity.bigIntegerValue());
			} else if (BigDecimal.class == type) {
				writeMethod.invoke(object, entity.bigDecimalValue());
			} else if (String.class == type) {
				writeMethod.invoke(object, entity.getValue());
			}
		}

		public void setForArrayByEntity(Object object, PrimitiveTypeEntity entity) {
			Class<?> componentType = type.getComponentType();
			if (boolean.class == componentType || Boolean.TYPE == componentType) {
				setForArray(object, entity.booleanValue());
			} else if (byte.class == componentType || Byte.TYPE == componentType) {
				setForArray(object, entity.byteValue());
			} else if (char.class == componentType || Character.TYPE == componentType) {
				setForArray(object, entity.getValue().charAt(0));
			} else if (int.class == componentType || Integer.TYPE == componentType) {
				setForArray(object, entity.intValue());
			} else if (long.class == componentType || Long.TYPE == componentType) {
				setForArray(object, entity.longValue());
			} else if (short.class == componentType || Short.TYPE == componentType) {
				setForArray(object, entity.shortValue());
			} else if (float.class == componentType || Float.TYPE == componentType) {
				setForArray(object, entity.floatValue());
			} else if (double.class == componentType || Double.TYPE == componentType) {
				setForArray(object, entity.doubleValue());
			} else if (BigInteger.class == componentType) {
				setForArray(object, entity.bigIntegerValue());
			} else if (BigDecimal.class == componentType) {
				setForArray(object, entity.bigDecimalValue());
			} else if (String.class == componentType) {
				setForArray(object, entity.getValue());
			}
		}

		public void setForArrayByEntity(Object object, ArrayEntity entity) {
			for (Entity e : entity.toArray()) {
				if (e instanceof PrimitiveTypeEntity) {
					setForArrayByEntity(object, (PrimitiveTypeEntity) e);
				}
			}
		}

		public void setForArray(Object object, Object v) {
			Object newArray = null;
			Object srcArray = invokeReadMethod(object);
			Class<?> componentType = type.getComponentType();
			if (srcArray == null) {
				newArray = Array.newInstance(componentType, 1);
				Array.set(newArray, 0, v);
			} else {
				int i = Array.getLength(srcArray);
				newArray = Array.newInstance(componentType, i + 1);
				System.arraycopy(srcArray, 0, newArray, 0, i);
				Array.set(newArray, i, v);
			}
			invokeWriteMethod(object, newArray);
		}

		public void setForCollectionByEntity(Object object, PrimitiveTypeEntity entity) {
			Class<?> type = genericTypes0[0];
			if (boolean.class == type || Boolean.TYPE == type) {
				setForCollection(object, t -> entity.booleanValue());
			} else if (byte.class == type || Byte.TYPE == type) {
				setForCollection(object, t -> entity.byteValue());
			} else if (char.class == type || Character.TYPE == type) {
				setForCollection(object, t -> entity.getValue().charAt(0));
			} else if (int.class == type || Integer.TYPE == type) {
				setForCollection(object, t -> entity.intValue());
			} else if (long.class == type || Long.TYPE == type) {
				setForCollection(object, t -> entity.longValue());
			} else if (short.class == type || Short.TYPE == type) {
				setForCollection(object, t -> entity.shortValue());
			} else if (float.class == type || Float.TYPE == type) {
				setForCollection(object, t -> entity.floatValue());
			} else if (double.class == type || Double.TYPE == type) {
				setForCollection(object, t -> entity.doubleValue());
			} else if (BigInteger.class == type) {
				setForCollection(object, t -> entity.bigIntegerValue());
			} else if (BigDecimal.class == type) {
				setForCollection(object, t -> entity.bigDecimalValue());
			} else if (String.class == type) {
				setForCollection(object, t -> entity.getValue());
			}
		}

		public void setForCollectionByEntity(Object object, ArrayEntity entity) {
			for (Entity e : entity.toArray()) {
				if (e instanceof PrimitiveTypeEntity) {
					setForCollectionByEntity(object, (PrimitiveTypeEntity) e);
				}
			}
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		public void setForCollection(Object object, Function<Class<Object>, Object> f) {
			Object o = invokeReadMethod(object);
			Collection collection = (Collection) o;
			if (o == null) {
				if (List.class == type) {
					collection = new ArrayList<>();
				} else if (Set.class == type) {
					collection = new HashSet<>();
				} else if (!type.isInterface()) {
					o = newInstance(type);
					collection = (Collection) o;
				}
				invokeWriteMethod(object, collection);
			}
			if (collection != null) {
				Class t = genericTypes0[0];
				collection.add(f.apply(t));
			}
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		public void setForMap(Object object, Function<Class<Object>, Object> f) {
			Object o = invokeReadMethod(object);
			Map map = (Map) o;
			if (o == null) {
				if (Map.class == type) {
					map = new HashMap<>();
				} else if (!type.isInterface()) {
					o = newInstance(type);
					map = (Map) o;
				}
				invokeWriteMethod(object, map);
			}
			if (map != null) {
				Class t = genericTypes0[1];
				map.put(name, f.apply(t));
			}
		}

		@SuppressWarnings("unchecked")
		public void setForBean(Object object, Function<Class<Object>, Object> f, Consumer<Object> c) {
			Object o = invokeReadMethod(object);
			if (o == null) {
				o = f.apply((Class<Object>) type);
				invokeWriteMethod(object, o);
			} else {
				c.accept(o);
			}
		}

		private <T> T newInstance(Class<T> c) {
			try {
				return c.getDeclaredConstructor().newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

	}

}
