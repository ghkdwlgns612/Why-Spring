package why.spring.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;
    private final static MemberRepository instance = new MemberRepository();

    private MemberRepository() {
    }//public으로 생성하는 것을 막아준다.
    public static MemberRepository getInstance() {
        return instance;
    }//딱 한개의 store를 생성해서 이것을 공유하면서 써야한다.조회만 가능.

    public Member save(Member member) {
        member.setId(sequence++);
        store.put(member.getId(), member);
        return member;
    }
    public Member findById (Long id) {
        return store.get(id);
    }
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
    public void clearStore() {
        store.clear();
    }
}
