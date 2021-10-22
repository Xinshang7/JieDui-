package zcy.Utils;

public class CountUtil {
    /**
     * 四则运算
     * @param num1 计算数
     * @param num2 计算数
     * @param sympol 运算符号
     * @return 结果
     */
    public static String countNum(String num1,String num2,String sympol){
        //将算数转换为分数形式，然后提取分子和分母
        String n1 = changeFraction(num1);
        String n2 = changeFraction(num2);
        int n1_numerator = Integer.parseInt(n1.substring(0,n1.indexOf("/")));
        int n1_denominator = Integer.parseInt(n1.substring(n1.indexOf("/")+1));
        int n2_numerator = Integer.parseInt(n2.substring(0,n2.indexOf("/")));
        int n2_denominator = Integer.parseInt(n2.substring(n2.indexOf("/")+1));
        int answer_denominator = 0;
        int answer_numerator = 0;
        //对两个数进行分数运算
        switch (sympol){
            case "+":{
                answer_denominator = n1_denominator*n2_denominator;
                answer_numerator=n1_numerator*n2_denominator+n2_numerator*n1_denominator;
                break;
            }
            case "-":{
                answer_denominator = n1_denominator*n2_denominator;
                answer_numerator = n1_numerator*n2_denominator-n2_numerator*n1_denominator;
                if (answer_numerator < 0) {
                    return "Error";
                }
                break;
            }
            case "÷":{
                if (n2_numerator == 0) {
                    return "Error";
                }
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
        //将结果化简为带分数
        return simplify(
                answer_numerator/answer_denominator+"'"+answer_numerator%answer_denominator+"/"+answer_denominator
        );

    }

    /**
     * 求最大公因数
     * @param a 约数
     * @param b 约数
     * @return 最大公因数
     */
    public static int divisor(int a,int b){
        int temp;
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
     * 转换成真分数
     * @param num 带分数
     * @return 真分数
     */
    public static String changeFraction(String num){
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
     * 将带分数形式化简为整数或真分数
     * @param num 带分数
     * @return 化简结果
     */
    public static String simplify(String num){
        int nature = Integer.parseInt(num.substring(0,num.indexOf("'")));
        int denominator = Integer.parseInt(num.substring(num.indexOf("/")+1));
        int numerator = Integer.parseInt(num.substring(num.indexOf("'")+1,num.indexOf("/")));
        if (nature == 0 && numerator == 0){
            return "1";
        } else if (nature == 0) {
            int d = CountUtil.divisor(numerator,denominator);
            numerator /= d;
            denominator /= d;
            return numerator+"/"+denominator;
        } else if (denominator == 1 || numerator == 0) {
            return String.valueOf(nature);
        } else {
            int d = CountUtil.divisor(numerator,denominator);
            numerator /= d;
            denominator /= d;
            return nature+"'"+numerator+"/"+denominator;
        }
    }
}
