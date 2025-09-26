// give a matrix return the index of celebrity that is known by everyone but himself knows no one
// tc: [[0,1,1,0],[0,0,0,0],[0,1,0,0],[1,1,0,0]]   o/p: 1(index of celebrity)
// note: each element knows itself i.e., when i=j : mat[i][j]=1

// brute-force:
class Solution {
    public int celebrity(int mat[][]) {
        // code here
        int n = mat.length;
        int knowMe[] = new int[n];
        int iKnow[] = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && mat[i][j] == 1) {
                    knowMe[j]++;
                    iKnow[i]++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (knowMe[i] == n - 1 && iKnow[i] == 0)
                return i;
        }
        return -1;
    }
}

// optimal:
public class celebrityProblem {
    public int celebrity(int mat[][]) {
        int n = mat.length;
        int top = 0, down = n - 1;
        while (top < down) {
            if (top != down && mat[top][down] == 1) {
                top++;
            } else if (top != down && mat[down][top] == 1) {
                down--;
            } else { // no one knows either
                top++;
                down--;
            }
        }
        if (top > down)
            return -1; // no celeb found when both cross each other

        // verify if actually celeb or not:
        int cand = top;
        for (int i = 0; i < n; i++) {
            if ((cand != i) && (mat[cand][i] == 1 || mat[i][cand] == 0))
                return -1;
        }
        return cand;
    }
}
