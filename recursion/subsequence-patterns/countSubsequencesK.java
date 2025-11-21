// count number of subsequences with given sum 'k'
// eg: nums=[1,2,1], o/p=2

// note: use this specific template for all similar 'count'-related problems

public class countSubsequencesK {
    public static void main(String[] args) {
        int nums[] = { 1, 2, 1 };
        System.out.println(subsequencesSumK(nums, 2));
    }

    public static int subsequencesSumK(int nums[], int k) {
        return helper(nums, k, 0, 0);
    }

    public static int helper(int nums[], int k, int sum, int i) {
        // base cases:
        if (i == nums.length) {
            if (sum == k)
                return 1;
            else
                return 0;
        }
        // choice-1: choose nums[i]:
        int left = helper(nums, k, sum, i + 1);

        // choice-2: dont choose nums[i]:
        int right = helper(nums, k, sum + nums[i], i + 1);

        return left + right;
    }
}
