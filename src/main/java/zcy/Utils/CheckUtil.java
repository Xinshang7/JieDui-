package zcy.Utils;
import java.util.*;

public class CheckUtil {
    // 字符串变集合
    public static ArrayList<String> stringToList (String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        sb.append(str);
        while (sb.length() > 0) {
            if(sb.charAt(0) == ' ') {
                sb.delete(0,1);
                continue;
            }
            if ( isOperator(String.valueOf(sb.charAt(0))) ) {
                arrayList.add(String.valueOf(sb.charAt(0)));
            }else {
                arrayList.add(sb.substring(0,sb.indexOf(" ")));
            }
            sb.delete(0,sb.indexOf(" "));
        }
        return arrayList;
    }

    // 判断一个字符是否为运算符
    public static boolean isOperator(String str) {
        for(int i = 0; i < 4 ;i++) {
            if (str.charAt(0) == QuestionUtil.symbol[i]) {
                return true;
            }
        }

        return false;
    }

    // 两条一个运算符的式子是否相同
    public static boolean isExchange(ArrayList<String> arrayList_1, int o_1, ArrayList<String> arrayList_2, int o_2) {
        // 运算符是否相等
        if (!arrayList_1.get(o_1).equals(arrayList_2.get(o_2))) {
            return false;
        }
        // 运算符是否为除号或减号
        if (arrayList_1.get(o_1).equals("÷") || arrayList_1.get(o_1).equals("-")) {
            if (arrayList_1.get(o_1-2).equals(arrayList_2.get(o_2-2)) && arrayList_1.get(o_1-1).equals(arrayList_2.get(o_2-1))) {
                return true;
            }
            else {
                return false;
            }
        }
        // 运算符为加号或乘号
        else {
            if (
                arrayList_1.get(o_1-2).equals(arrayList_2.get(o_2-2)) && arrayList_1.get(o_1-1).equals(arrayList_2.get(o_2-1))
                || arrayList_1.get(o_1-1).equals(arrayList_2.get(o_2-2)) && arrayList_1.get(o_1-2).equals(arrayList_2.get(o_2-1))
            ) {
                return true;
            }
            else return false;
        }
    }

    // 判断两个整条式子是否重复
    public static boolean examine(ArrayList<String> arrayList_1, ArrayList<String> arrayList_2) {
        // 判断两个
        if (arrayList_1.size() != arrayList_2.size()) {
            return false;
        }

        int operatorIndex_1 = isOperator(arrayList_1.get(2)) ? 2 : 3;
        int operatorIndex_2 = isOperator(arrayList_2.get(2)) ? 2 : 3;
        int addIndex_1 = operatorIndex_1-2;
        int addIndex_2 = operatorIndex_2-2;

        // 三个运算符+两个括号的情况
        if ( arrayList_1.size() == 7 && isOperator(arrayList_1.get(2)) && isOperator(arrayList_1.get(5)) && isOperator(arrayList_2.get(2)) && isOperator(arrayList_2.get(5)) ) {
                if (arrayList_1.get(6).equals(arrayList_2.get(6))) {
                    if ( isExchange(arrayList_1, 2, arrayList_2, 2) ) {
                        if ( isExchange(arrayList_1, 5, arrayList_2, 5 )) {
                            return true;
                        }
                        else return false;
                    }
                    else if ( isExchange(arrayList_1, 2, arrayList_2, 5) ) {
                        if ( isExchange(arrayList_1, 5, arrayList_2, 2 )) {
                            return true;
                        }
                        else return false;
                    }
                    else return false;
                }
                else return false;
        }
        else {
            while (arrayList_1.size() > 1) {
                if ( !isExchange(arrayList_1, operatorIndex_1, arrayList_2, operatorIndex_2) ) {
                    return false;
                }
                else {
                    for (int i=0; i<3; i++) {
                        arrayList_1.remove(operatorIndex_1-2);
                        arrayList_2.remove(operatorIndex_2-2);
                    }
                    arrayList_1.add(addIndex_1, "A" );
                    arrayList_2.add(addIndex_2, "A" );
                    operatorIndex_1 = 2;
                    operatorIndex_2 = 2;
                    addIndex_1 = addIndex_2 = 0;
                }
            }
        }
        return true;
    }
}
