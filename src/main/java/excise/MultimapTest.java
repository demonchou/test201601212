package excise;

import java.util.Collection;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

/**
 * Description:
 * Created by hzzhouhongfei.
 * 2017/8/4 15:34
 */
public class MultimapTest
{
	public static void main(String[] args)
	{
		Multimap<String, String> myMultimap = ArrayListMultimap.create();

		Multimap<String, String> pmMultimap = HashMultimap.create();
		Multimap<String, String> pcMultimap = HashMultimap.create();
		Multimap<String, String> mpMultimap = HashMultimap.create();
		Multimap<String, String> cpMultimap = HashMultimap.create();
		Multimap<String, String> tmpMultimap = HashMultimap.create();

		// Adding some key/value
		myMultimap.put("Fruits", "Bannana");
		myMultimap.put("Fruits", "Apple");
		myMultimap.put("Fruits", "Pear");
		myMultimap.put("Fruits", "Pear");
		myMultimap.put("Vegetables", "Carrot");

		pmMultimap.put("p1", "m1");
		pmMultimap.put("p1", "m2");
		pmMultimap.put("p2", "m1");
		pmMultimap.put("p2", "m2");
		pmMultimap.put("p3", "m1");
		pmMultimap.put("p3", "m3");
		pmMultimap.put("p4", "m4");
		pmMultimap.put("p4", "m5");
		pmMultimap.put("p5", "m4");
		pmMultimap.put("p5", "m5");

		pcMultimap.put("p1", "c1");
		pcMultimap.put("p1", "c2");
		pcMultimap.put("p2", "c1");
		pcMultimap.put("p2", "c3");
		pcMultimap.put("p3", "c3");
		pcMultimap.put("p3", "c5");
		pcMultimap.put("p4", "c1");
		pcMultimap.put("p4", "c7");
		pcMultimap.put("p5", "c8");
		pcMultimap.put("p5", "c5");

		// Getting the size
		int size = myMultimap.size();
		System.out.println(size); // 5

		// Getting values
		Collection<String> fruits = myMultimap.get("Fruits");
		System.out.println(fruits); //  [Bannana, Apple, Pear, Pear]
		System.out.println(ImmutableSet.copyOf(fruits));// [Bannana, Apple, Pear]
		// Set<Foo> set = Sets.newHashSet(list);
		// Set<Foo> foo = new HashSet<Foo>(myList);

		Collection<String> vegetables = myMultimap.get("Vegetables");
		System.out.println(vegetables); // [Carrot]

		// Iterating over entire Mutlimap
		for (String value : myMultimap.values()) {
			System.out.println(value);
		}

		// Removing a single value
		myMultimap.remove("Fruits", "Pear");
		System.out.println(myMultimap.get("Fruits")); // [Bannana, Apple, Pear]

		// Remove all values for a key
		myMultimap.removeAll("Fruits");
		System.out.println(myMultimap.get("Fruits")); // [] (Empty Collection!)

		//{p5=[m4, m5], p4=[m4, m5], p3=[m1, m3], p2=[m1, m2], p1=[m1, m2]}
		//{p5=[c8, c5], p4=[c1, c7], p3=[c3, c5], p2=[c1, c3], p1=[c1, c2]}
		//{m5=[p5, p4], m1=[p3, p2, p1], m2=[p2, p1], m3=[p3], m4=[p5, p4]}
		//{c1=[p4, p2, p1], c2=[p1], c5=[p5, p3], c3=[p3, p2], c8=[p5], c7=[p4]}
		//{c1=[m4, m5, m1, m2, m1, m2], c2=[m1, m2], c5=[m4, m5, m1, m3], c3=[m1, m3, m1, m2], c8=[m4, m5], c7=[m4, m5]}
		//{c1=[m4, m5, m1, m2], c2=[m1, m2], c5=[m4, m5, m1, m3], c3=[m1, m3, m2], c8=[m4, m5], c7=[m4, m5]}
		mpMultimap = Multimaps.invertFrom(pmMultimap, mpMultimap);
		cpMultimap = Multimaps.invertFrom(pcMultimap, cpMultimap);
		for (String keycp : cpMultimap.keySet())
		{
			Collection<String> pidscp = cpMultimap.get(keycp);
			for (String pidpm : pidscp)
			{
				Collection<String> ms = pmMultimap.get(pidpm);
				for (String element : ms)
				{
					tmpMultimap.put(keycp, element);
				}
			}
		}
		System.out.println(tmpMultimap);//RECEIVE_FLAG

		System.out.println("MAIN_T".equals("mian_t"));
		System.out.println("MAIN_T".equals("mian_T"));
		System.out.println("MAIN_T".equalsIgnoreCase("MAIN_t"));
		System.out.println("MAIN_T".equalsIgnoreCase("MAIN_T"));
		System.out.println("main_t".equalsIgnoreCase("MAIN_t"));
		System.out.println("main_t".equalsIgnoreCase("MAIN_T"));
	}
}
