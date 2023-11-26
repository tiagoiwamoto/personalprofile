package br.com.tiagoiwamoto.adapter.in;

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
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestResponse;

@Path(value = "/v1/api/openprofile")
public class OpenProfileRest {

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

    @GET
    public RestResponse<OpenProfileResponse> index(){
        var response = OpenProfileResponse.builder()
                .skills(this.skillUsecase.listarRegistros())
                .certifications(this.certificationUsecase.listarRegistros())
                .coursesCategories(this.courseCategoryUsecase.listarRegistros())
                .latestCourses(this.courseUsecase.top10())
                .experiences(this.experienceUsecase.listarRegistros())
                .profiles(this.profileUsecase.listarRegistros())
                .scholarities(this.scholarityUsecase.listarRegistros())
                .softwares(this.softwareUsecase.listarRegistros())
                .projects(this.projectUsecase.listarRegistros())
                .metrics(this.courseUsecase.getTotalOfCoursesByCategory())
                .resumes(this.resumeUsecase.listarRegistros()).build();
        return RestResponse.ResponseBuilder.ok(response, MediaType.APPLICATION_JSON).build();
    }
}
