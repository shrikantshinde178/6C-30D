// Problem Statement:
// An image smoother is a filter of the size 3 x 3 that can be applied to each cell of an image by rounding down the average of the cell and the eight surrounding cells (i.e., the average of the nine cells in the blue smoother). If one or more of the surrounding cells of a cell is not present, we do not consider it in the average (i.e., the average of the four cells in the red smoother).

// Given an m x n integer matrix img representing the grayscale of an image, return the image after applying the smoother on each cell of it.

// Example 1:

// Input: img = [[1,1,1],[1,0,1],[1,1,1]]
// Output: [[0,0,0],[0,0,0],[0,0,0]]
// Explanation:
// For the points (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0
// For the points (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0
// For the point (1,1): floor(8/9) = floor(0.88888889) = 0

// Example 2:

// Input: img = [[100,200,100],[200,50,200],[100,200,100]]
// Output: [[137,141,137],[141,138,141],[137,141,137]]
// Explanation:
// For the points (0,0), (0,2), (2,0), (2,2): floor((100+200+200+50)/4) = floor(137.5) = 137
// For the points (0,1), (1,0), (1,2), (2,1): floor((200+200+50+200+100+100)/6) = floor(141.666667) = 141
// For the point (1,1): floor((50+200+200+200+200+100+100+100+100)/9) = floor(138.888889) = 138
 
// Constraints:

// m == img.length
// n == img[i].length
// 1 <= m, n <= 200
// 0 <= img[i][j] <= 255

// Solution:

class ImageSmoother {
    // Method to perform image smoothing
    public int[][] imageSmoother(int[][] img) {

        // Create a new array to store the smoothed image
        int[][] result = new int[img.length][img[0].length];

        // Iterate through each pixel in the input image
        for (int i = 0; i < img.length; i++) {
            for (int j = 0; j < img[i].length; j++) {

                // Variables to store the sum of pixel values in neighboring cells
                int top = 0, bottom = 0, left = 0, right = 0, count = 0;

                // Calculate sum and count for top neighbors
                if (i > 0) {                    
                    top = img[i - 1][j];
                    count++;
                    if (j < img[i].length - 1) {
                        top += img[i - 1][j + 1];
                        count++;
                    }
                    if (j > 0) {
                        top += img[i - 1][j - 1];
                        count++;
                    }
                }

                // Calculate sum and count for left neighbor
                if (j > 0) {
                    left = img[i][j - 1];
                    count++;
                }

                // Calculate sum and count for right neighbor
                if (j < img[i].length - 1) {
                    right = img[i][j + 1];
                    count++;
                }

                // Calculate sum and count for bottom neighbors
                if (i < img.length - 1) {
                    bottom = img[i + 1][j];
                    count++;
                    if (j < img[i].length - 1) {
                        count++;
                        bottom += img[i + 1][j + 1];
                    }
                    if (j > 0) {
                        bottom += img[i + 1][j - 1];
                        count++;
                    }
                }

                // Calculate the average pixel value and update the result array
                int sum = img[i][j] + top + bottom + left + right;
                int average = (int) Math.floor(sum / (count + 1));
                result[i][j] = average;
            }
        }

        // Return the smoothed image
        return result;
    }
}
