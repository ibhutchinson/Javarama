package Javarama_local;

/**
 * lessonQuestion.java
 *
 * @author Isaac Hutchinson
 * @version 0.0.1
 */
public class lessonQuestion implements Comparable<lessonQuestion> {
    private int number;
    private String question;
    private String ans;

    /**
     * The lessonQuestion method is the constructor for the lessonQuestion of the class.
     *
     * @param number   int a question number.
     * @param question String a question.
     * @param ans
     */
    public lessonQuestion(int number, String question, String ans) {
        this.number = number;
        this.question = question;
        this.ans = ans;
    }

    /**
     * The getNumber method is a getter method that returns the questions number.
     *
     * @return int question number.
     */
    public int getNumber() {
        return number;
    }

    /**
     * The setNumber method is a setter method that set the question number to a new number.
     *
     * @param number int new number for question.
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * The getQuestion method is a getter method that returns a question.
     *
     * @return String lesson question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * The setQuestion method is a setter method that sets the question to a new question.
     *
     * @param question String new question.
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * The getAnswer method is a getter method that returns the answer of the current question.
     *
     * @return String question answer.
     */
    public String getAnswer() {
        return ans;
    }

    /**
     * The sAnswer method is a setter method that set the the answer of the current question to a new answer.
     *
     * @param ans String new answer for question.
     */
    public void setAnswer(String ans) {
        this.ans = ans;
    }


    /**
     * The compareTo method compares the values of the question numbers.
     *
     * @param that A question from the current lesson.
     * @return int value based on comparison of both lesson numbers.
     */
    public int compareTo(lessonQuestion that) {
        if (this.number < that.number) {
            return -1;
        } else if (this.number > that.number) {
            return 1;
        }

        return 0;
    }
}
