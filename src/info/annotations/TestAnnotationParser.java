package info.annotations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestAnnotationParser {
	
	public void parse(Class<?> clazz){
		Method[] methods = clazz.getMethods();
		int pass = 0,fail = 0;
		for(Method method : methods) {
			if(method.isAnnotationPresent(TestDemo.class)) {
				
				TestDemo td = method.getAnnotation(TestDemo.class);
				Class cls = td.expected();
				try {
					method.invoke(clazz.newInstance(),null);
					fail++;
				}catch (InvocationTargetException ite){
					System.out.println(ite.getTargetException());
					if(ite.getTargetException().getClass() == cls)
						pass++;
					else 
						fail++;
				}catch (Exception e) {
					e.printStackTrace();
					fail++;
					/*
					if(Exception.class != cls) {
						fail++;
					} else {
						pass++;
					}*/
						
				} 
			}
			
		}
		System.out.println(pass);
		System.out.println(fail);
	}

}
class Annotated {
	   @TestDemo(expected=Exception.class)
	   public void foo() throws Exception {
	      System.out.println("This is ");
	      throw new Exception();
	   }
	   @TestDemo(expected=RuntimeException.class)
	   public void foo1() {
	      System.out.println("This is one");
	      throw new RuntimeException();
	   }
	   
	}