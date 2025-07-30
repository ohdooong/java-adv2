package io.member;

import java.util.List;

public interface MemberRepository {

    /**
     * 멤버 추가
     */
    void add(Member member);

    /**
     * 멤버 전체 조회
     */
    List<Member> findAll();
}
