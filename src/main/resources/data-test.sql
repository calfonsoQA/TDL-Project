INSERT INTO task_domain(task_name)
VALUES 
('Cooking'),  
('Cleaning'),
('Admin Work');

INSERT INTO subtask_domain(subtask_description, effort_level, done, my_task_id)
VALUES 
('Buy utensils', 30, true, 1),
('Buy vacuum', 30, true, 2),
('Clean the kitchen', 30, true, 2),
('Sign documents', 30, true, 3), 
('Update calendar', 30, true, 3);