	-- DDL
	CREATE TABLE log_events (
	    event_id VARCHAR2(36) PRIMARY KEY,
	    event_type VARCHAR2(100),
	    timestamp TIMESTAMP,
	    duration_ms NUMBER,
	    client_ip_address VARCHAR2(15),
	    component VARCHAR2(10),
	    user_id VARCHAR2(36),
	    user_name VARCHAR2(100),
	    object_id VARCHAR2(36),
	    object_name VARCHAR2(100),
	    ipv_id VARCHAR2(36),
	    ipv_name VARCHAR2(100),
	    status_code NUMBER(4)
	);
	
	CREATE TABLE log_errors (
	    id NUMBER GENERATED ALWAYS AS IDENTITY,
	    event_id VARCHAR2(36),
	    message VARCHAR2(100),
	    CONSTRAINT fk_event_id FOREIGN KEY (event_id) REFERENCES log_events(event_id)
	);


-- Sample Example of DML H2 database
INSERT INTO log_events (event_id, event_type, timestamp, duration_ms, client_ip_address, component, user_id, user_name, object_id, object_name, status_code)
VALUES ('1', 'pmessage', TIMESTAMP '1970-01-01 00:00:00' + INTERVAL '1623045600' SECOND, 500, '192.168.0.1', 'CLI', '7094ec03-342a-4760-8b93-d3b67cd766ba', 'admin', '960157bf-7b9a-4cd9-9460-17de52aeb4cf', 'ice-dev-test-area', 200);
INSERT INTO log_events (event_id, event_type, timestamp, duration_ms, client_ip_address, component, user_id, user_name, object_id, object_name, status_code)
VALUES ('2', 'pmessage', TIMESTAMP '1970-01-01 00:00:00' + INTERVAL '1623045600' SECOND, 500, '192.168.0.2', 'CLI', '7094ec03-342a-4760-8b93-d3b67cd766ba', 'admin', 'ad73054d-b7e9-406c-8503-663409b46650', 'ice-dev-test-area', 200);
COMMIT;


-- Sample Example of DML Oracle database
INSERT INTO log_events (event_id, event_type, timestamp, duration_ms, client_ip_address, component, user_id, user_name, object_id, object_name, status_code)
VALUES ('1', 'pmessage', TO_TIMESTAMP('1970-01-01 01:00:00', 'YYYY-MM-DD HH:MI:SS') + INTERVAL '1000' SECOND, 500, '192.168.0.1', 'CLI', '7094ec03-342a-4760-8b93-d3b67cd766ba', 'admin', '960157bf-7b9a-4cd9-9460-17de52aeb4cf', 'ice-dev-test-area', 200);
COMMIT;



[
{
    "eventId":"3",
    "eventType": "pmessage",
    "timestamp": "245637456",
    "clientIpAddress":"200",
    "component":"CLI",
    "userId":"7094ec03-342a-4760-8b93-d3b67cd766ba",
    "userName":"admin",
    "objectId":"ad73054d-b7e9-406c-8503-663409b46650",
    "objectName":"ice-dev-test-area",
    "statusCode":"200"
}
]