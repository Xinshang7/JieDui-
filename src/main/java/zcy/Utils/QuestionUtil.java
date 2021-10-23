package zcy.Utils;
import java.util.*;

public class QuestionUtil {
    static final char[] symbol = {'+','-','×','÷','(',')'};
    private static final Random rd = new Random();
    /**
     * 生成随机数，并输出 X 或 X/X 或 X'X/X 格式的字符串
     * @param max 分子分母值的范围
     */
    public static String ranNum(int max){
        int nature = rd.nextInt(max);
        int denominator = rd.nextInt(max-1)+1;
        int numerator = rd.nextInt(denominator);
        return CountUtil.simplify(nature+"'"+numerator+"/"+denominator);
    }

    /**
     * 生成随机表达式
     * @param max 分子分母值的范围
     * @return 表达式
     */
    public static String setExpression(int max){
        int n = rd.nextInt(3)+1; // 多少个运算符
        StringBuffer sb = new StringBuffer();
        boolean bracket = false; // 判断添加左括号还是右括号
        int k = 0; // 添加左括号的下标
        for (int i=0; i<=n; i++) {
            // 式子开始没空格
            if (i != 0) {
                sb.append(" ");
            }
            // 生成左括号
            if (rd.nextDouble() < 0.5 && !bracket && i != n) {
                sb.append("( ");
                bracket = true;
                k=i;
            }
            // 生成随机数
            sb.append(ranNum(max));
            // 生成右括号
            if (bracket && k != i) {
                if (i != n) {
                    if ( rd.nextDouble() < 0.5) {
                        sb.append(" )");
                        bracket = false;
                    }
                } else {
                    if (k == 0) {
                        // 去除 ( 1 + 2 + 3 ) = 这种给整个式子添加括号的情况
                        sb.delete(0, 2);
                    }
                    else {
                        sb.append(" )");
                    }
                }
            }
            // 生成运算符
            if (i != n) {
                sb.append(" "+symbol[rd.nextInt(4)]);
            }
        }
        sb.append(" =");
        return sb.toString();
    }

    /**
     * 将表达式转换为逆波兰式
     * @param num 表达式
     * @return 逆波兰式
     */
    public static ArrayList<String> rePolish(String num){
        ArrayList<String> repolish;
        //暂时存储运算符的栈
        Stack<Character> s1 = new Stack<>();
        //存储逆波兰式的栈
        Stack<Character> s2 = new Stack<>();
        //设置符号优先级
        Map<Character,Integer> priority = new HashMap<>();
        priority.put('+',1);
        priority.put('-',1);
        priority.put('×',2);
        priority.put('÷',2);
        priority.put('(',0);
        priority.put(')',0);
        int n = 0;
        for (int i = 0; num.charAt(i) != '='; i++) {
            //判断该字符是否为运算符
            for (char c : symbol) {
                if (num.charAt(i) != c) {
                    n++;
                } else {
                    break;
                }
            }
            if (n == symbol.length) {
                //不是运算符则压入s2
                s2.push(num.charAt(i));
            } else if (s1.empty()) {
                //是运算符则压入s1
                s1.push(num.charAt(i));
            } else if (!s1.empty()){
                if(priority.get(s1.peek())<priority.get(num.charAt(i))){
                    //若取出的运算符优先级小于s1栈顶运算符，则运算符直接压入s1
                    s1.push(num.charAt(i));
                } else if (num.charAt(i) == '('){
                    //若为 ( 则直接压入s1
                    s1.push(num.charAt(i));
                } else if (num.charAt(i) == ')'){
                    //若为 ) 则将s1中 ( 之前的运算符全部压入s2
                    while (s1.peek()!='('){
                        s2.push(s1.pop());
                    }
                    s1.pop();
                } else if (priority.get(s1.peek())>=priority.get(num.charAt(i))){
                    //若取出的运算符优先级大于s1栈顶运算符，则运算符直接压入s1，则将s1栈顶运算符压入s2，然后将取出的运算符压入s1
                    s2.push(s1.pop());
                    s1.push(num.charAt(i));
                }
            }
            n=0;
        }
        //将s1剩余运算符压入s2
        while (!s1.empty()){
            s2.push(' ');
            s2.push(s1.pop());
        }
        StringBuffer sb = new StringBuffer();
        while (!s2.empty()){
            sb.append(s2.pop());
        }
        //将逆波兰式转换成ArrayList形式存储并返回
        repolish = CheckUtil.stringToList(sb.reverse()+" ");
        return repolish;
    }

    /**
     * 通过逆波兰式计算答案
     * @param repolish 逆波兰式
     * @return 答案
     */
    public static String calculate(List<String> repolish){
        Stack<String> s = new Stack<>();
        for (String value : repolish) {
            //若取出运算符则将栈顶两个算数出栈进行相应的计算，将结果重新压入栈
            if (value.equals("+") || value.equals("-")
                    || value.equals("×") || value.equals("÷")) {
                String num2 = s.pop();
                String num1 = s.pop();
                String str = CountUtil.countNum(num1, num2, value);
                if (str.equals("Error")) {
                    return "Error";
                } else {
                    s.push(str);
                }
            } else {
                //若取出算数则直接压入栈
                s.push(value);
            }
        }
        //栈中最后剩余的数则是逆波兰式的结果
        return s.pop();
    }

}
