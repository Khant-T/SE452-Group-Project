-- Pre-populate database

-- Workspaces
INSERT INTO workspaces (id, name)
    VALUES
        (1, 'Marketing Space');

-- Lists
INSERT INTO lists (id, name, workspace)
    VALUES
        (1, 'Marketing Projects', 1);

-- Statuses
INSERT INTO statuses (id, description, list)
    VALUES
        (1, 'To-do', 1),
        (2, 'In progress', 1),
        (3, 'Complete', 1);

-- Tasks
INSERT INTO tasks (id, description, list, status)
    VALUES
        (1, 'Schedule an appointment with S Supermarket', 1, 1),
        (2, 'Attend marketing lecture at Y Stadium', 1, 2),
        (3, 'Set-up a Snapchat account', 1, 3);


-- Subtasks
INSERT INTO subtasks (id, description, task, completed)
    VALUES
        (1, 'Reach out to marketing team of S Supermarket', 1, 0),
        (2, 'Confirm the date and time of appointment', 1, 0),
        (3, 'Day 1. Brand Strategy by Dr. A', 3, 1),
        (4, 'Day 2. Competitive Positioning by Miss I', 3, 1),
        (5, 'Day 3. Corporate Identity & Creative Development by Mr. O', 3, 0);