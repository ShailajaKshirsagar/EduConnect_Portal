package com.educonnect.repository;

import com.educonnect.entity.ActivityLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityLogRepo extends JpaRepository<ActivityLogs,Long> {

    @Query("SELECT a FROM ActivityLogs a WHERE a.userid =:userid ORDER BY a.timestamp DESC")
    List<ActivityLogs> findByUserId(@Param("userid") Long userid);
}
