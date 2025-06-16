package cat.cat.runner;

import cat.cat.helper.domain.CatHelper;
import cat.cat.helper.domain.CatHelperRepository;
import cat.cat.inventory.domain.Inventory;
import cat.cat.inventory.domain.InventoryRepository;
import cat.cat.member.domain.Member;
import cat.cat.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class TestApplicationRunner implements ApplicationRunner {
    private final CatHelperRepository catHelperRepository;
    private final MemberRepository memberRepository;
    private final InventoryRepository inventoryRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // memberId = 1
        memberRepository.save(new Member("test1@naver.com", "Lim", "test1", "123456", 3000.0));
        catHelperRepository.save(new CatHelper(1L, 1L, 1L, 100.0));
        inventoryRepository.save(new Inventory(1L, 1L, 100L));
        inventoryRepository.save(new Inventory(2L, 1L, 100L));
        inventoryRepository.save(new Inventory(3L, 1L, 100L));
        inventoryRepository.save(new Inventory(4L, 1L, 100L));
        inventoryRepository.save(new Inventory(5L, 1L, 100L));
        inventoryRepository.save(new Inventory(6L, 1L, 100L));
        inventoryRepository.save(new Inventory(7L, 1L, 100L));
        inventoryRepository.save(new Inventory(8L, 1L, 100L));

        // memberId = 2
        memberRepository.save(new Member("test2@naver.com", "Lee", "test2", "123456", 3000.0));
        catHelperRepository.save(new CatHelper(2L, 2L, 2L, 100.0));
        inventoryRepository.save(new Inventory(1L, 2L, 50L));
        inventoryRepository.save(new Inventory(2L, 2L, 50L));
        inventoryRepository.save(new Inventory(3L, 2L, 50L));
        inventoryRepository.save(new Inventory(4L, 2L, 50L));
        inventoryRepository.save(new Inventory(5L, 2L, 50L));
        inventoryRepository.save(new Inventory(6L, 2L, 50L));
        inventoryRepository.save(new Inventory(7L, 2L, 50L));
        inventoryRepository.save(new Inventory(8L, 2L, 50L));

        // memberId = 3
        memberRepository.save(new Member("test3@naver.com", "Jung", "test3", "123456", 3000.0));
        catHelperRepository.save(new CatHelper(3L, 3L, 3L, 100.0));
        inventoryRepository.save(new Inventory(1L, 3L, 30L));
        inventoryRepository.save(new Inventory(2L, 3L, 30L));
        inventoryRepository.save(new Inventory(3L, 3L, 30L));
        inventoryRepository.save(new Inventory(4L, 3L, 30L));
        inventoryRepository.save(new Inventory(5L, 3L, 30L));
        inventoryRepository.save(new Inventory(6L, 3L, 30L));
        inventoryRepository.save(new Inventory(7L, 3L, 30L));
        inventoryRepository.save(new Inventory(8L, 3L, 30L));

        log.info("인벤토리 전체 개수 : " + inventoryRepository.findAll().size());
    }

}
