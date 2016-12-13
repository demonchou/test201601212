package excise;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description  
 * @author hzzhouhongfei 
 * 2016年7月4日下午4:35:53
 */
class Student
{
	private String name;
	private String age;
	
	public Student(){}
	
	public Student(String name, String age)
	{
		super();
		this.name = name;
		this.age = age;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getAge()
	{
		return age;
	}
	public void setAge(String age)
	{
		this.age = age;
	}
	@Override
	public String toString()
	{
		return "Student [name=" + name + ", age=" + age + "]";
	}
	
}

class Student2
{
	private String name;
	private String age;
	Student student;
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getAge()
	{
		return age;
	}
	public void setAge(String age)
	{
		this.age = age;
	}
	public Student getStudent()
	{
		return student;
	}
	public void setStudent(Student student)
	{
		this.student = student;
	}
}
public class ListTest
{
	public static void main(String[] args)
	{
		Student[] studentArray = null;
		
		Student student1 = new Student("Tom", "16");
		Student student2 = new Student("Jack", "16");
		Student student3 = new Student("Merry", "16");
		
		List<Student> students = new ArrayList<>();
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 17; i++)
		{
			list.add(String.valueOf(i));
		}
		System.out.println("listSize:  "+list.size());
		
		students.add(student1);
		students.add(student2);
		students.add(student3);
		
		studentArray = students.toArray(new Student[0]);
		
		System.out.println(students.contains(student1));
		for (Student student : studentArray)
		{
			System.out.println(student);
		}
	}
}
