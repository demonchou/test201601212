package excise;
/**
 * Description:  可变类
 * @author hzzhouhongfei 
 * 2016年5月22日下午9:10:02
 */
class Name
{
	private String firstName;
	private String lastName;
	public Name(String fist, String last)
	{
		this.firstName = fist;
		this.lastName = last;
	}
	public String getLastName()
	{
		return lastName;
	}
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	public String getFirstName()
	{
		return firstName;
	}
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	@Override
	public String toString()
	{
		return "Name [firstName=" + firstName + ", lastName=" + lastName + "]";
	}
}
public class Dog
{
	private Name name;
	
	public Dog(Name name)
	{
		this.setName(name);
	}

	public static void main(String ag[])
	{
		Name name = new Name("悟空","孙" );
		Dog dog = new Dog(name);
		System.out.println(dog.getName().getFirstName());
		name.setFirstName("Tom");
		System.out.println(dog.getName().getFirstName());

	}

	public Name getName()
	{
		return name;
	}
	
	public void setName(Name name)
	{
		this.name = name;
	}
}
