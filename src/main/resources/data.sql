-- H2 초기 데이터
INSERT INTO login_type (name, enabled, updated_at) VALUES ('OAUTH_KAKAO', 'Y', NOW());
INSERT INTO login_type (name, enabled, updated_at) VALUES ('OAUTH_APPLE', 'Y', NOW());
INSERT INTO login_type (name, enabled, updated_at) VALUES ('OAUTH_NAVER', 'Y', NOW());
INSERT INTO login_type (name, enabled, updated_at) VALUES ('OAUTH_GOOGLE', 'Y', NOW());
INSERT INTO login_type (name, enabled, updated_at) VALUES ('OAUTH_GITHUB', 'Y', NOW());
INSERT INTO login_type (name, enabled, updated_at) VALUES ('OAUTH_FACEBOOK', 'Y', NOW());
INSERT INTO login_type (name, enabled, updated_at) VALUES ('OAUTH_LINKEDIN', 'Y', NOW());

INSERT INTO member_permission (name, updated_at) VALUES ('GUEST', NOW());
INSERT INTO member_permission (name, updated_at) VALUES ('USER', NOW());
INSERT INTO member_permission (name, updated_at) VALUES ('ADMIN', NOW());
