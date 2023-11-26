package br.com.tiagoiwamoto.adapter.in;

import br.com.tiagoiwamoto.core.usecase.CertificationUsecase;
import br.com.tiagoiwamoto.core.usecase.CourseCategoryUsecase;
import br.com.tiagoiwamoto.core.usecase.CourseUsecase;
import br.com.tiagoiwamoto.core.usecase.ExperienceUsecase;
import br.com.tiagoiwamoto.core.usecase.ProfileUsecase;
import br.com.tiagoiwamoto.core.usecase.ProjectUsecase;
import br.com.tiagoiwamoto.core.usecase.ResumeUsecase;
import br.com.tiagoiwamoto.core.usecase.ScholarityUsecase;
import br.com.tiagoiwamoto.core.usecase.SkillUsecase;
import br.com.tiagoiwamoto.core.usecase.SoftwareUsecase;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import jakarta.inject.Inject;
import mock.CertificationMock;
import mock.CourseCategoryMock;
import mock.CourseMock;
import mock.ExperienceMock;
import mock.ProfileMock;
import mock.ProjectMock;
import mock.ResumeMock;
import mock.ScholarityMock;
import mock.SkillMock;
import mock.SoftwareMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

@QuarkusTest
class OpenProfileRestTest {

    @Inject
    private OpenProfileRest rest;
    @InjectMock
    private CertificationUsecase certificationUsecase;
    @InjectMock
    private CourseCategoryUsecase courseCategoryUsecase;
    @InjectMock
    private CourseUsecase courseUsecase;
    @InjectMock
    private ExperienceUsecase experienceUsecase;
    @InjectMock
    private ProfileUsecase profileUsecase;
    @InjectMock
    private ProjectUsecase projectUsecase;
    @InjectMock
    private ResumeUsecase resumeUsecase;
    @InjectMock
    private ScholarityUsecase scholarityUsecase;
    @InjectMock
    private SkillUsecase skillUsecase;
    @InjectMock
    private SoftwareUsecase softwareUsecase;

    @Test
    @TestSecurity(authorizationEnabled = false)
    void index() {
        Mockito.when(certificationUsecase.listarRegistros())
                .thenReturn(List.of(CertificationMock.generateDataDto()));
        Mockito.when(courseCategoryUsecase.listarRegistros())
                .thenReturn(List.of(CourseCategoryMock.generateDataDto()));
        Mockito.when(courseUsecase.listarRegistros())
                .thenReturn(List.of(CourseMock.generateDataDto()));
        Mockito.when(experienceUsecase.listarRegistros())
                .thenReturn(List.of(ExperienceMock.generateDataDto()));
        Mockito.when(profileUsecase.listarRegistros())
                .thenReturn(List.of(ProfileMock.generateDataDto()));
        Mockito.when(projectUsecase.listarRegistros())
                .thenReturn(List.of(ProjectMock.generateDataDto()));
        Mockito.when(resumeUsecase.listarRegistros())
                .thenReturn(List.of(ResumeMock.generateDataDto()));
        Mockito.when(scholarityUsecase.listarRegistros())
                .thenReturn(List.of(ScholarityMock.generateDataDto()));
        Mockito.when(skillUsecase.listarRegistros())
                .thenReturn(List.of(SkillMock.generateDataDto()));
        Mockito.when(softwareUsecase.listarRegistros())
                .thenReturn(List.of(SoftwareMock.generateDataDto()));
        var resposta = this.rest.index();

        Assertions.assertEquals(200, resposta.getStatus());
    }
}