package filter;

import filter.*;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * @Description  该类工具适用于工厂模式通过反射机制，随机返回一个接口（或者父类）的实现类（或者子类）的对象
 * @author hzzhouhongfei 
 * 2016年7月2日下午9:10:52
 */
public class ClassUtils
{
	
	public static void main (String [] a)
	{
		List<Class> classes = getAllClassByInterface(Filter.class);
		Map<String, Object> calssMap = new HashMap<String, Object>();
		
		System.out.println(classes.size());
		System.out.println(classes);
	}
	
	/**
	* 给一个接口（或者父类），返回这个接口（或者父类）的所有实现类（或者子类）
	* （这些实现类（或者子类）的的包的路径必须要是接口（或者父类）的同一层或者下层）
	* @param c
	* @return
	*/
	@SuppressWarnings("unchecked")
	public static List<Class> getAllClassByInterface(Class c){
		List<Class> returnClassList = new ArrayList<Class>(); //返回结果
		//如果不是一个接口，则不做处理
		if(c.isInterface()){
			String packageName = c.getPackage().getName(); //获得当前的包名
			try {
				List<Class> allClass = getClasses(packageName); //获得当前包下以及子包下的所有类
				//判断是否是同一个接口
				for(int i=0;i<allClass.size();i++){
					if(c.isAssignableFrom(allClass.get(i))){ //判断是不是一个接口
						if(!c.equals(allClass.get(i))){ //本身不加进去
							returnClassList.add(allClass.get(i));
						}
					}
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return returnClassList;
	}
	/***
	 * 从一个包中查找出所有的类，在jar包中不能查找
	 * @param packageName
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	 @SuppressWarnings("unchecked")
	 private static List<Class> getClasses(String packageName) throws ClassNotFoundException, IOException {
		 ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		 String path = packageName.replace('.', '/');
		 Enumeration<URL> resources = classLoader.getResources(path);
		 List<File> dirs = new ArrayList<File>();
		 while (resources.hasMoreElements()) {
			 URL resource = resources.nextElement();
			 dirs.add(new File(resource.getFile()));
		 }
		 ArrayList<Class> classes = new ArrayList<Class>();
		 for (File directory : dirs) {
			 classes.addAll(findClasses(directory, packageName));
		 }
		 return classes;
	 }
	 
	/***
	* 找出目录directory下所有的class文件并以“完整包名”+“类名”的形式返回类名
	* @param directory
	* @param packageName
	* @return
	* @throws ClassNotFoundException
	*/
	@SuppressWarnings("unchecked")
	private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
		List<Class> classes = new ArrayList<Class>();
		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) 
			{
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file, packageName + "." + file.getName()));
			}
			else if(file.getName().endsWith(".class")) 
			{
				classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
			}
		}
		return classes;
	 }
}