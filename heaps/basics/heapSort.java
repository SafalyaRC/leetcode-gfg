// LC-912: https://leetcode.com/problems/sort-an-array/description/

public class heapSort {
    public int[] sortArray(int[] nums) {
        int n=nums.length;

        // step-1: build the max heap first
        for(int i=(n/2)-1;i>=0;i--){   // bottom-up approach starting from the internal nodes (last internal node at  (n/2)-1 th index)
            heapify(nums,i,n);
        }

        // step-2: since it's a max heap, the first element is always maximum, hence in order to get a sorted array, we will make sure that the max element is swapped to the end at every iteration in order to get a sorted array of nums
        for(int end=n-1;end>0;end--){

            swap(nums,0,end);  // put the last element to end

            // after putting the max element in the end, the heap property gets broken hence we need to heapify the array except the part till which it was swapped then repeat the previous step and continue until we get a sorted array nums:
            heapify(nums,0,end); // heapify from 0->end since after swapping, we keep updating the new limit of heapify() i.e. till before the last swapped portion
        }
        return nums;
    }

    public void heapify(int nums[], int i, int size){
        while(true){
            int largest=i;
            int left=2*i+1;
            int right=2*i+2;

            if(left<size && nums[left]>nums[largest]) largest=left;
            if(right<size && nums[right]>nums[largest]) largest=right;

            if(i==largest) break; // heap property is kept
            swap(nums,i,largest);
            i=largest;
        }
    }

    public void swap(int nums[], int a, int b){
        int temp=nums[a];
        nums[a]=nums[b];
        nums[b]=temp;
    }
}
