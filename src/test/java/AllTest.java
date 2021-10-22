import org.junit.jupiter.api.Test;
import zcy.Utils.CheckUtil;
import zcy.Utils.QuestionUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class AllTest {
    @Test
    public void setExpressionTest(){
        for (int i = 0; i < 10; i++) {
            System.out.println(QuestionUtil.setExpression(10));
        }
    }

    @Test
    public void rePolishTest(){
        for (int i = 0; i < 10; i++) {
            String str = QuestionUtil.setExpression(10);
            System.out.println("题目-->"+str);
            System.out.println("逆波兰式-->"+QuestionUtil.rePolish(str));
        }
    }

    @Test
    public void calculateTest(){
        for (int i = 0; i < 10; i++) {
            String str = QuestionUtil.setExpression(10);
            System.out.println("题目-->"+str);
            System.out.println("答案-->"+ QuestionUtil.calculate(QuestionUtil.rePolish(str)));
        }
    }

    @Test
    public void examineTest(){
        String[] s1 = {"1","2","-","3","+"};
        String[] s2 = {"3","1","2","-","+"};
        ArrayList<String> list1 = new ArrayList<>(Arrays.asList(s1));
        ArrayList<String> list2 = new ArrayList<>(Arrays.asList(s2));
        System.out.println(list1);
        System.out.println(list2);
        System.out.println(CheckUtil.examine(list1,list2));
        String[] s3 = {"1","2","÷","3","×"};
        String[] s4 = {"3","1","2","÷","×"};
        ArrayList<String> list3 = new ArrayList<>(Arrays.asList(s3));
        ArrayList<String> list4 = new ArrayList<>(Arrays.asList(s4));
        System.out.println(list3);
        System.out.println(list4);
        System.out.println(CheckUtil.examine(list3,list4));
        String[] s5 = {"1","2","÷","3","×"};
        ArrayList<String> list5 = new ArrayList<>(Arrays.asList(s5));
        ArrayList<String> list6 = new ArrayList<>(Arrays.asList(s5));
        System.out.println(list5);
        System.out.println(list6);
        System.out.println(CheckUtil.examine(list5,list6));
    }

}
