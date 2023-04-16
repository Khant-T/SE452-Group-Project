-- Pre-populate the database tables

-- Workspaces
INSERT INTO workspaces (id, name, user_id)
    VALUES
        (100, 'Marketing Space', 0),
        (115, 'Another Space', 0);

-- Lists
INSERT INTO lists (id, name, workspace_id)
    VALUES
        (100, 'Marketing To-dos', 100),
        (200, 'Ideas for Marketing Projects', 100);

-- Statuses
INSERT INTO statuses (id, description, list_id)
    VALUES
        (1, 'To-do', 1),
        (2, 'In progress', 1),
        (3, 'Complete', 1),
        (4, 'To-Be-Determined (TBD)', 2),
        (5, 'Confirmed Projects', 2);

-- Tasks
INSERT INTO tasks (id, description, list_id, status_id)
    VALUES
        (101, 'Schedule an appointment with S Supermarket', 100, 1),
        (102, 'Attend marketing lecture at Y Stadium', 100, 2),
        (103, 'Set-up a Snapchat account', 100, 3),
        (201, 'Revamping our style guidelilnes', 200, 5),
        (202, 'Sponsor social influencers', 200, 4),
        (203, 'Rent a billboard and make start making art pieces for it', 200, 4),
        (204, 'Set-up ad campaigns', 200, 5);


-- Subtasks
INSERT INTO subtasks (id, description, task_id, completed)
    VALUES
        (1, 'Reach out to marketing team of S Supermarket', 1, 0),
        (2, 'Confirm the date and time of appointment', 1, 0),
        (3, 'Day 1. Brand Strategy by Dr. A', 3, 1),
        (4, 'Day 2. Competitive Positioning by Miss I', 3, 1),
        (5, 'Day 3. Corporate Identity & Creative Development by Mr. O', 3, 0);