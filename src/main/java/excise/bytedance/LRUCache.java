package excise.bytedance;

import java.util.HashMap;
import java.util.Map;

public class LRUCache
{
	class Node
	{
		int key;
		int value;
		Node prev;
		Node next;

		public Node()
		{
		}

		public Node(int _key, int _value)
		{
			key = _key;
			value = _value;
		}
	}

	private Map<Integer, Node> cache = new HashMap<Integer, Node>();
	private int size;
	private int capacity;
	private Node head, tail;

	public LRUCache(int capacity)
	{
		this.size = 0;
		this.capacity = capacity;
		// 使用伪头部和伪尾部节点
		head = new Node();
		tail = new Node();
		head.next = tail;
		tail.prev = head;
	}

	public int get(int key)
	{
		Node node = cache.get(key);
		if (node == null)
		{
			return -1;
		}
		// 如果 key 存在，先通过哈希表定位，再移到头部
		moveToHead(node);
		return node.value;
	}

	public void put(int key, int value)
	{
		Node node = cache.get(key);
		if (node == null)
		{
			// 如果 key 不存在，创建一个新的节点
			Node newNode = new Node(key, value);
			// 添加进哈希表
			cache.put(key, newNode);
			// 添加至双向链表的头部
			addToHead(newNode);
			++size;
			if (size > capacity)
			{
				// 如果超出容量，删除双向链表的尾部节点
				Node tail = removeTail();
				// 删除哈希表中对应的项
				cache.remove(tail.key);
				--size;
			}
		}
		else
		{
			// 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
			node.value = value;
			moveToHead(node);
		}
	}

	private void addToHead(Node node)
	{
		node.prev = head;
		node.next = head.next;
		head.next.prev = node;
		head.next = node;
	}

	private void removeNode(Node node)
	{
		node.prev.next = node.next;
		node.next.prev = node.prev;
	}

	private void moveToHead(Node node)
	{
		removeNode(node);
		addToHead(node);
	}

	private Node removeTail()
	{
		Node res = tail.prev;
		removeNode(res);
		return res;
	}

	public static void main(String[] args)
	{
		int capacity = 3;

	}
}
