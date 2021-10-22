package zcy;

import zcy.Utils.FileUtil;
import zcy.Utils.QuestionUtil;
import zcy.Utils.CheckUtil;
import java.util.ArrayList;


public class Main {
    private static boolean flag = false;
    //已生成的题库
    private static ArrayList<Question> question = new ArrayList<>();
    //各数值的最大整数
    private static int maxNum = 10;
    //生成算式的数量
    private static int count = 10;
    //题目路径
    private static String exercises_path = "./Exercises.txt";
    //答案路径
    private static String answer_path = "./Answers.txt";
    //正确率路径
    private static String grade_path = "./Grade.txt";

    /**
     * 主程序
     * @param args 参数
     */
    public static void start(String[] args){
        boolean flag_n = false;
        boolean flag_r = false;
        boolean flag_e = false;
        boolean flag_a = false;
        try {
            for (int i = 0; i < args.length; i++) {
                if ("-n".equals(args[i])) {
                    count = Integer.parseInt(args[i+1]);
                    flag_n = true;
                } else if ("-r".equals(args[i])) {
                    maxNum = Integer.parseInt(args[i+1]);
                    flag_r = true;
                } else if ("-e".equals(args[i])) {
                    exercises_path = args[i+1];
                    flag_e = true;
                } else if ("-a".equals(args[i])) {
                    answer_path = args[i+1];
                    flag_a = true;
                }
            }
            if (flag_n && flag_r) {
                cwQuestion(count,maxNum);
            } else if (flag_a && flag_e) {
                getGrade(exercises_path,answer_path);
            } else {
                throw new Exception();
            }
        } catch (Exception e){
            System.out.println("格式错误！");
            System.out.println("Example:");
            System.out.println("Myapp.exe -n 10 -r 10");
            System.out.println("Myapp.exe -e <exercisefile>.txt -a <answerfile>.txt");
        }

    }

    /**
     * 生成题库
     * @param count 题目数量
     * @param maxNum 数值范围
     */
    public static void setQuestion(int count,int maxNum) {
        Question q;
        for (int i = 0; i < count; i++) {
            q = new Question(maxNum);
            for (Question value: question){
                flag = CheckUtil.examine(q.getRepolish(),value.getRepolish());
                if (flag)break;
            }
            if (q.getAnswer().equals("Error") || flag){
                i--;
                continue;
            };

            question.add(q);
        }
    }

    /**
     * 生成题目和答案并写入文件
     * @param count 题目数量
     * @param maxNum 数值范围
     */
    public static void cwQuestion(int count,int maxNum){
        if (count <= 0 || maxNum <= 0) {
            throw new IllegalArgumentException("请输入正确的数值");
        }
        setQuestion(count,maxNum);
        for (int i = 0; i < question.size(); i++) {
            FileUtil.write((i+1)+". "+question.get(i).getQuestion()+"\n",exercises_path);
            FileUtil.write((i+1)+". "+question.get(i).getAnswer()+"\n",answer_path);
        }
        System.out.println("结果已输出到指定路径");
    }

    /**
     * 比对题目和答案，计算正确率
     * @param exercises 题目
     * @param answer 答案
     */
    public static void getGrade(String exercises,String answer){
        //存储题目
        ArrayList<String> eList;
        //存储答案
        ArrayList<String> aList;
        //存储正确题目的编号
        ArrayList<String> Correct = new ArrayList<>();
        //存储错误题目的编号
        ArrayList<String> Wrong = new ArrayList<>();
        try {
            eList = FileUtil.read(exercises);
            aList = FileUtil.read(answer);
            if (eList.isEmpty()||aList.isEmpty()){
                System.out.println("题目或答案文件为空");
                System.exit(1);
            }
            for (int i = 0; i < aList.size(); i++) {
                if (aList.get(i).equals(QuestionUtil.calculate(QuestionUtil.rePolish(eList.get(i))))) {
                    Correct.add((i+1)+"");
                } else {
                    Wrong.add((i+1)+"");
                }
            }
            FileUtil.write("Correct",grade_path);
            writeGrade(Correct);
            FileUtil.write("Wrong",grade_path);
            writeGrade(Wrong);
            System.out.println("结果已输出到指定路径");
        }catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * 输出题目序号到指定路径
     * @param list 题目序号队列
     */
    public static void writeGrade(ArrayList<String> list){
        FileUtil.write(" :"+list.size()+"(",grade_path);
        for (int i = 0; i < list.size(); i++) {
            FileUtil.write(list.get(i),grade_path);
            if (i != list.size() - 1) {
                FileUtil.write(",",grade_path);
            }
        }
        FileUtil.write(")\n",grade_path);
    }

    public static void main(String[] args) {
        start(args);
    }
}
