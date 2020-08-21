/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    int minDepth(TreeNode* root) {
        if(root == NULL) return 0;
        solve(root, 1);

        return ans;
    }
    int ans = 10000;
    void solve(TreeNode *root, int k){
        if(root == NULL){
            ans = min(ans, k);
            return;
        }
        // cout<<root->val<<endl;
        if(root->left==NULL && root->right==NULL){
            ans = min(ans, k);
            return;
        }else if(root->left == NULL){
            solve(root->right, k+1);
        }else if(root->right == NULL){
            solve(root->left, k+1);
        }else{
            solve(root->left, k+1);
            solve(root->right, k+1);
        }
    }
};