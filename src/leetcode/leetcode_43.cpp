class Solution {
public:
    string multiply(string num1, string num2) {
        int n1 = num1.size();
        int n2 = num2.size();

//      保证被乘数一定比乘数的位数多
        if(n2 > n1){
            string tem = num2;
            num2 = num1;
            num1 = tem;

            int vt = n2;
            n2 = n1;
            n1 = vt;
        }

        int p1,p2;
        p2 = n2 - 1;

        int add[n1+n2];  //进位。根据：乘积位数最大为乘数位数与被乘数位数之和。同时被乘数每走完一位，都可以确定下来一位。并且在n1*n2轮乘法运算中，进位都可以循环使用，并且不会相互干扰。
        // 所以同时也将这个进位数组用来保存最终结果。
        for(int i = 0;i < n1+n2;i++) add[i] = 0;

        while(p2 >= 0){  //每次乘数都向高位移动一位。
            int v2 = num2[p2] - '0';
            p1 = n1 - 1;  //被乘数每次都从最后一位开始

            int tadd = n1 + p2;  //更新进位。至于为什么每次都“初始化为n1+p2”，可以结合基本数学知识思考即可

            while(p1 >= 0){
                int v1 = num1[p1--] - '0';
                //注意这里在计算时，除了v1*v2之外，一定还要带上当前位置处的进位。
                int a = (v1*v2 + add[tadd])/10;  //余数
                int r = (v1*v2 + add[tadd])%10;  //结果
                //再更新高位的进位，以及当前位的最终值
                add[tadd-1] += a;  //进位累加！
                add[tadd] = r;   //当前位是直接赋值！！！
                // cout<<v2<<"x"<<v1<<endl;
                tadd--;
            }
            p2--;  //进行下一轮
        }

        string ans = "";
        int i = 0;
        while(i<n1+n2 && add[i]==0) i++;
        if(i == n1+n2) //至少有一个数为0
            return "0";

        while(i < n1+n2){
            // cout<<add[i];
            ans += (add[i++]+'0');
            // ans.push_back(add[i++]+'0');
        }

        return ans;
    }
};