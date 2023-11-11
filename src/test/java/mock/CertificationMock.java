package mock;

import br.com.tiagoiwamoto.adapter.dto.CertificationDTO;
import br.com.tiagoiwamoto.core.entity.CertificationEntity;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;

public class CertificationMock {

    public static CertificationDTO generateDataDto(){
        Faker faker = new Faker(new Locale("pt-BR"));
        var data = new CertificationDTO();
        data.setUuid(UUID.randomUUID());
        data.setName(faker.name().fullName());
        data.setEarnDate(LocalDate.now());
        data.setPathOfImage("/files/mock/arquivo.png");
        data.setPathOfImageThumb("/files/mock/arquivo_th.png");
        data.setValidateUrl(faker.internet().url());
        data.setId(faker.number().numberBetween(1L, 9999));
        data.setCreatedAt(LocalDateTime.now());
        data.setUpdatedAt(LocalDateTime.now());
        return data;
    }

    public static CertificationEntity generateDataEntity(){
        Faker faker = new Faker(new Locale("pt-BR"));
        var data = new CertificationEntity();
        data.setUuid(UUID.randomUUID());
        data.setName(faker.name().fullName());
        data.setEarnDate(LocalDate.now());
        data.setPathOfImage("/files/mock/arquivo.png");
        data.setPathOfImageThumb("/files/mock/arquivo_th.png");
        data.setValidateUrl(faker.internet().url());
        data.setId(faker.number().numberBetween(1L, 9999));
        data.setCreatedAt(LocalDateTime.now());
        data.setUpdatedAt(LocalDateTime.now());
        return data;
    }

}
