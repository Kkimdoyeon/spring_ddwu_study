package hello.hellospring.repository;

import org.junit.jupiter.api.Test;
import hello.hellospring.domain.Member;
import static org.assertj.core.api.Assertions.assertThat;
//import org.assertj.core.api.Assertions;

//그냥 테스트 용도니까 public 안 붙여도 됨
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    //메소드가 끝날 때마다
    public void afterEach() {
        repository.clearStore();
    }


    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        repository.findById(member.getId());

        Member result = repository.findById(member.getId()).get();
        //Optional이라면 .get()으로 바로 꺼내기 가능
        //System.out.println("result = " + (result == member));
        //위처럼 글자로 보지 않으려면 Assertions 방법이 있음
        //Assertions.assertEquals(member, result);
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        //shift + f6 하면 변수이름 한번에 변경가능
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
