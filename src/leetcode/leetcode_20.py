class Solution:
    def isValid(self, s: str) -> bool:
        n = len(s)

        stack = []
        for i in range(0, n):
            ar = s[i]

            if ar=='(' or ar=='[' or ar=='{':
                stack.append(ar)
                continue
            if ar == ')':
                if len(stack)!=0 and stack[-1]=='(':
                    # stack = stack[:-1]
                    stack.pop()
                else:
                    return False
            elif ar == ']':
                if len(stack)!=0 and stack[-1]=='[':
                    # stack = stack[:-1]
                    stack.pop()
                else:
                    return False
            elif ar == '}':
                if len(stack)!=0 and stack[-1]=='{':
                    # stack = stack[:-1]
                    stack.pop()
                else:
                    return False

        return len(stack) == 0