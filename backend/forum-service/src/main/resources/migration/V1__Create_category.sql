CREATE TABLE categories (
    id UUID PRIMARY KEY,
    version BIGINT NOT NULL DEFAULT 0,
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    topics BIGINT NOT NULL DEFAULT 0,
    posts BIGINT NOT NULL DEFAULT 0,
    created_at  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

INSERT INTO categories (id, name, description) VALUES
('66f11a3f-bf49-4795-ad40-7d188554f25e', 'General Chat', 'A friendly space for everyday conversations. Catch up, share stories, and chat about whatever''s on your mind.'),
('f3121b5b-26a7-41b4-a794-fd987a215156', 'Tech & Programming', 'Discuss the latest tech, get coding help, share projects, and dive deep into software, hardware, and development.'),
('f5892ccf-2784-4fae-935d-353ac070e840', 'Help & Support', 'Get technical or community assistance. Ask questions, offer guidance, and help each other navigate the forum.'),
('05c3ba51-6401-46da-8782-d5331f1c1052', 'Random Fun', 'The home of memes, hilarious stories, weird facts, and anything lighthearted meant to make you laugh.'),
('e90bcae2-95b2-47a1-a066-7d68a3bc84a5', 'Marketplace', 'A community board for buying, selling, or trading items with fellow members. Please transact responsibly.'),
('270cdb76-aed4-436f-914c-55207ee0902a', 'Food & Drinks', 'Share recipes, restaurant finds, cooking tips, and food photos. Talk about everything delicious!'),
('14554f9b-0760-4281-9586-e6d2050f8883', 'Pets & Animals', 'Show off your furry, scaly, or feathery friends! Share photos, ask for pet care advice, and celebrate animals.'),
('cefa9af4-776b-49dc-a6d9-61b1ce89e970', 'Health & Wellness', 'Discuss fitness routines, mental well-being, nutrition, and healthy living in a supportive environment.'),
('7a57b3f3-70d2-49ca-bea3-00b6dc8a2fb4', 'Travel & Adventure', 'Share travel stories, photos, tips, and destination recommendations. Inspire others to explore the world.'),
('aff75e02-2cff-4824-b99f-8721ff233182', 'Books, Movies & TV', 'Your hub for all things entertainment. Discuss the latest releases, share reviews, and give recommendations.'),
('a4e8ab52-31bf-4334-9e02-ce8a8ecfc30a', 'Gaming & Geekery', 'Talk video games, tabletop RPGs, esports, comics, and all things geek culture. Share your latest achievements!'),
('4958bc87-c7f4-4bc1-916c-fd4263658a4f', 'Music & Audio', 'Discuss artists, share playlists, talk about concerts, gear, and explore all genres of music and sound.'),
('4a88c94c-63c1-47d7-8129-d7235021dd6e', 'Sports & Fitness', 'Cheer for your teams, discuss matches, follow athletes, and talk about your personal fitness goals.'),
('62ff9d95-8b5a-40a8-bf9c-a03c4686b34e', 'Fashion & Beauty', 'Share style tips, discuss trends, post outfit photos, and talk about skincare, makeup, and personal style.');