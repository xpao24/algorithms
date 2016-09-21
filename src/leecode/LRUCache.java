import java.util.HashMap;

/**
 * LRUCache  使用head-tail两个哨兵,使新加入节点放在head右边.删除tail左边的节点
 */
public class LRUCache {
    private HashMap<Integer,Node> cache;
    private int capacity;//容量
    private int size;//缓存大小
    private Node head;
    private Node tail;
    private class Node {
        int key;
        int value;
        Node before;
        Node next;
        public Node(){

        }
        public Node(int key,int value){
            this.key = key;
            this.value =value;
        }
    }
    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.cache = new HashMap<Integer,Node>();

        this.head = new Node();
        this.tail = new Node();
        this.tail.before = this.head;
        this.tail.next = null;
        this.head.next = tail;
        this.head.before = null;
    }
    public void moveToFront(Node node){
        removeNode(node);
        addNode(node);
    }
    public void removeNode(Node node){
        Node before = node.before;
        Node next = node.next;
        before.next = next;
        next.before = before;
    }
    public void addNode(Node node){
        node.before = head;
        node.next = head.next;
        head.next.before = node;
        head.next = node;
    }
    public Node removeTail(){
        Node node = tail.before;
        removeNode(node);
        return node;
    }
    public void set(int key,int value){
        Node node = cache.get(key);
        if(node == null){
            Node newNode = new Node(key,value);
            addNode(newNode);//插入队头右边
            cache.put(key,newNode);
            size++;
            if(size>capacity){
                Node temp = removeTail();//删除队尾
                cache.remove(temp.key);//从缓存删除
            }
        }else{
            node.value = value;
            moveToFront(node);
        }
    }
    public int get(int key){
        Node node = (Node)cache.get(key);
        if(node == null){
            return -1;
        }
        moveToFront(node);
        return node.value;
    }
}