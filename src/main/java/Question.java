import java.util.Arrays;
import java.util.Random;

public class Question {
    private static final char[] symbol = {'+','-','×','÷'};
    private static final Random rd = new Random();
    /**
     * 生成随机数，并输出 X 或 X/X 或 X'X/X 格式的字符串
     * @param max 分子分母值的范围
     */
    public static String ranNum(int max){
        int nature = rd.nextInt(max);
        int denominator = rd.nextInt(max-1)+1;
        int numerator = rd.nextInt(denominator);
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

    /**
     * 生成随机表达式
     * @param max 分子分母值的范围
     * @return 表达式
     */
    public static String setQuestion(int max){
        //运算符个数
        int n = rd.nextInt(3)+1;
        //存储运算符
        char[] operator = new char[n];
        //各运算符权重
        char[] priority = new char[n];
        for (int i = 0; i < n; i++) {
            int j = rd.nextInt(4);
            operator[i] = symbol[j];
            if (operator[i] == '+' || operator[i] == '-') {
                priority[i] = '1';
            }
            if (operator[i] == '×' || operator[i] == '÷') {
                priority[i] = '2';
            }
        }
        String str = Arrays.toString(priority).trim();
        if (n == 1) {
            return ranNum(max)+" "+operator[0]+" "+ranNum(max)+" "+"=";
        } else if (n == 2) {
            if (str.equals("12") || str.equals("22")) {
                return ranNum(max)+" "+operator[0]+" "+ranNum(max)+" "+operator[1]+" "+ranNum(max)+" "+"=";
            }else {
                return ranNum(max)+" "+operator[0]+" "+"("+" "+ranNum(max)+" "+operator[1]+" "+ranNum(max)+" "+")"+"=";
            }
        } else {
            if (str.equals("212") || str.equals("211")) {
                return ranNum(max)+" "+operator[0]+" "+"("+" "+ranNum(max)+" "+operator[1]+" "+ranNum(max)+" "+")"
                        +" "+operator[2]+" "+ranNum(max)+" "+"=";
            } else if (str.equals("121") || str.equals("221")) {
                return ranNum(max)+" "+operator[0]+" "+ranNum(max)+" "+operator[1]+" "+"("+" "+ranNum(max)
                        +" "+operator[2]+" "+ranNum(max)+" "+")"+" "+"=";
            }else if(str.equals("122")){
                return "("+" "+ranNum(max)+" "+operator[0]+" "+ranNum(max)+" "+")"+" "+operator[1]+" "+ranNum(max)
                        +" "+operator[2]+" "+ranNum(max)+" "+"=";
            }else {
                return ranNum(max)+" "+operator[0]+" "+ranNum(max)+" "+operator[1]+" "+ranNum(max)
                        +" "+operator[2]+" "+ranNum(max)+" "+"=";
            }
        }
    }



    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            String str = setQuestion(10);
            System.out.println(str);
            System.out.println(Answer.rePolish(str));
        }
    }
}
