package mock;

import br.com.tiagoiwamoto.adapter.dto.ProfileDTO;
import br.com.tiagoiwamoto.core.entity.ProfileEntity;
import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.UUID;

public class ProfileMock {

    public static ProfileDTO generateDataDto(){
        Faker faker = new Faker(new Locale("pt-BR"));
        var profile = new ProfileDTO();
        profile.setUuid(UUID.randomUUID());
        profile.setName(faker.name().fullName());
        profile.setEmail(faker.internet().emailAddress());
        profile.setPhone(faker.phoneNumber().cellPhone());
        profile.setDescription(faker.lorem().sentence(255));
        profile.setTitle(faker.name().title());
        profile.setSubTitle(faker.name().title());
        profile.setIsActive(faker.bool().bool());
        return profile;
    }

    public static ProfileEntity generateDataEntity(){
        Faker faker = new Faker(new Locale("pt-BR"));
        var profile = new ProfileEntity();
        profile.setId(faker.number().randomNumber());
        profile.setUuid(UUID.randomUUID());
        profile.setName(faker.name().fullName());
        profile.setEmail(faker.internet().emailAddress());
        profile.setPhone(faker.phoneNumber().cellPhone());
        profile.setDescription(faker.lorem().sentence(255));
        profile.setTitle(faker.name().title());
        profile.setSubTitle(faker.name().title());
        profile.setIsActive(faker.bool().bool());
        return profile;
    }

}
