// GFG: https://www.geeksforgeeks.org/problems/fractional-knapsack-1587115620/1

// example to use:  val[]={100,60,100,200}  wt[]={20,10,50,50}  capacity=90  -> o/p=380

/* in order to greedily finding the solution, instead of sorting by weights or by values, we sort
them by each unit ratio of val/wt in descending order to pick appropriate items orderly for 
maximized result, since taking the largest value first isnt an option either (eg: val[]={200,100} wt[]={10,6} cap=10) */


import java.util.Arrays;
public class fractionalKnapsack {
    class Item {
        int value, weight;
        double unitRatio; // per unit value/weight ratio

        public Item(int value, int weight) {
            this.value = value;
            this.weight = weight;
            unitRatio = (double) value / weight; // dont use (double)(val/wt) as this gives error
        }
    }

    public double KS(int[] val, int[] wt, int capacity) {
        int n = val.length;
        Item items[] = new Item[n]; // array of objects of class Item that will have val,wt and unit ratio

        // build the items:
        for (int i = 0; i < n; i++) {
            items[i] = new Item(val[i], wt[i]);
        }

        // sort the unit ratios in descending order
        Arrays.sort(items, (a, b) -> Double.compare(b.unitRatio, a.unitRatio));

        double maxValue = 0.0;

        // pick items greedily from the items object array
        for (Item it : items) {
            if (capacity == 0)
                break; // we stop when no capacity is left (is full)

            if (it.weight <= capacity) {
                maxValue += it.value;
                capacity -= it.weight;
            } else { // when we have to take the fractional part
                maxValue += it.unitRatio * capacity;
                capacity = 0; // after taking the fractional, all the capacity will be acquired
            }
        }

        return maxValue;

    }
}
