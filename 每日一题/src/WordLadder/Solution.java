package WordLadder;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/word-ladder-ii/
 * 给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列。转换需遵循如下规则：
 *
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 说明:
 *
 * 如果不存在这样的转换序列，返回一个空列表。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 *
 */
public class Solution {
    private static final int INF = 1<<20; // 无穷大
    private Map<String, Integer> wordId; // 词到id的映射
    private ArrayList<String> idWord; //id到词的映射
    private ArrayList<Integer>[] edges; //存储节点的边

    public Solution(){
        this.wordId = new HashMap<>();
        this.idWord = new ArrayList<>();
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        //先把beginword加进去
        if(!wordId.containsKey(beginWord)){
            wordId.put(beginWord, 0);
            idWord.add(beginWord);
        }
        //再把wordlist中的单词都映射为id
        int index = 1;
        for(String s: wordList){
            if(!wordId.containsKey(s)){
                wordId.put(s,index);
                idWord.add(s);
                index++;
            }
        }
        List<List<String>> resList = new ArrayList<>();
        //找一下endword
        if(!wordId.containsKey(endWord)){
            return resList;
        }
        int num = idWord.size();
        //初始化边
        this.edges = new ArrayList[num];
        for(int i=0; i< num; i++){
            this.edges[i] = new ArrayList();
        }
        //记录边
        for(int i=0; i< num; i++){
            for(int j= i+1; j<num; j++){
                if(this.transformWord(idWord.get(i), idWord.get(j))){
                    this.edges[i].add(j);
                    this.edges[j].add(i);
                }
            }
        }
        //cost[i]表示begin到第i个点的路径长度
        int[] cost = new int[num];
        cost[0] = 0;
        for(int i=1; i<num; i++){
            cost[i] = INF;
        }

        //设置一个存储路径的队列, 进行广度遍历
        ArrayDeque<ArrayList<Integer>> queue = new ArrayDeque<>();
        ArrayList<Integer> beginlist = new ArrayList<>();
        beginlist.add(wordId.get(beginWord));
        queue.offer(beginlist);
        int dest = wordId.get(endWord);

        while(!queue.isEmpty()){
            //取出队列中元素
            ArrayList<Integer> nowlist = queue.poll();
            int last = nowlist.get(nowlist.size()-1);
            if(last != dest){
                // 遍历相邻节点
                for(int e: this.edges[last]){
                    //判断该节点是否已访问, 如果已经访问过就不需要再考虑
                    if(cost[e] >= cost[last]+1){
                        //未访问或访问过但路径比现在找到的大, 那么加到路径中(添加等号是因为要找到所有最短路径)
                        cost[e] = cost[last]+1;
                        //这里注意,不要将同一个list对象放到队列中
                        ArrayList<Integer> tmp = new ArrayList<>(nowlist);
                        tmp.add(e);
                        queue.offer(tmp);
                    }
                }
            }else{
                //已经找到该路径,就将该路径转化成单词放到结果路径中
                ArrayList<String> res = new ArrayList<>();
                for( int n: nowlist){
                    res.add(idWord.get(n));
                }
                resList.add(res);
            }

        }
        return resList;
    }

    public boolean transformWord(String word1, String word2){
        int difference = 0;
        for (int i=0; i<word1.length()&&difference<2; i++){
            if(word1.charAt(i)!=word2.charAt(i)){
                difference++;
            }
        }
        return difference==1;
    }

    public static void main(String[] args) {
        String begin = "hit";

        String end = "cog";
        List<String> list = new ArrayList<>();
        list.add("hot");
        list.add("dot");
        list.add("dog");
        list.add("lot");
        list.add("log");
        list.add("cog");
        Solution s = new Solution();
        System.out.println(s.findLadders(begin, end, list));
    }
}
