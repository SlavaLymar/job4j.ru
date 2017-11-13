-- fill roles
INSERT INTO role (role) VALUES ('admin');
INSERT INTO role (role) VALUES ('user');

-- fill users
INSERT INTO users (login, password, name, create_user, role_id)
VALUES ('admin', 'admin', 'admin', now(), (SELECT r.id FROM role r WHERE r.role = 'admin'));
INSERT INTO users (login, password, name, create_user, role_id)
VALUES ('user', 'user', 'user', now(), (SELECT r.id FROM role r WHERE r.role = 'user'));

-- fill manufacturers
INSERT INTO manufacturers (manuf) VALUES ('toyota');
INSERT INTO manufacturers (manuf) VALUES ('nissan');
INSERT INTO manufacturers (manuf) VALUES ('kia');

-- fill models
INSERT INTO models (model, manuf_id) VALUES ('corolla', (SELECT m.id FROM manufacturers m WHERE m.manuf = 'toyota'));
INSERT INTO models (model, manuf_id) VALUES ('prius', (SELECT m.id FROM manufacturers m WHERE m.manuf = 'toyota'));
INSERT INTO models (model, manuf_id) VALUES ('murano', (SELECT m.id FROM manufacturers m WHERE m.manuf = 'nissan'));
INSERT INTO models (model, manuf_id) VALUES ('cee`d', (SELECT m.id FROM manufacturers m WHERE m.manuf = 'kia'));

-- fill transmissions
INSERT INTO transmissions (name) VALUES ('auto');
INSERT INTO transmissions (name) VALUES ('manual');

-- fill colours
INSERT INTO colours (color) VALUES ('black');
INSERT INTO colours (color) VALUES ('white');

-- fill bodies
INSERT INTO bodies (body) VALUES ('sedan');
INSERT INTO bodies (body) VALUES ('hatch');
INSERT INTO bodies (body) VALUES ('wagon');
