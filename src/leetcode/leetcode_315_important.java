package leetcode;

/*
USER：LQY
DATE：2020/7/11
TIME：8:25
*/
import java.util.ArrayList;
import java.util.List;

public class leetcode_315_important {

    public List<Integer>countSmaller(int []nums){
        List<Integer> ans = new ArrayList<>();
        int len = nums.length;
        if(len == 0) return ans;
        count = new int[len];
        temp = new int[len];
        index = new int[len];
        //初始化index数组
        for(int i = 0;i < len;i++)
            index[i] = i;

        if(len%2 == 0){
            //偶数
            suber(nums, 0, len-1);
        }else{
            //奇数长度最后一个另外处理
            suber(nums, 0, len-2);
            //再处理最后一个
            int last = nums[len-1];
            for(int i = 0;i < len-1;i++){
                if(nums[i] > last){
                    count[i]++;
                }
            }
        }
        for(int i: count){
            System.out.print(i+"\t");
            ans.add(i);
        }
        System.out.println();

        return ans;
    }

    private int []count;
    private int []index;  //存储原始数组的下标。排序的时候交换下标而原数组不变
    private int []temp;  //和一般的归并排序一样，需要一个额外的数组空间
    public void suber(int []nums, int left, int high){
        if(left >= high) return;
        int mid = (left + high) >> 1;  //用移位计算比较好

        suber(nums, left, mid);
        suber(nums, mid+1, high);
        mergeAndCount(nums, left, mid+1, high);

    }
    public void mergeAndCount(int []nums, int left, int mid, int right){

        //这样会超时
//        for(int i = left;i < mid;i++){
//            int lval = nums[i];
//            for(int j = mid;j <= right;j++){
//                if(lval > nums[j]) count[i]++;
//            }
//        }
        int i = left;
        int j = mid;
        int newi = left;
        while(i<mid && j<=right){
            if(nums[index[i]] <= nums[index[j]]){
                temp[newi++] = index[j++];
            }else{
                count[index[i]] += right - j + 1;   //这个地方是与传统归并排序唯一不同的地方。也是用来统计的关键
                temp[newi++] = index[i++];
            }
        }
        while(i < mid){
            temp[newi++] = index[i++];
        }
        while(j <= right){
            temp[newi++] = index[j++];
        }
        //此时，从left->right的index对应的temp数组已经排好序。
        // 这一趟排完序后将temp中的部分有序数列复制到index中
        for(int k = left;k <= right;k++){
            index[k] = temp[k];
        }
    }



    public static void main(String[] args) {
        int[] nums = new int[]{5183, 2271, 3067, 539, 8939, 2999, 9264, 737, 3974};
        leetcode_315_important solution = new leetcode_315_important();
        List<Integer> countSmaller = solution.countSmaller(nums);
        System.out.println(countSmaller);
    }
}
