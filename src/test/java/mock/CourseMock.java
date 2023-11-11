package mock;

import br.com.tiagoiwamoto.adapter.dto.CourseDTO;
import br.com.tiagoiwamoto.core.entity.CourseEntity;
import com.github.javafaker.Faker;
import org.jboss.resteasy.reactive.common.util.CaseInsensitiveMap;
import org.jboss.resteasy.reactive.multipart.FileUpload;
import org.jboss.resteasy.reactive.server.core.multipart.DefaultFileUpload;
import org.jboss.resteasy.reactive.server.core.multipart.FormData;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class CourseMock {

    public static CourseDTO generateDataDto(){
        Faker faker = new Faker(new Locale("pt-BR"));
        var data = new CourseDTO();
        data.setUuid(UUID.randomUUID());
        data.setName(faker.name().fullName());
        data.setPathOfImage("/files/mock/arquivo.png");
        data.setPathOfImageThumb("/files/mock/arquivo_th.png");
        data.setSchool(faker.funnyName().name());
        data.setDuration(faker.number().randomDouble(1,1, 999));
        data.setCourseCategoryUuid(UUID.randomUUID());
        data.setTitle(faker.funnyName().name());
        data.setFile(new DefaultFileUpload(faker.funnyName().name(), null));
        data.setId(faker.number().numberBetween(1L, 9999));
        data.setCreatedAt(LocalDateTime.now());
        data.setUpdatedAt(LocalDateTime.now());
        return data;
    }

    public static CourseEntity generateDataEntity(){
        Faker faker = new Faker(new Locale("pt-BR"));
        var data = new CourseEntity();
        data.setUuid(UUID.randomUUID());
        data.setName(faker.name().fullName());
        data.setPathOfImage("/files/mock/arquivo.png");
        data.setPathOfImageThumb("/files/mock/arquivo_th.png");
        data.setSchool(faker.funnyName().name());
        data.setDuration(faker.number().randomDouble(1,1, 999));
        data.setCourseCategory(CourseCategoryMock.generateDataEntity());
        data.setTitle(faker.funnyName().name());
        data.setId(faker.number().numberBetween(1L, 9999));
        data.setCreatedAt(LocalDateTime.now());
        data.setUpdatedAt(LocalDateTime.now());
        return data;
    }

    private static FileUpload generateMockFile(){
        FormData formData = new FormData(1);
        CaseInsensitiveMap map = new CaseInsensitiveMap();
        map.put("test", List.of("value"));
        String name = UUID.randomUUID().toString();
        formData.add(name, UUID.randomUUID().toString().getBytes(), name, map);
        DefaultFileUpload file = new DefaultFileUpload(UUID.randomUUID().toString(), formData.getFirst(name));
        return file;
    }

}
