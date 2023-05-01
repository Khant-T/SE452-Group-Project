-- Pre-populate the database tables

-- Workspaces
INSERT INTO workspaces (id, name, user_id)
    VALUES
        (101, 'Marketing Space', 0),
        (102, 'Another Space', 0),
        (103, 'Operations Space', 0),
        (104, 'Language Space', 1);

-- Lists
INSERT INTO lists (id, name, workspace_id)
    VALUES
        (201, 'Marketing To-dos', 101),
        (202, 'Ideas for Marketing Projects', 101),
        (203, 'Simple App To-dos', 102),
        (204, 'Write J-Unit test case', 103),
        (205, 'Depoly on Heroku', 103);

-- Statuses
INSERT INTO statuses (id, description, list_id)
    VALUES
        (301, 'To-do', 201),
        (302, 'In progress', 201),
        (303, 'Complete', 201),
        (351, 'To-Be-Determined (TBD)', 202),
        (352, 'Confirmed Projects', 202);

-- Tasks
INSERT INTO tasks (id, description, list_id, status_id)
    VALUES
        (401, 'Schedule an appointment with S Supermarket', 201, 301),
        (402, 'Attend marketing lecture at Y Stadium', 201, 302),
        (403, 'Set-up a Snapchat account', 201, 303),
        (451, 'Revamping our style guidelilnes', 202, 352),
        (452, 'Sponsor social influencers', 202, 351),
        (453, 'Rent a billboard and make start making art pieces for it', 202, 351),
        (454, 'Set-up ad campaigns', 202, 352);

-- Subtasks
INSERT INTO subtasks (id, description, task_id, completed)
    VALUES
        (501, 'Reach out to marketing team of S Supermarket', 401, 0),
        (502, 'Confirm the date and time of appointment', 401, 0),
        (551, 'Day 1. Brand Strategy by Dr. A', 402, 1),
        (552, 'Day 2. Competitive Positioning by Miss I', 402, 1),
        (553, 'Day 3. Corporate Identity & Creative Development by Mr. O', 402, 0);