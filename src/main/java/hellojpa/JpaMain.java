package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

     public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 등록
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");

            // 수정
//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("HelloJPA");

//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(1)
//                    .setMaxResults(10)
//                    .getResultList();
//
//            for (Member member : result) {
//                System.out.println("member.name = " + member.getName());
//            }

//            Member member = em.find(Member.class, 150L);
//            member.setName("ZZZZ"); // 수정 시엔 값 set만 하면 됨

//            Member member = new Member(200L, "member200");
//
//            em.persist(member);
//
//            em.flush(); // persist -> flush (db전송) -> commit (db커밋) commit
//
//            System.out.println("==============");

            // 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team);
            em.persist(member);

            // 영속성컨텍스트에서 디비로 넘겨 싱크를 맞추고 영속성컨텍스트는 초기화한다.
            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());

//            Team findTeam = findMember.getTeam();
//            System.out.println("findTeam = " + findTeam.getName());

            List<Member> members = findMember.getTeam().getMembers();

            for (Member m : members) {
                System.out.println("m = " + m.getUsername());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
