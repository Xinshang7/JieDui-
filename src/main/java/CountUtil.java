public class CountUtil {
    public static String countNum(String num1,String num2,String sympol){
        String n1 = name1(num1);
        String n2 = name1(num2);
        int n1_numerator = Integer.parseInt(n1.substring(0,n1.indexOf("/")));
        int n1_denominator = Integer.parseInt(n1.substring(n1.indexOf("/")+1));
        int n2_numerator = Integer.parseInt(n2.substring(0,n2.indexOf("/")));
        int n2_denominator = Integer.parseInt(n2.substring(n2.indexOf("/")+1));
        int answer_denominator = 0;
        int answer_numerator = 0;
        switch (sympol){
            case "+":{
                answer_denominator = n1_denominator*n2_denominator;
                answer_numerator=n1_numerator*n2_denominator+n2_numerator*n1_denominator;
                break;
            }
            case "-":{
                answer_denominator = n1_denominator*n2_denominator;
                answer_numerator = n1_numerator*n2_denominator-n2_numerator*n1_denominator;
                break;
            }
            case "÷":{
                answer_numerator = n1_numerator*n2_denominator;
                answer_denominator = n1_denominator*n2_numerator;
                break;
            }
            case "×":{
                answer_numerator = n1_numerator*n2_numerator;
                answer_denominator = n1_denominator*n2_denominator;
                break;
            }
        }
        return name2(
                answer_numerator/answer_denominator+"'"+answer_numerator%answer_denominator+"/"+answer_denominator
        );

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

    /**
     * 转换成分数
     * @param num
     * @return
     */
    public static String name1(String num){
        if (!num.contains("/") && !num.contains("'")){
            return num+"/"+"1";
        }else if (num.contains("/") && num.contains("'")){
            int nature = Integer.parseInt(num.substring(0,num.indexOf("'")));
            int denominator = Integer.parseInt(num.substring(num.indexOf("/")+1));
            int numerator = Integer.parseInt(num.substring(num.indexOf("'")+1,num.indexOf("/")));
            int new_numerator = nature*denominator+numerator;
            return new_numerator+"/"+denominator;
        }else {
            return num;
        }
    }

    /**
     * 将真分数形式转换为整数或分数
     * @param num
     * @return
     */
    public static String name2(String num){
        int nature = Integer.parseInt(num.substring(0,num.indexOf("'")));
        int denominator = Integer.parseInt(num.substring(num.indexOf("/")+1));
        int numerator = Integer.parseInt(num.substring(num.indexOf("'")+1,num.indexOf("/")));
        if (nature == 0) {
            return numerator+"/"+denominator;
        } else if (denominator == 1 || numerator == 0) {
            return String.valueOf(nature);
        }else {
            int d = CountUtil.divisor(numerator,denominator);
            numerator /= d;
            denominator /= d;
            return nature+"'"+numerator+"/"+denominator;
        }
    }
}
