User stories:
- A Quiz is made of N Questions
- Every Question is related to a topic, and has a difficulty rank number
- Every Question has a content and a list of Response
- Every Response has a text and a boolean (correct)
- Questions can have more than 1 Response correct

DATA LAYER:
- [x] Create a Db with relevant tables (provide script .sql)
- [x] Implement a DaoQuestion class that will
    - save Question into DB.
    - Update Question with a new Question
    - Delete a Question
    - search Question by topic

HOW TO RUN:
- [x] Implement test cases with JUnit