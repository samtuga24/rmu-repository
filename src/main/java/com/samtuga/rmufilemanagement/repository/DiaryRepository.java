package com.samtuga.rmufilemanagement.repository;

import com.samtuga.rmufilemanagement.entity.FileDiary;
import com.samtuga.rmufilemanagement.entity.OutgoingCorrespondence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;
import java.util.Optional;

@RestController
public interface DiaryRepository extends JpaRepository<FileDiary, Long> {
    Optional<FileDiary>findByReference(String reference);
    List<FileDiary> findFileDiaryByReference(String reference);
    List<FileDiary>findByRefIgnoreCase(String fileTitle);
//
    public static final String COUNT_ROWS = "SELECT COUNT(*) FROM file_diary";
    @Query(value = COUNT_ROWS, nativeQuery = true)
    long getCount();

    public static final String COUNT_BY_YEAR = "SELECT COUNT(*) FROM file_diary WHERE EXTRACT(YEAR FROM date_opened)=?1";
    @Query(value = COUNT_BY_YEAR, nativeQuery = true)
    int getRows(@Param("dateOpened") int year);

    public static final String SELECT_ALL = "SELECT * FROM file_diary WHERE EXTRACT(YEAR FROM date_opened)=?1";
    @Query(value = SELECT_ALL, nativeQuery = true)
    List<FileDiary> getOutgoingByYear(@Param("dateOpened") int year);

    public static final String SELECT_ALL_MONTH = "SELECT * FROM file_diary WHERE (EXTRACT(YEAR FROM date_opened)=?1 AND EXTRACT(MONTH FROM date_opened) BETWEEN 01 AND 03 )";
    @Query(value = SELECT_ALL_MONTH, nativeQuery = true)
    List<FileDiary> getOutgoingByQuarter(@Param("dateOpened") int year);

    public static final String SELECT_ALL_FIRST = "SELECT COUNT(*) FROM file_diary WHERE (EXTRACT(YEAR FROM date_opened)=?1 AND EXTRACT(MONTH FROM date_opened) BETWEEN 01 AND 03 )";
    @Query(value = SELECT_ALL_FIRST, nativeQuery = true)
    int getFirstQuarterCount(@Param("dateOpened") int year);

    public static final String SECOND_QUARTER = "SELECT * FROM file_diary WHERE (EXTRACT(YEAR FROM date_opened)=?1 AND EXTRACT(MONTH FROM date_opened) BETWEEN 04 AND 06 )";
    @Query(value = SECOND_QUARTER, nativeQuery = true)
    List<FileDiary> getSecondQuarter(@Param("dateOpened") int year);

    public static final String SECOND_QUARTER_COUNT = "SELECT COUNT(*) FROM file_diary WHERE (EXTRACT(YEAR FROM date_opened)=?1 AND EXTRACT(MONTH FROM date_opened) BETWEEN 04 AND 06 )";
    @Query(value = SECOND_QUARTER_COUNT, nativeQuery = true)
    int getSecondQuarterCount(@Param("dateOpened") int year);


    public static final String THIRD_QUARTER = "SELECT * FROM file_diary WHERE (EXTRACT(YEAR FROM date_opened)=?1 AND EXTRACT(MONTH FROM date_opened) BETWEEN 07 AND 09 )";
    @Query(value = THIRD_QUARTER, nativeQuery = true)
    List<FileDiary> getThirdQuarter(@Param("dateOpened") int year);

    public static final String THIRD_QUARTER_COUNT = "SELECT COUNT(*) FROM file_diary WHERE (EXTRACT(YEAR FROM date_opened)=?1 AND EXTRACT(MONTH FROM date_opened) BETWEEN 07 AND 09 )";
    @Query(value = THIRD_QUARTER_COUNT, nativeQuery = true)
    int getThirdQuarterCount(@Param("dateOpened") int year);

    public static final String FOURTH_QUARTER = "SELECT * FROM file_diary WHERE (EXTRACT(YEAR FROM date_opened)=?1 AND EXTRACT(MONTH FROM date_opened) BETWEEN 10 AND 12 )";
    @Query(value = FOURTH_QUARTER, nativeQuery = true)
    List<FileDiary> getFourthQuarter(@Param("dateOpened") int year);

    public static final String FOURTH_QUARTER_COUNT = "SELECT COUNT(*) FROM file_diary WHERE (EXTRACT(YEAR FROM date_opened)=?1 AND EXTRACT(MONTH FROM date_opened) BETWEEN 10 AND 12 )";
    @Query(value = FOURTH_QUARTER_COUNT, nativeQuery = true)
    int getFourthQuarterCount(@Param("dateOpened") int year);

}
