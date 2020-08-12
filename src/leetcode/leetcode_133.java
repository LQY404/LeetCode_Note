package leetcode;

import java.util.*;

/*
USER：LQY
DATE：2020/8/12
TIME：9:45
*/
public class leetcode_133 {

    public static void main(String []args){

    }
    Map<Node, Node> book = new HashMap<>();
    public Node cloneGraph(Node node) {
        if(node == null) return node;

        if(book.containsKey(node)) return book.get(node);

        Node ans = new Node(node.val, new ArrayList<>());
        book.put(node, ans);

        for(Node ner: node.neighbors){
            ans.neighbors.add(cloneGraph(ner));
        }
        return ans;

    }


    class Node{
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
