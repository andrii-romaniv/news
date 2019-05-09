delete from users;

INSERT INTO users(id,authority,email,image,password,username)
VALUES
('1', '0', 'vasyl.gladkyy@gmail.com', '7d409d7b-ada0-4217-b3f6-83759fe67b16-img_avatar3.png', '$2a$06$tHVFtKH8Bahj2pFoYznBTOMPvqakAV9DOjrOlLWtBYbxEP.q9oeOS', 'Admin'),
('2','1','asdasd@gmail.com','7d409d7b-ada0-4217-b3f6-83759fe67b16-img_avatar3.png','$2a$06$tHVFtKH8Bahj2pFoYznBTOMPvqakAV9DOjrOlLWtBYbxEP.q9oeOS','User');
