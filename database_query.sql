SELECT u.user_id, u.username, td.training_id, td.training_date, COUNT(1) as count
         FROM User_table u
        JOIN Training_details_table td ON u.user_id = td.user_id
        GROUP BY u.user_id, td.training_id, u.username, u.user_id, td.training_date
        HAVING COUNT(u.user_id) >= 2
        ORDER BY td.Training_date DESC;
