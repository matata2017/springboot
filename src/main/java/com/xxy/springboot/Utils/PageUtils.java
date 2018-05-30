package com.xxy.springboot.Utils;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.xxy.springboot.entity.BaseEntity;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.Transient;
import javax.persistence.criteria.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class PageUtils {
	public enum Operate{
		eq,gt,gte,lt,lte,like,startsWith,endsWith,between,in, isNull,noteq
	}
	static public <T extends BaseEntity> Specification<T> getSpecification(final T entity, final Map<String,Operate> operate){
		return getSpecification(entity, operate, new HashMap());
	}
	static public  Predicate toPredicate(Root root, CriteriaQuery<?> query,
										 CriteriaBuilder cb, final BaseEntity entity, final Map<String,Operate> operate, final Map<String,Object> customParams,
										 Map<String,From> map) {
		Method[] methods = entity.getClass().getMethods();
		Map<String,From> fromMap = new HashMap();
		fromMap.put("", root);
		Predicate p = getPredicate(entity, operate, methods, cb, null, fromMap,"",customParams);
		for (Entry<String, Object> entry : customParams.entrySet()){
			Predicate cur = getPredicate(root,entity.getClass(),entry,query,cb,map, operate, customParams);
			if(p == null) p = cur;
			else if(cur != null) p = cb.and(p,cur);
		}
		return p;
	}
	static private Predicate getPredicate(From root,Class<? extends BaseEntity> classes,
			Entry<String, Object> entry, CriteriaQuery<?> query,
			CriteriaBuilder cb,Map<String,From> fromMap,
			Map<String,Operate> operate,
			Map<String,Object> customParams) {
		From from = getFrom(root,fromMap,classes,entry.getKey());
		int index = entry.getKey().lastIndexOf('.');
		String name = entry.getKey().substring(index+1);
		return getPredicate(entry.getValue(), operate, cb, entry.getKey(), name , from);
	}
	static private From getFrom(From root,Map<String,From> fromMap,Class<? extends BaseEntity> classes, String key){
		String[] paths = key.split("\\.");
		String path = "";
		From cur = root;
		for (int i = 0; i < paths.length - 1; i++){
			String p = paths[i];
			path += (path.length() > 0?".":"") + p;
			if(fromMap.containsKey(path)){
				cur = fromMap.get(path);
				continue;
			}
			else{
				cur = cur.join(p);
				fromMap.put(path, cur);
			}
		}
		return cur;
	}
	@SuppressWarnings("rawtypes")
	static private Predicate getPredicate(BaseEntity entity,Map<String,Operate> operate, Method[] methods,CriteriaBuilder cb,Predicate p, Map<String, From> fromMap,String path, Map<String, Object> customParams){
		From from = fromMap.get(path);
		for (Method method : methods) {
			if(method.getName().startsWith("get")){
				String curPath = getPath(path,method);
				String name = getName(method);
				if("class".equals(name)) continue;
				try {
					if(method.getAnnotation(Transient.class) != null) continue;
					if(method.getParameterTypes().length > 0) continue;
					Object value = method.invoke(entity);

					if(value == null) continue;
					if(value instanceof Collection) continue;
					if(value instanceof BaseEntity){
						Method[] subMethods = value.getClass().getMethods();
						if(!fromMap.containsKey(curPath)){
							Join join = from.join(name,JoinType.LEFT);
							fromMap.put(curPath, join);
						}
						p = getPredicate((BaseEntity)value, operate, subMethods, cb, p, fromMap, curPath,customParams);
					}
					else{
						if(value instanceof String && Strings.isNullOrEmpty((String)value)) continue;
						Predicate tp = getPredicate(value,operate,cb,getPath(path,method),getName(method),from);
						if(tp == null) continue;
						if(p == null) p = tp;
						else p = cb.and(p,tp);
					}
				} catch (IllegalAccessException
						| IllegalArgumentException
						| InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		return p;
	}
	static private String getPath(String path, Method method) {
		String name = getName(method);
		return path + (path.length() > 0?".":"") + name;
	}
	static private String getName(Method method) {
		String name = method.getName().substring(3).substring(0,1).toLowerCase();
		if(method.getName().length() > 4) name += method.getName().substring(4);
		return name;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	static private Predicate getPredicate(Object value,
			Map<String, Operate> operate, CriteriaBuilder cb, String path,String name,From from) {
		Operate o = Operate.eq;
		if(operate.containsKey(path)){
			o = operate.get(path);
		}
		if(o == Operate.startsWith){
			return cb.like(from.get(name).as(value.getClass()), value + "%");
		}
		else if(o == Operate.endsWith){
			return cb.like(from.get(name).as(value.getClass()), "%" + value);
		}
		else if(o == Operate.like){
			return cb.like(from.get(name).as(value.getClass()), "%" + value + "%");
		}
		else if(o == Operate.gt){
			return cb.greaterThan(from.get(name).as(value.getClass()), (Comparable)value);
		}
		else if(o == Operate.gte){
			return cb.greaterThanOrEqualTo(from.get(name).as(value.getClass()), (Comparable)value);
		}
		else if(o == Operate.lt){
			return cb.lessThan(from.get(name).as(value.getClass()), (Comparable)value);
		}
		else if(o == Operate.lte){
			return cb.lessThanOrEqualTo(from.get(name).as(value.getClass()), (Comparable)value);
		}
		else if(o == Operate.isNull){
			return cb.isNull(from.get(name).as(value.getClass()));
		}else if(o == PageUtils.Operate.noteq) {
			return cb.not(cb.equal(from.get(name), value));
		}
		else if(o == Operate.in){
			Object[] values = (Object[]) value;
			if(values != null && values.length > 0){
				return from.get(name).in(values);
			}
			return null;
		}
		else if(o == Operate.between){
			Object[] values = (Object[]) value;
			if((values[0] == null || (values[0] instanceof String && Strings.isNullOrEmpty((String) values[0])))
				&& (values[1] == null || (values[1] instanceof String && Strings.isNullOrEmpty((String) values[1])))){
				return null;
			}
			else if((values[1] == null || (values[1] instanceof String && Strings.isNullOrEmpty((String) values[1])))){
				return cb.greaterThanOrEqualTo(from.get(name).as(values[0].getClass()), (Comparable)values[0]);
			}
			else if((values[1] == null || (values[1] instanceof String && Strings.isNullOrEmpty((String) values[1])))){
				return cb.lessThanOrEqualTo(from.get(name).as(values[1].getClass()), (Comparable)values[1]);
			}
			return cb.between(from.get(name).as(values[0].getClass()), (Comparable)values[0], (Comparable)values[1]);
		}
		else{
			return cb.equal(from.get(name).as(value.getClass()), value);
		}
	}
	static public <T extends BaseEntity> Specification<T> getSpecification(final T entity, final Map<String,Operate> operate, final Map<String,Object> customParams){
		Specification<T> ret = new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Map<String,From> map = Maps.newHashMap();
				return PageUtils.toPredicate(root,query,cb,entity,operate,customParams,map);
			}
		};
		return ret;
	}
}
