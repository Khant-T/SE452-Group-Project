-- Pre-populate the database tables

-- Workspaces
INSERT INTO workspaces (id, name, user_id)
    VALUES
        (100, 'Marketing Space', 0),
        (115, 'Another Space', 0),
        (120, 'Art Space', 0),
        (125, 'English Space', 0);

-- Lists
INSERT INTO lists (id, name, workspace_id)
    VALUES
        (100, 'Marketing To-dos', 100),
        (200, 'Ideas for Marketing Projects', 100),
        (300, 'Simple App To-dos', 200),
        (400, 'Write Junit test case', 200),
        (500, 'Depoly on Heroku', 300);

-- Statuses
INSERT INTO statuses (id, description, list_id)
    VALUES
        (100, 'To-do', 100),
        (200, 'In progress', 100),
        (300, 'Complete', 100),
        (400, 'To-Be-Determined (TBD)', 200),
        (500, 'Confirmed Projects', 200);

-- Tasks
INSERT INTO tasks (id, description, list_id, status_id)
    VALUES
        (101, 'Schedule an appointment with S Supermarket', 100, 1),
        (102, 'Attend marketing lecture at Y Stadium', 100, 2),
        (103, 'Set-up a Snapchat account', 100, 3),
        (201, 'Revamping our style guidelilnes', 200, 5),
        (202, 'Sponsor social influencers', 200, 4),
        (203, 'Rent a billboard and make start making art pieces for it', 200, 4),
        (204, 'Set-up ad campaigns', 200, 5),
        (205, '100% coverage on Unit test', 300, 2),
        (206, 'Complete execise on UI Interface', 300, 2);


-- Subtasks
INSERT INTO subtasks (id, description, task_id, completed)
    VALUES
        (100, 'Reach out to marketing team of S Supermarket', 1, 0),
        (200, 'Confirm the date and time of appointment', 1, 0),
        (300, 'Day 1. Brand Strategy by Dr. A', 3, 1),
        (400, 'Day 2. Competitive Positioning by Miss I', 3, 1),
        (500, 'Day 3. Corporate Identity & Creative Development by Mr. O', 3, 0);