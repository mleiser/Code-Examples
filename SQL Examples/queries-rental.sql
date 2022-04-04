--Print Apt number, building number, and floor number of apartments that are available
SELECT ANum, BuildingNum, FloorNum
FROM apartment 
WHERE Available = 'yes';

--Print names of clients who are not currently active clients. A client is considered as active if he/she is referenced in RentedBy relation.
SELECT UNIQUE c.CName 
FROM client c, rentedby r
WHERE c.CID not in (SELECT r.CID
                    FROM rentedby r);

--Print information about clients who have signed more than one lease contract since January 2014.
SELECT c.CID, c.CName, c.Phone, c.SSN , COUNT(r.CID)
FROM client c, rentedby r
WHERE c.CID = r.CID and extract (year from r.StartRent) > 2014 and extract (month from r.StartRent) > 1
GROUP BY c.CID, c.CName, c.Phone, c.SSN
HAVING COUNT(r.CID) > 1;

--Print name of clients who paid a rent of at least $1000 for every apartment they have rented.
SELECT UNIQUE c.CName
FROM client c, rentedby r
WHERE c.CID = r.CID and r.rent >= 1000;

--Print the average rent of the RENTAL database.
SELECT AVG(rent)
FROM rentedby;

--Consider the following SQL query:
    --SELECT R.ANUM, COUNT(*)
    --FROM RENTEDBY R
    --GROUP BY R.ANUM
    --HAVING COUNT(*) > 1;

--Corrected query:
SELECT UNIQUE r1.anum, (SELECT COUNT(*)
                        FROM rentedby
                        WHERE anum = r1.anum) as count
FROM rentedby r1, rentedby r2
WHERE r1.CID != r2.CID and r1.anum = r2.anum;
