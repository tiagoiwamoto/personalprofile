package mock;

import br.com.tiagoiwamoto.adapter.dto.SkillDTO;
import br.com.tiagoiwamoto.core.entity.SkillEntity;
import com.github.javafaker.Faker;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;

public class SkillMock {

    public static SkillDTO generateDataDto(){
        Faker faker = new Faker(new Locale("pt-BR"));
        var data = new SkillDTO();
        data.setUuid(UUID.randomUUID());
        data.setCategory(faker.internet().domainName());
        data.setHabilities(String.format("%s,%s,%s", faker.funnyName(), faker.funnyName(), faker.funnyName()));
        data.setCreatedAt(LocalDateTime.now());
        data.setUpdatedAt(LocalDateTime.now());
        return data;
    }

    public static SkillEntity generateDataEntity(){
        Faker faker = new Faker(new Locale("pt-BR"));
        var data = new SkillEntity();
        data.setUuid(UUID.randomUUID());
        data.setCategory(faker.internet().domainName());
        data.setHabilities(String.format("%s,%s,%s", faker.funnyName(), faker.funnyName(), faker.funnyName()));
        data.setCreatedAt(LocalDateTime.now());
        data.setUpdatedAt(LocalDateTime.now());
        data.setId(faker.number().numberBetween(1L, 9999));
        return data;
    }

}
