-- H2 초기 데이터
-- 멤버 관련
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

-- Stock
INSERT INTO stock (market_code, dart_code, corp_name, modify_date) VALUES (NULL, '00123456', '테스트컴퍼니', NOW());
INSERT INTO stock (market_code, dart_code, corp_name, modify_date) VALUES ('233456', '00555555', '페이퍼컴퍼니', NOW());
INSERT INTO stock (market_code, dart_code, corp_name, modify_date) VALUES ('233456', '00555555', '페이퍼컴퍼니', NOW());
INSERT INTO stock (market_code, dart_code, corp_name, modify_date) VALUES ('005930', '00126380', '삼성전자', NOW());
INSERT INTO stock (market_code, dart_code, corp_name, modify_date) VALUES ('012990', '00120100', 'LG석유화학', NOW());

INSERT INTO sector_type (id, name) VALUES ('001234', '테스트업');
INSERT INTO sector_type (id, name) VALUES ('000001', '스프링업');
INSERT INTO sector_type (id, name) VALUES ('000002', '제조업');
INSERT INTO sector_type (id, name) VALUES ('000003', 'ORM중계업');

INSERT INTO market_type (name) VALUES ('코스피');
INSERT INTO market_type (name) VALUES ('코스닥');
INSERT INTO market_type (name) VALUES ('코넥스');

INSERT INTO company_type (name) VALUES ('대기업');
INSERT INTO company_type (name) VALUES ('공기업');
INSERT INTO company_type (name) VALUES ('중소기업');

INSERT INTO stock_information (stock_id, market_type_id, sector_type_id, company_type_id, revenue, profit, capital) VALUES (1, 1, '001234', 1, 100, 1000, 10000);
INSERT INTO stock_information (stock_id, market_type_id, sector_type_id, company_type_id, revenue, profit, capital) VALUES (2, 2, '000001', 2, 10000, 10000, 10000);
INSERT INTO stock_information (stock_id, market_type_id, sector_type_id, company_type_id, revenue, profit, capital) VALUES (3, 3, '000002', 3, 10, 0, 0);
INSERT INTO stock_information (stock_id, market_type_id, sector_type_id, company_type_id, revenue, profit, capital) VALUES (4, 1, '000002', 3, 10, 0, 0);
INSERT INTO stock_information (stock_id, market_type_id, sector_type_id, company_type_id, revenue, profit, capital) VALUES (5, 1, '000003', 2, 0, 0, 1000);

--- Event
INSERT INTO event (stock_id, updated_at, event_type, created_at) VALUES (1, NOW(), 'TYPE', NOW());
