package mock;

import br.com.tiagoiwamoto.adapter.dto.CourseCategoryDTO;
import br.com.tiagoiwamoto.adapter.dto.ProfileDTO;
import br.com.tiagoiwamoto.core.entity.CourseCategoryEntity;
import br.com.tiagoiwamoto.core.entity.ProfileEntity;
import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.UUID;

public class CourseCategoryMock {

    public static CourseCategoryDTO generateDataDto(){
        Faker faker = new Faker(new Locale("pt-BR"));
        var profile = new CourseCategoryDTO();
        profile.setUuid(UUID.randomUUID());
        profile.setName(faker.name().fullName());
        profile.setDescription(faker.lorem().sentence(255));
        return profile;
    }

    public static CourseCategoryEntity generateDataEntity(){
        Faker faker = new Faker(new Locale("pt-BR"));
        var profile = new CourseCategoryEntity();
        profile.setId(faker.number().randomNumber());
        profile.setUuid(UUID.randomUUID());
        profile.setName(faker.name().fullName());
        profile.setDescription(faker.lorem().sentence(255));
        return profile;
    }

}
