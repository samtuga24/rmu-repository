package com.samtuga.rmufilemanagement.repository;

import com.samtuga.rmufilemanagement.entity.IncomingCorrespondence;
import com.samtuga.rmufilemanagement.entity.OutgoingCorrespondence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OutgoingRepository extends JpaRepository<OutgoingCorrespondence, Long> {
    Optional<OutgoingCorrespondence> findByReference(String reference);
    Optional<OutgoingCorrespondence>findBySubjectIgnoreCase(String subject);
    List<OutgoingCorrespondence>findByRefIgnoreCase(String sub);


    public static final String COUNT_ROWS = "SELECT COUNT(*) FROM outgoing_correspondence";
    @Query(value = COUNT_ROWS, nativeQuery = true)
    long getCount();

    public static final String COUNT_BY_YEAR = "SELECT COUNT(*) FROM outgoing_correspondence WHERE EXTRACT(YEAR FROM date_received_for_dispatch)=?1";
    @Query(value = COUNT_BY_YEAR, nativeQuery = true)
    int getRows(@Param("dateReceivedForDispatch") int year);

    public static final String SELECT_ALL = "SELECT * FROM outgoing_correspondence WHERE EXTRACT(YEAR FROM date_received_for_dispatch)=?1";
    @Query(value = SELECT_ALL, nativeQuery = true)
    List<OutgoingCorrespondence> getOutgoingByYear(@Param("dateReceivedForDispatch") int year);

    public static final String SELECT_ALL_MONTH = "SELECT * FROM outgoing_correspondence WHERE (EXTRACT(YEAR FROM date_received_for_dispatch)=?1 AND EXTRACT(MONTH FROM date_received_for_dispatch) BETWEEN 01 AND 03 )";
    @Query(value = SELECT_ALL_MONTH, nativeQuery = true)
    List<OutgoingCorrespondence> getOutgoingByQuarter(@Param("dateReceivedForDispatch") int year);

    public static final String SELECT_ALL_FIRST = "SELECT COUNT(*) FROM outgoing_correspondence WHERE (EXTRACT(YEAR FROM date_received_for_dispatch)=?1 AND EXTRACT(MONTH FROM date_received_for_dispatch) BETWEEN 01 AND 03 )";
    @Query(value = SELECT_ALL_FIRST, nativeQuery = true)
    int getFirstQuarterCount(@Param("dateReceivedForDispatch") int year);

    public static final String SECOND_QUARTER = "SELECT * FROM outgoing_correspondence WHERE (EXTRACT(YEAR FROM date_received_for_dispatch)=?1 AND EXTRACT(MONTH FROM date_received_for_dispatch) BETWEEN 04 AND 06 )";
    @Query(value = SECOND_QUARTER, nativeQuery = true)
    List<OutgoingCorrespondence> getSecondQuarter(@Param("dateReceivedForDispatch") int year);

    public static final String SECOND_QUARTER_COUNT = "SELECT COUNT(*) FROM outgoing_correspondence WHERE (EXTRACT(YEAR FROM date_received_for_dispatch)=?1 AND EXTRACT(MONTH FROM date_received_for_dispatch) BETWEEN 04 AND 06 )";
    @Query(value = SECOND_QUARTER_COUNT, nativeQuery = true)
    int getSecondQuarterCount(@Param("dateReceivedForDispatch") int year);


    public static final String THIRD_QUARTER = "SELECT * FROM outgoing_correspondence WHERE (EXTRACT(YEAR FROM date_received_for_dispatch)=?1 AND EXTRACT(MONTH FROM date_received_for_dispatch) BETWEEN 07 AND 09 )";
    @Query(value = THIRD_QUARTER, nativeQuery = true)
    List<OutgoingCorrespondence> getThirdQuarter(@Param("dateReceivedForDispatch") int year);

    public static final String THIRD_QUARTER_COUNT = "SELECT COUNT(*) FROM outgoing_correspondence WHERE (EXTRACT(YEAR FROM date_received_for_dispatch)=?1 AND EXTRACT(MONTH FROM date_received_for_dispatch) BETWEEN 07 AND 09 )";
    @Query(value = THIRD_QUARTER_COUNT, nativeQuery = true)
    int getThirdQuarterCount(@Param("dateReceivedForDispatch") int year);

    public static final String FOURTH_QUARTER = "SELECT * FROM outgoing_correspondence WHERE (EXTRACT(YEAR FROM date_received_for_dispatch)=?1 AND EXTRACT(MONTH FROM date_received_for_dispatch) BETWEEN 10 AND 12 )";
    @Query(value = FOURTH_QUARTER, nativeQuery = true)
    List<OutgoingCorrespondence> getFourthQuarter(@Param("dateReceivedForDispatch") int year);

    public static final String FOURTH_QUARTER_COUNT = "SELECT COUNT(*) FROM outgoing_correspondence WHERE (EXTRACT(YEAR FROM date_received_for_dispatch)=?1 AND EXTRACT(MONTH FROM date_received_for_dispatch) BETWEEN 10 AND 12 )";
    @Query(value = FOURTH_QUARTER_COUNT, nativeQuery = true)
    int getFourthQuarterCount(@Param("dateReceivedForDispatch") int year);

}
