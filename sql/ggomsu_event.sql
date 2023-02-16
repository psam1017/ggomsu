-- 이벤트 스케줄러의 상태확인
SHOW VARIABLES LIKE 'event%';
-- 스케쥴러 이벤트의 상태 on으로 변경
SET GLOBAL event_scheduler = ON;
-- 이벤트 확인
SHOW EVENTS; 
-- 마지막 이벤트 실행시점 확인
SELECT last_executed FROM INFORMATION_SCHEMA.EVENTS WHERE event_name = 'statusValueUpdates';
-- 이벤트 삭제
DROP EVENT IF EXISTS statusValueUpdates;

-- 이벤트 생성  : 생성 하루에 1번(상황에 따라 이벤트 실행 시간 지정을 지정할수 있다)
CREATE EVENT IF NOT EXISTS statusValueUpdates 
ON SCHEDULE EVERY '1' DAY  
DO
  UPDATE members 
  SET statusValue = 'DOR' 
  WHERE 0 < TIMESTAMPDIFF(YEAR, signAt, NOW()); 