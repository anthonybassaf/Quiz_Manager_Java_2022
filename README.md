# Quiz_Manager_Java_2022

# User Guide
The program will allow you to operate CRUD operations on Open Questions, MCQ Questions with questions and answers being stored in a database. You will also be able to search for questions based on topics and assemble a quiz (a set of questions based on a given topic) and export the quiz under a plain text format.

# CRUD Operations
The user will login with a username and password for authentication. The user will be able to select the action to take with a switch cases adapted to every operations. 1. Create/Update/Delete Student Information from database 2. Create/Update/Delete MCQ or Open Questions from database (based on QuestionType). 3. Create/Update/Delete Answers from database

# Important Comments
Some datamodels like QuestionType, QuestionList, Topic, TopicList, Difficulty and DifficultyLevel were added beyond the model of the project to accomodate the business logic of the project. That said, some of those classes and enumerations were still not used. The core datamodel classes of the project such as Answer, Question, MCQQuestion, MCQChoice, MCQAnswer, and Quiz remain the ones that are primarily used for the primary functionalities of the program.
