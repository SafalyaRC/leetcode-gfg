// LC-860: https://leetcode.com/problems/lemonade-change/

/*
  we are using a greedy strategy here to track the bills and return the change only in the bills we have inhand
  - track the five and ten bills for efficient change return
  case-1: if we get a 5 bill, just accept it as every lemonade is 5 each
  case-2: if we get a 10 bill, we must give a 5 bill in return (lemonade is 5 each)
  case-3: if we get a 20 bill, we must return 15 (20-lemonade of 5$):
   3.1: one 10 and one 5 bill
   3.2: three 5 bills
note: we only track 5 and 10 bills and no 20$ bills, as 20 is never returned as a change, we either return 5$ or 15$ as shown above
*/

public class lemonadeChanges {
    public boolean lemonadeChange(int[] bills) {
        int fives = 0, tens = 0;
        for (int bill : bills) {
            if (bill == 5)    // case-1
                fives++; 
            else if (bill == 10) { // case-2
                if (fives == 0)
                    return false;
                fives--;
                tens++;
            } else { // case-3
                if (tens > 0 && fives > 0) { // 3.1
                    tens--;
                    fives--;
                } else if (fives >= 3) { // 3.2
                    fives -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
