package leetcode;

/*
USER：LQY
DATE：2020/7/20
TIME：19:23
*/
public class leetcode_167 {

    public static void main(String []args){

    }
    public int[] twoSum(int[] numbers, int target) {
        int lens = numbers.length;

        int n1 = 0;
        int n2 = lens - 1;

        while(n1 < n2){

            int flag = (numbers[n1] + numbers[n2]) - target;
            if(flag == 0) break;
            else if(flag > 0){
                n2--;
            }else{
                n1++;
            }
        }
        return new int[]{n1+1, n2+1};

    }
}
