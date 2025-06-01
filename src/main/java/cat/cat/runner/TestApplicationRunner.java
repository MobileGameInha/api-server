package cat.cat.runner;

import cat.cat.helper.domain.CatHelper;
import cat.cat.helper.domain.CatHelperRepository;
import cat.cat.inventory.domain.Inventory;
import cat.cat.inventory.domain.InventoryRepository;
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
        catHelperRepository.save(new CatHelper(1L, 1L, 1L, 100.0));
        inventoryRepository.save(new Inventory(1L, 1L, 100L));
        inventoryRepository.save(new Inventory(2L, 1L, 100L));
        inventoryRepository.save(new Inventory(3L, 1L, 100L));
        inventoryRepository.save(new Inventory(4L, 1L, 100L));
        inventoryRepository.save(new Inventory(5L, 1L, 100L));
        inventoryRepository.save(new Inventory(6L, 1L, 100L));
        inventoryRepository.save(new Inventory(7L, 1L, 100L));
        inventoryRepository.save(new Inventory(8L, 1L, 100L));

        log.info("인벤토리 개수 : " + inventoryRepository.findAll().size());
    }
}
