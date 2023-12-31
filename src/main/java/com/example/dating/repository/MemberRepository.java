package com.example.dating.repository;

import com.example.dating.domain.Member;
import com.example.dating.dto.member.MemberCardDto;
import com.example.dating.dto.member.MemberGenderDto;
import com.example.dating.dto.member.MemberMbtiDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    @Query("select new com.example.dating.dto.member.MemberCardDto(m.id, m.name, m.residence, m.age, m.height, m.image) from Member m " +
            "where not m.gender = :gender and m.id not in (select h.receiver.id from Heart h where h.sender.id = :id) order by rand()")
    List<MemberCardDto> findRandomMember(@Param("id") Long id, @Param("gender") String gender, Pageable pageable);

    @Query("select new com.example.dating.dto.member.MemberMbtiDto(m.id, m.name, m.mbti) from Member m " +
            "where m.mbti in :mbtiList and not m.id = :id and m.id not in (select sm.id from Member sm where sm.id in :randomMemberIdList) order by rand()")
    List<MemberMbtiDto> findRandomMemberbyMbtiList(@Param("mbtiList") List<String> mbtiList,
                                                   @Param("randomMemberIdList") List<Long> randomMemberIdList,
                                                   @Param("id") Long id,
                                                   Pageable pageable);
}