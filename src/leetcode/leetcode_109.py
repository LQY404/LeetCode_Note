# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def sortedListToBST(self, head: ListNode) -> TreeNode:

        def solve(root: TreeNode, s: ListNode, e: ListNode, flag: int):
            if root==None or s==e or s==None:
                return

            size = 0
            p = s
            while p != e:
                size += 1
                p = p.next
            p = s
            index = 0
            while index < size//2:
                index += 1
                p = p.next

            if flag == -1:
                root.left = TreeNode(p.val)
                solve(root.left, s, p, -1)
                solve(root.left, p.next, e, 1)

            else:
                root.right = TreeNode(p.val)
                solve(root.right, s, p, -1)
                solve(root.right, p.next, e, 1)



        if head == None:
            return None

        size = 0
        p = head
        while p != None:
            size += 1
            p = p.next

        p = head
        index = 0
        while index < size//2:
            index += 1
            p = p.next

        root = TreeNode(p.val)
        solve(root, head, p, -1)
        solve(root, p.next, None, 1)

        return root