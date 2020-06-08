package com.ListNode.addTwoNumbers;

import com.ListNode.common.ListNode;

public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p = l1, q = l2;
        //哑节点,作为结果
        ListNode dummyNode = new ListNode(0);
        ListNode tmp = dummyNode;
        int carry = 0;//进位
        while(p!=null||q!=null){
            int a,b;
            if(p==null){
                a = 0;
            }else{
                a = p.val;
                p = p.next;
            }
            if(q==null){
                b = 0;
            }else{
                b = q.val;
                q = q.next;
            }
            int val = (a+b+carry)%10;
            carry = (a+b+carry)/10;
            ListNode node = new ListNode(val);
            tmp.next = node;
            tmp = tmp.next;
        }
        if(carry > 0){
            ListNode node = new ListNode(carry);
            tmp.next = node;
        }
        return dummyNode.next;
    }
}
