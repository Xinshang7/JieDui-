package zcy;
import zcy.Utils.QuestionUtil;

import java.util.ArrayList;


public class Question {
    private String question;
    private ArrayList<String> repolish;
    private String answer;

    public Question(int max) {
        this.question = QuestionUtil.setExpression(max);
        this.repolish = QuestionUtil.rePolish(this.question);
        this.answer = QuestionUtil.calculate(this.repolish);
    }

    public String getQuestion() {
        return question;
    }

    public ArrayList<String> getRepolish() {
        return repolish;
    }

    public String getAnswer() {
        return answer;
    }


}
