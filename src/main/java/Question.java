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
        return CountUtil.name2(nature+"'"+numerator+"/"+denominator);
    }

    /**
     * 生成随机表达式
     * @param max 分子分母值的范围
     * @return 表达式
     */
    public static String setQuestion(int max){
        /*//运算符个数
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
        }*/
        int n = rd.nextInt(3)+1;
        StringBuffer sb = new StringBuffer();

        boolean bracket = false;
        int k = 0;
        for (int i=0; i<=n; i++) {
            if (i != 0) {
                sb.append(" ");
            }

            if (rd.nextDouble() < 0.5 && bracket == false && i != n) {
                sb.append("(");
                bracket = true;
                k=i;
            }

            sb.append(ranNum(max));

            if (i != n && rd.nextDouble() < 0.5 && bracket == true && k != i) {
                sb.append(")");
                bracket = false;
            }

            if (i == n && bracket == true  && k != i) {
                if (k == 0) {
                    sb = sb.deleteCharAt(0);
                }
                else {
                    sb.append(")");
                }
            }

            sb.append(" ");
            if (i != n) {
                sb.append(symbol[rd.nextInt(4)]);
            }
        }
        sb.append("=");
        return sb.toString();
    }



    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            String str = setQuestion(10);
            System.out.println(str);
            System.out.println(Answer.rePolish(str));
            System.out.println(Answer.calculate(Answer.rePolish(str)));
        }

    }
}
