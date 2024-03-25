package hello.hellospring.service;

import hello.hellospring.domain.Member;
//import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Service //@Service 안에 @Component가 내포되어있음
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    //직접 생성하는 게 아니라 외부에서 데려오도록?
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /** 회원가입 */
    public Long join(Member member) {
        //같은 이름이 있는 중복 회원X
        //command ALT v 하면 자동완성..
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    /** 전체 회원 조회 */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}

