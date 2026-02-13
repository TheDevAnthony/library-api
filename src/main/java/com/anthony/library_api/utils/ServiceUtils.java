package com.anthony.library_api.utils;

import java.lang.reflect.Field;

public class ServiceUtils {

	public static void entityUpdate(Object entity, Object dto) {
		if (entity == null || dto == null)
            throw new IllegalArgumentException("Entity and DTO must not be null");
		
		Class<?> dtoClass = dto.getClass();
        Class<?> entityClass = entity.getClass();
        
        for (Field dtoField : dtoClass.getDeclaredFields()) {
            try {
                dtoField.setAccessible(true);
                Object value = dtoField.get(dto);
                
                if ((value != null && !(value instanceof String)) ||
                	(StringUtils.isValidString((String)value)))
                {
                    Field entityField = entityClass.getDeclaredField(dtoField.getName());
                    entityField.setAccessible(true);
                    entityField.set(entity, value);
                }

            } catch (NoSuchFieldException ignored) {} 
              catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
	}

}
