package mock;

import br.com.tiagoiwamoto.adapter.dto.ScholarityDTO;
import br.com.tiagoiwamoto.core.entity.ScholarityEntity;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;

public class ScholarityMock {

    public static ScholarityDTO generateDataDto(){
        Faker faker = new Faker(new Locale("pt-BR"));
        var data = new ScholarityDTO();
        data.setUuid(UUID.randomUUID());
        data.setSchoolName(faker.funnyName().name());
        data.setCourseName(faker.funnyName().name());
        data.setStartDate(LocalDate.now());
        data.setDateOfConclusion(LocalDate.now());
        data.setDuration(faker.number().numberBetween(100, 1000));
        data.setTitleReceivedCourse(faker.funnyName().name());
        data.setCreatedAt(LocalDateTime.now());
        data.setUpdatedAt(LocalDateTime.now());
        return data;
    }

    public static ScholarityEntity generateDataEntity(){
        Faker faker = new Faker(new Locale("pt-BR"));
        var data = new ScholarityEntity();
        data.setUuid(UUID.randomUUID());
        data.setSchoolName(faker.funnyName().name());
        data.setCourseName(faker.funnyName().name());
        data.setStartDate(LocalDate.now());
        data.setDateOfConclusion(LocalDate.now());
        data.setDuration(faker.number().numberBetween(100, 1000));
        data.setTitleReceivedCourse(faker.funnyName().name());
        data.setCreatedAt(LocalDateTime.now());
        data.setUpdatedAt(LocalDateTime.now());
        data.setId(faker.number().numberBetween(1L, 9999));
        return data;
    }

}
