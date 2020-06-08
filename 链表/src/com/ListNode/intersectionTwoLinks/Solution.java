package com.ListNode.intersectionTwoLinks;

import com.ListNode.common.ListNode;

/**
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists-lcci
 * 找到两个链表的交点
 * 原理: AD + DC + BD = BD + DC + AD
 */


public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode t1 = headA;
        ListNode t2 = headB;
        while(t1!=null||t2!=null){
            if(t1 == t2){
                return t1;
            }
            if(t1 != null){
                t1 = t1.next;
            }else{
                t1 = headB;
            }
            if(t2 != null){
                t2 = t2.next;
            }else{
                t2 = headA;
            }
        }
        return null;
    }
}