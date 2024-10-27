package org.practice.prefixSum;


import java.util.Arrays;
import java.util.List;

public class MaxSumOf3NonOverlappingSubarrays {
//
//    ref : https://www.youtube.com/watch?v=mXeT7-oZeQQ
//    Given an integer array nums and an integer k, find three non-overlapping subarrays of length k with maximum sum and return them.
//
//    Return the result as a list of indices representing the starting position of each interval (0-indexed). If there are multiple answers, return the lexicographically smallest one.
//
//
//
//    Example 1:
//
//    Input: nums = [1,2,1,2,6,7,5,1], k = 2
//    Output: [0,3,5]
//    Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
//    We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
//    Example 2:
//
//    Input: nums = [1,2,1,2,1,2,1,2,1], k = 2
//    Output: [0,2,4]

    public static void main(String[] args) {
        System.out.println(maxSumOfThreeSubarrays(new int[]{1,2,1,2,6,7,5,1},2));

    }

    public static List<Integer> maxSumOfThreeSubarrays(int[] nums, int k) {
//        Steps
//                1. Calculate prefix sum array
//                2. calculate left max sum array
//                3. calculate right max sum array
//                4. Calculate middle max sum and then calculate right and left max some points in lexicographically order smaller

        int n = nums.length;
        int[] pSum = new int[n];
        pSum[0] = nums[0];
        for(int i=1; i< n; i++) {
            pSum[i] = pSum[i-1] + nums[i];
        }

//        Arrays.stream(pSum).forEach(System.out::println);
        int sum = 0;
        int[] lSum = new int[n];
        for(int i=0; i< n; i++) {
            if(i< k) {
                sum += nums[i];
                lSum[i] += nums[i];
            } else {
                sum = sum - nums[i-k] + nums[i];
                lSum[i] = Math.max(lSum[i-1], sum);
            }
        }
//        Arrays.stream(lSum).forEach(System.out::println);

        sum = 0;
        int[] rSum = new int[n];
        for(int i=n-1; i>=0; i--) {
            if(i>= n-k) {
                sum+= nums[i];
                rSum[i] = sum;
            } else {
                sum = sum - nums[i+k] + nums[i];
                rSum[i] = Math.max(rSum[i+1], sum);
            }
        }
//        Arrays.stream(rSum).forEach(System.out::println);

        int maxSum = 0;
        int right = 0;
        int left = 0;
        int spmsa = -1;
        for(int i=k; i< n-2*k; i++) {
            if(maxSum < lSum[i-1] + rSum[i+k] + (pSum[i+k-1] - pSum[i-1])) {
                maxSum = lSum[i-1] + rSum[i+k] + (pSum[i+k-1] - pSum[i-1]);
                left = lSum[i-1];
                right = rSum[i+k];
                spmsa = i;
            }
        }

        System.out.println("middle point: " + spmsa);
        for(int i=k-1; i< spmsa; i++) {
            if(pSum[i]-((i-k<0)?0:pSum[i-k]) == left) {
                System.out.println(i-k+1);
                break;
            }
        }

        for(int i=spmsa+ 2*k-1; i< n; i++) {
            if(pSum[i]-(pSum[i-k]) == right) {
                System.out.println(i-k+1);
                break;
            }
        }







        return null;
    }
}
