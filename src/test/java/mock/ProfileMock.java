package mock;

import br.com.tiagoiwamoto.adapter.dto.ProfileDTO;
import br.com.tiagoiwamoto.core.entity.ProfileEntity;
import com.github.javafaker.Faker;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;

public class ProfileMock {

    public static ProfileDTO generateDataDto(){
        Faker faker = new Faker(new Locale("pt-BR"));
        var data = new ProfileDTO();
        data.setUuid(UUID.randomUUID());
        data.setName(faker.name().fullName());
        data.setEmail(faker.internet().emailAddress());
        data.setPhone(faker.phoneNumber().cellPhone());
        data.setDescription(faker.lorem().sentence(255));
        data.setTitle(faker.name().title());
        data.setSubTitle(faker.name().title());
        data.setIsActive(faker.bool().bool());
        data.setCreatedAt(LocalDateTime.now());
        data.setUpdatedAt(LocalDateTime.now());
        return data;
    }

    public static ProfileEntity generateDataEntity(){
        Faker faker = new Faker(new Locale("pt-BR"));
        var data = new ProfileEntity();
        data.setId(faker.number().randomNumber());
        data.setUuid(UUID.randomUUID());
        data.setName(faker.name().fullName());
        data.setEmail(faker.internet().emailAddress());
        data.setPhone(faker.phoneNumber().cellPhone());
        data.setDescription(faker.lorem().sentence(255));
        data.setTitle(faker.name().title());
        data.setSubTitle(faker.name().title());
        data.setIsActive(faker.bool().bool());
        data.setId(faker.number().numberBetween(1L, 9999));
        data.setCreatedAt(LocalDateTime.now());
        data.setUpdatedAt(LocalDateTime.now());
        return data;
    }

}
