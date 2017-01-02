package Javarama_local;

import java.util.ArrayList;

/**
 * lessonBuilder.java
 *
 * @author Isaac Hutchinson
 * @version 0.0.1
 */
public class lessonBuilder {
    static ArrayList<lessonQuestion> currentLesson;
    static String lessonTitleData;

    /**
     * The lessonBuilder is the constructor of the lessonBuilder class.
     */
    public lessonBuilder() {
        currentLesson = new ArrayList<>();
        lessonTitleData = "";
    }

    /**
     * The getCurrentLesson method is a getter method to get the current pulled lesson from the database.
     *
     * @return Current lesson pulled from database.
     */
    public ArrayList<lessonQuestion> getCurrentLesson() {
        return currentLesson;
    }

    /**
     * The setCurrentLesson is a setter method that sets a new lesson.
     *
     * @param currentLesson ArrayList of lessonQuestions.
     */
    public void setCurrentLesson(ArrayList<lessonQuestion> currentLesson) {
        this.currentLesson = currentLesson;
    }
}
