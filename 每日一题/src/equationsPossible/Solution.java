package equationsPossible;

import java.util.ArrayList;

public class Solution {
    private int parent[];
    public Solution(){
        parent = new int[26];
        for(int i=0;i<parent.length;i++){
            parent[i] = i;
        }
    }
    public boolean equationsPossible(String[] equations) {

        //先把所有的等式连通
        for(String s: equations){
            //判断是否为等式
            if(s.charAt(1)=='='){
                int x = s.charAt(0)-'a';
                int y = s.charAt(3)-'a';
                union(x,y);
            }
        }
        //再判断不等式
        for(String s: equations){
            if(s.charAt(1)=='!'){
                int x = s.charAt(0)-'a';
                int y = s.charAt(3)-'a';
                //判断它们是否在同一个集合
                if(find(x) == find(y)){
                    return false;
                }
            }
        }
        return true;
    }
    //返回根节点
    public int find(int root){
        while(parent[root]!=root){
            parent[root] = parent[parent[root]];
            root = parent[root];
        }
        return parent[root];
    }
    //将两个点连通在一起
    public void union(int root1, int root2){
        //如果是不连通的
        if(find(root1)!=find(root2)){
//            将第一个变量的根节点的父节点指向第二个变量的根节点
            parent[find(root1)] = find(root2);
        }

    }

}
