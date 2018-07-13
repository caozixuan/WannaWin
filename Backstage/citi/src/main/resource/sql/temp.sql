rollback;
commit;


UPDATE m_card
SET Points = Points - 100;
#WHERE MerchantID = 1;