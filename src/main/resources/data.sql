INSERT INTO question (topic, difficulty_rank, content)
values ('nature', 10, 'question1'),
       ('technology', 20, 'question2'),
       ('technology', 30, 'question3');

INSERT INTO response (text, is_correct, question_id)
values ('answer1', true, 1),
       ('answer2', false, 1),
       ('answer3', false, 3),
       ('answer4', false, 2),
       ('answer5', true, 1),
       ('answer6', false, 2),
       ('answer7', true, 1);

