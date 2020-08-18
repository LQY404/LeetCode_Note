/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    TreeNode* sortedListToBST(ListNode* head) {
        if(head == NULL) return NULL;
        //处理第一个[head, null)
        // 先zh
        int size = 0;
        ListNode *p = head;

        while(p != NULL){
            size++;
            p = p->next;
        }
        if(size == 1) return new TreeNode(head->val);

        int index = 0;
        p = head;
        while(index<size/2 && p!=NULL){
            index++;
            p = p->next;
        }
        TreeNode *root = new TreeNode(p->val);
        solve(root, head, p, -1);
        solve(root, p->next, NULL, 1);

        return root;

    }
    void solve(TreeNode *root, ListNode *s, ListNode *e, int flag){
        if(root==NULL || s==e || s==NULL) return;

        int size = 0;
        ListNode *p = s;
        while(p != e){
            size++;
            p = p->next;
        }
        p = s;
        int index = 0;
        while(index<size/2 && p!=NULL){
            index++;
            p = p->next;
        }
        switch(flag){
            case -1:
                root->left = new TreeNode(p->val);
                solve(root->left, s, p, -1);
                solve(root->left, p->next, e, 1);
                break;
            default:
                root->right = new TreeNode(p->val);
                solve(root->right, s, p, -1);
                solve(root->right, p->next, e, 1);
        }
    }
};