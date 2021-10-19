public class CountUtil {
    public static double countNum(String num1,String num2,String sympol){

    }

    /**
     * 求最大公因数
     * @param a
     * @param b
     * @return 最大公因数
     */
    public static int divisor(int a,int b){
        int temp = 0;
        if(a<b){
            temp=a;
            a=b;
            b=temp;
        }
        while (b != 0) {
            temp=a%b;
            a=b;
            b=temp;
        }
        return a;
    }
}
