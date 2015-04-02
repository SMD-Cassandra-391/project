SELECT 	huntpilotdn 
FROM 	project.t1 
WHERE 	(currentroutingreason, orignodeid, origspan, origvideocap_bandwidth, datetimeorigination)
        > (1,1,1,1,1)
AND      (currentroutingreason, orignodeid, origspan, origvideocap_bandwidth, datetimeorigination)
        < (1000,1000,1000,1000,1000)
LIMIT 4000
ALLOW FILTERING;

SELECT 	destcause_location, destipaddr
FROM 	project.t2
WHERE 	datetimeorigination = 110
AND     num >= 11612484378506
AND     num <= 45880092667983
LIMIT 4000;


SELECT 	origdevicename, duration
FROM 	project.t3
WHERE 	destdevicename IN ('a','f', 'g')
LIMIT 4000
ALLOW FILTERING;


SELECT 	count, duration
FROM 	project.t4
WHERE 	duration IN (10,20,30,40,50,60,70,80,90,100) 
ORDER BY count DESC
LIMIT 10;

SELECT 	callingnumber, callcount
FROM 	project.t5
WHERE 	callingNumber IN ('6214789','7350819', '1255452', '9151967', '9657707', '9998888') 
ORDER BY callcount ASC
LIMIT 6;