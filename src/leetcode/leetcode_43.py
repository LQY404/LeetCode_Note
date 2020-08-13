class Solution:
    def countSmaller(self, nums: List[int]) -> List[int]:
        lens = len(nums)
        ans = []

        if(lens == 0):
            return ans
        count = [0 for _ in range(lens)]
        temp = [0 for _ in range(lens)]
        index = [i for i in range(0, lens)]


        self.suber(nums, index, temp, count, 0, (lens-1))
        for i in count:
            ans.append(i)

        return ans

    def suber(self, nums: List[int], index: List[int], temp: List[int], count: List[int], left: int, right: int):
        if(left >= right):
            return
        mid = (left + right) >> 1

        self.suber(nums, index, temp, count, left, mid)
        self.suber(nums, index, temp, count, mid+1, right)

        self.mergeAndCount(nums, index, temp, count, left, mid+1, right)

    def mergeAndCount(self, nums: List[int], index: List[int], temp: List[int], count: List[int], left: int, mid: int, right: int):

        j = mid
        newi = i = left
        while(i<mid and j<=right):
            if(nums[index[i]] <= nums[index[j]]):
                temp[newi] = index[j]
                newi += 1
                j += 1
            else:
                count[index[i]] += right - j + 1
                temp[newi] = index[i]
                newi += 1
                i += 1

        while(i < mid):
            temp[newi] = index[i]
            newi += 1
            i += 1
        while(j <= right):
            temp[newi] = index[j]
            newi += 1
            j += 1

        for k in range(left, right+1):
            index[k] = temp[k]