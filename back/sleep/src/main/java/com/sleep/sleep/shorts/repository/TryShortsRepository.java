package com.sleep.sleep.shorts.repository;

import com.sleep.sleep.member.entity.Member;
import com.sleep.sleep.shorts.entity.Shorts;
import com.sleep.sleep.shorts.entity.TryShorts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TryShortsRepository extends JpaRepository<TryShorts,Integer> {

//    Optional<TryShorts> findByMemberIndexAndShortsNo(Member member, Shorts shorts);
    @Query(nativeQuery = true, value = "select * from try_shorts where member_no = :memberNo and shorts_no=:shortsNo")
    Optional<TryShorts> findByMemberIndexAndShortsNo(@Param("memberNo") int memberNo,@Param("shortsNo") int shortsNo);

    @Query(nativeQuery = true, value = "select * from try_shorts where member_no = :memberNo")
    List<TryShorts> findTryShortList(@Param("memberNo") int memberNo);

    @Query("SELECT COUNT(ts) FROM TryShorts ts WHERE ts.memberIndex.memberIndex = :memberIndex")
    int countTryNoByMemberNo(@Param("memberIndex") int memberIndex);
}
