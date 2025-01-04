package lld.practice.lrucache.one;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args[]){

/*
Input
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2],       [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, null, -1, 3, 4]
*/
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));
        cache.put(4, 4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));

    }
}
class LRUCache {

    Map<Integer,Node> map = new HashMap<>();
    Node head = null;
    Node end = null;
    int capacity = 0;
    public LRUCache(int capacity) {
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return  -1;
        Node node = map.get(key);
        int val = node.value;
        //remove this node and
        remove(node);
        //add the node at front
        addNode(node);
        //print();
        return val;
    }
    
    public void put(int key, int value) {
        //System.out.println(map +" "+ map.size());
        Node node = null;
        Node nodeToBeRemoved =  null;
        if(map.containsKey(key)){
            node = map.get(key);
            node.value = value; // value has been updated
            remove(node);///remove and put it at front as it newly accessed
        }
        else{
            node = new Node(key, value);

            if(map.size()< capacity){
                map.put(key,node);
            }
            //remove the last node from the list if capacity is reached
            else {
                
                //remove the last node from the map as well
                if(end!=null){
                    map.remove(end.key);
                    remove(end);
                }
                map.put(key,node);
            } 
        }
        addNode(node);
        //print();
    }
    public void addNode(Node node){
        //first node
        if(head ==null && end ==null){
            head = node;
            end = node;

        }
        //list has at least one node 
        else{
            node.right = head;
            node.left =null;
            head.left = node;
            head = node;
        }
    }

    public void remove(Node node) {
    if (node.left != null) {
        node.left.right = node.right;
    } else {
        head = node.right; // Node is head
    }

    if (node.right != null) {
        node.right.left = node.left;
    } else {
        end = node.left; // Node is end
    }
}
    public void print(){
        Node node = head;
        System.out.println("........");
        while(node!=null){
            System.out.println("("+node.key + ","+ node.value+")");
            node = node.right;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

class Node{
    public int key;
    public int value;
    public Node left = null;
    public Node right =null;
    public Node(int k, int v){
        this.value = v;
        this.key = k;
    }
    public String toString(){
        return "[k"+ key +",v"+ value+"]";
    }
}
