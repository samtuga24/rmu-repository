package com.samtuga.rmufilemanagement.repository;

import com.samtuga.rmufilemanagement.entity.IncomingCorrespondence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IncomingRepository extends JpaRepository<IncomingCorrespondence, Long> {
    Optional<IncomingCorrespondence> findByReference(String reference);

    Optional<IncomingCorrespondence> findBySubjectIgnoreCase(String subject);

    List<IncomingCorrespondence>findByRefIgnoreCase(String ref);
    List<IncomingCorrespondence>findIncomingByRefIgnoreCase(String reference);


    public static final String COUNT_ROWS = "SELECT COUNT(*) FROM incoming_correspondence";
    @Query(value = COUNT_ROWS, nativeQuery = true)
    long getCount();

    public static final String COUNT_BY_YEAR = "SELECT COUNT(*) FROM incoming_correspondence WHERE EXTRACT(YEAR FROM date_received)=?1";
    @Query(value = COUNT_BY_YEAR, nativeQuery = true)
    int getRows(@Param("dateReceived") int year);

    public static final String SELECT_ALL = "SELECT * FROM incoming_correspondence WHERE EXTRACT(YEAR FROM date_received)=?1";
    @Query(value = SELECT_ALL, nativeQuery = true)
    List<IncomingCorrespondence> getIncomingByYear(@Param("dateReceived") int year);

    public static final String SELECT_ALL_MONTH = "SELECT * FROM incoming_correspondence WHERE (EXTRACT(YEAR FROM date_received)=?1 AND EXTRACT(MONTH FROM date_received) BETWEEN 01 AND 03 )";
    @Query(value = SELECT_ALL_MONTH, nativeQuery = true)
    List<IncomingCorrespondence> getIncomingByQuarter(@Param("dateReceived") int year);

    public static final String SELECT_ALL_FIRST = "SELECT COUNT(*) FROM incoming_correspondence WHERE (EXTRACT(YEAR FROM date_received)=?1 AND EXTRACT(MONTH FROM date_received) BETWEEN 01 AND 03 )";
    @Query(value = SELECT_ALL_FIRST, nativeQuery = true)
    int getFirstQuarterCount(@Param("dateReceived") int year);

    public static final String SECOND_QUARTER = "SELECT * FROM incoming_correspondence WHERE (EXTRACT(YEAR FROM date_received)=?1 AND EXTRACT(MONTH FROM date_received) BETWEEN 04 AND 06 )";
    @Query(value = SECOND_QUARTER, nativeQuery = true)
    List<IncomingCorrespondence> getSecondQuarter(@Param("dateReceived") int year);

    public static final String SECOND_QUARTER_COUNT = "SELECT COUNT(*) FROM incoming_correspondence WHERE (EXTRACT(YEAR FROM date_received)=?1 AND EXTRACT(MONTH FROM date_received) BETWEEN 04 AND 06 )";
    @Query(value = SECOND_QUARTER_COUNT, nativeQuery = true)
    int getSecondQuarterCount(@Param("dateReceived") int year);


    public static final String THIRD_QUARTER = "SELECT * FROM incoming_correspondence WHERE (EXTRACT(YEAR FROM date_received)=?1 AND EXTRACT(MONTH FROM date_received) BETWEEN 07 AND 09 )";
    @Query(value = THIRD_QUARTER, nativeQuery = true)
    List<IncomingCorrespondence> getThirdQuarter(@Param("dateReceived") int year);

    public static final String THIRD_QUARTER_COUNT = "SELECT COUNT(*) FROM incoming_correspondence WHERE (EXTRACT(YEAR FROM date_received)=?1 AND EXTRACT(MONTH FROM date_received) BETWEEN 07 AND 09 )";
    @Query(value = THIRD_QUARTER_COUNT, nativeQuery = true)
    int getThirdQuarterCount(@Param("dateReceived") int year);

    public static final String FOURTH_QUARTER = "SELECT * FROM incoming_correspondence WHERE (EXTRACT(YEAR FROM date_received)=?1 AND EXTRACT(MONTH FROM date_received) BETWEEN 10 AND 12 )";
    @Query(value = FOURTH_QUARTER, nativeQuery = true)
    List<IncomingCorrespondence> getFourthQuarter(@Param("dateReceived") int year);

    public static final String FOURTH_QUARTER_COUNT = "SELECT COUNT(*) FROM incoming_correspondence WHERE (EXTRACT(YEAR FROM date_received)=?1 AND EXTRACT(MONTH FROM date_received) BETWEEN 10 AND 12 )";
    @Query(value = FOURTH_QUARTER_COUNT, nativeQuery = true)
    int getFourthQuarterCount(@Param("dateReceived") int year);

}
