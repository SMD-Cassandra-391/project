-- query 1
SELECT 	huntpilotdn 
FROM 	modified_project.t1 
WHERE 	num = 36681769924989
AND 	(currentroutingreason, orignodeid, origspan, origvideocap_bandwidth, datetimeorigination)
        > (1,1,1,1,1)
AND      (currentroutingreason, orignodeid, origspan, origvideocap_bandwidth, datetimeorigination)
        < (5000,5000,5000,5000,5000);

-- query 2
SELECT 	destcause_location, destipaddr
FROM 	modified_project.t2
WHERE 	num = 17306447014892 
AND     datetimeorigination > 1 
AND 	datetimeorigination < 5000;

-- query 3
SELECT 	origdevicename, duration
FROM 	modified_project.t3
WHERE 	num = 17702306473948
AND 	destdevicename IN ('a','f', 'g', 'h');

-- query 4
SELECT 	count, duration
FROM 	modified_project.t4
WHERE 	duration IN (10,20,30,40,50,60,70,80,90,100) 
ORDER BY count DESC
LIMIT 10;

-- query 5
SELECT 	callingnumber, callcount
FROM 	modified_project.t5
WHERE 	callingNumber IN ('6214789','7350819', '1255452', '9151967', '9657707', '9998888') 
ORDER BY callcount ASC
LIMIT 6;