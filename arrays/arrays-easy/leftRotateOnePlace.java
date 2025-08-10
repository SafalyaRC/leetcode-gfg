class Solution{
  void leftShift(int nums[]){
    int n=nums.length;
    int temp=nums[i];
    for(int i=1;i<=n-1;i++){
      arr[i-1]=arr[i];
    }
    arr[n-1]=temp;
  }
}
