select sr.id, sr.timestamp from sensor_reading as sr
where sr.timestamp >= '2022-10-11 00:00:00' and sr.timestamp <= '2022-10-11 23:59:59'
order by sr.timestamp desc
;
