import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

public class EntityManagerTest {
    EntityManager em;

    public void example(){
        Member member = new Member(1L, "홍길동");
        em.persist(member);
        em.detach(member);
        em.remove(member);
    }

    @Sql("/insert-member.sql")
    @Test
    void getMemberById(){
        Member member = memberRepository.findById(2L).get();
        memberRepository.save(member);
        assertThat(member.getName()).isEqualTo("B");

    }


}
