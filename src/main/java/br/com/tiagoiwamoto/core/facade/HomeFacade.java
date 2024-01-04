package br.com.tiagoiwamoto.core.facade;

import br.com.tiagoiwamoto.adapter.dto.OpenProfileResponse;
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
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class HomeFacade {

    @Inject
    private CertificationUsecase certificationUsecase;
    @Inject
    private CourseCategoryUsecase courseCategoryUsecase;
    @Inject
    private CourseUsecase courseUsecase;
    @Inject
    private ExperienceUsecase experienceUsecase;
    @Inject
    private ProfileUsecase profileUsecase;
    @Inject
    private ProjectUsecase projectUsecase;
    @Inject
    private ResumeUsecase resumeUsecase;
    @Inject
    private ScholarityUsecase scholarityUsecase;
    @Inject
    private SkillUsecase skillUsecase;
    @Inject
    private SoftwareUsecase softwareUsecase;

    public OpenProfileResponse metrics(){
        var response = OpenProfileResponse.builder()
                .skills(this.skillUsecase.listarRegistros())
                .certifications(this.certificationUsecase.listarRegistros())
                .latestCertifications(this.certificationUsecase.top10())
                .coursesCategories(this.courseCategoryUsecase.listarRegistros())
                .experiences(this.experienceUsecase.listarRegistros())
                .profiles(this.profileUsecase.listarRegistros())
                .scholarities(this.scholarityUsecase.listarRegistros())
                .softwares(this.softwareUsecase.listarRegistros())
                .projects(this.projectUsecase.listarRegistros())
                .coursesHoursMetrics(this.courseUsecase.getTotalHoursOfCoursesByCategory())
                .courseTotalMetrics(this.courseUsecase.getTotalOfCoursesByCategory())
                .latestCourses(this.courseUsecase.top10())
                .resumes(this.resumeUsecase.listarRegistros()).build();
        return response;
    }
}
