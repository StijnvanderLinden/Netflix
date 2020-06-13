-- tag::adocSQL[]
INSERT INTO video(id, title, description, duration, thumbnail, url)
VALUES (nextval('hibernate_sequence'), 'Frozen', 'Princess Elsa of Arendelle possesses magical powers that allow her to control and create ice and snow, often using them to play with her younger sister, Anna. After Elsa accidentally injures Anna with her magic, their parents, the King and Queen, take both siblings to a colony of trolls led by Grand Pabbie.', 6540, 'https://i.imgur.com/m0bj4K6.jpg', 'https://storageaccountmyres92f7.blob.core.windows.net/asset-9623b3b4-4a53-4520-987d-fdb995c20592/vagabond-episode-1.mp4?sv=2017-04-17&sr=c&si=81fb4aa3-f454-42e5-9a79-46499ec247ac&sig=MDN7eXWJdbJPSuIcT15x39AciqT06ZdpwXdFKA52VH4%3D&st=2020-06-04T11%3A33%3A08Z&se=2120-06-04T11%3A33%3A08Z');

INSERT INTO video(id, title, description, duration, thumbnail, url)
VALUES (nextval('hibernate_sequence'), 'Frozen 2', 'Princess Elsa of Arendelle possesses magical powers that allow her to control and create ice and snow, often using them to play with her younger sister, Anna. After Elsa accidentally injures Anna with her magic, their parents, the King and Queen, take both siblings to a colony of trolls led by Grand Pabbie.', 6540, 'https://i.imgur.com/dWsJRW2.png', 'https://storageaccountmyres92f7.blob.core.windows.net/asset-9623b3b4-4a53-4520-987d-fdb995c20592/vagabond-episode-1.mp4?sv=2017-04-17&sr=c&si=81fb4aa3-f454-42e5-9a79-46499ec247ac&sig=MDN7eXWJdbJPSuIcT15x39AciqT06ZdpwXdFKA52VH4%3D&st=2020-06-04T11%3A33%3A08Z&se=2120-06-04T11%3A33%3A08Z');

INSERT INTO video(id, title, description, duration, thumbnail, url)
VALUES (nextval('hibernate_sequence'), 'Avengers: Endgame', 'Twenty-three days after Thanos used the Infinity Gauntlet to disintegrate half of all life in the universe,[N 1] Carol Danvers rescues Tony Stark and Nebula from deep space and returns them to Earth, where they reunite with the remaining Avengers—Bruce Banner, Steve Rogers, Thor, Natasha Romanoff, and James Rhodes—and Rocket. Locating Thanos on an uninhabited planet, they plan to use the Infinity Stones to reverse "the Snap", but Thanos reveals he destroyed the Stones to prevent further use. Enraged, Thor decapitates Thanos.', 6540, 'https://i.imgur.com/g8AjckF.png', 'https://storageaccountmyres92f7.blob.core.windows.net/asset-9623b3b4-4a53-4520-987d-fdb995c20592/vagabond-episode-1.mp4?sv=2017-04-17&sr=c&si=81fb4aa3-f454-42e5-9a79-46499ec247ac&sig=MDN7eXWJdbJPSuIcT15x39AciqT06ZdpwXdFKA52VH4%3D&st=2020-06-04T11%3A33%3A08Z&se=2120-06-04T11%3A33%3A08Z');

INSERT INTO category(id, name)
VALUES (nextval('hibernate_sequence'), 'Action');

INSERT INTO category(id, name)
VALUES (nextval('hibernate_sequence'), 'Adventure');

INSERT INTO category(id, name)
VALUES (nextval('hibernate_sequence'), 'Comedy');

INSERT INTO category_video(category_id, videos_id)
VALUES (4, 1);

INSERT INTO category_video(category_id, videos_id)
VALUES (6, 2);

INSERT INTO category_video(category_id, videos_id)
VALUES (6, 1);

INSERT INTO category_video(category_id, videos_id)
VALUES (5, 3);

INSERT INTO category_video(category_id, videos_id)
VALUES (4, 3);


