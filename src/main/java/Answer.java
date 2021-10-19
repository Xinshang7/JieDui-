import java.util.*;

public class Answer {
    private static char[] symbol = {'+','-','×','÷','(',')'};
    /**
     * 将表达式转换为逆波兰式
     * @param num 表达式
     * @return 逆波兰式
     */
    public static String rePolish(String num){
        Stack<Character> s1 = new Stack<>();
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
            for (char c : symbol) {
                if (num.charAt(i) != c) {
                    n++;
                } else {
                    break;
                }
            }
            if (n == symbol.length) {
                s2.push(num.charAt(i));
            } else if (s1.empty()) {
                s1.push(num.charAt(i));
            } else if (!s1.empty()){
                if(priority.get(s1.peek())<priority.get(num.charAt(i))){
                    s1.push(num.charAt(i));
                } else if (num.charAt(i) == '('){
                    s1.push(num.charAt(i));
                } else if (num.charAt(i) == ')'){
                    while (s1.peek()!='('){
                        s2.push(s1.pop());
                    }
                    s1.pop();
                } else if (priority.get(s1.peek())>=priority.get(num.charAt(i))){
                    s2.push(s1.pop());
                    s1.push(num.charAt(i));
                }
            }
            n=0;
        }
        while (!s1.empty()){
            s2.push(' ');
            s2.push(s1.pop());
        }
        StringBuffer sb = new StringBuffer();
        while (!s2.empty()){
            sb.append(s2.pop());
        }
        return String.valueOf(sb.reverse()+" ");
    }

    public static String calculate(String repolish){
        Stack<String> s = new Stack<>();
        StringBuffer sb = new StringBuffer();
        sb.append(repolish);
        while (sb.length()!=0) {


            if(sb.charAt(0) == ' ') {
                sb.delete(0,1);
                continue;
            }



            if (sb.substring(0, sb.indexOf(" ")).equals("+") || sb.substring(0, sb.indexOf(" ")).equals("-")
                    || sb.substring(0, sb.indexOf(" ")).equals("×") || sb.substring(0, sb.indexOf(" ")).equals("÷")){
                String num2 = s.pop();
                String num1 = s.pop();



                s.push(CountUtil.countNum(num1,num2,sb.substring(0,sb.indexOf(" "))));
            }else {
                s.push(sb.substring(0,sb.indexOf(" ")));
            }
            sb.delete(0,sb.indexOf(" "));
        }
        return s.pop();
    }

}
