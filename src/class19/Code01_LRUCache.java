package class19;
//lc146
import java.util.HashMap;


// 本题测试链接 : https://leetcode.com/problems/lru-cache/
// 提交时把类名和构造方法名改成 : LRUCache
public class Code01_LRUCache {
//	class LRUCache {
//
//		public LRUCache(int capacity) {
//			c = new cache(capacity);
//		}
//
//		private cache c;
//
//		public int get(int key) {
//			return c.get(key);
//		}
//
//		public void put(int key, int value) {
//			c.put(key, value);
//		}
//
//		public class node {//双向链表的节点 作为v存入HashMap
//			public int key;
//			public int val;
//			public node last;
//			public node next;
//
//			public node(int key, int val) {
//				this.key = key;
//				this.val = val;
//			}
//		}
//
//		public class doublelinkedlist {//双向链表
//			private node head;
//			private node tail;//链表的头尾指针 头指针指向最早有操作的节点
//
//			public doublelinkedlist() {
//				head = null;
//				tail = null;
//			}
//
//			public void addnode(node n) {//在链表结尾加节点
//				if (head == null) {//链表中原来无节点 首位指针都指向新加节点
//					head = n;
//					tail = n;
//				} else {
//					tail.next = n;
//					n.last = tail;//节点连在尾部
//					tail = n;//尾指针跳转
//				}
//			}
//
//			public void movetotail(node n) {//把链表中的节点移动到链表结尾
//				if (n == tail) {
//					return;
//				}
//				if (n == head) {//要移动的节点原来是头结点
//					head = n.next;//头指针跳转
//					head.last = null;//新头指针last置空 完成原头结点的分离
//				} else {
//					n.last.next = n.next;
//					n.next.last = n.last;//原节点分离
//				}
//				n.last = tail;
//				n.next = null;
//				tail.next = n;
//				tail = n;
//			}
//
//			public node removehead() {//删去链表头结点 返回删去的节点 用于获得节点中记录的val
//				if (head == null) {
//					return null;
//				}
//				node ans = head;//记录要返回的节点
//				if (head == tail) {//原来只有一个节点
//					head = null;
//					tail = null;
//				} else {
//					head = ans.next;//头指针跳转
//					ans.next = null;//原头结点断连
//					head.last = null;//新头结点last指针置空
//				}
//				return ans;
//			}
//		}
//
//		public class cache {
//			private HashMap<Integer, node> hm;//k为key  v为记录了key和val的双向链表节点
//			private doublelinkedlist nodelist;//双向链表
//			private int capacity;
//
//			public cache(int capacity) {
//				hm = new HashMap<Integer, node>();
//				nodelist = new doublelinkedlist();
//				this.capacity = capacity;
//			}
//
//			public int get(int key) {
//				if (hm.containsKey(key)) {
//					node ans = hm.get(key);//获取查询到的节点
//					nodelist.movetotail(ans);//移动到链表尾部
//					return ans.val;//返回对应的val
//				}
//				return -1;//key不存在
//			}
//
//			public void put(int key, int val) {
//				if (hm.containsKey(key)) {//key已存在 更新val
//					node n = hm.get(key);
//					n.val = val;
//					nodelist.movetotail(n);//节点移动到尾
//				} else {//key不存在 在尾部新增对应节点 如果超过capa则删去原头结点
//					node n = new node(key, val);
//					hm.put(key, n);//key-节点记录存入HashMap
//					nodelist.addnode(n);
//					if (hm.size() == capacity + 1) {
//						node r = nodelist.removehead();
//						hm.remove(r.key);//根据删去的节点的key 把HashMap中对应的记录也删去
//					}
//				}
//			}
//		}
//	}
	public Code01_LRUCache(int capacity) {
		cache = new MyCache<>(capacity);
	}

	private MyCache<Integer, Integer> cache;

	public int get(int key) {
		Integer ans = cache.get(key);
		return ans == null ? -1 : ans;
	}

	public void put(int key, int value) {
		cache.set(key, value);
	}

	public static class Node<K, V> {
		public K key;
		public V value;
		public Node<K, V> last;
		public Node<K, V> next;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	public static class NodeDoubleLinkedList<K, V> {
		private Node<K, V> head;
		private Node<K, V> tail;

		public NodeDoubleLinkedList() {
			head = null;
			tail = null;
		}

		// 现在来了一个新的node，请挂到尾巴上去
		public void addNode(Node<K, V> newNode) {
			if (newNode == null) {
				return;
			}
			if (head == null) {
				head = newNode;
				tail = newNode;
			} else {
				tail.next = newNode;
				newNode.last = tail;
				tail = newNode;
			}
		}

		// node 入参，一定保证！node在双向链表里！
		// node原始的位置，左右重新连好，然后把node分离出来
		// 挂到整个链表的尾巴上
		public void moveNodeToTail(Node<K, V> node) {
			if (tail == node) {
				return;
			}
			if (head == node) {
				head = node.next;
				head.last = null;
			} else {
				node.last.next = node.next;
				node.next.last = node.last;
			}
			node.last = tail;
			node.next = null;
			tail.next = node;
			tail = node;
		}

		public Node<K, V> removeHead() {
			if (head == null) {
				return null;
			}
			Node<K, V> res = head;
			if (head == tail) {
				head = null;
				tail = null;
			} else {
				head = res.next;
				res.next = null;
				head.last = null;
			}
			return res;
		}

	}

	public static class MyCache<K, V> {
		private HashMap<K, Node<K, V>> keyNodeMap;
		private NodeDoubleLinkedList<K, V> nodeList;
		private final int capacity;

		public MyCache(int cap) {
			keyNodeMap = new HashMap<K, Node<K, V>>();
			nodeList = new NodeDoubleLinkedList<K, V>();
			capacity = cap;
		}

		public V get(K key) {
			if (keyNodeMap.containsKey(key)) {
				Node<K, V> res = keyNodeMap.get(key);
				nodeList.moveNodeToTail(res);
				return res.value;
			}
			return null;
		}

		// set(Key, Value)
		// 新增  更新value的操作
		public void set(K key, V value) {
			if (keyNodeMap.containsKey(key)) {
				Node<K, V> node = keyNodeMap.get(key);
				node.value = value;
				nodeList.moveNodeToTail(node);
			} else { // 新增！
				Node<K, V> newNode = new Node<K, V>(key, value);
				keyNodeMap.put(key, newNode);
				nodeList.addNode(newNode);
				if (keyNodeMap.size() == capacity + 1) {
					removeMostUnusedCache();
				}
			}
		}

		private void removeMostUnusedCache() {
			Node<K, V> removeNode = nodeList.removeHead();
			keyNodeMap.remove(removeNode.key);
		}

	}

}