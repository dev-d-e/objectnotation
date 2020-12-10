package com.github.dev.objectnotation;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

class ClassProperty {

	private final Map<String, Property> map = new HashMap<>();

	private final List<Exception> exceptions = new ArrayList<>();

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
			exceptions.add(e);
		}
	}

	public Optional<Property> getProperty(String name) {
		return Optional.ofNullable(map.get(name));
	}

	public boolean containsException() {
		return exceptions.size() > 0;
	}

	public List<Exception> getExceptions() {
		return exceptions;
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
					exceptions.add(e);
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
					exceptions.add(e);
				}
			}
			return false;
		}

		public boolean invokeWriteMethodByType(Object object, TextTypeAdapter adapter) {
			if (writeMethod != null) {
				try {
					Optional<Object> v = adapter.toAnother(type);
					if (v.isPresent()) {
						writeMethod.invoke(object, v.get());
					}
					return true;
				} catch (Exception e) {
					exceptions.add(e);
				}
			}
			return false;
		}

		public void setForArrayByType(Object object, TextTypeAdapter adapter) {
			adapter.toAnother(type.getComponentType()).ifPresent(v -> setForArray(object, v));
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

		public void setForCollectionByType(Object object, TextTypeAdapter adapter) {
			adapter.toAnother(genericTypes0[0]).ifPresent(v -> setForCollection(object, t -> v));
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
				exceptions.add(e);
			}
			return null;
		}

	}

}
