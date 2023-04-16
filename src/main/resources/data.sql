-- Pre-populate the database tables

-- Workspaces
INSERT INTO workspaces (id, name)
    VALUES
        (1, 'Marketing Space');

-- Lists
INSERT INTO lists (id, name, workspace_id)
    VALUES
        (1, 'Marketing To-dos', 1),
        (2, 'Ideas for Marketing Projects', 1);

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
        (1, 'Schedule an appointment with S Supermarket', 1, 1),
        (2, 'Attend marketing lecture at Y Stadium', 1, 2),
        (3, 'Set-up a Snapchat account', 1, 3),
        (4, 'Revamping our style guidelilnes', 2, 5),
        (5, 'Sponsor social influencers', 2, 4),
        (6, 'Set-up ad campaigns', 2, 5);


-- Subtasks
INSERT INTO subtasks (id, description, task_id, completed)
    VALUES
        (1, 'Reach out to marketing team of S Supermarket', 1, 0),
        (2, 'Confirm the date and time of appointment', 1, 0),
        (3, 'Day 1. Brand Strategy by Dr. A', 3, 1),
        (4, 'Day 2. Competitive Positioning by Miss I', 3, 1),
        (5, 'Day 3. Corporate Identity & Creative Development by Mr. O', 3, 0);