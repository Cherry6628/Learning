// class Solution {
//     int compute(int[][] matrix, int r, int c){

//         // if (matrix[r][c]==1){
//         //     return 0;
//         // } else if ((r==0) || (c==0)){
//         //     return 1;

        
//         if ((r==0) && (c==0)){
//             return 1;
//         } else if ( (r<0) || (c<0) || (matrix[r][c]==1) ){
//             return 0;
//         } else {
//             return compute(matrix, r-1, c) + compute(matrix, r, c-1);
//         }
//     }

//     public int uniquePathsWithObstacles(int[][] obstacleGrid) {
//         // Solution.obs = (obstacleGrid[0][0]==1)? 0:1;
//         int rows = obstacleGrid.length;
//         int cols = obstacleGrid[0].length;
//         int first = obstacleGrid[0][0];
//         int last = obstacleGrid[rows-1][cols-1];
//         if (first==1 || last==1){
//             return 0;
//         } else if ((rows==1 && cols==1)){
//             if (first==0){
//                 return 1;
//             }
//             return 0;
//         } else if (rows==1 && cols==2){
//             if ((obstacleGrid[0][0]==obstacleGrid[0][1]) && (obstacleGrid[0][0]==0)){
//                 return 1;
//             }
//             return 0;
//         } else if (rows==2 && cols==1){
//             if ((obstacleGrid[0][0]==obstacleGrid[1][0]) && (obstacleGrid[0][0]==0)){
//                 return 1;
//             }
//             return 0;
//         } 
//         Solution s = new Solution();
//         return s.compute(obstacleGrid, rows-1, cols-1);
//     }
// }


// class Solution {
//     int compute(int[][] matrix, int r, int c){
//         int[][] mat = new int[r][c];
//         for (int i=0; i<r; i++){
//             for (int j=0; j<c; j++){
//                 if (i==0 && j==0){
//                     mat[i][j]=1;
//                 } else if (matrix[i][j] == 1){
//                     mat[i][j]=0;
//                 } else if (i==0){
//                     mat[0][j] = mat[0][j-1];
//                 } else if (j==0){
//                     mat[i][0] = mat[i-1][0];
//                 } else{
//                     mat[i][j] = mat[i-1][j]+mat[i][j-1];
//                 }
//             }
//         }
//         return mat[r-1][c-1];
//     }

//     public int uniquePathsWithObstacles(int[][] obstacleGrid) {
//         // Solution.obs = (obstacleGrid[0][0]==1)? 0:1;
//         int rows = obstacleGrid.length;
//         int cols = obstacleGrid[0].length;
//         int first = obstacleGrid[0][0];
//         int last = obstacleGrid[rows-1][cols-1];
//         if (first==1 || last==1){
//             return 0;
//         } else if ((rows==1 && cols==1)){
//             if (first==0){
//                 return 1;
//             }
//             return 0;
//         } else if (rows==1 && cols==2){
//             if ((obstacleGrid[0][0]==obstacleGrid[0][1]) && (obstacleGrid[0][0]==0)){
//                 return 1;
//             }
//             return 0;
//         } else if (rows==2 && cols==1){
//             if ((obstacleGrid[0][0]==obstacleGrid[1][0]) && (obstacleGrid[0][0]==0)){
//                 return 1;
//             }
//             return 0;
//         } 
//         Solution s = new Solution();
//         return s.compute(obstacleGrid, rows, cols);
//     }
// }

class Solution {
    public int compute(int[][] matrix, int r, int c){
        for (int i=0; i<r; i++){
            for (int j=0; j<c; j++){
                if (i==0 && j==0){
                    matrix[0][0]=1;
                } else if (matrix[i][j] == 1){
                    matrix[i][j]=0;
                } else if (i==0){
                    matrix[0][j] = matrix[0][j-1];
                } else if (j==0){
                    matrix[i][0] = matrix[i-1][0];
                } else{
                    matrix[i][j] = matrix[i-1][j]+matrix[i][j-1];
                }
            }
        }
        return matrix[r-1][c-1];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int rows = obstacleGrid.length;
        int cols = obstacleGrid[0].length;
        if (obstacleGrid[0][0]==1 || obstacleGrid[rows-1][cols-1]==1){
            return 0;
        }
        return compute(obstacleGrid, rows, cols);
    }
}