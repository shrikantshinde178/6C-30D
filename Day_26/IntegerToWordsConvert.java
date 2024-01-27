// Problem Statement:
// Convert a non-negative integer num to its English words representation.

// Example 1:

// Input: num = 123
// Output: "One Hundred Twenty Three"
// Example 2:

// Input: num = 12345
// Output: "Twelve Thousand Three Hundred Forty Five"
// Example 3:

// Input: num = 1234567
// Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 
// Constraints:

// 0 <= num <= 231 - 1

// Hint 1
// Did you see a pattern in dividing the number into chunk of words? For example, 123 and 123000.
// Hint 2
// Group the number by thousands (3 digits). You can write a helper function that takes a number less than 1000 and convert just that chunk to words.
// Hint 3
// There are many edge cases. What are some good test cases? Does your code work with input such as 0? Or 1000010? (middle chunk is zero and should not be printed out)

// Solution:

class NumberToStringConvert {
    public static String [] ones= {"","One","Two","Three","Four","Five","Six","Seven","Eight","Nine"};
    public static String [] teens= {"Ten","Eleven","Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen"};
    public static String [] tens= {"","","Twenty","Thirty","Forty","Fifty","Sixty","Seventy","Eighty","Ninety"};
    public static String [] magnitude = {"","Thousand ","Million ","Billion ","Quadrillion ","Quintillion "};
    public String numberToWords(int num) 
    {
        //Magnitude of number
        int mag=0;
        //Initialize English Representation
        String engRep= "";
        //Edge Case of Zero
        if(num==0)
            return "Zero";
        //Iterate through taking each magnitude level and sending it off to helper method
        while(num>0)
        {
            if(num%1000!=0)
                engRep= subdivisionEng(num%1000)+" "+magnitude[mag]+engRep;
            mag++;
            num=num/1000;
        }
        return engRep.trim();
    }
    public String subdivisionEng(int sub)
    {
        String eng= "";
        int doubledig= sub%100;
        //If its in the teens dont go to the tens+ones method
        if(doubledig>9&&doubledig<20)
            eng = teens[doubledig%10];
        //Tens, Ones
        else
            eng= tens[doubledig/10]+" "+ones[doubledig%10];
        eng=eng.trim();
        //Hundreds
        if(sub>99)
            eng= ones[sub/100]+" Hundred "+eng;
        return eng.trim();
    }
}
