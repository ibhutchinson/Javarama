package Javarama_local;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;

/**
 * DatabaseConnect.java
 *
 * @author Isaac Hutchinson
 * @version 0.0.1
 */
public class DatabaseConnect {
    private String URL;
    private Connection conn;

    public DatabaseConnect() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        URL = "";
        conn = DriverManager.getConnection(URL, "", "");

    }


    /**
     * The isUserFound method is used to query the database for a valid user account matching the username and passHash
     * provided.
     *
     * @param passHash A SHA-256 hash String to query the database for.
     * @param uname    A username String to query the database for.
     * @return boolean value, true if a record is returned. False if no record is returned.
     * @throws Exception SQL Syntax
     * Reference: http://stackoverflow.com/questions/14995873/how-to-verify-username-and-password-in-java-mysql
     */
    public Boolean isUserFound(String passHash, String uname) throws Exception {
        String passCheckQuery = "SELECT * FROM `user` WHERE uname ='" + uname + "' and password ='" + passHash + "'";
        final PreparedStatement ps = conn.prepareStatement(passCheckQuery);
        if (passHash != null && uname != null) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        }

        return false;
    }

    /**
     * The userAlreadyExists method checks the username information for a new user registration against the database to
     * check if the username is already in use.
     *
     * @param uname A username String
     * @return boolean true for a username in use. False for a unused username.
     * @throws Exception SQL Syntax
     */
    public Boolean userAlreadyExists(String uname) throws Exception {
        String unameExits = "SELECT * FROM `user` WHERE uname ='" + uname + "'";
        final PreparedStatement ps = conn.prepareStatement(unameExits);
        if (uname != null) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        }
        return false;
    }

    /**
     * The codeAlreadyExists method checks the code information for a new user registration against the database to
     * check if the code is already used.
     *
     * @param code A number String provided by the instructor to the new user.
     * @return boolean true if the code is already used. False for a unused code.
     * @throws Exception SQL Syntax
     */
    public Boolean codeAlreadyExists(String code) throws Exception {
        String codeExits = "SELECT * FROM `user` WHERE UID ='" + code + "'";
        final PreparedStatement ps = conn.prepareStatement(codeExits);
        if (code != null) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        }
        return false;
    }

    /**
     * The newUser method is used to add a new user to the user table in the database.
     * <p>
     * The newUser method should only be used after new user information is validated.
     *
     * @param passHash A SHA-256 hash String of the users password.
     * @param uname    A username String.
     * @param id       A code provided by the instructor.
     * @throws Exception
     */
    public void newUser(String passHash, String uname, int id) throws Exception {
        String newUserQuery = "INSERT INTO `user`(`UID`, `uname`, `password`) VALUES ('" + id + "','" + uname + "','" + passHash + "')";
        final PreparedStatement ps = conn.prepareStatement(newUserQuery);
        ps.execute();

    }

    /**
     * The pullLesson method pulls the specified lesson from the database by lesson number.
     *
     * @param lessonID The int lesson number that corresponds to the set database value.
     * @return ArrayList of lesson questions in numerical order.
     * @throws Exception SQL Syntax.
     */
    public ArrayList<lessonQuestion> pullLesson(int lessonID) throws Exception {
        ArrayList<lessonQuestion> lesson = new ArrayList<>();
        String lessonQuery = "SELECT * FROM `question` WHERE `lessonid`= '" + lessonID + "'";
        PreparedStatement ps = conn.prepareStatement(lessonQuery);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            lesson.add(new lessonQuestion(rs.getInt("questionNumber"), rs.getString("questiontext"), rs.getString("answer")));
        }
        Collections.sort(lesson, (l1, l2) -> l1.compareTo(l2));
        return lesson;
    }

}
