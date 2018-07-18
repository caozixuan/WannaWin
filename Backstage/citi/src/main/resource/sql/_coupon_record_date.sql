SELECT name, SUM(points) AS totalPoints 
FROM coupon_view NATURAL JOIN (SELECT ItemID, item.name, points FROM item WHERE MerchantID = '1') AS item_tmp 
WHERE DATE(getTime) BETWEEN '2018-01-01 01:01:01' AND '2018-12-30 01:01:01' 
GROUP BY ItemID, name;
