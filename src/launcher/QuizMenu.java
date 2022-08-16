package launcher;

import datamodel.*;
import services.data.AnswerDBDAO;
import services.data.QuestionDBDAO;
import services.data.QuizDBDAO;
import services.data.StudentDBDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import static java.lang.System.getProperties;

public class QuizMenu {
    public static void menuLauncher() {
        // authentication, it returns a boolean, true if the authentication succeeded
        Scanner scanner = new Scanner(System.in);
        Properties properties = getProperties();
        if (properties == null) return;

        String defaultUserName = properties.getProperty("userName");
        String defaultPassword = properties.getProperty("password");

        System.out.println("Welcome, please enter your name: ");
        String userName = scanner.nextLine();
        System.out.println("please enter your password: ");
        String password = scanner.nextLine();

        boolean authenticated = userName.equals(defaultUserName)
                && password.equals(defaultPassword);

        if (!authenticated) {
            // we leave
            System.out.println("not authenticated, bye!");
            scanner.close();
            //TODO check how to close the properties file
            return;
        }

        System.out.println("credentials ok, welcome!");

        String userResponse = "";
        System.out.println("What operation would you like to do?");
        System.out.println("1. create a student");
        System.out.println("2. update a student");
        System.out.println("3. delete a student");
        System.out.println("4. create a question");
        System.out.println("5. update a question");
        System.out.println("6. delete a question");
        System.out.println("7. create an answer");
        System.out.println("8. update an answer");
        System.out.println("9. delete an answer");
        System.out.println("10. search questions based on topic");
        System.out.println("11. assemble a quiz");
        System.out.println("q. quit");
        System.out.println("Enter your choice");

        userResponse = scanner.nextLine();

        while(!"q".equals(userResponse)) {
           switch (userResponse) {
               case "1":
                   Student student = new Student("Student00", "Student Name");
                   System.out.println("Enter Student's Name: ");
                   student.setName(scanner.nextLine());
                   StudentDBDAO.create(student);
                   System.out.println("New Student added Successfully!");
                   break;
               case "2":
                   Student student1 = new Student("Student01", "Student Name");
                   System.out.println("Enter Student's Name you want to Update: ");
                   student1.setName(scanner.nextLine());
                   StudentDBDAO.search(student1);
                   System.out.println("Enter the New Student Name: ");
                   student1.setName(scanner.nextLine());
                   StudentDBDAO.update(student1);
                   System.out.println("Student Name Updated Successfully!");
                   break;
               case "3":
                   Student student2 = new Student("Student02", "Student Name");
                   System.out.println("Enter Student's Name: ");
                   student2.setName(scanner.nextLine());
                   StudentDBDAO.delete(student2);
                   System.out.println("Student deleted Successfully!");
                   break;
               case "4":
                   System.out.println("Enter Question Type: 1. MCQ \n 2. Open Question");
                   String response = scanner.nextLine();

                   switch (response) {
                       case "1":
                           MCQQuestion question = new MCQQuestion();
                           question.setType(QuestionType.MCQ);
                           System.out.println("Enter MCQ Question: ");
                           question.setQuestion(scanner.nextLine());
                           System.out.println("Enter MCQ Answer: ");
                           question.setAnswer(scanner.nextLine());
                           System.out.println("Enter Choice 1: ");
                           question.setChoice1(scanner.nextLine());
                           System.out.println("Enter Choice 2: ");
                           question.setChoice2(scanner.nextLine());
                           System.out.println("Enter Choice 3: ");
                           question.setChoice3(scanner.nextLine());
                           System.out.println("Set Difficulty: ");
                           question.setDifficulty(Integer.parseInt(scanner.nextLine()));
                           System.out.println("Enter Topics separated by a coma: ");
                           TopicList topicList = new TopicList();
                           topicList.setTopicsList(scanner.nextLine());
                           QuestionDBDAO.create(question);
                           System.out.println("MCQ Question Successfully Created!");
                           break;
                       case "2":
                           Question question1 = new Question();
                           question1.setType(QuestionType.OPEN);
                           System.out.println("Enter Open Question: ");
                           question1.setQuestion(scanner.nextLine());
                           QuestionDBDAO.create(question1);
                           System.out.println("Open Question Successfully Created!");
                   }
               case "5":
                   System.out.println("Enter Field to Update: 1. Question Type \n 2. Difficulty");
                   String field = scanner.nextLine();

                   switch (field) {
                       case "1":
                           Question question = new Question();
                           System.out.println("Enter Question to Update: ");
                           question.setQuestion(scanner.nextLine());
                           QuestionDBDAO.SearchByTitle(question);
                           if ("mcq".equals(scanner.nextLine())) {
                               question.setType(QuestionType.OPEN);
                           } else {
                               question.setType(QuestionType.MCQ);
                           }
                           QuestionDBDAO.update(question);
                           System.out.println("Question Type Updated Successfully!");
                           break;
                       case "2":
                           Question question1 = new Question();
                           System.out.println("Enter the Question you want to Update: ");
                           question1.setQuestion(scanner.nextLine());
                           QuestionDBDAO.SearchByTitle(question1);
                           System.out.println("Please set the New Question Difficulty: ");
                           question1.setDifficulty(Integer.parseInt(scanner.nextLine()));
                           QuestionDBDAO.update(question1);
                           System.out.println("Question Difficulty Updated Successfully!");
                           break;
                   }
               case "6":
                   Question question = new Question();
                   System.out.println("Enter Question Title You Want to Delete: ");
                   question.setQuestion(scanner.nextLine());
                   QuestionDBDAO.delete(question);
                   System.out.println("Question Deleted Successfully!");
                   break;

               case "7":
                   // Create Answer
                   System.out.println("Enter Answer Type: 1. MCQ \n 2. Open Answer");
                   String answer_type = scanner.nextLine();
                   switch (answer_type) {
                       case "1":
                           Answer mcq_answer = new Answer("Answer Text");
                           System.out.println("Enter MCQ Answer: ");
                           mcq_answer.setText(scanner.nextLine());
                           AnswerDBDAO.create(mcq_answer);
                           System.out.println("MCQ Answer Created Successfully!");
                           break;
                       case "2":
                           Answer open_answer = new Answer("Answer Text");
                           System.out.println("Enter Open Answer: ");
                           open_answer.setText(scanner.nextLine());
                           AnswerDBDAO.create(open_answer);
                           System.out.println("Open Answer Created Successfully!");
                           break;
                   }

               case "8":
                   // Update Answer
                   Answer answer = new Answer("Answer Text");
                   System.out.println("Enter the Answer to Update: ");
                   answer.setText(scanner.nextLine());
                   AnswerDBDAO.search(answer);
                   System.out.println("Enter the New Answer: ");
                   answer.setText(scanner.nextLine());
                   AnswerDBDAO.update(answer);
                   System.out.println("Answer Updated Successfully!");
                   break;

               case "9":
                   // Delete Answer
                   Answer answer1 = new Answer("Answer Text");
                   System.out.println("Enter the Answer You Want to Delete: ");
                   answer1.setText(scanner.nextLine());
                   AnswerDBDAO.delete(answer1);
                   System.out.println("Answer Deleted Successfully!");
                   break;

               case "10":
                   // Search Question based on Topic
                   System.out.println("Enter the Topic of the Question: ");
                   searchByTopic();

               case "11":
                   assembleQuiz();

               case "q":
                   System.exit(0);
                   break;

           }

        }
    }
    public static List<Question> searchByTopic() {
        Question question_topic = new Question();
        System.out.println("Enter the Topic You Are Looking For: ");
        question_topic.setTopics(new ArrayList<>());
        System.out.println("These are the Questions from this Topic: ");
        return QuestionDBDAO.SearchByTopicId(question_topic);
    }
    public static int assembleQuiz() {
        Scanner scanner = new Scanner(System.in);
        Quiz quiz = new Quiz("Quiz Title");
        System.out.println("Enter the Quiz Topic: ");
        Topic topic = new Topic("Entered Topic");
        topic.setTopic(scanner.nextLine());
        return QuizDBDAO.QuizBuilder(quiz);
    }
}
