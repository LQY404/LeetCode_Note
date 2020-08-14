class Solution {
public:
    bool isValid(string s) {
        vector<char> stack;

        int n = s.size();

        for(int i = 0;i < n;i++){
            char ar = s[i];
             switch(ar){
                default:
                    stack.push_back(ar);
                    break;
                case ')':
                    if(!stack.empty() && stack.back()=='(') stack.pop_back();
                    else return false;
                    break;
                case '}':
                    if(!stack.empty() && stack.back()=='{') stack.pop_back();
                    else return false;
                    break;
                case ']':
                    if(!stack.empty() && stack.back()=='[') stack.pop_back();
                    else return false;
                    break;
            }
        }
        return stack.empty();
    }
};