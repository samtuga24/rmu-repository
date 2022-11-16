package com.samtuga.rmufilemanagement.repository;

import com.samtuga.rmufilemanagement.entity.FileTransitSheet;
import com.samtuga.rmufilemanagement.entity.IncomingCorrespondence;
import com.samtuga.rmufilemanagement.entity.OutgoingCorrespondence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransitRepository extends JpaRepository<FileTransitSheet, Long> {
    Optional<FileTransitSheet>findByReference(String reference);
    List<FileTransitSheet>findByRefIgnoreCase(String reference);
//    Optional<FileTransitSheet>findFileTransitSheetByFileTitleIgnoreCase(String fileTitle);

    public static final String STATUS = "SELECT * FROM file_transit_sheet WHERE status='out' AND ref=?1";
    @Query(value = STATUS, nativeQuery = true)
    Optional<FileTransitSheet> getUpdate (@Param("ref") String status);

    public static final String COUNT_ROWS = "SELECT COUNT(*) FROM file_transit_sheet";
    @Query(value = COUNT_ROWS, nativeQuery = true)
    long getCount();

    public static final String COUNT_BY_YEAR = "SELECT COUNT(*) FROM file_transit_sheet WHERE EXTRACT(YEAR FROM date)=?1";
    @Query(value = COUNT_BY_YEAR, nativeQuery = true)
    int getRows(@Param("date") int year);

    public static final String SELECT_ALL = "SELECT * FROM file_transit_sheet WHERE EXTRACT(YEAR FROM date)=?1";
    @Query(value = SELECT_ALL, nativeQuery = true)
    List<FileTransitSheet> getOutgoingByYear(@Param("date") int year);

    public static final String SELECT_ALL_MONTH = "SELECT * FROM file_transit_sheet WHERE (EXTRACT(YEAR FROM date)=?1 AND EXTRACT(MONTH FROM date) BETWEEN 01 AND 03 )";
    @Query(value = SELECT_ALL_MONTH, nativeQuery = true)
    List<FileTransitSheet> getOutgoingByQuarter(@Param("dateReceivedForDispatch") int year);

    public static final String SELECT_ALL_FIRST = "SELECT COUNT(*) FROM file_transit_sheet WHERE (EXTRACT(YEAR FROM date)=?1 AND EXTRACT(MONTH FROM date) BETWEEN 01 AND 03 )";
    @Query(value = SELECT_ALL_FIRST, nativeQuery = true)
    int getFirstQuarterCount(@Param("date") int year);

    public static final String SECOND_QUARTER = "SELECT * FROM file_transit_sheet WHERE (EXTRACT(YEAR FROM date)=?1 AND EXTRACT(MONTH FROM date) BETWEEN 04 AND 06 )";
    @Query(value = SECOND_QUARTER, nativeQuery = true)
    List<FileTransitSheet> getSecondQuarter(@Param("date") int year);

    public static final String SECOND_QUARTER_COUNT = "SELECT COUNT(*) FROM file_transit_sheet WHERE (EXTRACT(YEAR FROM date)=?1 AND EXTRACT(MONTH FROM date) BETWEEN 04 AND 06 )";
    @Query(value = SECOND_QUARTER_COUNT, nativeQuery = true)
    int getSecondQuarterCount(@Param("date") int year);


    public static final String THIRD_QUARTER = "SELECT * FROM file_transit_sheet WHERE (EXTRACT(YEAR FROM date)=?1 AND EXTRACT(MONTH FROM date) BETWEEN 07 AND 09 )";
    @Query(value = THIRD_QUARTER, nativeQuery = true)
    List<FileTransitSheet> getThirdQuarter(@Param("date") int year);

    public static final String THIRD_QUARTER_COUNT = "SELECT COUNT(*) FROM file_transit_sheet WHERE (EXTRACT(YEAR FROM date)=?1 AND EXTRACT(MONTH FROM date) BETWEEN 07 AND 09 )";
    @Query(value = THIRD_QUARTER_COUNT, nativeQuery = true)
    int getThirdQuarterCount(@Param("date") int year);

    public static final String FOURTH_QUARTER = "SELECT * FROM file_transit_sheet WHERE (EXTRACT(YEAR FROM date)=?1 AND EXTRACT(MONTH FROM date) BETWEEN 10 AND 12 )";
    @Query(value = FOURTH_QUARTER, nativeQuery = true)
    List<FileTransitSheet> getFourthQuarter(@Param("dateReceivedForDispatch") int year);

    public static final String FOURTH_QUARTER_COUNT = "SELECT COUNT(*) FROM file_transit_sheet WHERE (EXTRACT(YEAR FROM date)=?1 AND EXTRACT(MONTH FROM date) BETWEEN 10 AND 12 )";
    @Query(value = FOURTH_QUARTER_COUNT, nativeQuery = true)
    int getFourthQuarterCount(@Param("date") int year);


}
